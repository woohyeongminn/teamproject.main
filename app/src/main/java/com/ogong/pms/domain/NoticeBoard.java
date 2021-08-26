package com.ogong.pms.domain;

import java.sql.Date;

public class NoticeBoard {
  private int notiNo; // 공지게시판 번호
  private String notiTitle; // 공지게시판 제목
  private String notiContent; // 공지게시판 내용
  private String notiWriter; // 공지게시판 작성자
  private Date notiRegisteredDate; // 공지게시판 등록일
  public int getNotiNo() {
    return notiNo;
  }
  public void setNotiNo(int notiNo) {
    this.notiNo = notiNo;
  }
  public String getNotiTitle() {
    return notiTitle;
  }
  public void setNotiTitle(String notiTitle) {
    this.notiTitle = notiTitle;
  }
  public String getNotiContent() {
    return notiContent;
  }
  public void setNotiContent(String notiContent) {
    this.notiContent = notiContent;
  }
  public String getNotiWriter() {
    return notiWriter;
  }
  public void setNotiWriter(String notiWriter) {
    this.notiWriter = notiWriter;
  }
  public Date getNotiRegisteredDate() {
    return notiRegisteredDate;
  }
  public void setNotiRegisteredDate(Date notiRegisteredDate) {
    this.notiRegisteredDate = notiRegisteredDate;
  }
}
