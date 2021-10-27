package com.ogong.pms.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class FreeBoard {

  private int studyNo;                      //스터디 번호
  private int freeBoardNo;                  //글 번호
  private String freeBoardTitle;            //글 제목
  private String freeBoardContent;          //글 내용 
  private Member freeBoardWriter;           //글 작성자
  private int freeBoardViewcount;           //글 조회수
  private Date freeBoardRegisteredDate;     //글 작성일

  private int countFile;       // 첨부파일 수
  private List<FreeBoardFile> freeBoardFile;  // 첨부파일

  private int countComment;       // 댓글 수
  private List<Comment> comment = new ArrayList<>(); //댓글

  private int countLike;       // 좋아요 수

  @Override
  public String toString() {
    return "FreeBoard [studyNo=" + studyNo + ", freeBoardNo=" + freeBoardNo + ", freeBoardTitle="
        + freeBoardTitle + ", freeBoardContent=" + freeBoardContent + ", freeBoardWriter="
        + freeBoardWriter + ", freeBoardViewcount=" + freeBoardViewcount
        + ", freeBoardRegisteredDate=" + freeBoardRegisteredDate + ", countFile=" + countFile
        + ", freeBoardFile=" + freeBoardFile + ", countComment=" + countComment + ", comment="
        + comment + ", countLike=" + countLike + "]";
  }

  public int getStudyNo() {
    return studyNo;
  }

  public void setStudyNo(int studyNo) {
    this.studyNo = studyNo;
  }

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

  public int getCountFile() {
    return countFile;
  }

  public void setCountFile(int countFile) {
    this.countFile = countFile;
  }

  public List<FreeBoardFile> getFreeBoardFile() {
    return freeBoardFile;
  }

  public void setFreeBoardFile(List<FreeBoardFile> freeBoardFile) {
    this.freeBoardFile = freeBoardFile;
  }

  public int getCountComment() {
    return countComment;
  }

  public void setCountComment(int countComment) {
    this.countComment = countComment;
  }

  public List<Comment> getComment() {
    return comment;
  }

  public void setComment(List<Comment> comment) {
    this.comment = comment;
  }

  public int getCountLike() {
    return countLike;
  }

  public void setCountLike(int countLike) {
    this.countLike = countLike;
  }

  public String getFileNames() {
    if (this.freeBoardFile == null) {
      return "";
    }
    StringBuilder names = new StringBuilder();
    for (FreeBoardFile freeBoardFile : this.freeBoardFile) {
      if (names.length() > 0) {
        names.append(", ");
      }
      names.append(freeBoardFile.getAtcFileName());
    }
    return names.toString();
  }
}
