package com.ogong.pms.domain;

import java.sql.Date;

public class CafeReservation {

  private int reservationNo; // 예약 번호
  private Member member; // 예약 회원
  private Cafe cafe; // 예약 카페
  private Date reservationDate; // 예약 날짜
  private int startTime; // 시작시간
  private int useTime; // 사용시간
  private int useMemberNumber; // 사용인원
  private int totalPrice; // 총금액
  private boolean wirteReview; // 리뷰작성 여부

  @Override
  public String toString() {
    return "CafeReservation [reservationNo=" + reservationNo + ", member=" + member + ", cafe="
        + cafe + ", reservationDate=" + reservationDate + ", startTime=" + startTime + ", useTime="
        + useTime + ", useMemberNumber=" + useMemberNumber + ", totalPrice=" + totalPrice
        + ", wirteReview=" + wirteReview + "]";
  }

  public int getReservationNo() {
    return reservationNo;
  }

  public void setReservationNo(int reservationNo) {
    this.reservationNo = reservationNo;
  }

  public Member getMember() {
    return member;
  }

  public void setMember(Member member) {
    this.member = member;
  }

  public Cafe getCafe() {
    return cafe;
  }

  public void setCafe(Cafe cafe) {
    this.cafe = cafe;
  }

  public Date getReservationDate() {
    return reservationDate;
  }

  public void setReservationDate(Date reservationDate) {
    this.reservationDate = reservationDate;
  }

  public int getStartTime() {
    return startTime;
  }

  public void setStartTime(int startTime) {
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
