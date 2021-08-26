package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Login;

public class LoginHandler {

  List<Login> loginList;
  MemberHandler memberHandler;

  public LoginHandler(List<Login> loginList, MemberHandler memberHandler) {
    this.loginList = loginList;
    this.memberHandler = memberHandler;
  }

  public void addLoginPage() {


  }

  //    Login login = new Login();
  //    login.setUserEmail(memberHandler.promptMember("이메일: "));
  //
  //    if (login.getUserEmail() == null) {
  //      return;
  //    } else if (login.getUserEmail()) {
  //      System.out.println("로그인되었습니다");
  //    }


}
