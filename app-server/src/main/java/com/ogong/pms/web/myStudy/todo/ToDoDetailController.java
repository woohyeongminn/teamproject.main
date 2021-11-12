package com.ogong.pms.web.myStudy.todo;

import java.io.IOException;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.dao.ToDoDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.domain.ToDo;

@WebServlet("/mystudy/todo/detail")
public class ToDoDetailController extends GenericServlet {
  private static final long serialVersionUID = 1L;

  StudyDao studyDao;
  MemberDao memberDao;
  ToDoDao toDoDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    studyDao = (StudyDao) 웹애플리케이션공용저장소.getAttribute("studyDao");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
    toDoDao = (ToDoDao) 웹애플리케이션공용저장소.getAttribute("toDoDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {

      int perNo = Integer.parseInt(request.getParameter("perno"));
      Member member = memberDao.findByNo(perNo);

      int studyNo = Integer.parseInt(request.getParameter("studyno"));
      Study myStudy = studyDao.findByNo(studyNo);

      int todoNo = Integer.parseInt(request.getParameter("todono"));
      ToDo todo = toDoDao.findByNo(myStudy.getStudyNo(), todoNo);

      request.setAttribute("todo", todo);
      request.setAttribute("member", member);
      request.setAttribute("study", myStudy);
      request.getRequestDispatcher("/myStudy/todo/ToDoDetail.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}