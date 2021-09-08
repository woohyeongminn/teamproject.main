//package com.ogong.pms.handler;
//
//import java.sql.Date;
//import java.util.List;
//import com.ogong.pms.domain.ToDo;
//import com.ogong.util.Prompt;
//
//public class ToDoAddHandler extends AbstractToDoHandler {
//
//  int ToDoNO;
//
//  public ToDoAddHandler(List<ToDo> todoList) {
//    super(todoList);
//
//    ToDo test = new ToDo();
//    test.setTodoNo(ToDoNO++);
//    test.setTodoContent("문제집 주문하기");
//    test.setTodoRemark("자바의 정석");
//    test.setTodoRegisteredDate(new Date(System.currentTimeMillis()));
//    test.setTodoStatus(0);
//    todoList.add(test);
//
//    test = new ToDo();
//    test.setTodoNo(ToDoNO++);
//    test.setTodoContent("다음달 시험 일정 확인하기");
//    test.setTodoRemark("자격증");
//    test.setTodoRegisteredDate(new Date(System.currentTimeMillis()));
//    test.setTodoStatus(1);
//    todoList.add(test);
//
//    test = new ToDo();
//    test.setTodoNo(ToDoNO++);
//    test.setTodoContent("장소 예약 리뷰 적어주기");
//    test.setTodoRemark("에이스터디카페");
//    test.setTodoRegisteredDate(new Date(System.currentTimeMillis()));
//    test.setTodoStatus(2);
//    todoList.add(test);
//
//    test = new ToDo();
//    test.setTodoNo(ToDoNO++);
//    test.setTodoContent("내일 점심 샌드위치 사먹기");
//    test.setTodoRemark("배고프다");
//    test.setTodoRegisteredDate(new Date(System.currentTimeMillis()));
//    test.setTodoStatus(1);
//    todoList.add(test);
//  }
//
//  @Override
//  public void execute() {
//    System.out.println();
//    System.out.println("▶ To-Do List 등록");
//
//    ToDo todo = new ToDo();
//
//    todo.setTodoNo(Prompt.inputInt(" 번호: "));
//    todo.setTodoTitle(Prompt.inputString(" 제목: "));
//    todo.setTodoContent(Prompt.inputString(" 내용: "));
//    todo.setTodoRemark(Prompt.inputString(" 비고: "));
//    todo.setTodoStatus(promptStatus());
//    todo.setTodoRegisteredDate(new Date(System.currentTimeMillis()));
//
//    todoList.add(todo);
//    System.out.println("할 일이 등록되었습니다.");
//  }
//}