package com.ogong.pms.handler;

import java.util.HashMap;
import java.util.List;
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
    System.out.println("▶ 내 스터디 상세");
    System.out.println();

    Member memeber = AuthPerMemberLoginHandler.getLoginUser();
    for(Study study : memeber.getPerMyStudy()) {
      if (study.getStudyTitle() == null) {
        System.out.println("가입된 스터디가 없습니다.");
        return;
      }
    }

    String inputTitle = Prompt.inputString("스터디명 : ");
    System.out.println();
    Study study = findByTitle(inputTitle);
    if (study == null) {
      System.out.println("해당 제목의 스터디가 없습니다.");
      return;
    }

    System.out.printf(" [%s]\n", study.getStudyTitle());
    System.out.printf(" >> 조장 : %s\n", study.getOwner().getPerNickname());
    System.out.printf(" >> 분야 : %s\n", study.getSubject());
    System.out.printf(" >> 지역 : %s\n", study.getArea());
    System.out.printf(" >> 인원수 : %d\n", study.getNumberOfPeple());
    System.out.printf(" >> 대면 : %s\n", study.getFace());
    System.out.printf(" >> 소개글 : %s\n", study.getIntroduction());

    if (AuthPerMemberLoginHandler.loginUser != null) {
      if (study.getOwner().getPerNickname().equals(AuthPerMemberLoginHandler.loginUser.getPerNickname())) {
      }
      selectUserModifyPage(study);
    }
  }

  private void selectUserModifyPage(Study study) {
    System.out.println("\n----------------------");

    System.out.println("1. 구성원");
    // 대기중인 회원 목록, 참가중인 회원 목록, 인원수, 
    // 조장은 위에 3개 + 승인, 거절, 권한 넘겨주기, 탈퇴시키기 까지

    System.out.println("2. 캘린더");
    System.out.println("3. To-do");
    System.out.println("4. 자유게시판");
    System.out.println("5. 화상미팅");
    System.out.println("6. 수정하기");
    System.out.println("7. 삭제&탈퇴하기");
    // 스터디 삭제는 되는데 탈퇴하기가 없음
    // 구성원은 탈퇴만 해야함, 조장은 스터디삭제도 할수 있음

    System.out.println("0. 뒤로 가기");

    int selectAdminNo = Prompt.inputInt("선택> ");
    switch (selectAdminNo) {
      case 1: listMember(study); break;
      case 2: commandMap.get("/calender/list").execute(); break;
      case 3: commandMap.get("/toDo/list").execute(); break;
      case 4: commandMap.get("/freeBoard/list").execute(); break;
      case 5: commandMap.get("/mystudy/detail").execute(); break;   // 임시로 넣었음
      case 6: commandMap.get("/study/update").execute(); break;   //구현 덜 했음
      case 7: commandMap.get("/myStudy/delete").execute(); break;   //구현 덜 했음
      default : return;
    }
  }
}
