package com.ogong.pms.domain;

import java.sql.Date;

public class Comment {

  private String commentText;   // 댓글 내용
  private Member commentWiter;  // 댓글 작성자
  private Date commentRegisteredDate;   // 댓글 작성일

  //private FreeBoard freeBoard;




  @Override
  public String toString() {
    return "Comment [commentText=" + commentText + ", commentWiter=" + commentWiter
        + ", commentRegisteredDate=" + commentRegisteredDate + "]";
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
  //  public FreeBoard getFreeBoard() {
  //    return freeBoard;
  //  }
  //  public void setFreeBoard(FreeBoard freeBoard) {
  //    this.freeBoard = freeBoard;
  //  }


}
