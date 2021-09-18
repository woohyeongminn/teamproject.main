//  수정 중.....

package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Study;

public class StudyListHandler extends AbstractStudyHandler {


  public StudyListHandler(List<Study> newStudyList) {
    super(newStudyList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println();
    System.out.println("▶ 스터디 목록");
    System.out.println();

    printStudyList();

  }

  protected void printStudyList() {
    for (Study study : studyList) {
      for (int i = 0; i < studyList.size(); i++) {
        if (studyList.get(i).getOwner() == null) {
          System.out.println(" >> 탈퇴된 회원의 스터디입니다.");
        }
      }

      System.out.printf(" (%d)\n 스터디명 : %s\n 분류 : %s\n 인원수 : %s/%s명\n 조장 : %s\n 대면/비대면 : %s\n",
          study.getStudyNo(),
          study.getStudyTitle(),
          study.getSubject(),
          study.getMembers().size() + 1,
          study.getNumberOfPeple(),
          study.getOwner().getPerNickname(),
          study.getFace());
      System.out.println();
    }
  }
}