package com.ogong.pms.domain;

import java.sql.Date;

public class ToDo {
  private int todoNo;           // 투두리스트 번호
  private String todoTitle;     // 투두리스트 제목
  private String todoContent;   // 투두리스트 내용
  private String todoRemark;    // 투두리스트 비고
  private Date todoRegisteredDate; // 투두리스트 등록일
  private int todoStatus;   // 투두리스트 상태

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
  public Date getTodoRegisteredDate() {
    return todoRegisteredDate;
  }
  public void setTodoRegisteredDate(Date todoRegisteredDate) {
    this.todoRegisteredDate = todoRegisteredDate;
  }
  public int getTodoStatus() {
    return todoStatus;
  }
  public void setTodoStatus(int todoStatus) {
    this.todoStatus = todoStatus;
  }
}
