//  수정 중.....

package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Study;
import com.ogong.util.Prompt;

public class StudyUpdateHandler extends AbstractStudyHandler {


  public StudyUpdateHandler(List<Study> newStudyList) {
    super(newStudyList);
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 스터디 수정");
    System.out.println();

    int inputNo = Prompt.inputInt("번호  : ");

    Study study = findByNo(inputNo);

    if (study == null) {
      System.out.println("해당 번호의 스터디가 없습니다.");
      return;
    }

    if (study.getOwner().getPerNo() != AuthPerMemberLoginHandler.getLoginUser().getPerNo()) {
      System.out.println("수정 권한이 없습니다.");
      return;
    }

    String studyTitle = Prompt.inputString(" 스터디명(" + study.getStudyTitle()  + ") : ");
    String face = Prompt.inputString(" 대면(" + study.getFace() + ")? ");
    String introduction = Prompt.inputString(" 소개글(" + study.getIntroduction() + ") : ");
    // 인원수 변경 넣을지 말지 > 현재 참여중인 인원보다는 적게 수정할수 없음
    System.out.println();
    String input = Prompt.inputString(" 정말 수정하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" 스터디 수정을 취소하였습니다.");
      return;
    }

    study.setStudyTitle(studyTitle);
    study.setFace(face);
    study.setIntroduction(introduction);

    System.out.println("스터디가 수정되었습니다.");
  }

}