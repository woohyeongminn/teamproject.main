package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.ToDo;
import com.ogong.util.Prompt;

public class ToDoUpdateHandler extends AbstractToDoHandler {

  public ToDoUpdateHandler(List<ToDo> todoList) {
    super(todoList);
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ To-Do List 변경");
    int todoNo = Prompt.inputInt(" 번호 : ");

    ToDo todo = findBytodoNo(todoNo);

    String todoContent = Prompt.inputString(String.format(" 내용(%s) : ", todo.getTodoContent()));
    int todoStatus = promptStatus(todo.getTodoStatus());
    String todoRemark = Prompt.inputString(String.format(" 비고(%s) : ", todo.getTodoRemark()));

    String input = Prompt.inputString("정말 변경하시겠습니까? (네 / 아니오)");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println("변경을 취소하였습니다.");
      return;
    }

    todo.setTodoContent(todoContent);
    todo.setTodoStatus(todoStatus);
    todo.setTodoRemark(todoRemark);
    System.out.println("할 일이 변경되었습니다.");
  }
}