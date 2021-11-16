package com.ogong.pms.web.member;

import javax.servlet.ServletContext;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.util.RandomPw;
import com.ogong.pms.util.SendMail;

@Controller
public class MemberFindController  {

  @Autowired MemberDao memberDao;
  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired ServletContext sc;


  @GetMapping("/member/findemailform")
  public ModelAndView findEmailForm() throws Exception {

    ModelAndView mv = new ModelAndView();

    mv.addObject("pageTitle", "내 정보 찾기");
    mv.addObject("contentUrl", "member/FindEmailForm.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @RequestMapping("/member/findemail")
  public ModelAndView findEmail(String[] tel, String perName) throws Exception {
    ModelAndView mv = new ModelAndView();

    String perTel = tel[0] + "-" + tel[1] + "-" + tel[2];
    Member perMember = memberDao.findEmailByNameAndTel(perName, perTel);

    if (perMember != null) {
      String findemail = perMember.getPerEmail().replace(".com", "****");
      System.out.println(perMember);
      mv.addObject("pageTitle", "🔎이메일 찾기완료");
      mv.addObject("findemail", findemail);
      mv.addObject("perMember", perMember);
      mv.addObject("contentUrl", "member/FindEmail.jsp");
      mv.setViewName("template1");

    } else {
      mv.addObject("pageTitle", "🔎정보 오류");
      mv.addObject("refresh", "2;url=findemailform");
      mv.addObject("contentUrl", "member/FindFail.jsp");
      mv.setViewName("template1");
    }
    return mv;
  } 

  @GetMapping("/member/getpwbyemailform")
  public ModelAndView getPwByEmailForm() throws Exception {

    ModelAndView mv = new ModelAndView();

    mv.addObject("pageTitle", "내 정보 찾기");
    mv.addObject("contentUrl", "member/GetPwByEmailForm.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @PostMapping("/member/getpwbyemail")
  public ModelAndView getPwByEmail(Member member, String site) throws Exception {
    ModelAndView mv = new ModelAndView();
    SendMail sendMail = new SendMail();

    String perName = member.getPerName();
    String perEmail = member.getPerEmail() + "@" + site;

    Member perMember = memberDao.findByNameAndEmail(perName, perEmail);

    RandomPw randomPw = new RandomPw();

    if (perMember != null) {
      String pw = randomPw.randomPw();      
      perMember.setPerPassword(pw);
      sendMail.sendMail(perEmail, pw);
      memberDao.updatePassword(perMember);
      sqlSessionFactory.openSession().commit();

      mv.addObject("pageTitle", "🔎임시 비밀번호 발급");
      mv.addObject("perMember", perMember);
      mv.addObject("contentUrl", "member/GetPwByEmail.jsp");
      mv.setViewName("template1");

    } else {
      mv.addObject("pageTitle", "🔎정보 오류");
      mv.addObject("refresh", "2;url=getpwbyemailform");
      mv.addObject("contentUrl", "member/FindFail.jsp");
      mv.setViewName("template1");
    }
    return mv;
  }


} 
















