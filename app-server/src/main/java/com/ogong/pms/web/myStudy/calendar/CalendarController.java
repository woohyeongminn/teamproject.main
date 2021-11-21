package com.ogong.pms.web.myStudy.calendar;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.CalendarDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Calendar;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;

@Controller
public class CalendarController {

  @Autowired StudyDao studyDao;
  @Autowired CalendarDao calendarDao;
  @Autowired SqlSessionFactory sqlSessionFactory;

  @RequestMapping("/mystudy/calendar/add")
  public ModelAndView calAdd(HttpSession session, Calendar calendar, int studyNo) throws Exception {
    ModelAndView mv = new ModelAndView();
    Member member = (Member) session.getAttribute("loginUser");
    calendar.setWriter(member);
    calendar.setStudyNo(studyNo);

    if (member == null) {
      throw new Exception("로그아웃");
    }
    if (calendar.getImportanceNo() == 1) {
      calendar.setImportance("⭐⭐⭐⭐⭐");
    } else if (calendar.getImportanceNo() == 2) {
      calendar.setImportance("⭐⭐⭐⭐☆");
    } else if (calendar.getImportanceNo() == 3) {
      calendar.setImportance("⭐⭐⭐☆☆");
    } else if (calendar.getImportanceNo() == 4) {
      calendar.setImportance("⭐⭐☆☆☆");
    } else if (calendar.getImportanceNo() == 5) {
      calendar.setImportance("⭐☆☆☆☆");
    }

    calendarDao.insert(calendar);
    sqlSessionFactory.openSession().commit();

    mv.setViewName("redirect:list?studyNo="+ studyNo);
    return mv;

  }


  @RequestMapping("/mystudy/calendar/list")
  public ModelAndView calList(HttpSession session, int studyNo) throws Exception {
    Member member = (Member) session.getAttribute("loginUser");

    Study myStudy = studyDao.findByMyNo(studyNo, member.getPerNo());
    List<Calendar> calendarList = calendarDao.findAll(studyNo);

    ModelAndView mv = new ModelAndView();

    mv.addObject("pageTitle","CALENDAR📆");
    mv.addObject("myStudy", myStudy);
    mv.addObject("calendarList", calendarList);
    mv.addObject("contentUrl","myStudy/calendar/selectable.jsp");
    mv.setViewName("template1");
    return mv;
  }
}

