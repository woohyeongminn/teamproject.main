package com.ogong.pms.domain;

public class Guilder {

  private int studyNo;
  private int guilderStatus;
  private Member member;

  @Override
  public String toString() {
    return "Guilder [studyNo=" + studyNo + ", guilderStatus=" + guilderStatus + ", guilder="
        + member + "]";
  }

  public int getStudyNo() {
    return studyNo;
  }

  public void setStudyNo(int studyNo) {
    this.studyNo = studyNo;
  }

  public int getGuilderStatus() {
    return guilderStatus;
  }

  public void setGuilderStatus(int guilderStatus) {
    this.guilderStatus = guilderStatus;
  }

  public Member getMember() {
    return member;
  }

  public void setMember(Member member) {
    this.member = member;
  }



}
