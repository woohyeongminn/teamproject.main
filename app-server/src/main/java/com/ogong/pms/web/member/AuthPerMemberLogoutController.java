package com.ogong.pms.web.member;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthPerMemberLogoutController {

  @GetMapping("/member/logout")
  public ModelAndView perLogout(HttpSession session) throws Exception {

    session.invalidate();
    ModelAndView mv = new ModelAndView();

    mv.setViewName("../index");
    return mv;

    //request.getRequestDispatcher("/member/PerMemberLoginForm.jsp").forward(request, response);
    //AuthCeoMemberLogoutController.accessLevel = Menu.LOGOUT;
  }
}
