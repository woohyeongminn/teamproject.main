package com.ogong.pms.servlet.myStudy.todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.dao.ToDoDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.domain.ToDo;

@WebServlet("/mystudy/todo/list")
public class ToDoListController extends HttpServlet {
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
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {

      int perNo = Integer.parseInt(request.getParameter("perno"));
      Member member = memberDao.findByNo(perNo);

      int studyNo = Integer.parseInt(request.getParameter("studyno"));

      Study myStudy = studyDao.findByNo(studyNo);
      List<ToDo> todoList = toDoDao.findAll(myStudy.getStudyNo());

      List<ToDo> countProgressing = new ArrayList<>();
      for (ToDo todo : todoList) {
        if (todo.getTodoStatus() == ToDo.PROGRESSING) {
          todo.setTodocomplete(getStatusToDo(todo.getTodoStatus()));
          countProgressing.add(todo);

        } else if (todo.getTodoStatus() == ToDo.COMPLETE) {
          todo.setTodocomplete(getStatusToDo(todo.getTodoStatus()));
          countProgressing.add(todo);
        }
      }

      request.setAttribute("member", member);
      request.setAttribute("study", myStudy);
      request.setAttribute("countProgressing", countProgressing);
      request.getRequestDispatcher("/myStudy/todo/ToDoList.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }

  // 상태 꺼내기
  private String getStatusToDo(int todoStatus) {
    switch (todoStatus) {
      case 1:
        return "○";
      case 2:
        return "✔";
      default:
        return null;
    }
  }
}