package com.ogong.pms.handler2;

import java.util.List;
import com.ogong.pms.domain.ToDo;

public class ToDoListHandler {

  List<ToDo> todoList;

  public ToDoListHandler(List<ToDo> todoList) {
    this.todoList = todoList;
  }

  public void list() {
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

  private String getStatusToDo(int todoStatus) {
    switch (todoStatus) {
      case 1: return "보류";
      case 2: return "진행 중";
      case 3: return "변경 예정";
      default: return "완료";
    }
  }
}