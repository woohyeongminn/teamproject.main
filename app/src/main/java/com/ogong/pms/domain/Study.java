package com.ogong.pms.domain;

import java.util.Date;

public class Study {

  private int studyNo;  // 스터디 번호
  private String studyTitle; // 스터디명
  private String owner; // 작성자(조장)
  //  private Member owner;
  private String subject; // 분야
  private String area; // 지역
  private String location; // 장소
  private int numberOfPeple; // 인원수
  private String face; // 대면/비대면
  private String introduction; // 소개글
  private Date registeredDate; // 스터디 가입일

  public int getStudyNo() {
    return studyNo;
  }
  public void setStudyNo(int studyNo) {
    this.studyNo = studyNo;
  }
  public String getStudyTitle() {
    return studyTitle;
  }
  public void setStudyTitle(String studyTitle) {
    this.studyTitle = studyTitle;
  }
  public String getOwner() {
    return owner;
  }
  public void setOwner(String owner) {
    this.owner = owner;
  }
  public String getSubject() {
    return subject;
  }
  public void setSubject(String subject) {
    this.subject = subject;
  }
  public String getArea() {
    return area;
  }
  public void setArea(String area) {
    this.area = area;
  }
  public String getLocation() {
    return location;
  }
  public void setLocation(String location) {
    this.location = location;
  }
  public int getNumberOfPeple() {
    return numberOfPeple;
  }
  public void setNumberOfPeple(int numberOfPeple) {
    this.numberOfPeple = numberOfPeple;
  }
  public String getFace() {
    return face;
  }
  public void setFace(String face) {
    this.face = face;
  }
  public String getIntroduction() {
    return introduction;
  }
  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }
}
