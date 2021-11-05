package com.ogong.pms.servlet.myStudy.guilder;

import java.util.List;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class GuilderListHandler implements Command {

  StudyDao studyDao;

  public GuilderListHandler(StudyDao studyDao) {
    this.studyDao = studyDao;
  }

  // 스터디 구성원 목록
  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 구성원");
    System.out.println();

    int inputNo = (int) request.getAttribute("inputNo");

    Study myStudy = studyDao.findByNo(inputNo);

    // 해당 스터디에 구성원 목록 가져오기
    //    List<Guilder> guilderList = studyDao.findByGuilderAll(myStudy.getStudyNo());
    //
    //    for (Guilder guilder : guilderList) {
    //      if (guilder.getGuilderStatus() == 2) {
    //        myStudy.getMembers().add(guilder.getMember());
    //
    //      } else if (guilder.getGuilderStatus() == 1) {
    //        myStudy.getWatingMember().add(guilder.getMember());
    //      }
    //    }

    List<Member> waitingGuilder = studyDao.findByWaitingGuilderAll(myStudy.getStudyNo());
    myStudy.setWatingMember(waitingGuilder);

    List<Member> guilders = studyDao.findByGuildersAll(myStudy.getStudyNo());
    myStudy.setMembers(guilders);

    for (Member m : guilders) {
      if (myStudy.getOwner().getPerNo() == m.getPerNo()) {
        myStudy.getMembers().remove(m);
        break;
      }
    }

    System.out.printf(" >> 스터디 구성원 (%s/%s명)\n" , myStudy.getMembers().size() + 1,
        myStudy.getNumberOfPeple());
    System.out.println(" 👤 조  장 : " + myStudy.getOwner().getPerNickname());
    System.out.println(" 👥 구성원 : " + myStudy.getMemberNames());

    // 조장만 보임
    if (AuthPerMemberLoginHandler.getLoginUser().getPerNo() !=
        myStudy.getOwner().getPerNo()) {
      return;
    }

    if(!myStudy.getWatingMemberNames().isEmpty()) {
      System.out.printf("\n ★ > 승인 대기 중인 회원이 %d명 있습니다.", myStudy.getWatingMember().size());

    } else if(myStudy.getWatingMemberNames().isEmpty()) {
      System.out.println("\n ☆ > 승인 대기 중인 회원이 없습니다.");
    }

    request.setAttribute("inputNo", myStudy.getStudyNo());

    System.out.println("\n----------------------");
    System.out.println();
    System.out.println("1. 승인 대기 목록");
    System.out.println("2. 조장 권한 위임");
    System.out.println("3. 구성원 탈퇴시키기");
    System.out.println("0. 뒤로 돌아가기");
    System.out.println();

    int inputGuilerNo = Prompt.inputInt("선택> ");
    switch (inputGuilerNo) {
      case 1: request.getRequestDispatcher("/myStudy/watingGuilderList").forward(request); return;
      case 2: request.getRequestDispatcher("/myStudy/guilderEntrust").forward(request); return;
      case 3: request.getRequestDispatcher("/myStudy/guilderDelete").forward(request); return;
      default: return;
    }
  }

}
