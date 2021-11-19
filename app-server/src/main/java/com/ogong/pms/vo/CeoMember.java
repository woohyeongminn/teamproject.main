package com.ogong.pms.vo;

import java.sql.Date;

public class CeoMember {

  public static final int INUSER = 1;      // 일반 유저
  public static final int OUTUSER = 2;     // 탈퇴 유저

  public static final int PER = 1;   // 개인유저
  public static final int CEO = 2;   // 사장유저

  private int ceoNo;                // 사장회원 고유번호 
  private String ceoName;           // 사장회원 이름
  private String ceoNickname;       // 사장회원 닉네임
  private String ceoEmail;          // 사장회원 이메일 - 회원가입, 로그인 할 때 필요
  private String ceoPassword;       // 사장회원 패스워드
  private String ceoTel;            // 사장회원 전화번호
  private String ceoPhoto;          // 사장회원 사진
  private Date ceoRegisteredDate;   // 사장회원 가입일
  private int ceoStatus;            // 1: 개인, 2: 사장
  private int active;               // 1: 일반, 2: 탈퇴
  private String ceoBossName;       // 사장회원 대표자명
  private String ceoLicenseNo;      // 사장회원 사업자등록번호

  @Override
  public String toString() {
    return "CeoMember [ceoNo=" + ceoNo + ", ceoName=" + ceoName + ", ceoNickname=" + ceoNickname
        + ", ceoEmail=" + ceoEmail + ", ceoPassword=" + ceoPassword + ", ceoTel=" + ceoTel
        + ", ceoPhoto=" + ceoPhoto + ", ceoRegisteredDate=" + ceoRegisteredDate + ", ceoStatus="
        + ceoStatus + ", active=" + active + ", ceoBossName=" + ceoBossName + ", ceoLicenseNo="
        + ceoLicenseNo + "]";
  }

  public int getCeoNo() {
    return ceoNo;
  }

  public void setCeoNo(int ceoNo) {
    this.ceoNo = ceoNo;
  }

  public String getCeoName() {
    return ceoName;
  }

  public void setCeoName(String ceoName) {
    this.ceoName = ceoName;
  }

  public String getCeoNickname() {
    return ceoNickname;
  }

  public void setCeoNickname(String ceoNickname) {
    this.ceoNickname = ceoNickname;
  }

  public String getCeoEmail() {
    return ceoEmail;
  }

  public void setCeoEmail(String ceoEmail) {
    this.ceoEmail = ceoEmail;
  }

  public String getCeoPassword() {
    return ceoPassword;
  }

  public void setCeoPassword(String ceoPassword) {
    this.ceoPassword = ceoPassword;
  }

  public String getCeoTel() {
    return ceoTel;
  }

  public void setCeoTel(String ceoTel) {
    this.ceoTel = ceoTel;
  }

  public String getCeoPhoto() {
    return ceoPhoto;
  }

  public void setCeoPhoto(String ceoPhoto) {
    this.ceoPhoto = ceoPhoto;
  }

  public Date getCeoRegisteredDate() {
    return ceoRegisteredDate;
  }

  public void setCeoRegisteredDate(Date ceoRegisteredDate) {
    this.ceoRegisteredDate = ceoRegisteredDate;
  }

  public int getCeoStatus() {
    return ceoStatus;
  }

  public void setCeoStatus(int ceoStatus) {
    this.ceoStatus = ceoStatus;
  }

  public int getActive() {
    return active;
  }

  public void setActive(int active) {
    this.active = active;
  }

  public String getCeoBossName() {
    return ceoBossName;
  }

  public void setCeoBossName(String ceoBossName) {
    this.ceoBossName = ceoBossName;
  }

  public String getCeoLicenseNo() {
    return ceoLicenseNo;
  }

  public void setCeoLicenseNo(String ceoLicenseNo) {
    this.ceoLicenseNo = ceoLicenseNo;
  }

  public static int getInuser() {
    return INUSER;
  }

  public static int getOutuser() {
    return OUTUSER;
  }

  public static int getPer() {
    return PER;
  }

  public static int getCeo() {
    return CEO;
  }

}