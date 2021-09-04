package com.ogong.pms.handler2;

import java.util.List;
import com.ogong.pms.domain.Member;

public class MemberDetailHandler extends AbstractMemberHandler {

  List<Member> memberList;

  public MemberDetailHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 내 프로필");

    //    String inputEmail = Prompt.inputString("이메일 입력하세요 ");
    //    Member member = findByEmail(inputEmail);
    //    if (member == null) {
    //      System.out.println("해당 이메일의 회원이 없습니다.");
    //      return;
    //    }

    try {
      Member member = PerLoginHandler.getLoginUser();

      System.out.printf("닉네임 : %s\n", member.getPerNickname());
      System.out.printf("이메일 : %s\n", member.getPerEmail());
      System.out.printf("사진 : %s\n", member.getPerPhoto());
      System.out.printf("가입일 : %s\n", member.getPerRegisteredDate());
    } catch (NullPointerException e) {
      System.out.println("로그인 하세요.");
    }
  }
}







