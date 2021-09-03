package com.ogong.pms.handler;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.ToDo;
import com.ogong.util.Prompt;

public class ToDoHandler {

  List<ToDo> todoList;

  public ToDoHandler(List<ToDo> todoList) {
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

  public void update() {
    System.out.println();
    System.out.println("▶ To-Do List 변경");
    int todoNo = Prompt.inputInt("선택하세요. ");

    ToDo todo = findBytodoNo(todoNo);

    String todoTitle = Prompt.inputString(String.format(
        "To-Do List 제목(%s): ", todo.getTodoTitle()));
    String todoContent = Prompt.inputString(String.format(
        "To-Do List 내용(%s): ", todo.getTodoContent()));
    int todoStatus = promptStatus(todo.getTodoStatus());

    String todoRemark = Prompt.inputString(String.format(
        "To-Do List 비고(%s): ", todo.getTodoRemark()));

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

  public void delete() {
    System.out.println();
    System.out.println("▶ To-Do List 삭제");
    int todoNo = Prompt.inputInt("번호 선택: ");

    ToDo todo = findBytodoNo(todoNo);

    if (todo == null) {
      System.out.println("다시 선택하세요.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N)");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("삭제를 취소하였습니다.");
      return;
    }

    todoList.remove(todo);

    System.out.println("To-Do List를 삭제하였습니다.");
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