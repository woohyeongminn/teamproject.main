package com.ogong.pms.domain;

public class Cafe {

  private int no; // 카페 번호
  private String cafeCeoEmail; // 사장 이메일
  private String cafeceoLicenseNo; // 사업자번호
  private String cafeCeoBossName; // 대표자명
  private String name; // 상호명
  private String mainImg; //카페 썸네일 이미지
  private String info; // 소개글
  private String location; // 주소
  private String phone; // 전화번호
  private String openTime; // 오픈시간
  private String closeTime; // 마감시간
  private String holiday; // 휴무일
  private int bookable; // 예약가능인원
  private int timePrice; // 시간당금액

  @Override
  public String toString() {
    return "Cafe [no=" + no + ", cafeCeoEmail=" + cafeCeoEmail + ", cafeceoLicenseNo="
        + cafeceoLicenseNo + ", cafeCeoBossName=" + cafeCeoBossName + ", name=" + name
        + ", mainImg=" + mainImg + ", info=" + info + ", location=" + location + ", phone=" + phone
        + ", openTime=" + openTime + ", closeTime=" + closeTime + ", holiday=" + holiday
        + ", bookable=" + bookable + ", timePrice=" + timePrice + "]";
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getCafeCeoEmail() {
    return cafeCeoEmail;
  }

  public void setCafeCeoEmail(String cafeCeoEmail) {
    this.cafeCeoEmail = cafeCeoEmail;
  }

  public String getCafeceoLicenseNo() {
    return cafeceoLicenseNo;
  }

  public void setCafeceoLicenseNo(String cafeceoLicenseNo) {
    this.cafeceoLicenseNo = cafeceoLicenseNo;
  }

  public String getCafeCeoBossName() {
    return cafeCeoBossName;
  }

  public void setCafeCeoBossName(String cafeCeoBossName) {
    this.cafeCeoBossName = cafeCeoBossName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMainImg() {
    return mainImg;
  }

  public void setMainImg(String mainImg) {
    this.mainImg = mainImg;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getOpenTime() {
    return openTime;
  }

  public void setOpenTime(String openTime) {
    this.openTime = openTime;
  }

  public String getCloseTime() {
    return closeTime;
  }

  public void setCloseTime(String closeTime) {
    this.closeTime = closeTime;
  }

  public String getHoliday() {
    return holiday;
  }

  public void setHoliday(String holiday) {
    this.holiday = holiday;
  }

  public int getBookable() {
    return bookable;
  }

  public void setBookable(int bookable) {
    this.bookable = bookable;
  }

  public int getTimePrice() {
    return timePrice;
  }

  public void setTimePrice(int timePrice) {
    this.timePrice = timePrice;
  }

}
