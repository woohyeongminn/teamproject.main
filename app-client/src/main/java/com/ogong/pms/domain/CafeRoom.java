package com.ogong.pms.domain;

import java.util.ArrayList;

public class CafeRoom {

  private int roomNo; // 룸 고유번호
  private Cafe cafe; // 카페번호
  private String roomName; // 룸 이름
  private String roomImg; // 룸 이미지
  private ArrayList<CafeRoomImage> roomImgs = new ArrayList<>(); // 룸 이미지들
  private String roomInfo; // 룸 설명
  private int people; // 인원제한수
  private int roomPrice; // 룸 시간당금액
  private int roomStatus; // 0 : 룸 운영중 1 : 룸 운영중단

  @Override
  public String toString() {
    return "CafeRoom [roomNo=" + roomNo + ", cafe=" + cafe + ", roomName=" + roomName + ", roomImg="
        + roomImg + ", roomInfo=" + roomInfo + ", people=" + people + ", roomPrice=" + roomPrice
        + ", roomStatus=" + roomStatus + "]";
  }

  public int getRoomNo() {
    return roomNo;
  }

  public void setRoomNo(int roomNo) {
    this.roomNo = roomNo;
  }

  public Cafe getCafe() {
    return cafe;
  }

  public void setCafe(Cafe cafe) {
    this.cafe = cafe;
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

  public int getPeople() {
    return people;
  }

  public void setPeople(int people) {
    this.people = people;
  }

  public ArrayList<CafeRoomImage> getRoomImgs() {
    return roomImgs;
  }

  public void setRoomImgs(ArrayList<CafeRoomImage> roomImgs) {
    this.roomImgs = roomImgs;
  }

  public String getCafeRoomImageNames() {
    if (roomImgs.isEmpty()) {
      return "";
    }

    StringBuilder names = new StringBuilder();
    for (CafeRoomImage cafeRoomImage : roomImgs) {
      if (names.length() > 0) {
        names.append(",");
      }
      names.append(cafeRoomImage.getName());
    }
    return names.toString();
  }
}
