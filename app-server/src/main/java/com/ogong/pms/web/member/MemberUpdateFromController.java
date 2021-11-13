package com.ogong.pms.web.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberUpdateFromController {

  @GetMapping("/member/updateform")
  public ModelAndView updateForm() {
    ModelAndView mv = new ModelAndView();

    mv.addObject("pageTitle", "💬프로필 수정");
    mv.addObject("contentUrl", "member/PerMemberUpdateForm.jsp");
    mv.setViewName("template1");
    return mv;

  }
}