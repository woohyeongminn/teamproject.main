package com.ogong.pms.handler;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.ToDo;
import com.ogong.util.Prompt;

public class ToDoAddHandler extends AbstractToDoHandler {

  public ToDoAddHandler(List<ToDo> todoList) {
    super(todoList);
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ To-Do List 등록");

    ToDo todo = new ToDo();

    todo.setTodoNo(Prompt.inputInt("번호: "));
    todo.setTodoTitle(Prompt.inputString("제목: "));
    todo.setTodoContent(Prompt.inputString("내용: "));
    todo.setTodoRemark(Prompt.inputString("비고: "));
    todo.setTodoStatus(promptStatus());
    todo.setTodoRegisteredDate(new Date(System.currentTimeMillis()));

    todoList.add(todo);
    System.out.println("To-Do List가 등록되었습니다.");
  }
}