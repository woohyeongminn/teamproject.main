package com.ogong.pms.handler.myStudy.guilder;

import java.util.List;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Guilder;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class GuilderDeleteHandler implements Command {

  StudyDao studyDao;

  public GuilderDeleteHandler(StudyDao studyDao) {
    this.studyDao = studyDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 구성원 탈퇴시키기");
    System.out.println();

    int inputNo = (int) request.getAttribute("inputNo");

    Study myStudy = studyDao.findByNo(inputNo);

    // 해당 스터디에 구성원 목록 가져오기
    List<Guilder> guilderAll = studyDao.findByGuilderAll(myStudy.getStudyNo());
    for (Guilder guilder : guilderAll) {
      if (guilder.getGuilderStatus() == 2) {
        myStudy.getMembers().add(guilder.getMember());

      } else if (guilder.getGuilderStatus() == 1) {
        myStudy.getWatingMember().add(guilder.getMember());
      }
    }

    List<Member> guilderList = myStudy.getMembers();

    if (myStudy.getMembers().isEmpty()) {
      System.out.println(" >> 탈퇴시킬 구성원이 없습니다.");
      return;
    }

    if (!myStudy.getMemberNames().equals("")) {
      String inputGuilderName = Prompt.inputString(" >> 탈퇴시킬 구성원의 닉네임을 입력하세요 : ");

      for (Member guilderMember : guilderList) { 

        if (!guilderMember.getPerNickname().equals(inputGuilderName)) {
          System.out.println("\n >> 해당 구성원이 존재하지 않습니다.");
          return;
        } 
        System.out.println();
        String input = Prompt.inputString(" 정말 탈퇴시키겠습니까? (네 / 아니오) ");

        if (!input.equalsIgnoreCase("네")) {
          System.out.println("\n >> 구성원 탈퇴를 취소하였습니다.");
          return;
        }
        studyDao.deleteGuilder(myStudy.getStudyNo(), guilderMember.getPerNo());
        System.out.println("\n >> 구성원이 탈퇴되었습니다.");
        break;
      }
      return;
    }
  }
}