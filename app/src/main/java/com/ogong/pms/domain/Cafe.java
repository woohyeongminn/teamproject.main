package com.ogong.pms.domain;

public class Cafe {

  private int no; // 번호 번호
  private String name; // 이름
  private String mainImg; //카페 썸네일 이미지
  private String info; // 소개글
  private String location; // 주소
  private String phone; // 전화번호
  private String hrs; // 운영시간
  private String personalSeat; // 개인좌석여부
  private String room; // 미팅룸여부
  private int bookable; // 예약가능인원
  private String usingNotebooks; // 노트북대여여부
  private String usingCopyMachine; // 복사기사용여부
  private String usingWifi; // 와이파이사용여부
  private String drinksProvided; // 음료제공유무
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
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
  public String getHrs() {
    return hrs;
  }
  public void setHrs(String hrs) {
    this.hrs = hrs;
  }
  public String getPersonalSeat() {
    return personalSeat;
  }
  public void setPersonalSeat(String personalSeat) {
    this.personalSeat = personalSeat;
  }
  public String getRoom() {
    return room;
  }
  public void setRoom(String room) {
    this.room = room;
  }
  public int getBookable() {
    return bookable;
  }
  public void setBookable(int bookable) {
    this.bookable = bookable;
  }
  public String getUsingNotebooks() {
    return usingNotebooks;
  }
  public void setUsingNotebooks(String usingNotebooks) {
    this.usingNotebooks = usingNotebooks;
  }
  public String getUsingCopyMachine() {
    return usingCopyMachine;
  }
  public void setUsingCopyMachine(String usingCopyMachine) {
    this.usingCopyMachine = usingCopyMachine;
  }
  public String getUsingWifi() {
    return usingWifi;
  }
  public void setUsingWifi(String usingWifi) {
    this.usingWifi = usingWifi;
  }
  public String getDrinksProvided() {
    return drinksProvided;
  }
  public void setDrinksProvided(String drinksProvided) {
    this.drinksProvided = drinksProvided;
  }

  @Override
  public String toString() {
    return "Cafe [no=" + no + ", name=" + name + ", mainImg=" + mainImg + ", info=" + info
        + ", location=" + location + ", phone=" + phone + ", hrs=" + hrs + ", personalSeat="
        + personalSeat + ", room=" + room + ", bookable=" + bookable + ", usingNotebooks="
        + usingNotebooks + ", usingCopyMachine=" + usingCopyMachine + ", usingWifi=" + usingWifi
        + ", drinksProvided=" + drinksProvided + "]";
  }

}
