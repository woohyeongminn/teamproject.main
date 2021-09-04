//  수정 중.....

package com.ogong.pms.handler2;

import java.util.List;
import com.ogong.pms.domain.Study;
import com.ogong.util.Prompt;

public class MyStudyHandler {

  List<Study> myStudyList;
  StudyHandler studyHandler;

  public MyStudyHandler(List<Study> myStudyList, StudyHandler studyHandler) {
    this.myStudyList = myStudyList;
    this.studyHandler = studyHandler;
  } 

  public void list() {
    System.out.println();
    System.out.println("▶ 스터디 목록");


    System.out.println("----------------------");
    System.out.println("1. 상세 보기");
    System.out.println("2. 뒤로가기");
    int selectNo = Prompt.inputInt("선택> ");
    switch (selectNo) {
      case 1 : detail(); break;
      case 2 : System.out.println("뒤로가기"); break;
      default : return;

    }
  }

  //        System.out.printf("%d, %s, %s, %s, %s, %s\n",
  //            study.getStudyNo(),
  //            study.getSubject(),
  //            study.getNumberOfPeple(),
  //            study.getStudyTitle(),
  //            study.getOwner().getPerNickname(),
  //            study.getFace());


  public void detail() {
    System.out.println();
    System.out.println("▶ 스터디 상세보기");

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

    System.out.println();
    System.out.println("1. 참여 신청하기");
    System.out.println("2. 구성원보기");
    System.out.println("3. 뒤로가기");
    int selectNo = Prompt.inputInt("선택> ");
    switch (selectNo) {
      case 1 : studyHandler.joinStudy(study); break;
      case 2 : studyHandler.listMember(study); break;
      default : return;
    }
  }

  private Study findByTitle (String title) {
    for (Study study : myStudyList) {
      if (study.getStudyTitle().equalsIgnoreCase(title)) {
        return study;
      }
    }
    return null;
  }

}
