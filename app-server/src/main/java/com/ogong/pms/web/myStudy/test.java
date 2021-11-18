package com.ogong.pms.web.myStudy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class test {

  @GetMapping("/mystudy/test")
  public ModelAndView testWebRTC(int studyno) {
    ModelAndView mv = new ModelAndView();
    mv.addObject("studyNo", studyno);
    mv.addObject("contentUrl", "myStudy/test.jsp");
    mv.setViewName("template1");
    return mv;
  }
}
