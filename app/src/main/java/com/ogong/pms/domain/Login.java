package com.ogong.pms.domain;

public class Login {

  private String userEmail;    // 로그인 이메일
  private String userPassword; // 로그인 비밀번호

  public String getUserEmail() {
    return userEmail;
  }
  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }
  public String getUserPassword() {
    return userPassword;
  }
  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }
}
