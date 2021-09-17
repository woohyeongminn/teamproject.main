package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Study;
import com.ogong.util.Prompt;

public class StudyDetailHandler extends AbstractStudyHandler {

  MyStudyDetailHandler myStudyDetailHandler;

  public StudyDetailHandler(List<Study> studyList) {
    super(studyList);
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 스터디 상세");
    System.out.println();

    int inputNo = Prompt.inputInt(" 번호 : ");

    Study study = findByNo(inputNo);

    if (study == null) {
      System.out.println(" >> 해당 번호의 스터디가 없습니다.");
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
        System.out.println();
        System.out.println();
        System.out.println("0. 뒤로가기");
        int selectNo = Prompt.inputInt("선택> ");
        switch (selectNo) {
          default : return;
        }
      } else {
        System.out.println("\n----------------------");
        System.out.println("1. 참여 신청하기");
        System.out.println("0. 뒤로가기");
        int selectNo = Prompt.inputInt("선택> ");
        switch (selectNo) {
          case 1 : joinStudy(study); break;
          default : return;
        }
      }
    }
  }
}
