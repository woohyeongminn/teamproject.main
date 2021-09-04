package com.ogong.pms.handler2;

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
    int todoNo = Prompt.inputInt("선택하세요. ");

    ToDo todo = findBytodoNo(todoNo);

    String todoTitle = Prompt.inputString(String.format("To-Do List 제목(%s) : ", todo.getTodoTitle()));
    String todoContent = Prompt.inputString(String.format("To-Do List 내용(%s) : ", todo.getTodoContent()));
    int todoStatus = promptStatus(todo.getTodoStatus());
    String todoRemark = Prompt.inputString(String.format("To-Do List 비고(%s) : ", todo.getTodoRemark()));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N)");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("변경을 취소하였습니다.");
      return;
    }

    todo.setTodoTitle(todoTitle);
    todo.setTodoContent(todoContent);
    todo.setTodoStatus(todoStatus);
    todo.setTodoRemark(todoRemark);
    System.out.println("To-Do List 변경이 완료되었습니다.");
  }
}