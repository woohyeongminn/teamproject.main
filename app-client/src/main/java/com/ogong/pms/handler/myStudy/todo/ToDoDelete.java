package com.ogong.pms.handler.myStudy.todo;

import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.dao.ToDoDao;
import com.ogong.pms.domain.Study;
import com.ogong.pms.domain.ToDo;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class ToDoDelete implements Command {

  StudyDao studyDao;
  ToDoDao toDoDao;
  SqlSession sqlSession;

  public ToDoDelete(StudyDao studyDao, ToDoDao toDoDao, SqlSession sqlSession) {
    this.studyDao = studyDao;
    this.toDoDao = toDoDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ To-Do List 삭제");
    System.out.println();

    int studyNo = (int) request.getAttribute("inputNo");
    int todoNo = (int) request.getAttribute("studyTodoNo");

    Study myStudy = studyDao.findByNo(studyNo);
    ToDo todo = toDoDao.findByNo(myStudy.getStudyNo(), todoNo);

    String input = Prompt.inputString(" 정말 삭제하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 삭제를 취소하였습니다.");
      request.getRequestDispatcher("/myStudy/todoList").forward(request);
      return;
    }

    toDoDao.delete(todo.getTodoNo());
    sqlSession.commit();

    System.out.println(" >> To-Do를 삭제하였습니다.");
    request.getRequestDispatcher("/myStudy/todoList").forward(request);
  }
}
