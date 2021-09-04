//  수정 중.....

package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Study;
import com.ogong.util.Prompt;

public class StudyUpdateHandler extends AbstractStudyHandler {


  public StudyUpdateHandler(List<Study> newStudyList) {
    super(newStudyList);
  }

  public void execute() {
    System.out.println();
    System.out.println("▶ 스터디 변경");

    String inputTitle = Prompt.inputString("제목 : ");

    Study study = findByTitle(inputTitle);

    if (study == null) {
      System.out.println("해당 제목의 스터디가 없습니다.");
      return;
    }

    if (study.getOwner().getPerNo() != AuthPerMemberLoginHandler.getLoginUser().getPerNo()) {
      System.out.println("변경 권한이 없습니다.");
      return;
    }

    String studyTitle = Prompt.inputString("스터디명(" + study.getStudyTitle()  + ") : ");
    String face = Prompt.inputString("대면(" + study.getFace() + ")? ");
    String introduction = Prompt.inputString("소개글(" + study.getIntroduction() + ") : ");

    String input = Prompt.inputString("정말 변경하시겠습니까? (네 / 아니오) ");
    if (input.equalsIgnoreCase("아니오") || input.length() == 0) {
      System.out.println("스터디 변경을 취소하였습니다.");
      return;
    }

    study.setStudyTitle(studyTitle);
    study.setFace(face);
    study.setIntroduction(introduction);

    System.out.println("스터디가 수정되었습니다.");
  }

}