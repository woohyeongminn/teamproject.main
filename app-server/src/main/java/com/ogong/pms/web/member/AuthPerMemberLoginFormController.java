package com.ogong.pms.web.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthPerMemberLoginFormController {

  @GetMapping("/member/form")
  public ModelAndView perLoginForm() {
    ModelAndView mv = new ModelAndView();

    mv.addObject("pageTitle", " 🖐 개인 회원 로그인 ");
    mv.addObject("contentUrl", "member/PerMemberLoginForm.jsp");
    mv.setViewName("template1");

    return mv;
  } 
}
