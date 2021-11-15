package com.ogong.pms.web.member;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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

    mv.addObject("pageTitle", "이메일 찾기");
    mv.addObject("contentUrl", "member/FindEmailForm.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @RequestMapping("/member/findemail")
  public ModelAndView findEmail(HttpServletRequest request) throws Exception {
    ModelAndView mv = new ModelAndView();

    Member perMember = new Member();

    if (Integer.parseInt(request.getParameter("status")) == 1) {
      perMember = memberDao.findByTel(request.getParameter("tel"));
    } else if (Integer.parseInt(request.getParameter("status")) == 2) {
      perMember = memberDao.findByName(request.getParameter("name"));
    }

    if (perMember != null) {
      mv.addObject("pageTitle", "🔎이메일 찾기");
      mv.addObject("perMember", perMember);
      mv.addObject("contentUrl", "member/FindEmail.jsp");
      mv.setViewName("template1");
    } else {
      mv.addObject("pageTitle", "🔎이메일 찾기");
      mv.addObject("contentUrl", "member/FindEmailFail.jsp");
      mv.setViewName("template1");
    }
    return mv;
  } 
}