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
}