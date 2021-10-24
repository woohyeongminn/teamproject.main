package com.ogong.pms.domain;

import java.sql.Date;

public class Calender {

  private int calenderNo;
  private Date startDate;
  private int month;   //월
  private int day;     //일
  private String dayOftheWeek; //요일
  private String calenderContent;   // 내용
  private Date endDate;    // 종료일
  private int importanceNo; // 중요도 번호
  private String importanceCalender;  // 중요도 정하기

  public int getCalenderNo() {
    return calenderNo;
  }
  public void setCalenderNo(int calenderNo) {
    this.calenderNo = calenderNo;
  }
  public Date getStartDate() {
    return startDate;
  }
  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }
  public int getMonth() {
    return month;
  }
  public void setMonth(int month) {
    this.month = month;
  }
  public int getDay() {
    return day;
  }
  public void setDay(int day) {
    this.day = day;
  }
  public String getDayOftheWeek() {
    return dayOftheWeek;
  }
  public void setDayOftheWeek(String dayOftheWeek) {
    this.dayOftheWeek = dayOftheWeek;
  }
  public String getCalenderContent() {
    return calenderContent;
  }
  public void setCalenderContent(String calenderContent) {
    this.calenderContent = calenderContent;
  }
  public Date getEndDate() {
    return endDate;
  }
  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
  public int getImportanceNo() {
    return importanceNo;
  }
  public void setImportanceNo(int importanceNo) {
    this.importanceNo = importanceNo;
  }
  public String getImportanceCalender() {
    return importanceCalender;
  }
  public void setImportanceCalender(String importanceCalender) {
    this.importanceCalender = importanceCalender;
  }

  @Override
  public String toString() {
    return "Calender [calenderNo=" + calenderNo + ", startDate=" + startDate + ", month=" + month
        + ", day=" + day + ", dayOftheWeek=" + dayOftheWeek + ", calenderContent=" + calenderContent
        + ", endDate=" + endDate + ", importanceNo=" + importanceNo + ", importanceCalender="
        + importanceCalender + "]";
  }
}
