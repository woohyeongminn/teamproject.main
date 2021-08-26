package com.ogong.pms.domain;

import java.util.Date;

public class Study {

  public int studyNo;
  public String studyTitle;
  public String owner;
  public String subject;
  public String area;
  public String location;
  public int numberOfPeple;
  public String face;
  public String introduction;
  public Date registeredDate;

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
