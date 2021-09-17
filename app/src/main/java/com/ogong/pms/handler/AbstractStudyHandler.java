//  수정 중.....

package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Study;

public abstract class AbstractStudyHandler implements Command {

  List<Study> studyList;

  public AbstractStudyHandler(List<Study> studyList) {
    this.studyList = studyList;
  }

  protected void printStudyList() {
    for (Study study : studyList) {
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