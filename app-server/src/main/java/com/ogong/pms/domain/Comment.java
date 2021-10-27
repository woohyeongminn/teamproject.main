package com.ogong.pms.domain;

import java.sql.Date;

public class Comment {

  private int studyNo;                      //스터디 번호
  private int boardNo;                      //글 번호
  private int commentNo;                    // 댓글 번호
  private String commentText;               // 댓글 내용
  private Member commentWiter;              // 댓글 작성자
  private Date commentRegisteredDate;       // 댓글 작성일

  @Override
  public String toString() {
    return "Comment [studyNo=" + studyNo + ", boardNo=" + boardNo + ", commentNo=" + commentNo
        + ", commentText=" + commentText + ", commentWiter=" + commentWiter
        + ", commentRegisteredDate=" + commentRegisteredDate + "]";
  }

  public int getStudyNo() {
    return studyNo;
  }

  public void setStudyNo(int studyNo) {
    this.studyNo = studyNo;
  }

  public int getBoardNo() {
    return boardNo;
  }

  public void setBoardNo(int boardNo) {
    this.boardNo = boardNo;
  }

  public int getCommentNo() {
    return commentNo;
  }

  public void setCommentNo(int commentNo) {
    this.commentNo = commentNo;
  }

  public String getCommentText() {
    return commentText;
  }

  public void setCommentText(String commentText) {
    this.commentText = commentText;
  }

  public Member getCommentWiter() {
    return commentWiter;
  }

  public void setCommentWiter(Member commentWiter) {
    this.commentWiter = commentWiter;
  }

  public Date getCommentRegisteredDate() {
    return commentRegisteredDate;
  }

  public void setCommentRegisteredDate(Date commentRegisteredDate) {
    this.commentRegisteredDate = commentRegisteredDate;
  }


}
