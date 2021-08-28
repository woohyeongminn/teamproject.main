package com.ogong.pms.domain;

import java.sql.Date;

public class FreeBoard {

  private int freeBoardNo;              //글 번호
  private String freeBoardTitle;        //제목
  private String freeBoardContent;      //내용 
  private String freeBoardAtcFile;      //첨부파일
  private String freeBoardWriter;       //작성자
  private String freeBoardViewcount;    //조회수
  private Date freeBoardRegisteredDate; //작성일



  public int getFreeBoardNo() {
    return freeBoardNo;
  }
  public void setFreeBoardNo(int freeBoardNo) {
    this.freeBoardNo = freeBoardNo;
  }

  public String getFreeBoardTitle() {
    return freeBoardTitle;
  }
  public void setFreeBoardTitle(String freeBoardTitle) {
    this.freeBoardTitle = freeBoardTitle;
  }

  public String getFreeBoardContent() {
    return freeBoardContent;
  }
  public void setFreeBoardContent(String freeBoardContent) {
    this.freeBoardContent = freeBoardContent;
  }

  public String getFreeBoardAtcFile() {
    return freeBoardAtcFile;
  }
  public void setFreeBoardAtcFile(String freeBoardAtcFile) {
    this.freeBoardAtcFile = freeBoardAtcFile;
  }



  public String getFreeBoardWriter() {
    return freeBoardWriter;
  }
  public void setFreeBoardWriter(String freeBoardWriter) {
    this.freeBoardWriter = freeBoardWriter;
  }


  public String getFreeBoardViewcount() {
    return freeBoardViewcount;
  }
  public void setFreeBoardViewcount(String freeBoardViewcount) {
    this.freeBoardViewcount = freeBoardViewcount;
  }
  public Date getFreeBoardRegisteredDate() {
    return freeBoardRegisteredDate;
  }
  public void setFreeBoardRegisteredDate(Date freeBoardRegisteredDate) {
    this.freeBoardRegisteredDate = freeBoardRegisteredDate;
  }




}
