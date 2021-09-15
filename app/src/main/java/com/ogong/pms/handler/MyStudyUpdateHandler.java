
package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Study;
import com.ogong.util.Prompt;

public class MyStudyUpdateHandler extends AbstractStudyHandler {


  public MyStudyUpdateHandler(List<Study> newStudyList) {
    super(newStudyList);
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 스터디 수정");
    System.out.println();

    int inputNo = Prompt.inputInt(" 번호  : ");

    Study myStudy = findByMyStudyNo(inputNo);

    if (myStudy == null) {
      return;
    }

    if (myStudy.getOwner().getPerNo() != AuthPerMemberLoginHandler.getLoginUser().getPerNo()) {
      System.out.println(" >> 수정 권한이 없습니다.");
      return;
    }

    String studyTitle = Prompt.inputString(" 스터디명(" + myStudy.getStudyTitle()  + ") : ");
    String face = Prompt.inputString(" 대면(" + myStudy.getFace() + ")? ");
    String introduction = Prompt.inputString(" 소개글(" + myStudy.getIntroduction() + ") : ");
    // 인원수 변경 넣을지 말지 > 현재 참여중인 인원보다는 적게 수정할수 없음
    System.out.println();
    String input = Prompt.inputString(" 정말 수정하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 스터디 수정을 취소하였습니다.");
      return;
    }

    myStudy.setStudyTitle(studyTitle);
    myStudy.setFace(face);
    myStudy.setIntroduction(introduction);

    System.out.println(" >> 스터디가 수정되었습니다.");
  }

}