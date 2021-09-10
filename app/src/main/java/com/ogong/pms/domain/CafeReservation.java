package com.ogong.pms.domain;

import java.sql.Date;
import java.time.LocalTime;

public class CafeReservation {

  private int reservationNo; // 예약 번호
  private int memberNo; // 예약 회원
  private int cafeNo; // 예약 카페
  private Date reservationDate; // 예약 날짜
  private LocalTime startTime; // 시작시간
  private int useTime; // 사용시간
  private int useMemberNumber; // 사용인원
  private int totalPrice; // 총금액
  private boolean wirteReview; // 리뷰작성 여부

  @Override
  public String toString() {
    return "CafeReservation [reservationNo=" + reservationNo + ", memberNo=" + memberNo
        + ", cafeNo=" + cafeNo + ", reservationDate=" + reservationDate + ", useTime=" + useTime
        + ", useMemberNumber=" + useMemberNumber + ", totalPrice=" + totalPrice + ", wirteReview="
        + wirteReview + "]";
  }

  public int getReservationNo() {
    return reservationNo;
  }

  public void setReservationNo(int reservationNo) {
    this.reservationNo = reservationNo;
  }

  public int getMemberNo() {
    return memberNo;
  }

  public void setMemberNo(int memberNo) {
    this.memberNo = memberNo;
  }

  public int getCafeNo() {
    return cafeNo;
  }

  public void setCafeNo(int cafeNo) {
    this.cafeNo = cafeNo;
  }

  public Date getReservationDate() {
    return reservationDate;
  }

  public void setReservationDate(Date reservationDate) {
    this.reservationDate = reservationDate;
  }

  public LocalTime getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalTime startTime) {
    this.startTime = startTime;
  }

  public int getUseTime() {
    return useTime;
  }

  public void setUseTime(int useTime) {
    this.useTime = useTime;
  }

  public int getUseMemberNumber() {
    return useMemberNumber;
  }

  public void setUseMemberNumber(int useMemberNumber) {
    this.useMemberNumber = useMemberNumber;
  }

  public int getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(int totalPrice) {
    this.totalPrice = totalPrice;
  }

  public boolean getWirteReview() {
    return wirteReview;
  }

  public void setWirteReview(boolean wirteReview) {
    this.wirteReview = wirteReview;
  }
}
