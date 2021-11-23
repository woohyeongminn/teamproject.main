package com.ogong.pms.web.member;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.domain.Member;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@RestController
@Controller
public class PerMemberController {

  @Autowired MemberService memberService;
  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired MemberDao memberDao;
  @Autowired ServletContext sc;


  @GetMapping("/member/addform")
  public ModelAndView addForm() {
    ModelAndView mv = new ModelAndView();

    mv.addObject("pageTitle", "✏회원 가입");
    mv.addObject("contentUrl", "member/PerMemberAddForm.jsp");
    mv.setViewName("template1");
    return mv;

  }

  @PostMapping("/member/add")
  protected ModelAndView add(Member member, Part photoFile, String[] tel, String nick, String id, String site) throws Exception {

    String perTel = tel[0] + "-" + tel[1] + "-" + tel[2];
    member.setPerTel(perTel);

    member.setPerEmail(id +'@'+ site);
    member.setPerNickname(nick);

    member.setPerStatus(Member.PER);

    if (photoFile.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      photoFile.write(sc.getRealPath("/upload/member") + "/" + filename);
      member.setPerPhoto(filename);

      Thumbnails.of(sc.getRealPath("/upload/member") + "/" + filename)
      .size(40, 40)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_40x40";
        }
      });

      Thumbnails.of(sc.getRealPath("/upload/member") + "/" + filename)
      .size(80, 80)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_80x80";
        }
      });

      Thumbnails.of(sc.getRealPath("/upload/member") + "/" + filename)
      .size(110, 110)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_110x110";
        }
      });

    } else {

      member.setPerPhoto("perProfile");
    }

    memberDao.insert(member);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:form");
    return mv;
  }

  //이메일 중복확인 처리
  @RequestMapping(value="/member/idOverlap", method=RequestMethod.POST)
  public void idOverlap(HttpServletResponse response, @RequestParam("id") String id) throws IOException {
    //@RequestParam는 요청의 특정 파라미터 값을 찾아낼 때 사용하는 어노테이션
    memberService.idOverlap(id,response);  //서비스에 있는 idOverlap 호출.
  }

  //닉네임 중복확인 처리
  @RequestMapping(value="/member/nickOverlap", method=RequestMethod.POST)
  public void nickOverlap(HttpServletResponse response, @RequestParam("nick") String nick) throws IOException {
    //@RequestParam는 요청의 특정 파라미터 값을 찾아낼 때 사용하는 어노테이션
    memberService.nickOverlap(nick,response);  //서비스에 있는 nickOverlap 호출.
  }

  @RequestMapping("/member/detail")
  public ModelAndView detail(HttpSession session) throws Exception {

    Member loginPer = (Member) session.getAttribute("loginUser");

    if (loginPer == null) {
      throw new Exception("로그인한 회원이 없습니다.");
    } 

    Member perMember = memberDao.findByNo(loginPer.getPerNo());

    if (perMember == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("perMember", perMember);
    mv.addObject("pageTitle", "📜 마이페이지");
    mv.addObject("contentUrl", "member/PerMemberDetail.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @RequestMapping("/member/updateform")
  public ModelAndView updateForm(HttpSession session) throws Exception {

    Member loginPer = (Member) session.getAttribute("loginUser");

    if (loginPer == null) {
      throw new Exception("로그인한 회원이 없습니다.");
    } 

    Member perMember = memberDao.findByNo(loginPer.getPerNo());

    if (perMember == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }
    ModelAndView mv = new ModelAndView();

    mv.addObject("perMember", perMember);
    mv.addObject("pageTitle", "📜 마이페이지");
    mv.addObject("contentUrl", "member/PerMemberUpdateForm.jsp");
    mv.setViewName("template1");
    return mv;

  }

  @RequestMapping("/member/update")
  protected ModelAndView update(Member member, Part photoFile, String nick) throws Exception {

    Member oldMember = memberDao.findByNo(member.getPerNo());

    if (oldMember == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    } 

    if (photoFile.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      photoFile.write(sc.getRealPath("/upload/member") + "/" + filename);
      member.setPerPhoto(filename);

      Thumbnails.of(sc.getRealPath("/upload/member") + "/" + filename)
      .size(40, 40)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_40x40";
        }
      });

      Thumbnails.of(sc.getRealPath("/upload/member") + "/" + filename)
      .size(80, 80)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_80x80";
        }
      });

      Thumbnails.of(sc.getRealPath("/upload/member") + "/" + filename)
      .size(80, 80)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_110x110";
        }
      });

      member.setPerPhoto(filename);
      member.setPerNickname(nick);
      member.setPerRegisteredDate(oldMember.getPerRegisteredDate());

    } else {
      // 기존 정보로 
      member.setPerPhoto(oldMember.getPerPhoto());
      member.setPerNickname(nick);
      member.setPerRegisteredDate(oldMember.getPerRegisteredDate());
    }

    memberDao.updateName(member);
    memberDao.updateNickname(member);
    memberDao.updateEmail(member);
    memberDao.updatePassword(member);
    memberDao.updatePhoto(member);
    memberDao.updateTel(member);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:detail");
    return mv;
  }

  @RequestMapping("/member/deleteform")
  protected ModelAndView deleteForm(HttpSession session) throws Exception {
    Member perMember = (Member) session.getAttribute("loginUser");
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "개인 회원탈퇴");
    mv.addObject("perMember", perMember);
    mv.addObject("contentUrl", "member/PerMemberDeleteForm.jsp");
    mv.setViewName("template1");

    return mv;
  }

  @PostMapping("/member/delete")
  public ModelAndView delete(int perNo, String perEmail, String perPassword) throws Exception {
    ModelAndView mv = new ModelAndView();

    Member perMember = memberDao.findByEmailAndPassword(perEmail, perPassword);
    System.out.println(perMember);
    if (perMember != null) {

      perMember.setPerName("Deleted Member("+ perMember.getPerName() +")");
      perMember.setPerNickname("Deleted Member("+ perMember.getPerNickname() +")");
      perMember.setPerEmail("Deleted Email");
      perMember.setPerPassword("Deleted Password");
      perMember.setPerPhoto("perProfile");
      perMember.setPerTel("Deleted Tel");
      perMember.setPerStatus(1);
      perMember.setActive(2);

      System.out.println(perMember);
      memberDao.updateActive(perMember);
      sqlSessionFactory.openSession().commit();

      mv.addObject("pageTitle", "감사합니다.");
      mv.addObject("contentUrl", "member/PerMemberDelete.jsp");
      mv.setViewName("template1");
      return mv;
    } 

    mv.addObject("pageTitle", "⚠정보 오류");
    mv.addObject("refresh", "1;url=deleteform");
    mv.addObject("contentUrl", "member/InputFail.jsp");
    mv.setViewName("template1");
    return mv;
  } 

  @RequestMapping("/member/sample")
  public ModelAndView sample() throws Exception {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("member/Sample");
    return mv;
  }
}

