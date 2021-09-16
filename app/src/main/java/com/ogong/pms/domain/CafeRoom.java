package com.ogong.pms.domain;

import java.time.LocalTime;

public class CafeRoom {

  private int roomNo; // 룸 고유번호
  private int cafeNo; // 카페번호
  private String roomName; // 룸 이름
  private String roomImg; // 룸 이미지
  private String roomInfo; // 룸 설명
  private LocalTime startTime; // 룸 시작시간
  private LocalTime endTime; // 룸 마감시간
  private int roomPrice; // 룸 시간당금액
  private int roomStatus; // 0 : 룸 운영중 1 : 룸 운영중단

  @Override
  public String toString() {
    return "CafeRoom [roomNo=" + roomNo + ", cafeNo=" + cafeNo + ", roomName=" + roomName
        + ", roomImg=" + roomImg + ", roomInfo=" + roomInfo + ", startTime=" + startTime
        + ", endTime=" + endTime + ", roomPrice=" + roomPrice + ", roomStatus=" + roomStatus + "]";
  }

  public int getRoomNo() {
    return roomNo;
  }

  public void setRoomNo(int roomNo) {
    this.roomNo = roomNo;
  }

  public int getCafeNo() {
    return cafeNo;
  }

  public void setCafeNo(int cafeNo) {
    this.cafeNo = cafeNo;
  }

  public String getRoomName() {
    return roomName;
  }

  public void setRoomName(String roomName) {
    this.roomName = roomName;
  }

  public String getRoomImg() {
    return roomImg;
  }

  public void setRoomImg(String roomImg) {
    this.roomImg = roomImg;
  }

  public String getRoomInfo() {
    return roomInfo;
  }

  public void setRoomInfo(String roomInfo) {
    this.roomInfo = roomInfo;
  }

  public LocalTime getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalTime startTime) {
    this.startTime = startTime;
  }

  public LocalTime getEndTime() {
    return endTime;
  }

  public void setEndTime(LocalTime endTime) {
    this.endTime = endTime;
  }

  public int getRoomPrice() {
    return roomPrice;
  }

  public void setRoomPrice(int roomPrice) {
    this.roomPrice = roomPrice;
  }

  public int getRoomStatus() {
    return roomStatus;
  }

  public void setRoomStatus(int roomStatus) {
    this.roomStatus = roomStatus;
  }
}
