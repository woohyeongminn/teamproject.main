//  수정 중.....

package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Study;
import com.ogong.util.Prompt;

public class StudyDeleteHandler extends AbstractStudyHandler {


  public StudyDeleteHandler(List<Study> newStudyList) {
    super(newStudyList);
  }

  public void execute() {
    System.out.println();
    System.out.println("▶ 스터디 삭제");

    String inputTitle = Prompt.inputString("제목  : ");

    Study study = findByTitle(inputTitle);

    if (study == null) {
      System.out.println("해당 제목의 스터디가 없습니다.");
      return;
    }

    if (study.getOwner().getPerNo() != AuthPerMemberLoginHandler.getLoginUser().getPerNo()) {
      System.out.println("삭제 권한이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까? (네 / 아니오) ");
    if (input.equalsIgnoreCase("아니오") || input.length() == 0) {
      System.out.println("스터디 삭제를 취소하였습니다.");
      return;
    }

    studyList.remove(study);

    System.out.println("스터디를 삭제하였습니다.");
  }

}