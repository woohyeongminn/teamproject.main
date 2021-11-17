package com.ogong.pms.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

  @GetMapping("/index")
  public ModelAndView index() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "오늘의 공부");
    mv.setViewName("index");
    return mv;
  }

  @GetMapping("/index2")
  public ModelAndView index2() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "오늘의 공부");
    mv.setViewName("index2");
    return mv;
  }
}
