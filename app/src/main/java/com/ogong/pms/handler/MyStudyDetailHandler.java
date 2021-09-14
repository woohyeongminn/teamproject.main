package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Comment;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.util.Prompt;

public class MyStudyDetailHandler extends AbstractStudyHandler {

  MyStudyToDo myStudyToDo;
  MyStudyCalender myStudyCalender;
  MyStudyFreeBoard myStudyFreeBoard;
  List<Comment> commentList;

  public MyStudyDetailHandler(List<Study> studyList, MyStudyToDo myStudyToDo, 
      MyStudyCalender myStudyCalender, MyStudyFreeBoard myStudyFreeBoard,
      List<Comment> commentList) {
    super(studyList);
    this.myStudyToDo = myStudyToDo;
    this.myStudyCalender = myStudyCalender;
    this.myStudyFreeBoard = myStudyFreeBoard;
    this.commentList = commentList;
  }


  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 내 스터디 상세");
    System.out.println();

    Member memeber = AuthPerMemberLoginHandler.getLoginUser();
    for(Study study : memeber.getPerMyStudy()) {
      if (study.getStudyTitle() == null) {
        System.out.println(" >> 가입된 스터디가 없습니다.");
        return;
      }
    }

    int inputNo = Prompt.inputInt(" 번호  : ");

    Study study = findByMyStudyNo(inputNo);

    if (study == null) {
      return;
    }

    System.out.printf(" \n(%s)\n", study.getStudyNo());
    System.out.printf(" [%s]\n", study.getStudyTitle());
    System.out.printf(" >> 조장 : %s\n", study.getOwner().getPerNickname());
    System.out.printf(" >> 분야 : %s\n", study.getSubject());
    System.out.printf(" >> 지역 : %s\n", study.getArea());
    System.out.printf(" >> 인원수 : %s/%s명\n",
        study.getMembers().size() + 1, study.getNumberOfPeple());
    System.out.printf(" >> 대면 : %s\n", study.getFace());
    System.out.printf(" >> 소개글 : %s\n", study.getIntroduction());

    if (AuthPerMemberLoginHandler.loginUser != null) {
      if (study.getOwner().getPerNickname().equals(
          AuthPerMemberLoginHandler.loginUser.getPerNickname())) {
      }
      selectUserModifyPage(study);
    }
  }


  private Study findByMyStudyNo(int inputNo) {
    Member memeber = AuthPerMemberLoginHandler.getLoginUser();

    try {
      for (int i = 0; i < studyList.size(); i++) {
        if (memeber.getPerMyStudy().get(i).getStudyNo() == inputNo) {
          Study study = memeber.getPerMyStudy().get(i);
          return study;
        }
      }
    } catch (Exception e) {
      System.out.println(" >> 해당 번호의 스터디가 없습니다.");
    }
    return null;
  }

  private void selectUserModifyPage(Study study) {
    while (true) {
      System.out.println("\n----------------------");

      System.out.println("1. 구성원");
      // 대기중인 회원 거절하기
      // 대기중인 회원 승인할때 회원 고유번호로 입력하도록
      // 조장 : 권한 넘겨주기, 탈퇴시키기 까지

      System.out.println("2. 캘린더");
      System.out.println("3. To-do");
      System.out.println("4. 자유게시판");
      System.out.println("5. 화상미팅");
      System.out.println("6. 수정하기");
      System.out.println("7. 삭제 & 탈퇴하기");
      // 스터디 삭제는 되는데 탈퇴하기가 없음
      // 구성원은 탈퇴만 해야함, 조장은 스터디삭제도 할수 있음

      System.out.println("0. 뒤로 가기");

      int selectAdminNo = Prompt.inputInt("선택> ");
      switch (selectAdminNo) {
        case 1: listMember(study); break;
        case 2: myStudyCalender.listCalender(study); break;
        case 3: myStudyToDo.listToDo(study); break;
        case 4: myStudyFreeBoard.listFreeBoard(commentList, study); break;

        case 5: myStudyCalender.listCalender(study); break;   // 임시로 넣었음
        case 6: myStudyCalender.listCalender(study); break;     // 구현 덜 했음
        case 7: myStudyCalender.listCalender(study); break;   // 구현 덜 했음
        default : return;
      }
    }

  }
}
