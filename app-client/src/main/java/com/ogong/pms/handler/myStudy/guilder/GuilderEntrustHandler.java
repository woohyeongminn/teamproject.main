package com.ogong.pms.handler.myStudy.guilder;

import java.util.List;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.dao.StudyGuilderDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class GuilderEntrustHandler implements Command { 

  StudyDao studyDao;
  StudyGuilderDao studyGuilderDao;


  public GuilderEntrustHandler(StudyDao studyDao, StudyGuilderDao studyGuilderDao) {
    this.studyDao = studyDao;
    this.studyGuilderDao = studyGuilderDao;
  }


  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 조장 권한 위임");
    System.out.println();

    int inputNo = (int) request.getAttribute("inputNo");

    Member member = AuthPerMemberLoginHandler.getLoginUser();

    Study myStudy = studyDao.findByNo(inputNo);

    List<Member> guilders = myStudy.getMembers();

    if (myStudy.getMembers().isEmpty()) {
      System.out.println(" >> 해당 스터디 구성원이 없습니다.");
      return;
    }

    for (int i = 0; i < myStudy.getMembers().size(); i++) {
      System.out.println(" 구성원 : " + myStudy.getMembers().get(i).getPerNickname());
    }
    System.out.println("----------------------");
    System.out.println();

    if (guilders != null) {
      String inputGuilderNick = Prompt.inputString(" >> 조장 권한을 위임해 줄 구성원을 선택하세요 : ");
      //Member entrustList = new Member();

      for (Member entrustGuilder : guilders) {

        if (entrustGuilder.getPerNickname().equals(inputGuilderNick)) {
          System.out.println();
          System.out.printf(" '%s'님에게 조장 권한을 위임하시겠습니까?", entrustGuilder.getPerNickname());
          String input = Prompt.inputString(" (네 / 아니오) ");

          if (!input.equalsIgnoreCase("네")) {
            System.out.println();
            System.out.println(" >> 다시 진행해 주세요.");
            return;

          } else if (!entrustGuilder.getPerNickname().equals(inputGuilderNick)) {
            System.out.println();
            System.out.println(" >> 구성원의 닉네임을 다시 입력하세요.");
            break;
          }

          System.out.printf(" >> '%s'님이 조장이 되셨습니다.", inputGuilderNick);
          System.out.println();

          //          if (entrustGuilder != null) {
          //            myStudy.getMembers().remove(entrustGuilder);
          //          }
          //          myStudy.getMembers().remove(entrustGuilder); // 추가

          System.out.println();
          String inputGuilder = Prompt.inputString(
              " >> 구성원으로 다시 돌아가시겠습니까? (네 / 아니오) ");

          if (!inputGuilder.equalsIgnoreCase("네")) {
            studyGuilderDao.updateOwner(myStudy.getStudyNo(), entrustGuilder.getPerNo());
            System.out.println();
            System.out.println(" >> 해당 스터디에서 탈퇴되었습니다.");
            return;
          }
          // 구성원에서 조장이 됐기 때문에
          studyGuilderDao.updateOwner(myStudy.getStudyNo(), entrustGuilder.getPerNo());
          //studyDao.deleteGuilder(myStudy.getStudyNo(), entrustGuilder.getPerNo());
          studyGuilderDao.insertGuilder(myStudy.getStudyNo(), member.getPerNo());
          studyGuilderDao.updateGuilder(myStudy.getStudyNo(), member.getPerNo());
          System.out.println();
          System.out.println(" >> 구성원이 되셨습니다.");
        }
      }

      return;
    }
  }
}
