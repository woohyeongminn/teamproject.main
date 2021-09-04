package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Member;

public class MemberListHandler extends AbstractMemberHandler {

  public MemberListHandler(List<Member> memberList) {
    super(memberList);

  }

  // 관리자
  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 회원 목록");
    System.out.println();

    for (Member member : memberList) {
      System.out.printf("닉네임 : %s\n 이메일 : %s\n 가입일 : %s\n",
          member.getPerNickname(), 
          member.getPerEmail(),
          member.getPerRegisteredDate());
      System.out.println();
    }
  }
}







