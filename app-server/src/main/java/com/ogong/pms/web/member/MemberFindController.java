package com.ogong.pms.web.member;

import javax.servlet.ServletContext;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.domain.Member;

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
  public ModelAndView findEmail(String[] tel, String name) throws Exception {
    ModelAndView mv = new ModelAndView();

    String perTel = tel[0] + "-" + tel[1] + "-" + tel[2];
    Member perMember = memberDao.findEmail(name, perTel);

    if (perMember != null) {
      String findemail = perMember.getPerEmail().replace(".com", "****");
      System.out.println(perMember);
      mv.addObject("pageTitle", "🔎이메일 찾기완료");
      mv.addObject("findemail", findemail);
      mv.addObject("perMember", perMember);
      mv.addObject("contentUrl", "member/FindEmail.jsp");
      mv.setViewName("template1");

    } else {
      mv.addObject("pageTitle", "🔎이메일 찾기실패");
      mv.addObject("refresh", "2;url=findemailform");
      mv.addObject("contentUrl", "member/FindEmailFail.jsp");
      mv.setViewName("template1");
    }
    return mv;
  } 
}