package com.ogong.pms.web.myStudy.calendar;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;

@Controller
public class CalendarListController {

  @Autowired StudyDao studyDao;

  @RequestMapping("/mystudy/calendar/list")
  public ModelAndView calList(HttpSession session, int studyNo) throws Exception {
    Member member = (Member) session.getAttribute("loginUser");

    Study myStudy = studyDao.findByMyNo(studyNo, member.getPerNo());

    ModelAndView mv = new ModelAndView();

    mv.addObject("pageTitle","CALENDAR📆");
    mv.addObject("myStudy", myStudy);
    mv.addObject("contentUrl","myStudy/calendar/Calendar.jsp");
    mv.setViewName("template1");
    return mv;
  }
}

