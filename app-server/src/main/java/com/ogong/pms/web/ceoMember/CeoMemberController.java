package com.ogong.pms.web.ceoMember;

import java.util.List;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired ServletContext sc;

  //------------------------------------------------------------------------------------------------------------------------------------------------------
  //기업 회원가입 폼
  @GetMapping("/ceomember/addform")
  public ModelAndView ceoAddForm() {

    ModelAndView mv = new ModelAndView();

    // 중복체크 하려고?
    //Collection<CeoMember> ceoMemberList  = ceoMemberDao.findAll();
    //request.setAttribute("ceoMemberList", ceoMemberList);

    mv.addObject("pageTitle", "기업 회원가입");
    mv.addObject("contentUrl", "ceoMember/CeoMemberAddForm.jsp");
    mv.setViewName("template1");
    return mv;
  }

  //기업 회원가입
  @PostMapping("/ceomember/add")
  public ModelAndView coeAdd(CeoMember ceoMember, Part photoFile, String[] tel, String site) throws Exception {

    String ceoTel = tel[0] + "-" + tel[1] + "-" + tel[2];
    ceoMember.setCeoTel(ceoTel);

    ceoMember.setCeoEmail(ceoMember.getCeoEmail() +'@'+ site);

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
    }

    ceoMemberDao.insert(ceoMember);
    ceoMemberDao.insertCeo(ceoMember);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.addObject("Refresh", "1;url=list");
    mv.addObject("pageTitle", "기업 회원가입");
    mv.addObject("contentUrl", "ceoMember/CeoMemberAdd.jsp");
    mv.setViewName("template1");
    return mv;
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
  public ModelAndView ceoUpdate(CeoMember ceoMember, Part photoFile) throws Exception {

    System.out.println(ceoMember);

    CeoMember oldCeoMember = ceoMemberDao.findByNo(ceoMember.getCeoNo());

    if (oldCeoMember == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }

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