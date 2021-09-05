//  수정 중.....

package com.ogong.pms.handler;

import java.util.HashMap;
import java.util.List;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.util.Prompt;

public class MyStudyListHandler extends AbstractStudyHandler {

  HashMap<String, Command> commandMap;

  public MyStudyListHandler(List<Study> studyList, HashMap<String, Command> commandMap) {
    super(studyList);
    this.commandMap = commandMap;
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 스터디 목록");

    Member member = AuthPerMemberLoginHandler.getLoginUser();
    // 0905 실행안됨
    for (Study perStudy : member.getPerMyStudy()) {
      String obj = "";
      if(perStudy.getStudyTitle().equals(obj)) {
        System.out.println("가입한 스터디가 없습니다.");
      }
      System.out.println(perStudy.getStudyTitle());
    }
    //

    System.out.println("----------------------");
    System.out.println("1. 상세 보기");
    System.out.println("0. 뒤로가기");
    int selectNo = Prompt.inputInt("선택> ");

    switch (selectNo) {
      case 1 : commandMap.get("/myStudy/detail").execute(); break;
      case 2 : System.out.println("뒤로가기"); break;
      default : return;
    }
  }
}