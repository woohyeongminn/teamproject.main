package com.ogong.pms.web.auth;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.AdminDao;
import com.ogong.pms.domain.Admin;

@Controller
public class AuthAdminController {

  @Autowired AdminDao adminDao;
  @Autowired ServletContext sc;

  @GetMapping("/admin/form")
  public ModelAndView adminloginform() {

    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "🖐 오늘의 공부 로그인");
    mv.addObject("contentUrl", "admin/AdminLoginForm.jsp");
    mv.setViewName("template1");
    return mv;
  } 

  @PostMapping("/admin/login")
  public ModelAndView adminlogin(String email, String password, String saveEmail, HttpSession session, HttpServletResponse response) throws Exception {

    Cookie cookie = null;
    if (saveEmail != null) {
      cookie = new Cookie("email", email);
      cookie.setMaxAge(60 * 60 * 24 * 7); // 일주일 (초 * 분 * 시간 * 일)

    } else {
      cookie = new Cookie("email", "");
      cookie.setMaxAge(0); // 유효 기간을 0으로 설정하면 웹 브라우저가 받는 즉시 무효한 쿠키가 된다.
    }
    response.addCookie(cookie);

    Admin admin = adminDao.findByEmailAndPassword(email, password);

    ModelAndView mv = new ModelAndView();

    if (admin != null) {
      session.setAttribute("loginAdmin", admin);
      mv.setViewName("redirect:detail");
    }

    else {
      mv.addObject("refresh", "2;url=form");
      mv.addObject("pageTitle", "로그인 오류!");
      mv.addObject("contentUrl", "admin/AdminLoginFail.jsp");
      mv.setViewName("template1");
    }
    return mv;
  }

  @GetMapping("/admin/logout")
  public ModelAndView adminlogout(HttpSession session) throws Exception {

    session.invalidate();
    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:../index");
    return mv;
  }
}
