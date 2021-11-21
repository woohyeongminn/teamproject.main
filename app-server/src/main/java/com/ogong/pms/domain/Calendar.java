package com.ogong.pms.domain;

import java.sql.Date;

public class Calendar {

  private int calendarNo; 
  private Date calendarDate;
  private String calendarContent;   // 내용
  private int importanceNo; // 중요도 번호
  private String importance;  // 중요도 정하기
  private Member writer;
  private int studyNo;

  public int getCalendarNo() {
    return calendarNo;
  }
  public void setCalendarNo(int calendarNo) {
    this.calendarNo = calendarNo;
  }
  public Date getCalendarDate() {
    return calendarDate;
  }
  public void setCalendarDate(Date calendarDate) {
    this.calendarDate = calendarDate;
  }
  public String getCalendarContent() {
    return calendarContent;
  }
  public void setCalendarContent(String calendarContent) {
    this.calendarContent = calendarContent;
  }
  public int getImportanceNo() {
    return importanceNo;
  }
  public void setImportanceNo(int importanceNo) {
    this.importanceNo = importanceNo;
  }
  public String getImportance() {
    return importance;
  }
  public void setImportance(String importance) {
    this.importance = importance;
  }
  public Member getWriter() {
    return writer;
  }
  public void setWriter(Member writer) {
    this.writer = writer;
  }
  public int getStudyNo() {
    return studyNo;
  }
  public void setStudyNo(int studyNo) {
    this.studyNo = studyNo;
  }
  @Override
  public String toString() {
    return "Calendar [calendarNo=" + calendarNo + ", calendarDate=" + calendarDate
        + ", calendarContent=" + calendarContent + ", importanceNo=" + importanceNo
        + ", importance=" + importance + ", writer=" + writer + ", studyNo=" + studyNo + "]";
  }

}
