package com.ogong.pms.handler2;

import java.util.List;
import com.ogong.pms.domain.ToDo;
import com.ogong.util.Prompt;

public class ToDoUpdateHandler {

  List<ToDo> todoList;

  public ToDoUpdateHandler(List<ToDo> todoList) {
    this.todoList = todoList;
  }

  public void update() {
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

  private String getStatusToDo(int todoStatus) {
    switch (todoStatus) {
      case 1: return "보류";
      case 2: return "진행 중";
      case 3: return "변경 예정";
      default: return "완료";
    }
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