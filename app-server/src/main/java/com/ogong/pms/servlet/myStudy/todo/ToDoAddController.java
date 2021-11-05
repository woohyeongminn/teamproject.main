package com.ogong.pms.servlet.myStudy.todo;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.dao.ToDoDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.domain.ToDo;

@WebServlet("/mystudy/todo/add")
public class ToDoAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  StudyDao studyDao;
  MemberDao memberDao;
  ToDoDao toDoDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    studyDao = (StudyDao) 웹애플리케이션공용저장소.getAttribute("studyDao");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
    toDoDao = (ToDoDao) 웹애플리케이션공용저장소.getAttribute("toDoDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {

      int perNo = Integer.parseInt(request.getParameter("perno"));
      Member member = memberDao.findByNo(perNo);

      int studyNo = Integer.parseInt(request.getParameter("studyno"));
      //      Member member = AuthPerMemberLoginHandler.getLoginUser();
      Study myStudy = studyDao.findByNo(studyNo);

      if (myStudy.getStudyTitle() == null) {
        throw new Exception(" >> 가입된 스터디가 없습니다.");
      }

      ToDo todo = new ToDo();

      todo.setStudyNo(myStudy.getStudyNo());
      todo.setTodoStatus(1);
      todo.setTodoContent(request.getParameter("content"));
      todo.setTodoRemark(request.getParameter("note"));
      todo.setTodoWriter(member);
      todo.setTodoDate(new Date(System.currentTimeMillis()));

      toDoDao.insert(todo);
      sqlSession.commit();

      response.sendRedirect("list?perno="+member.getPerNo()+"&studyno="+myStudy.getStudyNo());

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
