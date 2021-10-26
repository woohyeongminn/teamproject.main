package com.ogong.pms.domain;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cafe {

  public static final int WAIT = 1;
  public static final int GENERAL = 2;
  public static final int STOP = 3;
  public static final int DELETE = 4;

  private int no; // 카페 번호
  private CeoMember ceoMember; // 사장
  private String name; // 상호명
  private String mainImg; //카페 썸네일 이미지
  private ArrayList<CafeImage> cafeImgs = new ArrayList<>(); // 카페 이미지들
  private String info; // 소개글
  private String location; // 주소
  private String phone; // 전화번호
  private LocalTime openTime; // 오픈시간
  private LocalTime closeTime; // 마감시간
  private String holiday; // 휴무일
  private Map<Integer, Date> cafeHolidays = new HashMap<>(); // 카페 휴무일들
  private int viewCount; // 조회수
  private int bookable; // 예약가능인원(개인좌석)
  private int timePrice; // 시간당금액(개인좌석)
  private int cafeStatus; // 0 : 승인대기 , 1 : 운영중 , 2 : 운영중단 3 : 삭제 -> cafeStatus 관리자, 사장 한테만 보임

  private int countReview; // 리뷰 수
  private double avgReview; // 리뷰 평균

  @Override
  public String toString() {
    return "Cafe [no=" + no + ", ceoMember=" + ceoMember + ", name=" + name + ", mainImg=" + mainImg
        + ", cafeImgs=" + cafeImgs + ", info=" + info + ", location=" + location + ", phone="
        + phone + ", openTime=" + openTime + ", closeTime=" + closeTime + ", holiday=" + holiday
        + ", cafeHolidays=" + cafeHolidays + ", viewCount=" + viewCount + ", bookable=" + bookable
        + ", timePrice=" + timePrice + ", cafeStatus=" + cafeStatus + ", countReview=" + countReview
        + ", avgReview=" + avgReview + "]";
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public CeoMember getCeoMember() {
    return ceoMember;
  }

  public void setCeoMember(CeoMember ceoMember) {
    this.ceoMember = ceoMember;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMainImg() {
    return mainImg;
  }

  public void setMainImg(String mainImg) {
    this.mainImg = mainImg;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public LocalTime getOpenTime() {
    return openTime;
  }

  public void setOpenTime(LocalTime openTime) {
    this.openTime = openTime;
  }

  public LocalTime getCloseTime() {
    return closeTime;
  }

  public void setCloseTime(LocalTime closeTime) {
    this.closeTime = closeTime;
  }

  public String getHoliday() {
    if (holiday == null) {
      return "없음";
    }
    return holiday;
  }

  public void setHoliday(String holiday) {
    this.holiday = holiday;
  }

  public int getBookable() {
    return bookable;
  }

  public void setBookable(int bookable) {
    this.bookable = bookable;
  }

  public int getTimePrice() {
    return timePrice;
  }

  public void setTimePrice(int timePrice) {
    this.timePrice = timePrice;
  }

  public int getCafeStatus() {
    return cafeStatus;
  }

  public void setCafeStatus(int cafeStatus) {
    this.cafeStatus = cafeStatus;
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public Map<Integer, Date> getCafeHolidays() {
    return cafeHolidays;
  }

  public void setCafeHolidays(Map<Integer, Date> cafeHolidays) {
    this.cafeHolidays = cafeHolidays;
  }

  public ArrayList<CafeImage> getCafeImgs() {
    return cafeImgs;
  }

  public void setCafeImgs(ArrayList<CafeImage> cafeImgs) {
    this.cafeImgs = cafeImgs;
  }

  public int getCountReview() {
    return countReview;
  }

  public void setCountReview(int countReview) {
    this.countReview = countReview;
  }

  public double getAvgReview() {
    return avgReview;
  }

  public void setAvgReview(double avgReview) {
    this.avgReview = avgReview;
  }

  public String getCafeImageNames() {
    if (cafeImgs.isEmpty()) {
      return "";
    }

    StringBuilder names = new StringBuilder();
    for (CafeImage cafeImage : cafeImgs) {
      if (names.length() > 0) {
        names.append(",");
      }
      names.append(cafeImage.getName());
    }
    return names.toString();
  }
}
