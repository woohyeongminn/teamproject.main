package com.ogong.pms.handler;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.ToDo;
import com.ogong.util.Prompt;

public abstract class AbstractToDoHandler implements Command {

  List<ToDo> todoList;

  public AbstractToDoHandler(List<ToDo> todoList) {
    this.todoList = todoList;
  }

  //작성
  protected void addToDo() {
    System.out.println();
    System.out.println("▶ To-Do List 등록");

    ToDo todo = new ToDo();

    todo.setTodoNo(Prompt.inputInt(" 번호: "));
    todo.setTodoTitle(Prompt.inputString(" 제목: "));
    todo.setTodoContent(Prompt.inputString(" 내용: "));
    todo.setTodoRemark(Prompt.inputString(" 비고: "));
    todo.setTodoStatus(promptStatus());
    todo.setTodoDate(new Date(System.currentTimeMillis()));

    todoList.add(todo);
    System.out.println("할 일이 등록되었습니다.");
  }

  //목록
  protected void listToDo() {
    System.out.println();
    System.out.println("▶ To-Do List 목록");
    System.out.println();

    for (ToDo todo : todoList) {
      System.out.printf(" (%s) - %s\n 내용 : %s\n 비고 : %s\n DATE : %s\n", 
          todo.getTodoNo(), 
          getStatusToDo(todo.getTodoStatus()),
          todo.getTodoContent(),
          todo.getTodoRemark(),
          todo.getTodoDate());

      System.out.println();
    }

    System.out.println("========================");
    System.out.println("1. To-do 상세");
    System.out.println("2. To-do 등록");
    System.out.println("3. To-do 삭제");
    System.out.println("0. 뒤로가기");
    System.out.println("========================");
    int selete = Prompt.inputInt("선택> ");
    switch (selete) {
      case 1 : detailToDo(); break;
      case 2 : addToDo(); break;
      case 3 : deleteTodo(); break;
      default:break;
    }
  }

  //상세
  protected void detailToDo() {
    System.out.println();
    System.out.println("▶ To-Do List 상세보기");
    int todoNo = Prompt.inputInt("번호 : ");

    ToDo todo = findBytodoNo(todoNo);

    if (todo == null) {
      System.out.println("다시 선택하세요.");
      return;
    }

    System.out.printf(" [%s]\n", todo.getTodoTitle());
    System.out.printf(" >> 내용 : %s\n", todo.getTodoContent());
    System.out.printf(" >> 비고 : %s\n", todo.getTodoRemark());
    System.out.printf(" >> 상태 : %d\n", todo.getTodoStatus());
    System.out.printf(" >> DATE : %s\n", todo.getTodoDate());

    System.out.println("========================");
    System.out.println("1. To-do 목록");
    System.out.println("2. To-do 등록");
    System.out.println("3. To-do 삭제");
    System.out.println("0. 뒤로가기");
    System.out.println("========================");
    int selete = Prompt.inputInt("선택> ");
    switch (selete) {
      case 1 : listToDo(); break;
      case 2 : addToDo(); break;
      case 3 : deleteTodo(); break;
      default:break;
    }
  }

  //삭제
  protected void deleteTodo() {
    System.out.println();
    System.out.println("▶ To-Do List 삭제");
    System.out.println();
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


  //상태 선택
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

  //상태 꺼내기
  public String getStatusToDo(int todoStatus) {
    switch (todoStatus) {
      case 1: return "보류";
      case 2: return "진행 중";
      case 3: return "변경 예정";
      default: return "완료";
    }
  }

  //번호로 객체 찾기
  public ToDo findBytodoNo(int todoNo) {
    for (ToDo todo : todoList) {
      if (todo.getTodoNo() == todoNo) {
        return todo;
      }
    }
    return null;
  }

  //번호 구하기 (중복x)
  // private String getNo() {
  //   int max = 0;;
  //   for(ToDo todo : todoList) {
  //     if(Integer.parseUnsignedInt(todo.getTodoNo()) > max) {
  //       max = Integer.parseInt(todo.getTodoNo());
  //     }
  //   }
  //   return (max + 1) + "";
  // }
}