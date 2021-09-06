package com.ogong.pms.domain;

import java.sql.Date;

public class Calender {

  private int month;   //월
  private int day;     //일
  private String dayOftheWeek; //요일
  private String calenderContent;   // 내용
  //  private Date startDay;  // 시작일
  private Date endDay;    // 종료일
  private String studyTitle;  // 스터디 명


  @Override
  public String toString() {
    return "Calender [month=" + month + ", day=" + day + ", dayOftheWeek=" + dayOftheWeek
        + ", calenderContent=" + calenderContent + ", endDay=" + endDay + ", studyTitle="
        + studyTitle + "]";
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
  public String getStudyTitle() {
    return studyTitle;
  }
  public void setStudyTitle(String studyTitle) {
    this.studyTitle = studyTitle;
  }
  public Date getEndDay() {
    return endDay;
  }
  public void setEndDay(Date endDay) {
    this.endDay = endDay;
  }

}
