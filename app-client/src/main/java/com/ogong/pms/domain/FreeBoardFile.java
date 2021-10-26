package com.ogong.pms.domain;

public class FreeBoardFile {

  private int boardNo;           //글 번호
  private int atcFileNo;      //첨부파일 번호
  private String atcFileName;    //첨부파일 이름

  @Override
  public String toString() {
    return "FreeBoardFile [boardNo=" + boardNo + ", atcFileNo=" + atcFileNo + ", atcFileName="
        + atcFileName + "]";
  }

  public int getBoardNo() {
    return boardNo;
  }
  public void setBoardNo(int boardNo) {
    this.boardNo = boardNo;
  }
  public int getAtcFileNo() {
    return atcFileNo;
  }
  public void setAtcFileNo(int atcFileNo) {
    this.atcFileNo = atcFileNo;
  }
  public String getAtcFileName() {
    return atcFileName;
  }
  public void setAtcFileName(String atcFileName) {
    this.atcFileName = atcFileName;
  }


}
