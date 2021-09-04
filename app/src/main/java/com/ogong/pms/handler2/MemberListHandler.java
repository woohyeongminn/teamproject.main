package com.ogong.pms.handler2;

import java.util.List;
import com.ogong.pms.domain.Member;

public class MemberListHandler extends AbstractMemberHandler {

  List<Member> memberList;

  public MemberListHandler(List<Member> memberList) {
    super(memberList);

  }

  // 관리자
  public void list() {
    System.out.println();
    System.out.println("▶ 회원 가입 확인");

    for (Member member : memberList) {
      System.out.printf("닉네임 : %s\n 이메일 : %s\n 가입일 : %s\n",
          member.getPerNickname(), 
          member.getPerEmail(),
          member.getPerRegisteredDate());
    }
  }
}







