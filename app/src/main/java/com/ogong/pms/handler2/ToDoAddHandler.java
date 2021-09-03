package com.ogong.pms.handler2;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.ToDo;
import com.ogong.util.Prompt;

public class ToDoAddHandler {

  List<ToDo> todoList;

  public ToDoAddHandler(List<ToDo> todoList) {
    this.todoList = todoList;
  }

  public void add() {
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

  private String getStatusToDo(int todoStatus) {
    switch (todoStatus) {
      case 1: return "보류";
      case 2: return "진행 중";
      case 3: return "변경 예정";
      default: return "완료";
    }
  }

  private int promptStatus() {
    return promptStatus(-1);
  }

  private int promptStatus(int todoStatus) {
    if (todoStatus == -1) {
      System.out.println("진행 상황: ");
    } else {
      System.out.printf("진행 상황(%s): \n", getStatusToDo(todoStatus));
    }
    System.out.println("0: 보류");
    System.out.println("1: 진행 중");
    System.out.println("2: 변경 예정");
    System.out.println("3: 완료");
    return Prompt.inputInt("선택하세요. ");
  }
}