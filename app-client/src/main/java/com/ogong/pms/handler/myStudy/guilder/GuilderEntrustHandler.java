package com.ogong.pms.handler.myStudy.guilder;

import java.util.List;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class GuilderEntrustHandler implements Command { 

  StudyDao studyDao;

  public GuilderEntrustHandler(StudyDao studyDao) {
    this.studyDao = studyDao;
  }


  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 조장 권한 위임");
    System.out.println();

    int inputNo = (int) request.getAttribute("inputNo");

    Member member = AuthPerMemberLoginHandler.getLoginUser();

    Study myStudy = studyDao.findByNo(inputNo);

    List<Member> guilder = studyDao.findByGuildersAll(myStudy.getStudyNo());
    myStudy.setMembers(guilder);

    List<Member> guilders = myStudy.getMembers();

    if (guilders.isEmpty()) {
      System.out.println(" >> 해당 스터디 구성원이 없습니다.");
      return;
    }

    for (int i = 0; i < guilders.size(); i++) {
      System.out.println(" 구성원 : " + guilders.get(i).getPerNickname());
    }

    System.out.println("----------------------");
    System.out.println();

    if (guilders != null) {
      String inputGuilderNick = Prompt.inputString(" >> 조장 권한을 위임해 줄 구성원을 선택하세요 : ");

      for (Member entrustGuilder : guilders) {

        if (!entrustGuilder.getPerNickname().equals(inputGuilderNick)) {
          System.out.println();
          System.out.println(" >> 구성원의 닉네임을 다시 입력하세요.");
          break;
        }

        System.out.println();
        System.out.printf(" '%s'님에게 조장 권한을 위임하시겠습니까?", entrustGuilder.getPerNickname());
        String input = Prompt.inputString(" (네 / 아니오) ");

        if (!input.equalsIgnoreCase("네")) {
          System.out.println();
          System.out.println(" >> 다시 진행해 주세요.");
          return;
        } 

        System.out.printf(" >> '%s'님이 조장이 되었습니다.", inputGuilderNick);
        System.out.println();

        System.out.println();
        String inputGuilder = Prompt.inputString(
            " >> 구성원으로 다시 돌아가시겠습니까? (네 / 아니오) ");

        if (!inputGuilder.equalsIgnoreCase("네")) {
          // 조장 위임 후 기존 길더는 길더에서 삭제되고 조장자리로 들어간다.
          // 조장은 길더에 추가되지 않고 스터디에서 탈퇴된다.
          studyDao.deleteGuilder(myStudy.getStudyNo(), entrustGuilder.getPerNo());
          studyDao.updateOwner(myStudy.getStudyNo(), entrustGuilder.getPerNo());
          System.out.println();
          System.out.println(" >> 해당 스터디에서 탈퇴되었습니다.");
          return;
        }

        studyDao.updateOwner(myStudy.getStudyNo(), entrustGuilder.getPerNo());      // 조장 바꿔주고
        studyDao.insertGuilder(myStudy.getStudyNo(), member.getPerNo());            // 기존 조장을 구성원에 넣고
        studyDao.updateGuilder(myStudy.getStudyNo(), member.getPerNo());            // 승인여부를 승인으로 바로 바꿔준다
        System.out.println();
        System.out.println(" >> 구성원이 되었습니다.");
      }
      return;
    }
  }
}
