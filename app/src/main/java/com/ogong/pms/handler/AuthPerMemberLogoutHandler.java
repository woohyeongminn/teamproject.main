package com.ogong.pms.handler;

import com.ogong.pms.domain.Member;

public class AuthPerMemberLogoutHandler implements Command {

  static Member loginUser;
  public static Member getLoginUser() {
    return loginUser;
  }

  @Override
  public void execute() {
    System.out.println();
    loginUser = null;
    System.out.println("로그아웃되었습니다.");
  }

}
