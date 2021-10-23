package com.ogong.pms.handler.myStudy;

import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class MyStudyDeleteHandler implements Command {

  StudyDao studyDao;

  public MyStudyDeleteHandler(StudyDao studyDao) {
    this.studyDao = studyDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 스터디 삭제");

    int no = (int) request.getAttribute("inputNo");
    Member member = AuthPerMemberLoginHandler.getLoginUser();
    Study myStudy = studyDao.findByNo(no);

    if (myStudy.getOwner().getPerNo() == member.getPerNo() &&
        myStudy.getMembers().size() > 0) {
      System.out.println(" >> 구성원이 있는 스터디는 삭제할 수 없습니다.");
      return;
    }

    if (myStudy.getOwner().getPerNo() != member.getPerNo()) {
      System.out.println(" >> 삭제 권한이 없습니다.");
      return;
    }

    String input = Prompt.inputString(" 정말 삭제하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 스터디 삭제를 취소하였습니다.");
      return;
    }

    studyDao.delete(myStudy.getStudyNo(), member.getPerNo());
    System.out.println(" >> 스터디가 삭제 되었습니다.");
  }
}