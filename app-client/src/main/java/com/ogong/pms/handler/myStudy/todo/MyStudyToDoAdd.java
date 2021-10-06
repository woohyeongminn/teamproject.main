package com.ogong.pms.handler.myStudy.todo;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.domain.ToDo;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.PromptStudy;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class MyStudyToDoAdd implements Command {

  int ToDoNo = 5;
  RequestAgent requestAgent;
  PromptStudy promptStudy;

  public MyStudyToDoAdd(List<ToDo> toDoList, List<Study> studyList) {
    this.toDoList = toDoList;
    this.studyList = studyList;
  }

  //등록
  protected void addToDo() {
    System.out.println();
    System.out.println("▶ To-Do List 등록");
    System.out.println();

    Study study;

    Member member = AuthPerMemberLoginHandler.getLoginUser();
    if (member == null ) {
      System.out.println(" >> 로그인 한 회원만 조회 가능합니다.");
      return;
    }

    if (study.getStudyTitle() == null) {
      System.out.println(" >> 가입된 스터디가 없습니다.");
      return;
    }

    ToDo todo = new ToDo();

    todo.setTodoContent(Prompt.inputString(" 내용: "));
    todo.setTodoRemark(Prompt.inputString(" 비고: "));
    todo.setTodoStatus(1);
    todo.setTodoDate(new Date(System.currentTimeMillis()));

    String input = Prompt.inputString(" 정말 등록하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 등록을 취소하였습니다.");
      listToDo(study); // 취소되면 그냥 다시 목록 출력
      return;
    }

    todo.setTodoNo(ToDoNo++);
    toDoList.add(todo);
    study.getMyStudyToDo().add(todo);

    System.out.println(" >> 할 일이 등록되었습니다.");
  }
}



