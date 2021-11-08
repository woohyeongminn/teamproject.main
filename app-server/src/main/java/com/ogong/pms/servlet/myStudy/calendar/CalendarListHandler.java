package com.ogong.pms.servlet.myStudy.calendar;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogong.pms.dao.StudyDao;

@WebServlet("/mystudy/calendarlist")
public class CalendarListHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;

  //CalendarDao calendarDao;
  StudyDao studyDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    studyDao = (StudyDao) 웹애플리케이션공용저장소.getAttribute("studyDao");
    // calendarDao = (CalendarDao) 웹애플리케이션공용저장소.getAttribute("calendarDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      //int studyNo = Integer.parseInt(request.getParameter("studyNo"));
      // List<Calendar> calendarList = calendarDao.findAll(studyNo);

      //request.setAttribute("calendarList", calendarList);
      request.getRequestDispatcher("/myStudy/calendar/calindex.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("errer", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}

