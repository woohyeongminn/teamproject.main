package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.ToDo;
import com.ogong.util.Prompt;

public abstract class AbstractToDoHandler implements Command {

  List<ToDo> todoList;

  public AbstractToDoHandler(List<ToDo> todoList) {
    this.todoList = todoList;
  }

  public String getStatusToDo(int todoStatus) {
    switch (todoStatus) {
      case 1: return "보류";
      case 2: return "진행 중";
      case 3: return "변경 예정";
      default: return "완료";
    }
  }

  public int promptStatus() {
    return promptStatus(-1);
  }

  public int promptStatus(int todoStatus) {
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

  public ToDo findBytodoNo(int todoNo) {
    ToDo[] arr = todoList.toArray(new ToDo[0]);
    for (ToDo todo : arr) {
      if (todo.getTodoNo() == todoNo) {
        return todo;
      }
    }
    return null;
  }
}