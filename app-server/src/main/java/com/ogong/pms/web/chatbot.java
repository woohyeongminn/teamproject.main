package com.ogong.pms.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class chatbot {

  @GetMapping("/todaystudy")

  public ModelAndView chatPage() {
    ModelAndView mv = new ModelAndView();

    mv.setViewName("chatbot");

    return mv;
  } 
}
