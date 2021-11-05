package com.ogong.pms.servlet.myStudy.todo;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.dao.ToDoDao;
import com.ogong.pms.domain.Study;
import com.ogong.pms.domain.ToDo;

@WebServlet("/mystudy/todo/update")
public class ToDoUpdateController extends HttpServlet {
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
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {

      int studyNo = Integer.parseInt(request.getParameter("studyno"));
      Study myStudy = studyDao.findByNo(studyNo);

      int todoNo = Integer.parseInt(request.getParameter("todono"));
      ToDo todo = toDoDao.findByNo(myStudy.getStudyNo(), todoNo);

      int status = Integer.parseInt(request.getParameter("progress_no"));

      todo.setTodoStatus(status);
      todo.setTodoContent(request.getParameter("content"));
      todo.setTodoRemark(request.getParameter("note"));

      toDoDao.update(todo);
      sqlSession.commit();

      response.sendRedirect("list?todono="+todo.getTodoNo()+"&studyno="+myStudy.getStudyNo());

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
