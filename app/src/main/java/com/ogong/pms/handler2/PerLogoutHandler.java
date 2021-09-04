package com.ogong.pms.handler2;

import com.ogong.pms.domain.Member;

public class PerLogoutHandler implements Command {

  static Member loginUser;
  public static Member getLoginUser() {
    return loginUser;
  }

  @Override
  public void execute() {
    System.out.println();
    loginUser = null;
    System.out.println("로그아웃 되었습니다.");
  }

}
