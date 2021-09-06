package com.ogong.pms.domain;

import java.sql.Date;
import java.util.List;

public class FreeBoard {

  private int freeBoardNo;              //글 번호
  private String freeBoardTitle;        //제목
  private String freeBoardContent;      //내용 
  private String freeBoardAtcFile;      //첨부파일
  // 0831 eun 수정
  private Member freeBoardWriter;       //작성자
  private int freeBoardViewcount;    //조회수
  private Date freeBoardRegisteredDate; //작성일

  private List<Comment> comment; //댓글


  @Override
  public String toString() {
    return "FreeBoard [freeBoardNo=" + freeBoardNo + ", freeBoardTitle=" + freeBoardTitle
        + ", freeBoardContent=" + freeBoardContent + ", freeBoardAtcFile=" + freeBoardAtcFile
        + ", freeBoardWriter=" + freeBoardWriter + ", freeBoardViewcount=" + freeBoardViewcount
        + ", freeBoardRegisteredDate=" + freeBoardRegisteredDate + ", comment=" + comment + "]";
  }

  public List<Comment> getComment() {
    return comment;
  }

  public void setComment(List<Comment> comment) {
    this.comment = comment;
  }

  public int getFreeBoardNo() {
    return freeBoardNo + 1;
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

  public Member getFreeBoardWriter() {
    return freeBoardWriter;
  }

  public void setFreeBoardWriter(Member freeBoardWriter) {
    this.freeBoardWriter = freeBoardWriter;
  }

  public int getFreeBoardViewcount() {
    return freeBoardViewcount;
  }

  public void setFreeBoardViewcount(int freeBoardViewcount) {
    this.freeBoardViewcount = freeBoardViewcount;
  }

  public Date getFreeBoardRegisteredDate() {
    return freeBoardRegisteredDate;
  }

  public void setFreeBoardRegisteredDate(Date freeBoardRegisteredDate) {
    this.freeBoardRegisteredDate = freeBoardRegisteredDate;
  }
}
