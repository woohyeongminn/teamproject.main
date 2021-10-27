package com.ogong.pms.servlet;

public abstract class AbstractLoginHandler {

  public static final int LOGOUT = 0x01; //유저 로그아웃인 경우
  public static final int PER_LOGIN = 0x02;  // 개인 로그인인 경우
  public static final int CEO_LOGIN = 0x04;  // 기업회원 로그인인 경우
  public static final int ADMIN_LOGIN = 0x08;  // 관리자 로그인인 경우

  public static int accessLevel = LOGOUT;

  public static int getUserAccessLevel() {
    return accessLevel;
  }
}
