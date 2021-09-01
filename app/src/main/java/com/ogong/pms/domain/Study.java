package com.ogong.pms.domain;

import java.sql.Date;

public class Study {

  public int studyNo;  // 스터디 번호
  public String studyTitle; // 스터디명
  // 0831 eun 추가
  public Member owner; // 작성자(조장)
  public String subject; // 분야
  public String area; // 지역
  public String location; // 장소
  public int numberOfPeple; // 인원수
  public String face; // 대면/비대면
  public String introduction; // 소개글
  public Date registeredDate; // 스터디 가입일



  @Override
  public String toString() {
    return "Study [studyNo=" + studyNo + ", studyTitle=" + studyTitle + ", owner=" + owner
        + ", subject=" + subject + ", area=" + area + ", location=" + location + ", numberOfPeple="
        + numberOfPeple + ", face=" + face + ", introduction=" + introduction + ", registeredDate="
        + registeredDate + "]";
  }

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
  public Member getOwner() {
    return owner;
  }
  public void setOwner(Member owner) {
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
