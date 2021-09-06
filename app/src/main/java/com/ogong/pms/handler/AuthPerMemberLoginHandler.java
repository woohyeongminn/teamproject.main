package com.ogong.pms.handler;

import com.ogong.menu.MenuGroup;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class AuthPerMemberLoginHandler implements Command {

  PromptPerMember promptPerMember;

  static Member loginUser;
  public static Member getLoginUser() {
    return loginUser;
  }

  public AuthPerMemberLoginHandler(PromptPerMember promptPerMember) {
    this.promptPerMember = promptPerMember;
  }

  @Override
  public void execute() {

    System.out.println();
    System.out.println("1. 로그인");
    System.out.println("2. NAVER로 시작하기");
    System.out.println("3. KAKAO로 시작하기");
    System.out.println("4. GOOGLE로 시작하기");
    System.out.println("0. 뒤로가기");
    int selectLogin = Prompt.inputInt("선택> ");
    switch (selectLogin) {
      case 1 : siteLogin(); break;
      case 2 : siteLogin(); break;
      case 3 : siteLogin(); break;
      case 4 : siteLogin(); break;
      default : return;
    }
  }

  private void siteLogin() {
    System.out.println();
    String inputEmail = Prompt.inputString("이메일 : ");
    String inputPassword = "";
    Member member = promptPerMember.getMemberByPerEmail(inputEmail);
    if (member == null) {
      System.out.println("등록된 회원이 아닙니다.");
    }
    while (member != null) {
      inputPassword = Prompt.inputString("비밀번호 : ");
      if (member.getPerPassword().equals(inputPassword)) {
        member.setPerEmail(inputEmail);
        member.setPerPassword(inputPassword);
        System.out.println();
        System.out.println("로그인되었습니다.");
        loginUser = member;
        MenuGroup.successLogin = true;
        return;
      }
      String input = Prompt.inputString("비밀번호를 잊어버렸나요? (네 / 아니오) ");
      if (input.equalsIgnoreCase("아니오") || input.length() == 0) {
        System.out.println("비밀번호를 다시 입력하세요.");
        continue;
      } else {
        promptPerMember.findByPerPw();
      }
    } 
  }
  //
  //  private void naverLogin() {
  //    System.out.println("NAVER 로그인");
  //  }
  //  private void kakaoLogin() {
  //    System.out.println("KAKAO 로그인");
  //  }
  //  private void googleLogin() {
  //    System.out.println("GOOGLE 로그인");
  //  }

}
