//package com.ogong.pms.handler;
//
//import java.util.List;
//import com.ogong.pms.domain.ToDo;
//import com.ogong.util.Prompt;
//
//public class ToDoDetailHandler extends AbstractToDoHandler {
//
//  public ToDoDetailHandler(List<ToDo> todoList) {
//    super(todoList);
//  }
//
//  @Override
//  public void execute() {
//    System.out.println();
//    System.out.println("▶ To-Do List 상세보기");
//    int todoNo = Prompt.inputInt("번호 : ");
//
//    ToDo todo = findBytodoNo(todoNo);
//
//    if (todo == null) {
//      System.out.println("다시 선택하세요.");
//      return;
//    }
//
//    System.out.printf(" [%s]\n", todo.getTodoTitle());
//    System.out.printf(" >> 내용 : %s\n", todo.getTodoContent());
//    System.out.printf(" >> 비고 : %s\n", todo.getTodoRemark());
//    System.out.printf(" >> 상태 : %d\n", todo.getTodoStatus());
//    System.out.printf(" >> DATE : %s\n", todo.getTodoRegisteredDate());
//  }
//}