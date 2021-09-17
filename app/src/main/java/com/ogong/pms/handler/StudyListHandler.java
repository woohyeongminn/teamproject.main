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

    //    System.out.println("----------------------");
    //    System.out.println("1. 상세");
    //    System.out.println("2. 검색");
    //    System.out.println("0. 이전");
    //    int selectNo = Prompt.inputInt("선택> ");
    //    switch (selectNo) {
    //      case 1 : detail(); break;
    //      case 2 : search(); break;
    //      case 3 : System.out.println("이전"); break;
    //      default : return;
    //    }
  }
}