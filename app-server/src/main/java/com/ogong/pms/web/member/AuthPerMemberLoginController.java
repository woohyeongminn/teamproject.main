package com.ogong.pms.web.member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.domain.Member;

@Controller
public class AuthPerMemberLoginController {

  @Autowired MemberDao memberDao;

  @PostMapping("/member/login")
  public ModelAndView perLogin(String email, String password, String saveEmail, HttpServletResponse response, HttpSession session) throws Exception {
    Cookie cookie = null;
    if (saveEmail != null) {
      cookie = new Cookie("email", email);
      cookie.setMaxAge(60 * 60 * 24 * 7);

    } else {
      cookie = new Cookie("email", "");
      cookie.setMaxAge(0); // 유효기간을 0으로 설정하면 웹브라우저가 받는 즉시 무효한 쿠기가 된다.
    }

    response.addCookie(cookie);

    Member member = memberDao.findByEmailAndPassword(email, password);

    ModelAndView mv = new ModelAndView();

    if (member != null) {
      session.setAttribute("loginUser", member);
      mv.setViewName("redirect:../index");


    } else {
      mv.addObject("pageTitle", "해당 계정이 존재하지 않습니다.");
      mv.addObject("contentUrl", "member/PerMemberLoginFail.jsp");
      mv.setViewName("template1");
    }
    return mv;
  } 
}
