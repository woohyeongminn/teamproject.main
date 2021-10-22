package com.ogong.pms.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeImage;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.CafeRoom;

public class MybatisCafeDao implements CafeDao {

  Connection con;
  SqlSession sqlSession;

  public MybatisCafeDao (SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  // -----------------------Cafe--------------------------------------

  @Override
  public List<Cafe> getCafeList() throws Exception {
    return sqlSession.selectList("CafeMapper.getCafeList");
  }

  @Override
  public List<Cafe> getCafeListByMember() throws Exception {
    return sqlSession.selectList("CafeMapper.getCafeListByMember");
  }

  @Override
  public List<Cafe> findCafeListByLocation(String input) throws Exception {
    return sqlSession.selectList("CafeMapper.findCafeListByLocation", input);
  }

  @Override
  public Cafe findByCafeNo(int cafeNo) throws Exception {
    Cafe cafe = sqlSession.selectOne("CafeMapper.findByCafeNo", cafeNo);

    HashMap<String,Object> params = new HashMap<>();
    params.put("cafeNo", cafeNo);
    cafe.setHoliday(sqlSession.selectOne("CafeMapper.getCafeHoliday", params));

    return cafe;
  }

  @Override
  public Cafe findByCafeNoMember(int cafeNo) throws Exception {
    Cafe cafe = sqlSession.selectOne("CafeMapper.findByCafeNoMember", cafeNo);

    HashMap<String,Object> params = new HashMap<>();
    params.put("cafeNo", cafeNo);
    cafe.setHoliday(sqlSession.selectOne("CafeMapper.getCafeHoliday", params));

    return cafe;
  }

  @Override
  public Cafe findByCeoMember(int ceoNo) throws Exception {
    Cafe cafe = sqlSession.selectOne("CafeMapper.findByCeoMember", ceoNo);

    HashMap<String,Object> params = new HashMap<>();
    params.put("ceoNo", ceoNo);
    cafe.setHoliday(sqlSession.selectOne("CafeMapper.getCafeHoliday", params));

    return cafe;
  }

  @Override
  public void insertCafe(Cafe cafe, ArrayList<CafeImage> fileNames, ArrayList<Date> holidays) throws Exception {
    sqlSession.insert("CafeMapper.insertCafe", cafe);

    if (!fileNames.isEmpty()) {
      HashMap<String,Object> params = new HashMap<>();
      params.put("fileNames", fileNames);
      params.put("cafeNo", cafe.getNo());

      sqlSession.insert("CafeMapper.insertCafeImage", params);
    }

    if (!holidays.isEmpty()) {
      // 해야됨 고민중
    }

    sqlSession.commit();
    System.out.println(" >> 카페 등록 완료!");

  }

  @Override
  public void updateCafe(Cafe cafe) throws Exception {
    //    requestAgent.request("cafe.update", cafe);
    //
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println(" >> 카페 수정을 실패하였습니다.");
    //    } else {
    //      //      System.out.println(" >> 수정이 완료 되었습니다.");
    //    }
  }

  public void updateCafeStatusToGENERAL(Cafe cafe) throws Exception {
    sqlSession.update("CafeMapper.updateCafeStatusToGENERAL", cafe.getNo());
    sqlSession.commit();
  }

  @Override
  public void deleteCafe(Cafe cafe) throws Exception {
    sqlSession.update("CafeMapper.deleteCafe", cafe.getNo());
    sqlSession.commit();
  }

  //-----------------------CafeReview--------------------------------------

  @Override
  public List<CafeReview> getCafeReviewList() throws Exception {
    return sqlSession.selectList("CafeMapper.getCafeReviewList");
  }

  @Override
  public List<CafeReview> findReviewListByCafeNo(int cafeNo) throws Exception {
    return sqlSession.selectList("CafeMapper.findReviewListByCafeNo", cafeNo);
  }

  @Override
  public List<CafeReview> findReviewListByMember(int memberNo) throws Exception {
    return sqlSession.selectList("CafeMapper.findReviewListByMember", memberNo);
  }

  @Override
  public CafeReview findByReviewNo(int reviewNo) throws Exception {
    return sqlSession.selectOne("CafeMapper.findByReviewNo", reviewNo);
  }

  @Override
  public void insertCafeReview(CafeReview cafeReview) throws Exception {
    sqlSession.insert("CafeMapper.insertCafeReview", cafeReview);
    sqlSession.update("CafeMapper.updateCafeReservationReviewStatus", cafeReview.getReservationNo());
    sqlSession.commit();
  }

  @Override
  public void deleteCafeReview(int reviewNo) throws Exception {
    sqlSession.delete("CafeMapper.deleteCafeReview", reviewNo);
    sqlSession.commit();
  }

  //-----------------------CafeRoom--------------------------------------

  @Override
  public List<CafeRoom> getCafeRoomList() throws Exception {
    return sqlSession.selectList("CafeMapper.getCafeRoomList");
  }

  @Override
  public List<CafeRoom> findCafeRoomListByCafe(int cafeNo) throws Exception {
    return sqlSession.selectList("CafeMapper.findCafeRoomListByCafe", cafeNo);
  }

  @Override
  public CafeRoom findByRoomNo(int roomNo) throws Exception {
    return sqlSession.selectOne("CafeMapper.findByRoomNo", roomNo);
  }

  @Override
  public void insertCafeRoom(CafeRoom cafeRoom, ArrayList<CafeImage> fileNames) throws Exception {
    sqlSession.insert("CafeMapper.insertCafeRoom", cafeRoom);

    if (!fileNames.isEmpty()) {
      HashMap<String,Object> params = new HashMap<>();
      params.put("fileNames", fileNames);
      params.put("cafeRoomNo", cafeRoom.getRoomNo());

      sqlSession.insert("CafeMapper.insertCafeRoomImage", params);
    }
  }

  @Override
  public void updateCafeRoom(CafeRoom cafeRoom) throws Exception {
    sqlSession.update("CafeMapper.updateCafeRoom", cafeRoom);
  }

  @Override
  public void deleteCafeRoom(int roomNo) throws Exception {
    sqlSession.delete("CafeMapper.deleteCafeRoom", roomNo);
  }

  //-----------------------CafeReservation--------------------------------------

  @Override
  public List<CafeReservation> getCafeReservationList() throws Exception {
    return sqlSession.selectList("CafeMapper.getCafeReservationList");
  }

  @Override
  public List<CafeReservation> findReservationListByMember(int memberNo) throws Exception {
    return sqlSession.selectList("CafeMapper.findReservationListByMember", memberNo);
  }

  @Override
  public List<CafeReservation> findReservationListByCeoMember(int ceoNo) throws Exception {
    return sqlSession.selectList("CafeMapper.findReservationListByCeoMember", ceoNo);
  }

  @Override
  public CafeReservation findReservationByMember(int memberNo, int reserNo) throws Exception {
    HashMap<String,Object> params = new HashMap<>();
    params.put("memberNo", memberNo);
    params.put("reserNo", reserNo);
    return sqlSession.selectOne("CafeMapper.findReservationByMember", params);
  }

  @Override
  public void insertReservation(CafeReservation cafeReservation) throws Exception {
    sqlSession.insert("CafeMapper.insertReservation", cafeReservation);
  }

  // mariadb에선 필요 없으나 다른 dao에서는 쓰는거라 일단 남겨둠
  @Override
  public void updateWirteReview(int reservationNo) throws Exception {
  }

  @Override
  public void deleteReservation(CafeReservation cafeReservation, int status) throws Exception {
    HashMap<String,Object> params = new HashMap<>();
    params.put("status", status);
    params.put("reservationNo", cafeReservation.getReservationNo());
    sqlSession.delete("CafeMapper.deleteReservation", params);
  }


}
