//package com.ogong.pms.dao.impl;
//
//import java.sql.Connection;
//import java.util.HashMap;
//import java.util.List;
//import org.apache.ibatis.session.SqlSession;
//import com.ogong.pms.dao.CafeDao;
//import com.ogong.pms.domain.Cafe;
//import com.ogong.pms.domain.CafeReservation;
//import com.ogong.pms.domain.CafeReview;
//import com.ogong.pms.domain.CafeRoom;
//
//public class MybatisCafeDao implements CafeDao {
//
//  Connection con;
//  SqlSession sqlSession;
//
//  public MybatisCafeDao (SqlSession sqlSession) {
//    this.sqlSession = sqlSession;
//  }
//
//  // -----------------------Cafe--------------------------------------
//
//  @Override
//  public List<Cafe> getCafeList() throws Exception {
//    return sqlSession.selectList("CafeMapper.getCafeList");
//  }
//
//  @Override
//  public List<Cafe> getCafeListByMember() throws Exception {
//    return sqlSession.selectList("CafeMapper.getCafeListByMember");
//  }
//
//  @Override
//  public List<Cafe> findCafeListByLocation(String input) throws Exception {
//    return sqlSession.selectList("CafeMapper.findCafeListByLocation", input);
//  }
//
//  @Override
//  public Cafe findByCafeNo(int cafeNo) throws Exception {
//    return sqlSession.selectOne("CafeMapper.findByCafeNo", cafeNo);
//  }
//
//  @Override
//  public Cafe findByCafeNoMember(int cafeNo) throws Exception {
//    return sqlSession.selectOne("CafeMapper.findByCafeNoMember", cafeNo);
//  }
//
//  @Override
//  public Cafe findByCeoMember(int ceoNo) throws Exception {
//    return sqlSession.selectOne("CafeMapper.findByCeoMember", ceoNo);
//  }
//
//  @Override
//  public String getCafeHoliday(HashMap<String,Object> params) throws Exception {
//    return sqlSession.selectOne("CafeMapper.getCafeHoliday", params);
//  }
//
//  @Override
//  public void insertCafe(Cafe cafe) throws Exception {
//    sqlSession.insert("CafeMapper.insertCafe", cafe);
//    sqlSession.commit();
//  }
//
//  @Override
//  public void insertCafeImage(HashMap<String,Object> params) throws Exception {
//    sqlSession.insert("CafeMapper.insertCafeImage", params);
//    sqlSession.commit();
//  }
//
//  @Override
//  public void deleteCafeImage(HashMap<String,Object> params) throws Exception {
//    sqlSession.insert("CafeMapper.deleteCafeImage", params);
//    sqlSession.commit();
//
//  }
//
//  @Override
//  public void updateCafe(Cafe cafe) throws Exception {
//    sqlSession.update("CafeMapper.updateCafe", cafe);
//    sqlSession.commit();
//  }
//
//  public void updateCafeStatusToGENERAL(Cafe cafe) throws Exception {
//    sqlSession.update("CafeMapper.updateCafeStatusToGENERAL", cafe.getNo());
//    sqlSession.commit();
//  }
//
//  @Override
//  public void deleteCafe(Cafe cafe) throws Exception {
//    sqlSession.update("CafeMapper.deleteCafe", cafe.getNo());
//    sqlSession.commit();
//  }
//
//  //-----------------------CafeReview--------------------------------------
//
//  @Override
//  public List<CafeReview> getCafeReviewList() throws Exception {
//    return sqlSession.selectList("CafeMapper.getCafeReviewList");
//  }
//
//  @Override
//  public List<CafeReview> findReviewListByCafeNo(int cafeNo) throws Exception {
//    return sqlSession.selectList("CafeMapper.findReviewListByCafeNo", cafeNo);
//  }
//
//  @Override
//  public List<CafeReview> findReviewListByMember(int memberNo) throws Exception {
//    return sqlSession.selectList("CafeMapper.findReviewListByMember", memberNo);
//  }
//
//  @Override
//  public CafeReview findByReviewNo(int reviewNo) throws Exception {
//    return sqlSession.selectOne("CafeMapper.findByReviewNo", reviewNo);
//  }
//
//  @Override
//  public void insertCafeReview(CafeReview cafeReview) throws Exception {
//    sqlSession.insert("CafeMapper.insertCafeReview", cafeReview);
//    sqlSession.commit();
//  }
//
//  @Override
//  public void deleteCafeReview(int reviewNo) throws Exception {
//    sqlSession.delete("CafeMapper.deleteCafeReview", reviewNo);
//    sqlSession.commit();
//  }
//
//  //-----------------------CafeRoom--------------------------------------
//
//  @Override
//  public List<CafeRoom> getCafeRoomList() throws Exception {
//    return sqlSession.selectList("CafeMapper.getCafeRoomList");
//  }
//
//  @Override
//  public List<CafeRoom> findCafeRoomListByCafe(int cafeNo) throws Exception {
//    return sqlSession.selectList("CafeMapper.findCafeRoomListByCafe", cafeNo);
//  }
//
//  @Override
//  public CafeRoom findByRoomNo(int roomNo) throws Exception {
//    return sqlSession.selectOne("CafeMapper.findByRoomNo", roomNo);
//  }
//
//  @Override
//  public void insertCafeRoom(CafeRoom cafeRoom) throws Exception {
//    sqlSession.insert("CafeMapper.insertCafeRoom", cafeRoom);
//    sqlSession.commit();
//  }
//
//  @Override
//  public void updateCafeRoom(CafeRoom cafeRoom) throws Exception {
//    sqlSession.update("CafeMapper.updateCafeRoom", cafeRoom);
//    sqlSession.commit();
//  }
//
//  @Override
//  public void deleteCafeRoom(int roomNo) throws Exception {
//    sqlSession.delete("CafeMapper.deleteCafeRoom", roomNo);
//    sqlSession.commit();
//  }
//
//  @Override
//  public void insertCafeRoomImage(HashMap<String,Object> params) throws Exception {
//    sqlSession.insert("CafeMapper.insertCafeRoomImage", params);
//    sqlSession.commit();
//  }
//
//  @Override
//  public void deleteCafeRoomImage(HashMap<String,Object> params) throws Exception {
//    sqlSession.insert("CafeMapper.deleteCafeRoomImage", params);
//    sqlSession.commit();
//  }
//
//  //-----------------------CafeReservation--------------------------------------
//
//  @Override
//  public List<CafeReservation> getCafeReservationList() throws Exception {
//    return sqlSession.selectList("CafeMapper.getCafeReservationList");
//  }
//
//  @Override
//  public List<CafeReservation> findReservationListByMember(int memberNo) throws Exception {
//    return sqlSession.selectList("CafeMapper.findReservationListByMember", memberNo);
//  }
//
//  @Override
//  public List<CafeReservation> findReservationListByCeoMember(int ceoNo) throws Exception {
//    return sqlSession.selectList("CafeMapper.findReservationListByCeoMember", ceoNo);
//  }
//
//  @Override
//  public CafeReservation findReservationByMember(int memberNo, int reserNo) throws Exception {
//    HashMap<String,Object> params = new HashMap<>();
//    params.put("memberNo", memberNo);
//    params.put("reserNo", reserNo);
//    return sqlSession.selectOne("CafeMapper.findReservationByMember", params);
//  }
//
//  @Override
//  public void insertReservation(CafeReservation cafeReservation) throws Exception {
//    sqlSession.insert("CafeMapper.insertReservation", cafeReservation);
//    sqlSession.commit();
//  }
//
//  @Override
//  public void updateCafeReservationReviewStatus(int reservationNo) throws Exception {
//    sqlSession.update("CafeMapper.updateCafeReservationReviewStatus", reservationNo);
//    sqlSession.commit();
//  }
//
//  @Override
//  public void deleteReservation(CafeReservation cafeReservation, int status) throws Exception {
//    HashMap<String,Object> params = new HashMap<>();
//    params.put("status", status);
//    params.put("reservationNo", cafeReservation.getReservationNo());
//    sqlSession.delete("CafeMapper.deleteReservation", params);
//    sqlSession.commit();
//  }
//
//
//}
