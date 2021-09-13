package com.ogong.pms.domain;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalTime;

@SuppressWarnings("serial")
public class CafeReservation implements Serializable{

  private int reservationNo; // 예약 번호
  private int memberNo; // 예약 회원
  private int cafeNo; // 예약 카페
  private Date reservationDate; // 예약 날짜
  private LocalTime startTime; // 시작시간
  private int useTime; // 사용시간
  private int useMemberNumber; // 사용인원
  private int totalPrice; // 총금액
  private boolean wirteReview; // 리뷰작성 여부
  private int roomNo; // 룸 예약시 룸 번호
  private int paymentNo; // 결제시 결제 번호
  private int reservationStatus; // 0 : 결제대기 , 1 : 결제완료 , 2 : 예약취소 , 3 : 결제취소

  @Override
  public String toString() {
    return "CafeReservation [reservationNo=" + reservationNo + ", memberNo=" + memberNo
        + ", cafeNo=" + cafeNo + ", reservationDate=" + reservationDate + ", startTime=" + startTime
        + ", useTime=" + useTime + ", useMemberNumber=" + useMemberNumber + ", totalPrice="
        + totalPrice + ", wirteReview=" + wirteReview + ", roomNo=" + roomNo + ", paymentNo="
        + paymentNo + ", reservationStatus=" + reservationStatus + "]";
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

  public int getRoomNo() {
    return roomNo;
  }

  public void setRoomNo(int roomNo) {
    this.roomNo = roomNo;
  }

  public int getPaymentNo() {
    return paymentNo;
  }

  public void setPaymentNo(int paymentNo) {
    this.paymentNo = paymentNo;
  }

  public int getReservationStatus() {
    return reservationStatus;
  }

  public void setReservationStatus(int reservationStatus) {
    this.reservationStatus = reservationStatus;
  }
}
