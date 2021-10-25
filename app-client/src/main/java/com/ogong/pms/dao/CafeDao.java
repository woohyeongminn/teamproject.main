package com.ogong.pms.dao;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.CafeRoom;

public interface CafeDao {

  //-----------------------Cafe--------------------------------------

  List<Cafe> getCafeList() throws Exception;
  List<Cafe> getCafeListByMember() throws Exception;
  List<Cafe> findCafeListByLocation(String input) throws Exception;

  Cafe findByCafeNo(int cafeNo) throws Exception;
  Cafe findByCafeNoMember(int cafeNo) throws Exception;
  Cafe findByCeoMember(int ceoNo) throws Exception;

  // 카페 휴무일
  String getCafeHoliday(HashMap<String,Object> params) throws Exception;
  void insertCafeHolidays(HashMap<String,Object> params) throws Exception;

  void insertCafe(Cafe cafe) throws Exception;
  void updateCafe(Cafe cafe) throws Exception;
  void deleteCafe(int cafeNo) throws Exception;

  // 관리자 카페 승인
  void updateCafeStatusToGENERAL(int cafeNo) throws Exception;

  // 카페 이미지
  void insertCafeImage(HashMap<String,Object> params) throws Exception;
  void deleteCafeImage(HashMap<String,Object> param) throws Exception;

  //-----------------------CafeReview--------------------------------------

  List<CafeReview> getCafeReviewList() throws Exception;
  List<CafeReview> findReviewListByCafeNo(int cafeNo) throws Exception;
  List<CafeReview> findReviewListByMember(int memberNo) throws Exception;
  CafeReview findByReviewNo(int reviewNo) throws Exception;
  void insertCafeReview(CafeReview cafeReview) throws Exception;
  void deleteCafeReview(int reviewNo) throws Exception;

  //-----------------------CafeRoom--------------------------------------

  List<CafeRoom> getCafeRoomList() throws Exception;
  List<CafeRoom> findCafeRoomListByCafe(int cafeNo) throws Exception;
  CafeRoom findByRoomNo(int roomNo) throws Exception;
  void insertCafeRoom(CafeRoom cafeRoom) throws Exception;
  void updateCafeRoom(CafeRoom cafeRoom) throws Exception;
  void deleteCafeRoom(int roomNo) throws Exception;

  // 스터디룸 이미지
  void insertCafeRoomImage(HashMap<String,Object> params) throws Exception;
  void deleteCafeRoomImage(HashMap<String,Object> params) throws Exception;

  //-----------------------CafeReservation--------------------------------------

  List<CafeReservation> getCafeReservationList() throws Exception;
  List<CafeReservation> findReservationListByMember(int memberNo) throws Exception;
  List<CafeReservation> findReservationListByCeoMember(int ceoNo) throws Exception;
  CafeReservation findReservationByMember(@Param("memberNo")int memberNo, @Param("reserNo")int reserNo) throws Exception;
  void insertReservation(CafeReservation cafeReservation) throws Exception;
  void deleteReservation(@Param("reservationNo")int reservationNo, @Param("status")int status) throws Exception;

  // 리뷰 등록시 예약테이블에도 리뷰 등록했다고 변경
  void updateCafeReservationReviewStatus(int reservationNo) throws Exception;
}
