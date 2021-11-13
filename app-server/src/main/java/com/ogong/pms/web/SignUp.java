package com.ogong.pms.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignUp  {

  @GetMapping("/signup")
  protected ModelAndView signUp() {
    ModelAndView mv = new ModelAndView();

    mv.addObject("pageTitle", "회원가입");
    mv.addObject("contentUrl", "signUp.jsp");
    mv.setViewName("template1");

    return mv;
  } 
}
