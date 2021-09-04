//  수정 중.....

package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.util.Prompt;

public class MyStudyListHandler extends AbstractStudyHandler {

  public MyStudyListHandler(List<Study> myStudyList) {
    super(myStudyList);
  } 

  public void execute() {
    System.out.println();
    System.out.println("▶ 스터디 목록");

    Member member = AuthPerMemberLoginHandler.getLoginUser();
    for (Study study : member.getPerMyStudy()) {
      System.out.println(study.getStudyTitle());
    }

    System.out.println("----------------------");
    System.out.println("1. 상세 보기");
    System.out.println("0. 뒤로가기");
    int selectNo = Prompt.inputInt("선택> ");
    switch (selectNo) {
      case 1 : detail(); break;
      case 2 : System.out.println("뒤로가기"); break;
      default : return;

    }
  }

  //        System.out.printf("%d, %s, %s, %s, %s, %s\n",
  //            study.getStudyNo(),
  //            study.getSubject(),
  //            study.getNumberOfPeple(),
  //            study.getStudyTitle(),
  //            study.getOwner().getPerNickname(),
  //            study.getFace());

}
