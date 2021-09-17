package com.ogong.pms.domain;

import java.sql.Date;
import java.util.List;

public class AskBoard {
  private int askNo; // 문의게시판 번호
  private String askTitle; // 문의게시판 제목
  private String askContent; // 문의게시판 내용
  private Member askMemberWriter = new Member(); // 문의게시판 개인 작성자
  private CeoMember askCeoWriter = new CeoMember(); // 문의게시판 기업 작성자
  private int askVeiwCount; // 문의게시판 조회수
  private Date askRegisteredDate; // 문의게시판 등록일
  private List<Comment> adminComment; // 문의게시판 관리자 (댓글)// 답변 1개!
  // String type 으로....하는 것이 좋다!

  @Override
  public String toString() {
    return "AskBoard [askNo=" + askNo + ", askTitle=" + askTitle + ", askContent=" + askContent
        + ", askMemberWriter=" + askMemberWriter + ", askCeoWriter=" + askCeoWriter
        + ", askVeiwCount=" + askVeiwCount + ", askRegisteredDate=" + askRegisteredDate
        + ", adminComment=" + adminComment + "]";
  }

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
  public Member getAskMemberWriter() {
    return askMemberWriter;
  }
  public void setAskMemberWriter(Member askMemberWriter) {
    this.askMemberWriter = askMemberWriter;
  }
  public CeoMember getAskCeoWriter() {
    return askCeoWriter;
  }
  public void setAskCeoWriter(CeoMember askCeoWriter) {
    this.askCeoWriter = askCeoWriter;
  }
  public int getAskVeiwCount() {
    return askVeiwCount;
  }
  public void setAskVeiwCount(int askVeiwCount) {
    this.askVeiwCount = askVeiwCount;
  }
  public Date getAskRegisteredDate() {
    return askRegisteredDate;
  }
  public void setAskRegisteredDate(Date askRegisteredDate) {
    this.askRegisteredDate = askRegisteredDate;
  }
  public List<Comment> getAdminComment() {
    return adminComment;
  }
  public void setAdminComment(List<Comment> adminComment) {
    this.adminComment = adminComment;
  }



}
