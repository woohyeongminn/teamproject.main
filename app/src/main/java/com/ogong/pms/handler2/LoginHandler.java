package com.ogong.pms.handler2;

import java.util.List;
import com.ogong.menu.MenuGroup;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class LoginHandler {

  List<Member> memberList;
  MemberHandler memberHandler;

  static Member loginUser;
  public static Member getLoginUser() {
    return loginUser;
  }


  public LoginHandler(List<Member> memberList, MemberHandler memberHandler) {
    this.memberList = memberList;
    this.memberHandler = memberHandler;
  }

  public void addLoginPage() {
    System.out.println();
    String inputEmail = Prompt.inputString("이메일 : ");
    String inputPassword = "";
    Member member = memberHandler.findByInputEmail(inputEmail);
    if (member == null) {
      System.out.println("등록된 회원이 아닙니다.");
    }
    while (member != null) {
      inputPassword = Prompt.inputString("비밀번호 : ");
      if (member.getPerPassword().equals(inputPassword)) {
        member.setPerEmail(inputEmail);
        member.setPerPassword(inputPassword);
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
        memberHandler.findPw();
      }
    } 
  }

  public void logOut() {
    System.out.println();
    loginUser = null;
    System.out.println("로그아웃 되었습니다.");
  }

  public void naverLogin() {
    System.out.println("NAVER 로그인");
  }
  public void kakaoLogin() {
    System.out.println("KAKAO 로그인");
  }
  public void googleLogin() {
    System.out.println("GOOGLE 로그인");
  }

}
