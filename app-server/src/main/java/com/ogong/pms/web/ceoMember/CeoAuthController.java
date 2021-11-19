package com.ogong.pms.web.ceoMember;

import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.vo.CeoMember;

@Controller
public class CeoAuthController {

  @Autowired CeoMemberDao ceoMemberDao;
  @Autowired ServletContext sc;

  // 로그인 폼
  @GetMapping("/ceomember/form")
  public ModelAndView ceoLoginForm() {

    ModelAndView mv = new ModelAndView();

    mv.addObject("pageTitle", "🖐 오늘의 공부 로그인");
    mv.addObject("contentUrl", "ceoMember/CeoMemberLoginForm.jsp");
    mv.setViewName("template1");

    return mv;
  }


  // 로그인
  @PostMapping("/ceomember/login")
  public ModelAndView ceoLogin(String email, String password, String saveEmail, HttpServletResponse response, HttpSession session) throws Exception {
    Cookie cookie = null;

    if (saveEmail != null) {
      cookie = new Cookie("email", email);
      cookie.setMaxAge(60 * 60 * 24 * 7);
      // cookie.setPath(getServletContext().getContextPath() + "/ceomember");

    } else {
      cookie = new Cookie("email", "");
      cookie.setMaxAge(0);        // 유효기간을 0으로 설정, 웹브라우저가 받는 즉시 무효한 쿠키가 된다
    }

    response.addCookie(cookie);

    CeoMember ceoMember = ceoMemberDao.findByEmailAndPassword(email, password);

    ModelAndView mv = new ModelAndView();

    response.setContentType("text/html; charset=utf-8");
    PrintWriter out = response.getWriter();

    if (ceoMember != null && ceoMember.getCeoStatus() == CeoMember.CEO) {
      if (ceoMember.getActive() == CeoMember.INUSER) {
        session.setAttribute("loginCeoUser", ceoMember);
        mv.setViewName("redirect:../index");
      }

    } else {
      //      mv.addObject("pageTitle", "해당 계정이 존재하지 않습니다.");
      //      mv.addObject("contentUrl", "auth/LoginFail.jsp");
      //      mv.setViewName("template1");
      out.println("<script> alert('아이디 또는 비밀번호가 틀립니다.');");
      out.println("history.go(-1); </script>");
      out.close();
    }
    return mv;
  }


  // 로그아웃
  @GetMapping("/ceomember/logout")
  public ModelAndView ceoLogout(HttpSession session) throws Exception {

    session.invalidate();
    ModelAndView mv = new ModelAndView();

    mv.setViewName("redirect:../index");
    return mv;
  }
}