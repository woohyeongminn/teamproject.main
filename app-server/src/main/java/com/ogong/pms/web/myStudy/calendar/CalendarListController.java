package com.ogong.pms.web.myStudy.calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CalendarListController {

  @RequestMapping("/mystudy/calendar/list")
  public ModelAndView calList() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("contentUrl","/myStudy/calendar/Calendar.jsp");
    mv.setViewName("template1");
    return mv;
  }
}

