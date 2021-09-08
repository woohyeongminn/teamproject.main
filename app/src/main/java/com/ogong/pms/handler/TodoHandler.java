package com.ogong.pms.handler;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.ToDo;
import com.ogong.util.Prompt;

public class TodoHandler extends AbstractToDoHandler{

  int ToDoNo;
  List<ToDo> todoList;

  public TodoHandler(List<ToDo> todoList) {
    super(todoList);

    ToDo test = new ToDo();
    test.setTodoNo(ToDoNo++);
    test.setTodoContent("문제집 주문하기");
    test.setTodoRemark("자바의 정석");
    test.setTodoDate(new Date(System.currentTimeMillis()));
    test.setTodoStatus(0);
    todoList.add(test);

    test = new ToDo();
    test.setTodoNo(ToDoNo++);
    test.setTodoContent("다음달 시험 일정 확인하기");
    test.setTodoRemark("자격증");
    test.setTodoDate(new Date(System.currentTimeMillis()));
    test.setTodoStatus(1);
    todoList.add(test);

    test = new ToDo();
    test.setTodoNo(ToDoNo++);
    test.setTodoContent("장소 예약 리뷰 적어주기");
    test.setTodoRemark("에이스터디카페");
    test.setTodoDate(new Date(System.currentTimeMillis()));
    test.setTodoStatus(2);
    todoList.add(test);

    test = new ToDo();
    test.setTodoNo(ToDoNo++);
    test.setTodoContent("내일 점심 샌드위치 사먹기");
    test.setTodoRemark("배고프다");
    test.setTodoDate(new Date(System.currentTimeMillis()));
    test.setTodoStatus(1);
    todoList.add(test);

  }

  @Override
  public void execute() {

    System.out.println("========================");
    System.out.println("1. To-do 작성");
    System.out.println("2. To-do 목록");
    System.out.println("0. 뒤로가기");
    System.out.println("========================");
    int selete = Prompt.inputInt("선택> ");

    switch (selete) {
      case 1 : addToDo(); break;
      case 2 : listToDo(); break;
      default:break;
    }
  }
}



