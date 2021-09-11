package com.ogong.pms.handler;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.domain.ToDo;
import com.ogong.util.Prompt;

public class MyStudyToDo {

  int ToDoNo;
  List<Study> studyList;
  List<ToDo> toDoList;
  Study study;

  public MyStudyToDo(List<ToDo> toDoList, List<Study> studyList) {
    this.toDoList = toDoList;
    this.studyList = studyList;

    ToDoNo = 1;

    ToDo test = new ToDo();
    test.setTodoNo(ToDoNo++);
    test.setTodoContent("문제집 주문하기");
    test.setTodoRemark("자바의 정석");
    test.setTodoDate(new Date(System.currentTimeMillis()));
    test.setTodoStatus(1);
    toDoList.add(test);
    studyList.get(0).getMyStudyToDo().add(test);

    test = new ToDo();
    test.setTodoNo(ToDoNo++);
    test.setTodoContent("다음달 시험 일정 확인하기");
    test.setTodoRemark("자격증");
    test.setTodoDate(new Date(System.currentTimeMillis()));
    test.setTodoStatus(1);
    toDoList.add(test);
    studyList.get(0).getMyStudyToDo().add(test);

    test = new ToDo();
    test.setTodoNo(ToDoNo++);
    test.setTodoContent("장소 예약 리뷰 적어주기");
    test.setTodoRemark("에이스터디카페");
    test.setTodoDate(new Date(System.currentTimeMillis()));
    test.setTodoStatus(2);
    toDoList.add(test);
    studyList.get(1).getMyStudyToDo().add(test);

    test = new ToDo();
    test.setTodoNo(ToDoNo++);
    test.setTodoContent("내일 점심 샌드위치 사먹기");
    test.setTodoRemark("배고프다");
    test.setTodoDate(new Date(System.currentTimeMillis()));
    test.setTodoStatus(2);
    toDoList.add(test);
    studyList.get(1).getMyStudyToDo().add(test);

  }

  //작성
  protected void addToDo(Study study) {
    System.out.println();
    System.out.println("▶ To-Do List 등록");
    System.out.println();

    Member member = AuthPerMemberLoginHandler.getLoginUser();
    if (member == null ) {
      System.out.println("로그인 한 회원만 조회 가능합니다.");
      return;
    }

    if (study.getStudyTitle() == null) {
      System.out.println("가입된 스터디가 없습니다.");
      return;
    }

    ToDo todo = new ToDo();

    todo.setTodoNo(Prompt.inputInt(" 번호: "));
    todo.setTodoContent(Prompt.inputString(" 내용: "));
    todo.setTodoRemark(Prompt.inputString(" 비고: "));
    todo.setTodoStatus(promptStatus());
    todo.setTodoDate(new Date(System.currentTimeMillis()));

    String input = Prompt.inputString("정말 등록하시겠습니까? (네 / 아니오)");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println("등록을 취소하였습니다.");
      listToDo(study);
      return;
    }

    toDoList.add(todo);
    study.getMyStudyToDo().add(todo);
    System.out.println("할 일이 등록되었습니다.");

    listToDo(study);
    return;
  }

  //목록
  //  protected void listToDo(Study study) {
  //    System.out.println();
  //    System.out.println("▶ To-Do List 목록");
  //    System.out.println();
  //
  //    System.out.println(" ▼▼ 진행중인 To-do ▼▼ ");
  //
  //    List<ToDo> toDoArrayList = new ArrayList<>(); // 메서드 안에서 선언할때는 초기화해야한다 (null 쓰지 마세요)
  //
  //    for (ToDo todo : study.getMyStudyToDo()) {
  //      if (todo.getTodoStatus() != 2 || todo.getTodoStatus() == 1) {
  //        System.out.printf(" %s | %s 내용 : %s 비고 : %s DATE : %s\n", 
  //            getStatusToDo(todo.getTodoStatus()),
  //            todo.getTodoNo(),
  //            todo.getTodoContent(),
  //            todo.getTodoRemark(),
  //            todo.getTodoDate());
  //        System.out.println();
  //
  //        toDoArrayList.add(todo); 
  //      }
  //    }
  //    if (toDoArrayList.isEmpty()) {
  //      System.out.println("등록된 To-Do List가 없습니다.");
  //    }
  //
  //    System.out.println(" ▼▼ 완료된 To-do ▼▼ ");
  //
  //    for(int i = 0; i < toDoArrayList.size(); i++) {
  //      //int todoStatus = toDoArrayList.get(i).getTodoStatus();
  //
  //      for (ToDo todo : study.getMyStudyToDo()) {
  //        if (todo.getTodoStatus() != 2 || todo.getTodoStatus() == 1) {
  //          System.out.println(" 완료된 To-Do List가 없습니다.");
  //          break;
  //
  //        }else if (todo.getTodoStatus() != 1 || todo.getTodoStatus() == 2) {
  //          System.out.printf(" %s | %s 내용 : %s 비고 : %s DATE : %s\n", 
  //              getStatusToDo(todo.getTodoStatus()),
  //              todo.getTodoNo(),
  //              todo.getTodoContent(),
  //              todo.getTodoRemark(),
  //              todo.getTodoDate());
  //          System.out.println();
  //          break;
  //        }
  //      }
  //    }
  //
  //    System.out.println("---------------------");
  //    System.out.println("1. 상세");
  //    System.out.println("2. 등록");
  //    System.out.println("0. 이전");
  //    int selete = Prompt.inputInt("선택> ");
  //    switch (selete) {
  //      case 1 : detailToDo(toDoArrayList, study); break;
  //      case 2 : addToDo(study); break;
  //      default : return;
  //    }
  //  }

  protected void listToDo(Study study) {
    System.out.println();
    System.out.println("▶ To-Do List 목록");
    System.out.println();


    List<ToDo> toDoArrayList = new ArrayList<>(); // 메서드 안에서 선언할때는 초기화해야한다 (null 쓰지 마세요)

    System.out.println(" ▼▼ 진행중인 To-do ▼▼ ");
    for (ToDo todo : study.getMyStudyToDo()) {
      if (todo.getTodoStatus() == 1) {
        System.out.printf(" %s | %s | 내용 : %s | 비고 : %s | DATE : %s\n", 
            getStatusToDo(todo.getTodoStatus()),
            todo.getTodoNo(),
            todo.getTodoContent(),
            todo.getTodoRemark(),
            todo.getTodoDate());
        System.out.println();
        toDoArrayList.add(todo); 

      }
    }
    if (toDoArrayList.isEmpty()) {
      System.out.println("등록된 To-Do List가 없습니다.");
    }

    System.out.println(" ▼▼ 완료된 To-do ▼▼ ");
    for (ToDo todo : study.getMyStudyToDo()) {
      if (todo.getTodoStatus() == 2) {
        System.out.printf(" %s | %s | 내용 : %s | 비고 : %s | DATE : %s\n", 
            getStatusToDo(todo.getTodoStatus()),
            todo.getTodoNo(),
            todo.getTodoContent(),
            todo.getTodoRemark(),
            todo.getTodoDate());
        System.out.println();
        todo.getTodocomplete();
      }
      //      if () {
      //        System.out.println("완료된 To-Do List가 없습니다.");
      //      }
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
  //}


  //상세
  protected void detailToDo(List<ToDo> toDoArrayList, Study study) {
    System.out.println();
    System.out.println("▶ To-Do List 상세보기");
    System.out.println();

    int todoNo;

    ToDo detailToDo = new ToDo();

    while (true) {
      todoNo = Prompt.inputInt("번호 : ");
      System.out.println();

      if (toDoArrayList.isEmpty()) {
        System.out.println("다시 선택하세요.");
        continue;
      }

      for (int i = 0; i < toDoArrayList.size(); i++) {
        if (toDoArrayList.get(i).getTodoNo() == todoNo) {
          System.out.printf(" >> 내용 : %s\n", toDoArrayList.get(i).getTodoContent());
          System.out.printf(" >> 비고 : %s\n", toDoArrayList.get(i).getTodoRemark());
          if (toDoArrayList.get(i).getTodoStatus() == 1) {
            System.out.println(" >> 상태 : 진행 중");
          } else if (toDoArrayList.get(i).getTodoStatus() == 2) {
            System.out.println(" >> 상태 : 완료");
          }
          System.out.printf(" >> DATE : %s\n", toDoArrayList.get(i).getTodoDate());
          detailToDo = toDoArrayList.get(i);
        }
      }
      break;
    }

    System.out.println("\n----------------------");
    System.out.println("1. 수정");
    System.out.println("2. 삭제");
    System.out.println("0. 이전");
    int selete = Prompt.inputInt("선택> ");
    switch (selete) {
      case 1 : updateToDo(detailToDo, study); break;
      case 2 : deleteToDo(detailToDo, study); break;
      case 0 : listToDo(study); break;
      default : return;
    }
  }

  // 수정
  protected void updateToDo(ToDo detailToDo, Study study) {
    System.out.println();
    System.out.println("▶ To-Do List 변경");
    System.out.println();

    String todoContent = Prompt.inputString(String.format(" 내용(%s) : ", detailToDo.getTodoContent()));
    String todoRemark = Prompt.inputString(String.format(" 비고(%s) : ", detailToDo.getTodoRemark()));

    int todoStatus = promptStatus(detailToDo.getTodoStatus());

    System.out.println();
    String input = Prompt.inputString("정말 변경하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println("변경을 취소하였습니다.");
      listToDo(study);
      return;
    }

    detailToDo.setTodoContent(todoContent);
    detailToDo.setTodoStatus(todoStatus);
    detailToDo.setTodoRemark(todoRemark);

    System.out.println("할 일이 변경되었습니다.");
    listToDo(study);
    return;
  }


  //삭제
  protected void deleteToDo(ToDo detailToDo, Study study) {
    System.out.println();
    System.out.println("▶ To-Do List 삭제");
    System.out.println();

    String input = Prompt.inputString("정말 삭제하시겠습니까? (네 / 아니오)");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println("삭제를 취소하였습니다.");
      listToDo(study);
      return;
    }

    toDoList.remove(detailToDo);
    study.getMyStudyToDo().remove(detailToDo);

    System.out.println("할 일을 삭제하였습니다.");
    listToDo(study);
    return;
  }


  //상태 선택
  public int promptStatus() {
    return promptStatus(-1);
  }

  public int promptStatus(int todoStatus) {
    if (todoStatus == -1) {
      System.out.println();
      System.out.println("진행 상황: ");
      System.out.println("1: 진행 중");
      System.out.println();
      return Prompt.inputInt("선택> ");
    } else {
      System.out.printf(" 진행 상황(%s):", getStatusToDo(todoStatus));
    }
    System.out.println();
    System.out.println(" 1: 진행 중");
    System.out.println(" 2: 완료");
    return Prompt.inputInt(" 선택> ");
  }

  //상태 꺼내기
  public String getStatusToDo(int todoStatus) {
    switch (todoStatus) {
      case 1: return "○";
      case 2: return "✔";
      default: return null;
    }
  }

  //번호로 객체 찾기
  //  public ToDo findBytodoNo(int todoNo) {
  //    for (ToDo todo : todoList) {
  //      if (todo.getTodoNo() == todoNo) {
  //        return todo;
  //      }
  //    }
  //    return null;
  //  }
}



