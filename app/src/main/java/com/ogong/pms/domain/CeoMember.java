package com.ogong.pms.domain;

import java.sql.Date;

public class CeoMember {

  private int ceoNo;

  private String ceoEmail;    //기업회원 이메일
  private String ceoName;   //기업회원 이름
  private String ceoPassword;   //기업회원 패스워드
  private String ceoPhoto;      //기업회원 사진
  private String ceoLicenseNo;  //기업회원 사업자등록번호
  private String ceoBossName;   //기업회원 대표자명
  private String ceoStoreName;  //기업회원 점포명
  private String ceoStoreDetailAddress; //기업회원 점포상세주소
  private Date ceoregisteredDate;   //기업회원 가입일



  public int getCeoNo() {
    return ceoNo;
  }
  public void setCeoNo(int ceoNo) {
    this.ceoNo = ceoNo;
  }
  public String getCeoEmail() {
    return ceoEmail;
  }
  public void setCeoEmail(String ceoEmail) {
    this.ceoEmail = ceoEmail;
  }
  public String getCeoName() {
    return ceoName;
  }
  public void setCeoName(String ceoName) {
    this.ceoName = ceoName;
  }
  public String getCeoPassword() {
    return ceoPassword;
  }
  public void setCeoPassword(String ceoPassword) {
    this.ceoPassword = ceoPassword;
  }
  public String getCeoPhoto() {
    return ceoPhoto;
  }
  public void setCeoPhoto(String ceoPhoto) {
    this.ceoPhoto = ceoPhoto;
  }
  public String getCeoLicenseNo() {
    return ceoLicenseNo;
  }
  public void setCeoLicenseNo(String ceoLicenseNo) {
    this.ceoLicenseNo = ceoLicenseNo;
  }
  public String getCeoBossName() {
    return ceoBossName;
  }
  public void setCeoBossName(String ceoBossName) {
    this.ceoBossName = ceoBossName;
  }
  public String getCeoStoreName() {
    return ceoStoreName;
  }
  public void setCeoStoreName(String ceoStoreName) {
    this.ceoStoreName = ceoStoreName;
  }
  public String getCeoStoreDetailAddress() {
    return ceoStoreDetailAddress;
  }
  public void setCeoStoreDetailAddress(String ceoStoreDetailAddress) {
    this.ceoStoreDetailAddress = ceoStoreDetailAddress;
  }
  public Date getCeoregisteredDate() {
    return ceoregisteredDate;
  }
  public void setCeoregisteredDate(Date ceoregisteredDate) {
    this.ceoregisteredDate = ceoregisteredDate;
  }









}