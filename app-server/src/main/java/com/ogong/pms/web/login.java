package com.ogong.pms.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class login {

  @GetMapping("/login")
  public ModelAndView loginPage() {
    ModelAndView mv = new ModelAndView();

    mv.addObject("pageTitle", "로그인 선택");
    mv.addObject("contentUrl", "login.jsp");
    mv.setViewName("template1");

    return mv;
  } 
}
