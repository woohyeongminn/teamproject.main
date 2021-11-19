package com.ogong.pms.dao;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ogong.pms.vo.CafeReservation;

public interface CafeReservationDao {

  //-----------------------CafeReservation--------------------------------------

  List<CafeReservation> getCafeReservationList() throws Exception;
  List<CafeReservation> findReservationListByMember(@Param("memberNo")int memberNo, @Param("offset")int offset, @Param("length")int length) throws Exception;
  List<CafeReservation> searchReservationListByMember(HashMap<String,Object> params) throws Exception;
  int countByMember(@Param("memberNo")int memberNo, @Param("searchDate")String searchDate) throws Exception;
  int countByMember(HashMap<String,Object> params) throws Exception;
  List<CafeReservation> findReservationListByDate(@Param("selectedDate")String selectedDate, @Param("roomNo")int roomNo) throws Exception;
  List<CafeReservation> findReservationListByCeoMember(int ceoNo) throws Exception;
  CafeReservation findReservationByMember(@Param("memberNo")int memberNo, @Param("reserNo")int reserNo) throws Exception;
  CafeReservation findReservationByCeoMember(@Param("ceoNo")int ceoNo, @Param("reserNo")int reserNo) throws Exception;
  void insertReservation(CafeReservation cafeReservation) throws Exception;
  void insertPaymentReservation(CafeReservation cafeReservation) throws Exception;
  void updateReservationStatusComplete() throws Exception;
  void deleteReservation(@Param("reservationNo")int reservationNo, @Param("status")int status) throws Exception;

  // 리뷰 등록시 예약테이블에도 리뷰 등록했다고 변경
  void updateCafeReservationReviewStatus(int reservationNo) throws Exception;

}
