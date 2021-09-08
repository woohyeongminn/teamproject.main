//package com.ogong.pms.handler;
//
//import java.util.List;
//import com.ogong.pms.domain.ToDo;
//
//public class ToDoListHandler extends AbstractToDoHandler {
//
//  public ToDoListHandler(List<ToDo> todoList) {
//    super(todoList);
//  }
//
//  @Override
//  public void execute() {
//    System.out.println();
//    System.out.println("▶ To-Do List 목록");
//    System.out.println();
//
//    for (ToDo todo : todoList) {
//      System.out.printf(" (%d) - %s\n 내용 : %s\n 비고 : %s\n DATE : %s\n", 
//          todo.getTodoNo(), 
//          getStatusToDo(todo.getTodoStatus()),
//          todo.getTodoContent(),
//          todo.getTodoRemark(),
//          todo.getTodoRegisteredDate());
//      System.out.println();
//    }
//  }
//}