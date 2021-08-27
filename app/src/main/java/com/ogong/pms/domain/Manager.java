package com.ogong.pms.domain;

import java.sql.Date;

public class Manager {
  private int hostNo; // 공지게시판 번호 - 관리자용
  private String hostTitle; // 공지게시판 제목 - 관리자용
  private String hostContent; // 공지게시판 내용 - 관리자용
  private String hostWriter; // 공지게시판 작성자 - 관리자용
  private Date hostRegisteredDate; // 공지게시판 등록일 - 관리자용

  public int getHostNo() {
    return hostNo;
  }
  public void setHostNo(int hostNo) {
    this.hostNo = hostNo;
  }
  public String getHostTitle() {
    return hostTitle;
  }
  public void setHostTitle(String hostTitle) {
    this.hostTitle = hostTitle;
  }
  public String getHostContent() {
    return hostContent;
  }
  public void setHostContent(String hostContent) {
    this.hostContent = hostContent;
  }
  public String getHostWriter() {
    return hostWriter;
  }
  public void setHostWriter(String hostWriter) {
    this.hostWriter = hostWriter;
  }
  public Date getHostRegisteredDate() {
    return hostRegisteredDate;
  }
  public void setHostRegisteredDate(Date hostRegisteredDate) {
    this.hostRegisteredDate = hostRegisteredDate;
  }
}
