package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Login;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class LoginHandler {

  List<Login> loginList;
  MemberHandler memberHandler;

  public LoginHandler(List<Login> loginList, MemberHandler memberHandler) {
    this.loginList = loginList;
    this.memberHandler = memberHandler;
  }

  public void addLoginPage() {
    String inputEmail = Prompt.inputString("이메일: ");
    Member member = memberHandler.findByEmail(inputEmail);
    if (member == null) {
      System.out.println("등록된 회원이 아닙니다.");
      return;
    }
    while (member != null) {
      String inputPassword = Prompt.inputString("비밀번호: ");
      if (member.getPerPassword().equals(inputPassword)) {
        System.out.println("로그인되었습니다.");
        break;
      }
      System.out.println("비밀번호를 다시 입력하세요.");
      continue;
    }
  }


}
