package com.ogong.pms.handler;

public class AuthAdminLogoutHandler implements Command {

  public void execute() {
    System.out.println();
    AuthAdminLoginHandler.loginAdmin = null;
    System.out.println("로그아웃 되었습니다.");
  }


}
