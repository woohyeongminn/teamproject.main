package com.ogong.pms.handler2;

import java.util.List;
import com.ogong.pms.domain.ToDo;
import com.ogong.util.Prompt;

public class ToDoDetailHandler {

  List<ToDo> todoList;

  public ToDoDetailHandler(List<ToDo> todoList) {
    this.todoList = todoList;
  }

  public void detail() {
    System.out.println();
    System.out.println("▶ To-Do List 상세보기");
    int todoNo = Prompt.inputInt("선택하세요. ");

    ToDo todo = findBytodoNo(todoNo);

    if (todo == null) {
      System.out.println("다시 선택하세요.");
      return;
    }

    System.out.printf("제목: %s\n", todo.getTodoTitle());
    System.out.printf("내용: %s\n", todo.getTodoContent());
    System.out.printf("비고: %s\n", todo.getTodoRemark());
    System.out.printf("진행 상황: %d\n", todo.getTodoStatus());
    System.out.printf("DATE: %s\n", todo.getTodoRegisteredDate());
  }

  private ToDo findBytodoNo(int todoNo) {
    ToDo[] arr = todoList.toArray(new ToDo[0]);
    for (ToDo todo : arr) {
      if (todo.getTodoNo() == todoNo) {
        return todo;
      }
    }
    return null;
  }
}