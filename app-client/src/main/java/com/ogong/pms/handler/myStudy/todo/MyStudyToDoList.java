package com.ogong.pms.handler.myStudy.todo;

import java.util.ArrayList;
import java.util.List;
import com.ogong.pms.domain.Study;
import com.ogong.pms.domain.ToDo;
import com.ogong.util.Prompt;

public class MyStudyToDoList {

  int ToDoNo = 5;
  List<Study> studyList;
  List<ToDo> toDoList;
  Study study;

  public MyStudyToDoList(List<ToDo> toDoList, List<Study> studyList) {
    this.toDoList = toDoList;
    this.studyList = studyList;
  }

  // 목록
  public void listToDo(Study study) {
    System.out.println();
    System.out.println("▶ To-Do List 목록");
    System.out.println();

    // 메서드 안에서 선언할때는 초기화해야한다 (null 쓰지 마세요)
    List<ToDo> toDoArrayList = new ArrayList<>(); 

    System.out.println(" >> 진행 중 ");

    int countProgressing = 0;

    for (ToDo todo : study.getMyStudyToDo()) {
      if (todo.getTodoStatus() == ToDo.PROGRESSING) {
        System.out.printf(" %s | %s | 내용 : %s | 비고 : %s | DATE : %s\n", 
            getStatusToDo(todo.getTodoStatus()),
            todo.getTodoNo(),
            todo.getTodoContent(),
            todo.getTodoRemark(),
            todo.getTodoDate());
        System.out.println();
        countProgressing++;
      }
      toDoArrayList.add(todo);
    }

    if (countProgressing == 0) {
      System.out.println(" ○ | 진행 중인 To-Do List가 없습니다.\n");
    }

    System.out.println(" >> 완료 ");

    int countComplete = 0;

    for (ToDo todo : study.getMyStudyToDo()) {
      if (todo.getTodoStatus() == ToDo.COMPLETE) {
        System.out.printf(" %s | %s | 내용 : %s | 비고 : %s | DATE : %s\n", 
            getStatusToDo(todo.getTodoStatus()),
            todo.getTodoNo(),
            todo.getTodoContent(),
            todo.getTodoRemark(),
            todo.getTodoDate());
        System.out.println();
        countComplete++;
      }
    }

    if (countComplete == 0) {
      System.out.println(" ✔ | 완료된 To-Do List가 없습니다.\n");
    }

    System.out.println("---------------------");
    System.out.println("1. 상세");
    System.out.println("2. 등록");
    System.out.println("0. 이전");
    int selete = Prompt.inputInt("선택> ");
    switch (selete) {
      case 1 : detailToDo(toDoArrayList, study); break;
      case 2 : addToDo(study); break;
      default : return;
    }
  }

  //상태 꺼내기
  private String getStatusToDo(int todoStatus) {
    switch (todoStatus) {
      case 1: return "○";
      case 2: return "✔";
      default: return null;
    }
  }
}



