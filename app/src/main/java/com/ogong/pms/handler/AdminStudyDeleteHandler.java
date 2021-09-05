package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Admin;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.util.Prompt;

public class AdminStudyDeleteHandler extends AbstractStudyHandler {

  public AdminStudyDeleteHandler(List<Study> newStudyList) {
    super(newStudyList);
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 스터디 삭제");

    String inputTitle = Prompt.inputString("제목  : ");

    Study study = findByTitle(inputTitle);

    if (study == null) {
      System.out.println("해당 제목의 스터디가 없습니다.");
      return;
    }

    //    if (study.getOwner().getPerNo() != AuthPerMemberLoginHandler.getLoginUser().getPerNo()) {
    //      System.out.println("삭제 권한이 없습니다.");
    //      return;
    //    }

    if (study.getOwner().getPerNickname() != AuthAdminLoginHandler.getLoginAdmin().getMasterNickname()) {

      String input = Prompt.inputString("정말 삭제하시겠습니까? (네 / 아니오) ");
      if (!input.equalsIgnoreCase("네")) {
        System.out.println("스터디 삭제를 취소하였습니다.");
        return;
      }
    }

    Admin admin = AuthAdminLoginHandler.getLoginAdmin();
    Member member = AuthPerMemberLoginHandler.getLoginUser();

    studyList.remove(study);
    //    member.getPerMyStudy().remove(study);

    System.out.println("스터디를 삭제하였습니다.");
  }
}