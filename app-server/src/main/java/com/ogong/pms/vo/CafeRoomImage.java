package com.ogong.pms.vo;

public class CafeRoomImage {

  private int no;
  private String name;
  private int cafeRoomNo;

  public CafeRoomImage() {}

  public CafeRoomImage(String name) {
    this.name = name;
  }

  public CafeRoomImage(int no, String name) {
    this.no = no;
    this.name = name;
  }

  @Override
  public String toString() {
    return "CafeImage [no=" + no + ", name=" + name + ", cafeNo=" + cafeRoomNo + "]";
  }

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

  public int getCafeNo() {
    return cafeRoomNo;
  }

  public void setCafeNo(int cafeNo) {
    this.cafeRoomNo = cafeNo;
  }
}
