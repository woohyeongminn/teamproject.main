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
    System.out.println();

    Member member = AuthPerMemberLoginHandler.getLoginUser();
    if (member == null ) {
      System.out.println(" >> 로그인 한 회원만 조회 가능합니다.");
      return;
    }

    // test용으로 setPerMyStudy에 new로 생성해서
    // 값은 안 넣었지만 null값이 있으므로 사이즈로 비교해야 한다.

    if(member.getPerMyStudy().size() == 0) {
      System.out.println(" >> 가입한 스터디가 없습니다.");
      return;
    }

    for (Study perStudy : member.getPerMyStudy()) {
      System.out.printf(" (%s)\n [%s]\n", perStudy.getStudyNo(), perStudy.getStudyTitle());
      System.out.println();

    }

    System.out.println("----------------------");
    System.out.println("1. 상세");
    System.out.println("0. 이전");
    int selectNo = Prompt.inputInt("선택> ");

    switch (selectNo) {
      case 1 : commandMap.get("/myStudy/detail").execute(); break;
      case 2 : System.out.println(" 이전"); break;
      default : return;
    }
  }
}