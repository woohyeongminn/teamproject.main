package com.ogong.pms.vo;

import java.sql.Date;

public class Calendar {

  private int calendarNo;
  private Date startDate;
  private int year; // 년
  private int month;   //월
  private int day;     //일
  private String dayOftheWeek; //요일
  private String calendarContent;   // 내용
  private Date endDate;    // 종료일
  private int importanceNo; // 중요도 번호
  private String importanceCalendar;  // 중요도 정하기

  public int getCalendarNo() {
    return calendarNo;
  }
  public void setCalendarNo(int calendarNo) {
    this.calendarNo = calendarNo;
  }
  public Date getStartDate() {
    return startDate;
  }
  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }
  public int getYear() {
    return year;
  }
  public void setYear(int year) {
    this.year = year;
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
  public String getCalendarContent() {
    return calendarContent;
  }
  public void setCalendarContent(String calendarContent) {
    this.calendarContent = calendarContent;
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
  public String getImportanceCalendar() {
    return importanceCalendar;
  }
  public void setImportanceCalendar(String importanceCalendar) {
    this.importanceCalendar = importanceCalendar;
  }

  @Override
  public String toString() {
    return "Calendar [calendarNo=" + calendarNo + ", startDate=" + startDate + ", year=" + year
        + ", month=" + month + ", day=" + day + ", dayOftheWeek=" + dayOftheWeek
        + ", calendarContent=" + calendarContent + ", endDate=" + endDate + ", importanceNo="
        + importanceNo + ", importanceCalendar=" + importanceCalendar + "]";
  }
}
