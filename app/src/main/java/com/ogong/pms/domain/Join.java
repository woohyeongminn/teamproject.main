package com.ogong.pms.domain;

public class Join {
  private String joinNickname;   // 회원가입 닉네임
  private String joinEmail;     // 회원가입 이메일     
  private String joinPassword;  // 회원가입 비밀번호
  private String joinPhoto;     // 회원가입 사진

  public String getJoinNickname() {
    return joinNickname;
  }
  public void setJoinNickname(String joinNickname) {
    this.joinNickname = joinNickname;
  }
  public String getJoinEmail() {
    return joinEmail;
  }
  public void setJoinEmail(String joinEmail) {
    this.joinEmail = joinEmail;
  }
  public String getJoinPassword() {
    return joinPassword;
  }
  public void setJoinPassword(String joinPassword) {
    this.joinPassword = joinPassword;
  }
  public String getJoinPhoto() {
    return joinPhoto;
  }
  public void setJoinPhoto(String joinPhoto) {
    this.joinPhoto = joinPhoto;
  }
  public Object getPerRegisteredDate() {
    return null;
  }
  public String getPerNo() {
    // TODO Auto-generated method stub
    return null;
  }
}
