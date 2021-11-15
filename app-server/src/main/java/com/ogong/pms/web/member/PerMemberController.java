package com.ogong.pms.web.member;

import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.domain.Member;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@Controller
public class PerMemberController {


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
  protected ModelAndView add(Member member, Part photoFile, String[] tel, String site) throws Exception {

    String perTel = tel[0] + "-" + tel[1] + "-" + tel[2];
    member.setPerTel(perTel);

    member.setPerEmail(member.getPerEmail() +'@'+ site);

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
    }

    memberDao.insert(member);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.addObject("refresh", "2;url=form");
    mv.addObject("pageTitle", "👋환영 합니다!");
    mv.addObject("contentUrl", "member/PerMemberAdd.jsp");
    mv.setViewName("template1");

    return mv;
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
  protected ModelAndView update(Member member, Part photoFile) throws Exception {

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
      member.setPerRegisteredDate(oldMember.getPerRegisteredDate());

    } else {
      // 기존 정보로 
      member.setPerPhoto(oldMember.getPerPhoto());
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
  protected ModelAndView deleteForm(Member perMember) throws Exception {

    if (perMember == null) {
      throw new Exception("회원이 존재하지 않습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("perMember", perMember);
    mv.addObject("pageTitle", "개인 회원탈퇴");
    mv.addObject("contentUrl", "member/PerMemberDeleteForm.jsp");
    mv.setViewName("template1");

    return mv;
  }

  @PostMapping("/member/delete")
  public ModelAndView ceoDelete(int perNo, String perEmail, String perPassword) throws Exception {

    Member perMember = memberDao.findByNo(perNo);

    if (perMember == null) {
      throw new Exception("로그인 하세요.");
    }

    if (!(perMember.getPerEmail().equals(perEmail)) && (perMember.getPerPassword().equals(perPassword))) {
      throw new Exception("이메일과 패스워드가 일치하지 않습니다.");
    } 

    perMember.setPerName("Deleted Name");
    perMember.setPerNickname("Deleted Member("+ perMember.getPerNickname() +")");
    perMember.setPerEmail("Deleted Email");
    perMember.setPerPassword("Deleted Password");
    perMember.setPerPhoto("Deleted Photo");
    perMember.setPerStatus(Member.PER);
    perMember.setActive(Member.OUTUSER);

    memberDao.updateActive(perMember);
    sqlSessionFactory.openSession().commit();
    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:logout");
    return mv;
  }
}
