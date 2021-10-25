package com.ogong.pms.domain;

import java.sql.Date;

public class AdminNotice {
  private int adminNotiNo; // 공지게시판 번호
  private String adminNotiTitle; // 공지게시판 제목
  private String adminNotiContent; // 공지게시판 내용
  //  private String adminNotiWriter; // 공지게시판 작성자
  private int adminNotiFileNo; // 공지게시판 첨부파일 번호
  private String adminNotiFile; // 공지게시판 첨부파일
  private Date adminNotiRegisteredDate; // 공지게시판 등록일

  @Override
  public String toString() {
    return "AdminNotice [adminNotiNo=" + adminNotiNo + ", adminNotiTitle=" + adminNotiTitle
        + ", adminNotiContent=" + adminNotiContent + ", adminNotiFileNo=" + adminNotiFileNo
        + ", adminNotiFile=" + adminNotiFile + ", adminNotiRegisteredDate="
        + adminNotiRegisteredDate + "]";
  }

  public int getAdminNotiNo() {
    return adminNotiNo;
  }
  public void setAdminNotiNo(int adminNotiNo) {
    this.adminNotiNo = adminNotiNo;
  }
  public String getAdminNotiTitle() {
    return adminNotiTitle;
  }
  public void setAdminNotiTitle(String adminNotiTitle) {
    this.adminNotiTitle = adminNotiTitle;
  }
  public String getAdminNotiContent() {
    return adminNotiContent;
  }
  public void setAdminNotiContent(String adminNotiContent) {
    this.adminNotiContent = adminNotiContent;
  }
  public int getAdminNotiFileNo() {
    return adminNotiFileNo;
  }
  public void setAdminNotiFileNo(int adminNotiFileNo) {
    this.adminNotiFileNo = adminNotiFileNo;
  }
  public String getAdminNotiFile() {
    return adminNotiFile;
  }
  public void setAdminNotiFile(String adminNotiFile) {
    this.adminNotiFile = adminNotiFile;
  }
  public Date getAdminNotiRegisteredDate() {
    return adminNotiRegisteredDate;
  }
  public void setAdminNotiRegisteredDate(Date adminNotiRegisteredDate) {
    this.adminNotiRegisteredDate = adminNotiRegisteredDate;
  }

}
