package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class MemberUpdateHandler extends AbstractMemberHandler {

  public MemberUpdateHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 프로필 수정");

    try {
      Member member = AuthPerMemberLoginHandler.getLoginUser();

      String perNickName = Prompt.inputString("닉네임(" + member.getPerNickname()  + ") : ");
      String perEmail = Prompt.inputString("이메일(" + member.getPerEmail() + ") : ");
      String perPassword = Prompt.inputString("암호(" + member.getPerPassword() + ") : ");
      String perPhoto = Prompt.inputString("사진(" + member.getPerPhoto() + ") : ");

      System.out.println();
      String input = Prompt.inputString("정말 변경하시겠습니까? (네 / 아니오) ");
      if (!input.equalsIgnoreCase("네")) {
        System.out.println("개인회원 변경을 취소하였습니다.");
        return;
      }
      member.setPerNickname(perNickName);
      member.setPerEmail(perEmail);
      member.setPerPassword(perPassword);
      member.setPerPhoto(perPhoto);

      System.out.println("개인회원 정보를 변경하였습니다.");

    } catch (NullPointerException e) {
      System.out.println("로그인 하세요.");
    }
  }
}
