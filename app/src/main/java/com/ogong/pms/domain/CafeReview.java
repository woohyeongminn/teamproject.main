package com.ogong.pms.domain;

import java.sql.Date;

public class CafeReview {

  int reviewNo; // 리뷰 번호
  String content; // 리뷰 내용
  int grade; // 별점
  Cafe cafe; // 가게 번호
  Member member; // 리뷰 등록한 아이디
  Date RegisteredDate; // 리뷰 등록일

  @Override
  public String toString() {
    return "CafeReview [reviewNo=" + reviewNo + ", content=" + content + ", grade=" + grade
        + ", cafe=" + cafe + ", member=" + member + ", RegisteredDate=" + RegisteredDate + "]";
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

  public Cafe getCafe() {
    return cafe;
  }

  public void setCafe(Cafe cafe) {
    this.cafe = cafe;
  }

  public Member getMember() {
    return member;
  }

  public void setMember(Member member) {
    this.member = member;
  }

  public Date getRegisteredDate() {
    return RegisteredDate;
  }

  public void setRegisteredDate(Date registeredDate) {
    RegisteredDate = registeredDate;
  }

}
