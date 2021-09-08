package com.ogong.pms.handler;

public class AuthCeoMemberLogoutHandler implements Command {

  public void execute() {
    System.out.println();
    AuthCeoMemberLoginHandler.loginCeoMember = null;
    System.out.println("로그아웃 되었습니다.");
  }


}
