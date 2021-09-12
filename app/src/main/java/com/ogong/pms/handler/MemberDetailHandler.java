package com.ogong.pms.handler;

import java.util.HashMap;
import java.util.List;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class MemberDetailHandler extends AbstractMemberHandler {

  HashMap<String, Command> commandMap;

  public MemberDetailHandler(List<Member> memberList, HashMap<String, Command> commandMap) {
    super(memberList);
    this.commandMap = commandMap;
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 프로필");

    try {
      Member member = AuthPerMemberLoginHandler.getLoginUser();
      System.out.println();
      System.out.printf(" [%s]\n", member.getPerNickname());
      System.out.printf(" >> 이메일 : %s\n", member.getPerEmail());
      System.out.printf(" >> 사  진 : %s\n", member.getPerPhoto());
      System.out.printf(" >> 가입일 : %s\n", member.getPerRegisteredDate());

    } catch (NullPointerException e) {
      System.out.println();
      System.out.println(" >> 로그인 하세요.");
    }

    System.out.println();
    System.out.println("1. 수정");
    System.out.println("0. 이전");
    int selectNo = Prompt.inputInt("선택> ");
    switch (selectNo) {
      case 1 : commandMap.get("/member/update").execute(); break;
      default : return;
    }
  }
}
