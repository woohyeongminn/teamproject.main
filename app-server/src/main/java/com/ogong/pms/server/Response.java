package com.ogong.pms.server;

// 역할 
// - 클라이언트에게 응답할 정보를 보관하는 일을 한다.

public class Response {

  public static final String SUCCESS = "success";
  public static final String FAIL = "fail";

  String status;
  String value;

  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public String getValue() {
    return value;
  }
  public void setValue(String value) {
    this.value = value;
  }
}
