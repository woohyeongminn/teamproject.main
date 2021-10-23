package com.ogong.pms.handler.myStudy;

import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class MyStudyExitHandler implements Command {

  StudyDao studyDao;

  public MyStudyExitHandler(StudyDao studyDao) {
    this.studyDao = studyDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 탈퇴하기");
    System.out.println();
    int inputNo = (int) request.getAttribute("inputNo");

    Member member = AuthPerMemberLoginHandler.getLoginUser();
    Study myStudy = studyDao.findByNo(inputNo);

    if (myStudy.getOwner().getPerNo() == member.getPerNo() &&
        myStudy.getMembers().size() > 0) {
      System.out.println(" >> 구성원에게 조장 권한을 위임하고 탈퇴를 진행해 주세요.");
      return;
    }

    int count = 0;
    for (Member m : myStudy.getMembers()) {
      if (m.getPerNo() == 
          AuthPerMemberLoginHandler.getLoginUser().getPerNo()) {
        count++;
      }
    }

    if (count != 0) {
      String input = Prompt.inputString(" 정말 탈퇴하시겠습니까?(네 / 아니오)");
      if (!input.equals("네")) {
        System.out.println(" >> 탈퇴를 취소하였습니다.");
        return;
      }
      studyDao.deleteGuilder(myStudy.getStudyNo(), member.getPerNo());
      System.out.println(" >> 탈퇴되었습니다.");
    }
    else if (myStudy.getOwner().getPerNo() == member.getPerNo() && 
        myStudy.getMembers().size() == 0) {

      String input = Prompt.inputString(" 정말 탈퇴하시겠습니까?(네 / 아니오)");
      if (!input.equals("네")) {
        System.out.println(" >> 탈퇴를 취소하였습니다.");
        return;
      }

      studyDao.delete(myStudy.getStudyNo(), member.getPerNo());

      System.out.println(" >> 스터디가 삭제 되었습니다.");
    }
  }

}
