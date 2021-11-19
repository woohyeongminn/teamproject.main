package com.ogong.pms.vo;

public class CafeImage {

  private int no;
  private String name;
  private int cafeNo;

  public CafeImage() {}

  public CafeImage(String name) {
    this.name = name;
  }

  public CafeImage(int no, String name) {
    this.no = no;
    this.name = name;
  }

  @Override
  public String toString() {
    return "CafeImage [no=" + no + ", name=" + name + ", cafeNo=" + cafeNo + "]";
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
    return cafeNo;
  }

  public void setCafeNo(int cafeNo) {
    this.cafeNo = cafeNo;
  }
}
