package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.ToDo;

public class ToDoListHandler extends AbstractToDoHandler {

  public ToDoListHandler(List<ToDo> todoList) {
    super(todoList);
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ To-Do List 목록");

    ToDo[] todos = new ToDo[todoList.size()];

    todoList.toArray(todos);

    for (ToDo todo : todos) {
      System.out.printf("%d, %s, %s, %s, %s, %s\n", 
          todo.getTodoNo(), 
          todo.getTodoTitle(), 
          todo.getTodoContent(),
          todo.getTodoRemark(),
          getStatusToDo(todo.getTodoStatus()),
          todo.getTodoRegisteredDate());
    }
  }
}