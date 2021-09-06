package com.ogong.pms.handler;

import java.util.HashMap;
import java.util.List;
import com.ogong.pms.domain.Calender;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.util.Prompt;

public class MyStudyDetailHandler extends AbstractStudyHandler {

  HashMap<String, Command> commandMap;

  public MyStudyDetailHandler(List<Study> studyList, HashMap<String, Command> commandMap) {
    super(studyList);
    this.commandMap = commandMap;
  }


  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 스터디 상세");

    Member memeber = AuthPerMemberLoginHandler.getLoginUser();
    for(Study study : memeber.getPerMyStudy()) {
      if (study.getStudyTitle() == null) {
        System.out.println("가입된 스터디가 없습니다.");
        return;
      }
    }

    String inputTitle = Prompt.inputString("제목 : ");
    Study study = findByTitle(inputTitle);
    if (study == null) {
      System.out.println("해당 제목의 스터디가 없습니다.");
      return;
    }

    System.out.printf("스터디명 : %s\n", study.getStudyTitle());
    System.out.printf("조장 : %s\n", study.getOwner().getPerNickname());
    System.out.printf("분야 : %s\n", study.getSubject());
    System.out.printf("지역 : %s\n", study.getArea());
    System.out.printf("인원수 : %d\n", study.getNumberOfPeple());
    System.out.printf("대면 : %s\n", study.getFace());
    System.out.printf("소개글 : %s\n", study.getIntroduction());

    if (AuthPerMemberLoginHandler.loginUser != null) {
      if (study.getOwner().getPerNickname().equals(AuthPerMemberLoginHandler.loginUser.getPerNickname())) {
      }
      selectUserModifyPage(study);
    }
  }

  private void selectUserModifyPage(Study study) {
    System.out.println();
    System.out.println("1. 구성원 관리");        // 대기중인 회원 목록, 참가중인 회원 목록, 인원수, 
    // 조장은 위에 3개 + 승인, 거절, 권한 넘겨주기, 탈퇴시키기 까지
    System.out.println("2. 캘린더");
    System.out.println("3. To-do List");
    System.out.println("4. 자유게시판");
    System.out.println("5. 화상미팅");
    System.out.println("6. 삭제&탈퇴하기");      // 구성원은 탈퇴만 해야함, 조장은 스터디삭제도 할수 있음
    System.out.println("0. 이전 메뉴");

    int selectAdminNo = Prompt.inputInt("선택> ");
    switch (selectAdminNo) {
      case 1: listMember(study); break;
      //      case 2: commandMap.get("/calender/list").execute(); break;   
      case 2: listCalender(study); break;
      case 3: commandMap.get("/toDo/list").execute(); break;
      case 4: commandMap.get("/freeBoard/list").execute(); break;
      case 5: commandMap.get("/mystudy/detail").execute(); break;
      case 6: commandMap.get("/myStudy/delete").execute(); break;   //바꿔야됨
      default : return;
    }
  }


  // 기존 핸들러(캘린더)에서 가지고옴
  // 0906 파라미터 추가 
  public void listCalender(Study study) {
    System.out.println();
    System.out.println("▶ 일정 목록");
    System.out.println();

    int selectMonth; 

    while (true) {
      System.out.println("'월'을 입력해주세요.");
      System.out.println();
      selectMonth = Prompt.inputInt("월: ");
      if (selectMonth > 12 || selectMonth < 1) {
        System.out.println("정확한 '월'을 입력해주세요.");
        continue;
      }
      Calender month = null;
      for (Calender calender : study.getMyStudyCalender()) {
        if (selectMonth == calender.getMonth()) {
          month = calender;
          System.out.printf(
              " [ %d월 %d일 %s요일 ]\n %s\n",
              calender.getMonth(), 
              calender.getDay(),
              calender.getDayOftheWeek(),
              calender.getCalenderContent());
          System.out.println();
        }
      }

      if (month == null) {
        System.out.println();
        System.out.printf("'%d월'에 등록된 일정이 없습니다.\n", selectMonth);
        System.out.println("1. 날짜 재입력");
        System.out.println("2. 등록   하기");
        System.out.println("3. 취       소");
        int selectNo = Prompt.inputInt("선택> ");
        switch (selectNo) {
          case 1 : continue;
          case 2 : commandMap.get("/calender/add").execute(); break;
          case 3 : return;
          default : return;
        }
      }
      if (month != null) {
        System.out.println("1. 상세   보기");
        System.out.println("2. 등록   하기");
        System.out.println("3. 날짜 재입력");
        System.out.println("4. 취       소");
        int selectNo = Prompt.inputInt("선택> ");
        switch (selectNo) {
          case 1 : commandMap.get("/calender/detail").execute(); break;
          //case 2 : commandMap.get("/calender/add").execute(); return;
          case 2 : addCalender(study); return; 
          case 3 : continue;
          case 4 : return;
          default : return;
        }
      }
    }

  }

  // 0906 파라미터 추가
  public void addCalender(Study study) {
    System.out.println();
    System.out.println("▶ 일정 등록");

    Calender calender = new Calender();



    System.out.println();
    System.out.println("시작 날짜를 입력해 주세요.");

    int month = Prompt.inputInt("월(1~12) : ");
    if (month > 12) {
      System.out.println("등록할 수 없는 '월'입니다.");
      System.out.println("다시 등록해주세요.");
      return;

    }
    calender.setMonth(month);

    int day;
    while (true) {
      day = Prompt.inputInt("일(1~31) : ");
      if (month == 2) {
        if (day > 28 || day < 1) {
          System.out.println("등록할 수 없는 '일'입니다.");
          System.out.println("다시 등록해주세요.");
          continue;
        }
      } 
      else if (month < 8 && (month % 2) == 1) {
        if (day > 31 || day < 1) {
          System.out.println("등록할 수 없는 '일'입니다.");
          System.out.println("다시 등록해주세요.");
          continue;
        }
      }
      else if (month < 8 && (month % 2) == 0) {
        if (day > 30 || day < 1) {
          System.out.println("등록할 수 없는 '일'입니다.");
          System.out.println("다시 등록해주세요.");
          continue;
        }
      }
      else if (month >= 8 && (month % 2) == 1) {
        if (day > 30 || day < 1) {
          System.out.println("등록할 수 없는 '일'입니다.");
          System.out.println("다시 등록해주세요.");
          continue;
        }
      }
      else if (month >= 8 && (month % 2) == 0) {
        if (day > 31 || day < 1) {
          System.out.println("등록할 수 없는 '일'입니다.");
          System.out.println("다시 등록해주세요.");
          continue;
        }
      }
      break;
    }

    calender.setDay(day);

    System.out.println("예시> 월");
    String inputDay;
    while (true) {
      inputDay = Prompt.inputString("요일 : ");
      if (inputDay.equals("월") || inputDay.equals("화") || inputDay.equals("수") ||
          inputDay.equals("목") || inputDay.equals("금") || inputDay.equals("토") ||
          inputDay.equals("일")) {
        calender.setDayOftheWeek(inputDay);
        break;
      }
      else {
        System.out.println("등록할 수 없는 '요일'입니다.");
      }
    }

    calender.setCalenderContent(Prompt.inputString("내용 : "));
    calender.setEndDay(Prompt.inputDate("종료일 : "));

    String input = Prompt.inputString("등록하시겠습니까? (네 / 아니오)");
    if (!input.equals("네")) {
      System.out.println("등록을 취소하였습니다.");
      return;
    }
    System.out.printf("'%d'월에 일정이 추가 되었습니다.\n",
        calender.getMonth());

    study.getMyStudyCalender().add(calender);
  }



}
