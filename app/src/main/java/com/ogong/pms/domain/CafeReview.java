package com.ogong.pms.domain;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class CafeReview implements Serializable{

  int reviewNo; // 리뷰 번호
  String content; // 리뷰 내용
  int grade; // 별점
  int cafeNo; // 가게 번호
  int memberNo; // 리뷰 등록한 아이디
  Date RegisteredDate; // 리뷰 등록일
  int reviewStatus; // 0 : 기본 1 : 삭제

  @Override
  public String toString() {
    return "CafeReview [reviewNo=" + reviewNo + ", content=" + content + ", grade=" + grade
        + ", cafeNo=" + cafeNo + ", memberNo=" + memberNo + ", RegisteredDate=" + RegisteredDate
        + ", reviewStatus=" + reviewStatus + "]";
  }

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

  public int getMemberNo() {
    return memberNo;
  }

  public void setMemberNo(int memberNo) {
    this.memberNo = memberNo;
  }

  public Date getRegisteredDate() {
    return RegisteredDate;
  }

  public void setRegisteredDate(Date registeredDate) {
    RegisteredDate = registeredDate;
  }

  public int getReviewStatus() {
    return reviewStatus;
  }

  public void setReviewStatus(int reviewStatus) {
    this.reviewStatus = reviewStatus;
  }

}
