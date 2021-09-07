package com.ogong.pms.handler;

import java.util.HashMap;
import java.util.List;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class MemberListHandler extends AbstractMemberHandler {

  HashMap<String, Command> commandMap;


  public MemberListHandler(List<Member> memberList, HashMap<String, Command> commandMap) {
    super(memberList);
    this.commandMap = commandMap;
  }

  // 관리자
  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 회원 목록");
    System.out.println();

    int no = 1;

    for (Member member : memberList) {
      System.out.printf(" (%d)\n 닉네임 : %s\n 이메일 : %s\n 가입일 : %s\n",
          no++,
          member.getPerNickname(), 
          member.getPerEmail(),
          member.getPerRegisteredDate());
      System.out.println();
    }
    selectUserModifyPage();
  }

  private void selectUserModifyPage() {
    System.out.println();
    System.out.println("1. 상세보기");
    System.out.println("2. 수정하기");
    System.out.println("3. 탈퇴시키기");
    System.out.println("0. 뒤로가기");

    int selectAdminNo = Prompt.inputInt("선택> ");
    switch (selectAdminNo) {
      case 1: commandMap.get("/adminMember/detail").execute(); break;
      case 2: commandMap.get("/adminMember/update").execute(); break;
      case 3: commandMap.get("/adminMember/delete").execute(); break;
      default : return;
    }
  }
}







