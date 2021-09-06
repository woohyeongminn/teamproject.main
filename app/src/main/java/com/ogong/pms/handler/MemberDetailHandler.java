package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Member;

public class MemberDetailHandler extends AbstractMemberHandler {

  public MemberDetailHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 프로필");

    try {
      Member member = AuthPerMemberLoginHandler.getLoginUser();

      System.out.printf("닉네임 : %s\n", member.getPerNickname());
      System.out.printf("이메일 : %s\n", member.getPerEmail());
      System.out.printf("사진 : %s\n", member.getPerPhoto());
      System.out.printf("가입일 : %s\n", member.getPerRegisteredDate());

    } catch (NullPointerException e) {
      System.out.println("로그인 하세요.");
    }
  }
}
