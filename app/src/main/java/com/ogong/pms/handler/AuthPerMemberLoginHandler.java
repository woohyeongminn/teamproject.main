package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class AuthPerMemberLoginHandler implements Command {

  PromptPerMember promptPerMember;
  List<Member> memberList;

  static Member loginUser;
  public static Member getLoginUser() {
    return loginUser;
  }

  public AuthPerMemberLoginHandler(PromptPerMember promptPerMember, List<Member> memberList) {
    this.promptPerMember = promptPerMember;
    this.memberList = memberList;
  }

  @Override
  public void execute() {

    System.out.println();
    String inputEmail = Prompt.inputString(" 이메일 : ");
    String inputPassword = "";
    Member member = promptPerMember.getMemberByPerEmail(inputEmail);

    if (member == null) {
      System.out.println(" >> 등록된 회원이 아닙니다.");
    }

    while (member != null) {
      inputPassword = Prompt.inputString(" 비밀번호 : ");

      if (member.getPerPassword().equals(inputPassword)) {
        member.setPerEmail(inputEmail);
        member.setPerPassword(inputPassword);
        System.out.println();
        System.out.printf(" >> '%s'님 환영합니다!\n", member.getPerNickname());
        loginUser = member;
        return;
      }
      System.out.println(" >> 비밀번호를 다시 입력하세요.\n");
      return;
    }
  } 
}
