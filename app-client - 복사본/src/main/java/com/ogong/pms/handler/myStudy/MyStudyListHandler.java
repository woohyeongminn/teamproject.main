package com.ogong.pms.handler.myStudy;

import java.util.List;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;

public class MyStudyListHandler implements Command {

  StudyDao studyDao;

  public MyStudyListHandler(StudyDao studyDao) {
    this.studyDao = studyDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 스터디 목록");
    System.out.println();

    Member member = AuthPerMemberLoginHandler.getLoginUser();
    if (member == null ) {
      System.out.println(" >> 로그인 한 회원만 조회 가능합니다.");
      return;
    }

    List<Study> studyList = studyDao.findAll();
    //List<Guilder> guilderList = studyDao.findByGuilderMyAll(member.getPerNo());

    System.out.println(" ************** 내 스터디 ************** \n");

    //조장일때
    int ownerCount = 0;
    System.out.println(" | 👤 조장 | ");
    for (Study study : studyList) {
      if (study.getOwner().getPerNo() == member.getPerNo()) {
        System.out.printf(" (%s) [%s]\n", study.getStudyNo(), study.getStudyTitle());
        System.out.println();
        ownerCount++;
      }
    }

    if(ownerCount == 0) {
      System.out.println("  >> 조장으로 참여 중인 스터디가 없습니다.\n");
      System.out.println();
    }
    //구성원일때 / 수정 전
    int joinCount = 0;
    System.out.println(" | 👥 구성원 | ");
    for (int i = 0; i < studyList.size(); i++) {
      List<Member> guilders = studyDao.findByGuildersAll(studyList.get(i).getStudyNo());
      studyList.get(i).setMembers(guilders);
      for (Member mem : studyList.get(i).getMembers())
        if (mem.getPerNo() == member.getPerNo()) {
          if (studyList.get(i).getOwner().getPerNo()!=member.getPerNo()) {
            System.out.printf(" (%s) [%s]\n", studyList.get(i).getStudyNo(), studyList.get(i).getStudyTitle());
            System.out.println();
            joinCount++;
          }
        }
    }


    // 구성원일때
    //    int joinCount = 0;
    //    System.out.println(" | 👥 구성원 | ");
    //    for (int i = 0; i < studyList.size(); i++) {
    //      for (int j = 0; j < guilderList.size(); j++) {
    //        if (studyList.get(i).getStudyNo() == guilderList.get(j).getStudyNo()) {
    //          if (guilderList.get(j).getGuilderStatus() == 2) {
    //            studyList.get(i).getMembers().add(guilderList.get(j).getMember());
    //            System.out.printf(" (%s) [%s]\n", studyList.get(i).getStudyNo(), studyList.get(i).getStudyTitle());
    //            System.out.println();
    //            joinCount++;
    //          }
    //        }
    //      }
    //    }

    if(joinCount == 0) {
      System.out.println("  >> 구성원으로 참여 중인 스터디가 없습니다.\n");
      System.out.println();
    }

    // 승인 대기 중----------------------------------------------------------------------- 
    System.out.println(" ************** 승인 대기 중 ************** \n");

    // 수정 전
    int waitCount = 0;
    for (int i = 0; i < studyList.size(); i++) {
      List<Member> waitingGuilders = studyDao.findByWaitingGuilderAll(studyList.get(i).getStudyNo());
      studyList.get(i).setWatingMember(waitingGuilders);
      for (Member mem :  studyList.get(i).getWatingMember()) {
        if (mem.getPerNo() == member.getPerNo()) {
          System.out.printf(" (%s) [%s]\n", studyList.get(i).getStudyNo(),
              studyList.get(i).getStudyTitle());
          System.out.println();
          waitCount++;
        }
      }
    }

    //    int waitCount = 0;
    //    for (int i = 0; i < studyList.size(); i++) {
    //      for (int j = 0; j < guilderList.size(); j++) {
    //        if (studyList.get(i).getStudyNo() == guilderList.get(j).getStudyNo()) {
    //          if (guilderList.get(j).getGuilderStatus() == 1) {
    //            studyList.get(i).getWatingMember().add(guilderList.get(j).getMember());
    //            System.out.printf(" (%s) [%s]\n", studyList.get(i).getStudyNo(), studyList.get(i).getStudyTitle());
    //            System.out.println();
    //            waitCount++;
    //          }
    //        }
    //      }
    //    }

    if (waitCount == 0) {
      System.out.println("  >> 승인 대기 중인 스터디가 없습니다.\n");
    }
  }
}