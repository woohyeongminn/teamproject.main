package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.ToDo;
import com.ogong.util.Prompt;

public class ToDoDeleteHandler extends AbstractToDoHandler {

  public ToDoDeleteHandler(List<ToDo> todoList) {
    super(todoList);
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ To-Do List 삭제");
    int todoNo = Prompt.inputInt("번호 : ");

    ToDo todo = findBytodoNo(todoNo);

    if (todo == null) {
      System.out.println("다시 선택하세요.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까? (네 / 아니오)");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println("삭제를 취소하였습니다.");
      return;
    }

    todoList.remove(todo);

    System.out.println("할 일을 삭제하였습니다.");
  }
}