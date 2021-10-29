package com.ogong.pms.servlet.study;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Study;

@WebServlet("/study/list")
public class StudyListController extends GenericServlet {
  private static final long serialVersionUID = 1L;

  StudyDao studyDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    studyDao = (StudyDao) 웹애플리케이션공용저장소.getAttribute("studyDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      Collection<Study> studyList = studyDao.findAll();

      request.setAttribute("studyList", studyList);

      RequestDispatcher 요청배달자 = request.getRequestDispatcher("/study/StudyList.jsp");
      요청배달자.forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);

      RequestDispatcher 요청배달자 = request.getRequestDispatcher("/Error.jsp");
      요청배달자.forward(request, response);
    }
  }
}
