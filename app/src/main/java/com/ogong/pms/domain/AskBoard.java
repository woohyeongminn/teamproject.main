package com.ogong.pms.domain;

import java.sql.Date;

public class AskBoard {
  private int askNo; // 문의게시판 번호
  private String askTitle; // 문의게시판 제목
  private String askContent; // 문의게시판 내용
  private String askWriter; // 문의게시판 작성자
  private Date askRegisteredDate; // 문의게시판 등록일
  public int getAskNo() {
    return askNo;
  }
  public void setAskNo(int askNo) {
    this.askNo = askNo;
  }
  public String getAskTitle() {
    return askTitle;
  }
  public void setAskTitle(String askTitle) {
    this.askTitle = askTitle;
  }
  public String getAskContent() {
    return askContent;
  }
  public void setAskContent(String askContent) {
    this.askContent = askContent;
  }
  public String getAskWriter() {
    return askWriter;
  }
  public void setAskWriter(String askWriter) {
    this.askWriter = askWriter;
  }
  public Date getAskRegisteredDate() {
    return askRegisteredDate;
  }
  public void setAskRegisteredDate(Date askRegisteredDate) {
    this.askRegisteredDate = askRegisteredDate;
  }

}
