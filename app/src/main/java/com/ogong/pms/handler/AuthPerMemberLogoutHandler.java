package com.ogong.pms.handler;

public class AuthPerMemberLogoutHandler implements Command {

  @Override
  public void execute() {
    System.out.println();
    AuthPerMemberLoginHandler.loginUser = null;
    System.out.println("로그아웃 되었습니다.");
  }

}
