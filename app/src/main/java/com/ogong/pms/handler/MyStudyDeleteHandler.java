package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.util.Prompt;

public class MyStudyDeleteHandler extends AbstractStudyHandler {

  public MyStudyDeleteHandler(List<Study> newStudyList) {
    super(newStudyList);
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 스터디 삭제");

    int inputNo = Prompt.inputInt("번호  : ");

    Study study = findByNo(inputNo);

    if (study == null) {
      System.out.println("해당 번호의 스터디가 없습니다.");
      return;
    }

    if (study.getOwner().getPerNo() != AuthPerMemberLoginHandler.getLoginUser().getPerNo()) {
      System.out.println("삭제 권한이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println("스터디 삭제를 취소하였습니다.");
      return;
    }

    Member member = AuthPerMemberLoginHandler.getLoginUser();

    studyList.remove(study);
    member.getPerMyStudy().remove(study);

    System.out.println("스터디를 삭제하였습니다.");
  }
}