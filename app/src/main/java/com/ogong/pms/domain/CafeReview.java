package com.ogong.pms.domain;

import java.sql.Date;

public class CafeReview {

  int reviewNo; // 리뷰 번호
  String content; // 리뷰 내용
  int grade; // 별점
  int cafeNo; // 가게 번호
  String email; // 리뷰 등록한 아이디
  Date RegisteredDate; // 리뷰 등록일

  public int getReviewNo() {
    return reviewNo;
  }
  public void setReviewNo(int reviewNo) {
    this.reviewNo = reviewNo;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public int getGrade() {
    return grade;
  }
  public void setGrade(int grade) {
    this.grade = grade;
  }
  public int getCafeNo() {
    return cafeNo;
  }
  public void setCafeNo(int cafeNo) {
    this.cafeNo = cafeNo;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public Date getRegisteredDate() {
    return RegisteredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    RegisteredDate = registeredDate;
  }

}
