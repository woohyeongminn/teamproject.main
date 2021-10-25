package com.ogong.pms.handler.myStudy.todo;

import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.dao.ToDoDao;
import com.ogong.pms.domain.Study;
import com.ogong.pms.domain.ToDo;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class ToDoDetail implements Command {

  StudyDao studyDao;
  ToDoDao toDoDao;

  public ToDoDetail(StudyDao studyDao, ToDoDao toDoDao) {
    this.studyDao = studyDao;
    this.toDoDao = toDoDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ To-Do List 상세보기");
    System.out.println();

    int inputNo = (int) request.getAttribute("inputNo");

    Study myStudy = studyDao.findByNo(inputNo);
    // List <ToDo> todoList = myStudy.getMyStudyToDo();

    int inputTodoNo = Prompt.inputInt(" 번호 : ");
    System.out.println();

    ToDo todoList = toDoDao.findByNo(myStudy.getStudyNo(), inputTodoNo);

    if (todoList == null) {
      System.out.println(" >> 해당 번호의 To-Do가 없습니다.");
      request.getRequestDispatcher("/myStudy/todoList").forward(request);
      return;
    }

    // int[] arry = new int[2]; // studyNo와 해당 todo 번호 둘 다 넘기려고 배열로 받음
    // arry[0] = (int) request.getAttribute("inputNo"); // study 번호를 넘겨 줌

    if (todoList.getTodoNo() == inputTodoNo) {
      System.out.printf(" >> 내용 : %s\n", todoList.getTodoContent());
      System.out.printf(" >> 비고 : %s\n", todoList.getTodoRemark());

      if (todoList.getTodoStatus() == ToDo.PROGRESSING) {
        System.out.println(" >> 상태 : 진행 중");

      } else if (todoList.getTodoStatus() == ToDo.COMPLETE) {
        System.out.println(" >> 상태 : 완료");
      }

      System.out.printf(" >> DATE : %s\n", todoList.getTodoDate());

      // arry[1] = i; // 해당 todo 넘겨 줌
      // inputTodoNo = -1; // -1으로 초기화 시키면 아래 if문 실행 안 되고 바로 아래로 내려감
    }

    request.setAttribute("studyTodoNo", inputTodoNo);

    System.out.println("\n----------------------");
    System.out.println("1. 수정");
    System.out.println("2. 삭제");
    System.out.println("0. 이전");
    int selete = Prompt.inputInt("선택> ");

    switch (selete) {
      case 1:
        request.getRequestDispatcher("/myStudy/todoUpdate").forward(request);
        return;
      case 2:
        request.getRequestDispatcher("/myStudy/todoDelete").forward(request);
        return;
      case 0:
        request.getRequestDispatcher("/myStudy/todoList").forward(request);
        return;
      default:
        return;
    }
  }
}
