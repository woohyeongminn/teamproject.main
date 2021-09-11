package com.ogong.pms.domain;

import java.sql.Date;

public class ToDo {
  private int todoNo;           // 번호
  private String todoTitle;     // 제목
  private String todoContent;   // 내용
  private String todoRemark;    // 비고
  private Date todoDate;        // 등록일
  private int todoStatus;       // 상태
  private String todocomplete;  // 완료된 투두리스트


  public String getTodocomplete() {
    return todocomplete;
  }
  public void setTodocomplete(String todocomplete) {
    this.todocomplete = todocomplete;
  }
  public int getTodoNo() {
    return todoNo;
  }
  public void setTodoNo(int todoNo) {
    this.todoNo = todoNo;
  }
  public String getTodoTitle() {
    return todoTitle;
  }
  public void setTodoTitle(String todoTitle) {
    this.todoTitle = todoTitle;
  }
  public String getTodoContent() {
    return todoContent;
  }
  public void setTodoContent(String todoContent) {
    this.todoContent = todoContent;
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
}
