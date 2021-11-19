package com.ogong.pms.vo;

import java.sql.Date;

public class ToDo {

  public static final int PROGRESSING = 1;      // 진행중
  public static final int COMPLETE = 2;         // 완료

  private int todoNo;             // 투두리스트 번호
  private int studyNo;            // 스터디 번호
  // private String todoTitle;    // [필요 X] 투두리스트 제목
  private String todoContent;     // 투두리스트 내용
  private Member todoWriter;      // 투두리스트 작성자
  private String todoRemark;      // 투두리스트 비고
  private Date todoDate;          // 투두리스트 등록일
  private int todoStatus;         // 투두리스트 상태
  private String todocomplete; // 투두리스트 스트링타입

  @Override
  public String toString() {
    return "ToDo [todoNo=" + todoNo + ", studyNo=" + studyNo + ", todoContent=" + todoContent
        + ", todoWriter=" + todoWriter + ", todoRemark=" + todoRemark + ", todoDate=" + todoDate
        + ", todoStatus=" + todoStatus + ", todocomplete=" + todocomplete + "]";
  }

  public int getTodoNo() {
    return todoNo;
  }

  public void setTodoNo(int todoNo) {
    this.todoNo = todoNo;
  }

  public int getStudyNo() {
    return studyNo;
  }

  public void setStudyNo(int studyNo) {
    this.studyNo = studyNo;
  }

  public String getTodoContent() {
    return todoContent;
  }

  public void setTodoContent(String todoContent) {
    this.todoContent = todoContent;
  }

  public Member getTodoWriter() {
    return todoWriter;
  }

  public void setTodoWriter(Member todoWriter) {
    this.todoWriter = todoWriter;
  }

  public String getTodoRemark() {
    return todoRemark;
  }

  public void setTodoRemark(String todoRemark) {
    this.todoRemark = todoRemark;
  }

  public Date getTodoDate() {
    return todoDate;
  }

  public void setTodoDate(Date todoDate) {
    this.todoDate = todoDate;
  }

  public int getTodoStatus() {
    return todoStatus;
  }

  public void setTodoStatus(int todoStatus) {
    this.todoStatus = todoStatus;
  }

  public String getTodocomplete() {
    return todocomplete;
  }
  public void setTodocomplete(String todocomplete) {
    this.todocomplete = todocomplete;
  }
}
