package com.ogong.pms.web.study;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudyAddFormController {

  @GetMapping("/study/form")
  public ModelAndView form() {
    ModelAndView mv = new ModelAndView();

    mv.addObject("pageTitle", "스터디 등록");
    mv.addObject("contentUrl", "study/StudyAddForm.jsp");
    mv.setViewName("template1");
    return mv;
  }
}
