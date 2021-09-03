package com.ogong.pms.domain;

import java.sql.Date;

public class Comment {

  private String commentText;

  private Member commentOwner;
  private Date perRegisteredDate;
  private FreeBoard freeBoard;




  @Override
  public String toString() {
    return "Comment [commentText=" + commentText + ", commentOwner=" + commentOwner
        + ", perRegisteredDate=" + perRegisteredDate + ", freeBoard=" + freeBoard + "]";
  }


  public String getCommentText() {
    return commentText;
  }
  public void setCommentText(String commentText) {
    this.commentText = commentText;
  }
  public Member getCommentOwner() {
    return commentOwner;
  }
  public void setCommentOwner(Member commentOwner) {
    this.commentOwner = commentOwner;
  }
  public Date getPerRegisteredDate() {
    return perRegisteredDate;
  }
  public void setPerRegisteredDate(Date perRegisteredDate) {
    this.perRegisteredDate = perRegisteredDate;
  }
  public FreeBoard getFreeBoard() {
    return freeBoard;
  }
  public void setFreeBoard(FreeBoard freeBoard) {
    this.freeBoard = freeBoard;
  }


}
