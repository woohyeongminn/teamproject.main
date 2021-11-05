package com.ogong.pms.servlet.myStudy.todo;

import java.io.IOException;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.dao.ToDoDao;
import com.ogong.pms.domain.Study;
import com.ogong.pms.domain.ToDo;

@WebServlet("/mystudy/todo/updateform")
public class ToDoUpdateFormController extends GenericServlet {
  private static final long serialVersionUID = 1L;

  StudyDao studyDao;
  ToDoDao toDoDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    studyDao = (StudyDao) 웹애플리케이션공용저장소.getAttribute("studyDao");
    toDoDao = (ToDoDao) 웹애플리케이션공용저장소.getAttribute("toDoDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {

      int studyNo = Integer.parseInt(request.getParameter("studyno"));
      Study myStudy = studyDao.findByNo(studyNo);

      int todoNo = Integer.parseInt(request.getParameter("todono"));
      ToDo todo = toDoDao.findByNo(myStudy.getStudyNo(), todoNo);

      request.setAttribute("todo", todo);
      request.setAttribute("study", myStudy);

      request.getRequestDispatcher("/myStudy/todo/ToDoUpdateForm.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
