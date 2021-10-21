package com.ogong.pms.handler.myStudy;

import java.util.List;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;

public class MyStudyListHandler implements Command {

  private static final String Collection = null;

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

    // 참여중-----------------------------------------------------------------------
    // test용으로 setPerMyStudy에 new로 생성해서 값은 안 넣었지만
    // null값이 있으므로 사이즈로 비교해야 한다.
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

    System.out.println();

    // 구성원일때
    int joinCount = 0;
    System.out.println(" | 👥 구성원 | ");
    for (int i = 0; i < studyList.size(); i++) {
      for (Member mem : studyList.get(i).getMembers()) {
        if (mem.getPerNo() == member.getPerNo())
          System.out.printf(" (%s) [%s]\n", studyList.get(i).getStudyNo(), studyList.get(i).getStudyTitle());
        System.out.println();
        joinCount++;
      }
    }

    if(joinCount == 0) {
      System.out.println("  >> 구성원으로 참여 중인 스터디가 없습니다.\n");
      System.out.println();
    }

    // 승인 대기 중----------------------------------------------------------------------- 
    System.out.println(" ************** 승인 대기 중 ************** \n");

    int waitCount = 0;
    for (int i = 0; i < studyList.size(); i++) {
      for (Member mem : studyList.get(i).getWatingMember()) {
        if (mem.getPerNo() == member.getPerNo()) {
          System.out.printf(" (%s) [%s]\n", studyList.get(i).getStudyNo(),
              studyList.get(i).getStudyTitle());
          System.out.println();
          waitCount++;
        }
      }
    }

    if (waitCount == 0) {
      System.out.println("  >> 승인 대기 중인 스터디가 없습니다.\n");
    }
  }
}