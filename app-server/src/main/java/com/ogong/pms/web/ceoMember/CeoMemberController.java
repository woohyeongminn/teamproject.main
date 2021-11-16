package com.ogong.pms.web.ceoMember;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.dao.CafeReviewDao;
import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.CeoMember;
import com.ogong.pms.web.cafe.CafeHandlerHelper;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@Controller
public class CeoMemberController {

  @Autowired CeoMemberDao ceoMemberDao;
  @Autowired CafeDao cafeDao;
  @Autowired CafeReviewDao cafeReviewDao;
  @Autowired CeoMemberService ceoMemberService;
  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired ServletContext sc;

  //------------------------------------------------------------------------------------------------------------------------------------------------------
  //기업 회원가입 폼
  @GetMapping("/ceomember/addform")
  public ModelAndView ceoAddForm() {

    ModelAndView mv = new ModelAndView();

    mv.addObject("pageTitle", "오늘의공부 회원가입");
    mv.addObject("contentUrl", "ceoMember/CeoMemberAddForm.jsp");
    mv.setViewName("template1");
    return mv;
  }

  //기업 회원가입
  @PostMapping("/ceomember/add")
  public ModelAndView coeAdd(CeoMember ceoMember, Part photoFile, String tel1, String tel2, String tel3, String id, String site) throws Exception {

    String ceoTel = tel1 + "-" + tel2 + "-" + tel3;
    ceoMember.setCeoTel(ceoTel);

    ceoMember.setCeoEmail(id+'@'+ site);

    ceoMember.setCeoStatus(CeoMember.CEO);

    if (photoFile.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      photoFile.write(sc.getRealPath("/upload/ceoMember") + "/" + filename);
      ceoMember.setCeoPhoto(filename);

      Thumbnails.of(sc.getRealPath("/upload/ceoMember") + "/" + filename)
      .size(40, 40)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_40x40";
        }
      });

      Thumbnails.of(sc.getRealPath("/upload/ceoMember") + "/" + filename)
      .size(80, 80)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_80x80";
        }
      });

      Thumbnails.of(sc.getRealPath("/upload/ceoMember") + "/" + filename)
      .size(110, 110)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_110x110";
        }
      });
    }

    ceoMemberDao.insert(ceoMember);
    ceoMemberDao.insertCeo(ceoMember);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.addObject("refresh", "2;url=form");
    mv.addObject("pageTitle", "👋환영 합니다!");
    mv.addObject("contentUrl", "ceoMember/CeoMemberAdd.jsp");
    mv.setViewName("template1");
    return mv;
  }


  //아이디 중복확인 처리
  @RequestMapping(value="/ceomember/idOverlap", method=RequestMethod.POST)
  public void idOverlap(HttpServletResponse response, @RequestParam("id") String id) throws IOException {
    //@RequestParam는 요청의 특정 파라미터 값을 찾아낼 때 사용하는 어노테이션
    ceoMemberService.idOverlap(id,response);  //서비스에 있는 idOverlap 호출.
  }

  //------------------------------------------------------------------------------------------------------------------------------------------------------
  //마이페이지
  @GetMapping("/ceomember/detail")
  public ModelAndView ceoDetail(HttpSession session) throws Exception {

    CeoMember loginCeo = (CeoMember) session.getAttribute("loginCeoUser");

    if (loginCeo == null) {
      throw new Exception("로그인한 회원이 없습니다.");
    } 

    CeoMember ceoMember = ceoMemberDao.findByNo(loginCeo.getCeoNo());

    if (ceoMember == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    } 

    Cafe cafe = cafeDao.findByCeoMember(ceoMember.getCeoNo());

    ModelAndView mv = new ModelAndView();

    if (cafe != null) {
      String status = CafeHandlerHelper.getCafeStatusLabel(cafe.getCafeStatus());
      List<CafeReview> reviewList = cafeReviewDao.findReviewListByCafeNo(cafe.getNo());

      mv.addObject("ceoMember", ceoMember);
      mv.addObject("cafe", cafe);
      mv.addObject("cafeStatus", status);
      mv.addObject("reviewList", reviewList);
    }

    mv.addObject("ceoMember", ceoMember);

    mv.addObject("pageTitle", "🙂 마이페이지");
    mv.addObject("contentUrl", "ceoMember/CeoMemberDetail.jsp");
    mv.setViewName("template1");
    return mv;
  }

  //------------------------------------------------------------------------------------------------------------------------------------------------------
  // 기업회원 수정 폼
  @PostMapping("/ceomember/updateform")
  public ModelAndView ceoUpdateForm(CeoMember ceoMember) throws Exception {

    if (ceoMember == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("ceoMember", ceoMember);
    mv.addObject("pageTitle", "🙂 마이페이지 - 프로필 수정");
    mv.addObject("contentUrl", "ceoMember/CeoMemberUpdateForm.jsp");
    mv.setViewName("template1");
    return mv;
  }

  //기업회원 개인정보 수정
  @PostMapping("/ceomember/update")
  public ModelAndView ceoUpdate(CeoMember ceoMember, String tel1, String tel2, String tel3, Part photoFile) throws Exception {

    CeoMember oldCeoMember = ceoMemberDao.findByNo(ceoMember.getCeoNo());

    if (oldCeoMember == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }

    String ceoTel = tel1 + "-" + tel2 + "-" + tel3;
    ceoMember.setCeoTel(ceoTel);

    // 사진
    if (photoFile.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      photoFile.write(sc.getRealPath("/upload/ceoMember") + "/" + filename);
      ceoMember.setCeoPhoto(filename);

      Thumbnails.of(sc.getRealPath("/upload/ceoMember") + "/" + filename)
      .size(40, 40)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_40x40";
        }
      });

      Thumbnails.of(sc.getRealPath("/upload/ceoMember") + "/" + filename)
      .size(80, 80)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_80x80";
        }
      });

      Thumbnails.of(sc.getRealPath("/upload/ceoMember") + "/" + filename)
      .size(80, 80)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_110x110";
        }
      });

      ceoMember.setCeoPhoto(filename);
      ceoMember.setCeoRegisteredDate(oldCeoMember.getCeoRegisteredDate());

    } else {
      // 기존 정보로 
      ceoMember.setCeoPhoto(oldCeoMember.getCeoPhoto());
      ceoMember.setCeoRegisteredDate(oldCeoMember.getCeoRegisteredDate());
    }

    ceoMemberDao.updateCeoMember(ceoMember);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:detail");
    return mv;
  }

  // 기업회원 비밀번호 변경 폼
  @GetMapping("/ceomember/openPwPopup")
  public ModelAndView openPwPopup(HttpSession session) throws Exception {

    CeoMember loginCeo = (CeoMember) session.getAttribute("loginCeoUser");

    if (loginCeo == null) {
      throw new Exception("로그인한 회원이 없습니다.");
    } 

    CeoMember ceoMember = ceoMemberDao.findByNo(loginCeo.getCeoNo());

    ModelAndView mv = new ModelAndView();
    mv.addObject("ceoMember", ceoMember);
    mv.addObject("pageTitle", "비밀번호 변경");
    mv.setViewName("ceoMember/openPwPopup");
    return mv;
  }

  //기업회원 비밀번호 변경
  @PostMapping("/ceomember/updatepassword")
  public ModelAndView ceoUpdatePw(int ceoNo, String oldPw, String newPw, String newPwChk) throws Exception {

    CeoMember ceoMember = ceoMemberDao.findByNo(ceoNo);

    if (ceoMember == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }

    if (!ceoMember.getCeoPassword().equals(oldPw)) {
      throw new Exception("기존 비밀번호 확인을 실패하였습니다.");
    }

    if (!newPw.equals(newPwChk)) {
      throw new Exception("새로운 비밀번호 확인을 실패하였습니다.");
    }

    ceoMember.setCeoPassword(newPwChk);
    ceoMemberDao.updatePassword(ceoMember);

    ModelAndView mv = new ModelAndView();
    mv.setViewName("updatePwPopup.jsp");
    return mv;
  }
  //------------------------------------------------------------------------------------------------------------------------------------------------------
  // 기업회원 탈퇴 폼
  @PostMapping("/ceomember/deleteform")
  public ModelAndView ceoDeleteForm(CeoMember ceoMember) throws Exception {

    if (ceoMember == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("ceoMember", ceoMember);
    mv.addObject("pageTitle", "기업 회원탈퇴");
    mv.addObject("contentUrl", "ceoMember/CeoMemberDeleteForm.jsp");
    mv.setViewName("template1");
    return mv;
  }


  // 비밀번호 체크
  //  @PostMapping("/ceomember/deleteCheck")
  //  public ModelAndView ceoDeleteCheck(int ceoNo, String inputCeoPassword) throws Exception {
  //    CeoMember ceoMember = ceoMemberDao.findByNo(ceoNo);
  //
  //    if (ceoMember == null) {
  //      throw new Exception("로그인 하세요.");
  //    }
  //
  //    if (!ceoMember.getCeoPassword().equals(inputCeoPassword)) {
  //      throw new Exception("패스워드가 일치하지 않습니다.");
  //    } 
  //
  //    ModelAndView mv = new ModelAndView();
  //    mv.addObject("ceoMember", ceoMember);
  //    return mv;
  //  }

  // 기업회원 탈퇴
  @PostMapping("/ceomember/delete")
  public ModelAndView ceoDelete(int ceoNo, String ceoEmail, String ceoPassword) throws Exception {

    CeoMember ceoMember = ceoMemberDao.findByNo(ceoNo);

    if (ceoMember == null) {
      throw new Exception("로그인 하세요.");
    }

    if (!(ceoMember.getCeoEmail().equals(ceoEmail)) && (ceoMember.getCeoPassword().equals(ceoPassword))) {
      throw new Exception("이메일과 패스워드가 일치하지 않습니다.");
    } 

    ceoMember.setCeoName("Deleted Member("+ ceoMember.getCeoName() +")");
    ceoMember.setCeoNickname("Deleted Member("+ ceoMember.getCeoNickname() +")");
    ceoMember.setCeoBossName("Deleted Member("+ ceoMember.getCeoBossName() +")");
    ceoMember.setCeoEmail("Deleted Email");
    ceoMember.setCeoPassword("Deleted Password");
    ceoMember.setCeoPhoto("Deleted Photo");
    ceoMember.setCeoLicenseNo("Deleted LicenseNo");
    ceoMember.setCeoTel("Deleted Tel");
    ceoMember.setCeoStatus(CeoMember.CEO);
    ceoMember.setActive(CeoMember.OUTUSER);

    ceoMemberDao.updateActive(ceoMember);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:logout");
    return mv;
  }

}