package com.ogong.pms.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
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
import com.ogong.pms.domain.Member;

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
    return sqlSession.selectOne("CafeMapper.findByCafeNo", cafeNo);
  }

  @Override
  public Cafe findByCafeNoMember(int cafeNo) throws Exception {
    return sqlSession.selectOne("CafeMapper.findByCafeNoMember", cafeNo);
  }

  @Override
  public Cafe findByCeoMember(int ceoNo) throws Exception {
    return sqlSession.selectOne("CafeMapper.findByCeoMember", ceoNo);
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
    try (PreparedStatement stmt = con.prepareStatement(
        "select studyroom_no, name, cafe_no"
            + " from studycafe_room")) {

      ArrayList<CafeRoom> list = new ArrayList<>();

      try (ResultSet rs = stmt.executeQuery()) {
        while(rs.next()) {
          CafeRoom cafeRoom = new CafeRoom();

          cafeRoom.setRoomNo(rs.getInt("studyroom_no"));
          cafeRoom.setRoomName(rs.getString("name"));
          Cafe cafe = new Cafe();
          cafe.setNo(rs.getInt("cafe_no"));
          cafeRoom.setCafe(cafe);

          list.add(cafeRoom);
        }
      }
      return list;
    }
  }

  @Override
  public List<CafeRoom> findCafeRoomListByCafe(int cafeNo) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select studyroom_no, name"
            + " from studycafe_room"
            + " where cafe_no = ?"
            + " order by studyroom_no asc")) {

      stmt.setInt(1, cafeNo);

      ArrayList<CafeRoom> list = new ArrayList<>();

      try (ResultSet rs = stmt.executeQuery()) {
        while(rs.next()) {
          CafeRoom cafeRoom = new CafeRoom();

          cafeRoom.setRoomNo(rs.getInt("studyroom_no"));
          cafeRoom.setRoomName(rs.getString("name"));

          list.add(cafeRoom);
        }
      }
      return list;
    }
  }

  @Override
  public CafeRoom findByRoomNo(int roomNo) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select r.studyroom_no, r.name room_name, r.cafe_no, r.introduction, r.people, r.hourly_amount,"
            + " rp.photo_no, rp.name photo_name"
            + " from studycafe_room r"
            + " left outer join studycafe_room_photo rp on r.studyroom_no=rp.studyroom_no"
            + " where r.studyroom_no = ?")) {

      stmt.setInt(1, roomNo);

      CafeRoom cafeRoom = null;

      try (ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
          if (cafeRoom == null) {
            cafeRoom = new CafeRoom();

            cafeRoom.setRoomNo(rs.getInt("studyroom_no"));
            cafeRoom.setRoomName(rs.getString("room_name"));
            Cafe cafe = new Cafe();
            cafe.setNo(rs.getInt("cafe_no"));
            cafeRoom.setCafe(cafe);
            cafeRoom.setRoomInfo(rs.getString("introduction"));
            cafeRoom.setPeople(rs.getInt("people"));
            cafeRoom.setRoomPrice(rs.getInt("hourly_amount"));
          }

          String photoName = rs.getString("photo_name");

          if (photoName != null) {
            cafeRoom.getRoomImgs().put(rs.getInt("photo_no"), photoName);
          }
        }
        return cafeRoom;
      }
    }
  }

  @Override
  public void insertCafeRoom(CafeRoom cafeRoom) throws Exception {
    //    requestAgent.request("cafeRoom.insert", cafeRoom);
    //
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println(" >> 스터디룸 등록을 실패하였습니다.");
    //    } else {
    //      System.out.println(" >> 등록되었습니다.");
    //    }
  }

  @Override
  public void updateCafeRoom(CafeRoom cafeRoom) throws Exception {
    //    requestAgent.request("cafeRoom.update", cafeRoom);
    //
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println(" >> 스터디룸 수정을 실패하였습니다.");
    //    } else {
    //      System.out.println(" >> 수정이 완료 되었습니다.");
    //    }
  }

  @Override
  public void deleteCafeRoom(int roomNo) throws Exception {
    //    HashMap<String,String> params = new HashMap<>();
    //    params.put("roomNo", String.valueOf(roomNo));
    //    requestAgent.request("cafeRoom.delete", params);
    //
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println(" >> 스터디룸 삭제 실패하였습니다.");
    //    } else {
    //      System.out.println(" >> 스터디룸을 삭제하였습니다.");
    //    }
  }

  //-----------------------CafeReservation--------------------------------------

  @Override
  public List<CafeReservation> getCafeReservationList() throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select rs.studycafe_rsv_no, rs.studyroom_no, rs.per_member_no, rs.rsv_dt, rs.using_dt,"
            + " rs.start_time, rs.using_time, rs.people, rs.total_price, rs.review, rst.rsv_status_no,"
            + " rst.rsv_name, c.cafe_no, c.name cafe_name"
            + " from studycafe_reservation rs"
            + " join studycafe_reservation_status rst on rs.rsv_status_no=rst.rsv_status_no"
            + " join studycafe_room sr on rs.studyroom_no=sr.studyroom_no"
            + " join studycafe c on sr.cafe_no=c.cafe_no"
            + " order by rs.studycafe_rsv_no asc");

        ResultSet rs = stmt.executeQuery()) {

      ArrayList<CafeReservation> list = new ArrayList<>();

      while(rs.next()) {
        CafeReservation cafeReservation = new CafeReservation();

        cafeReservation.setReservationNo(rs.getInt("studycafe_rsv_no"));
        cafeReservation.setRoomNo(rs.getInt("studyroom_no"));

        Member member = new Member();
        member.setPerNo(rs.getInt("per_member_no"));
        cafeReservation.setMember(member);

        cafeReservation.setReservationDate(rs.getDate("rsv_dt"));
        cafeReservation.setUseDate(rs.getDate("using_dt"));
        cafeReservation.setStartTime(rs.getTime("start_time").toLocalTime());
        cafeReservation.setUseTime(rs.getInt("using_time"));
        cafeReservation.setUseMemberNumber(rs.getInt("people"));
        cafeReservation.setTotalPrice(rs.getInt("total_price"));

        int review = rs.getInt("review");
        boolean isReview = false;
        if (review == 2) {
          isReview = true;
        }
        cafeReservation.setWirteReview(isReview);
        cafeReservation.setReservationStatus(rs.getInt("rsv_status_no"));
        cafeReservation.setReservationStatusName(rs.getString("rsv_name"));

        Cafe cafe = new Cafe();
        cafe.setNo(rs.getInt("cafe_no"));
        cafe.setName(rs.getString("cafe_name"));
        cafeReservation.setCafe(cafe);

        list.add(cafeReservation);
      }

      return list;
    }
  }

  @Override
  public List<CafeReservation> findReservationListByMember(int memberNo) throws Exception {
    return sqlSession.selectList("CafeMapper.findReservationListByMember", memberNo);
  }

  @Override
  public List<CafeReservation> findReservationListByCeoMember(int ceoNo) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select rs.studycafe_rsv_no, rs.studyroom_no, rs.per_member_no, rs.rsv_dt, rs.using_dt,"
            + " rs.start_time, rs.using_time, rs.people, rs.total_price, rs.review, rst.rsv_status_no,"
            + " rst.rsv_name, c.cafe_no, c.name cafe_name"
            + " from studycafe_reservation rs"
            + " join studycafe_reservation_status rst on rs.rsv_status_no=rst.rsv_status_no"
            + " join studycafe_room sr on rs.studyroom_no=sr.studyroom_no"
            + " join studycafe c on sr.cafe_no=c.cafe_no"
            + " where c.ceo_member_no=" + ceoNo
            + " order by rs.studycafe_rsv_no asc");

        ResultSet rs = stmt.executeQuery()) {

      ArrayList<CafeReservation> list = new ArrayList<>();

      while(rs.next()) {
        CafeReservation cafeReservation = new CafeReservation();

        cafeReservation.setReservationNo(rs.getInt("studycafe_rsv_no"));
        cafeReservation.setRoomNo(rs.getInt("studyroom_no"));

        Member member = new Member();
        member.setPerNo(rs.getInt("per_member_no"));
        cafeReservation.setMember(member);

        cafeReservation.setReservationDate(rs.getDate("rsv_dt"));
        cafeReservation.setUseDate(rs.getDate("using_dt"));
        cafeReservation.setStartTime(rs.getTime("start_time").toLocalTime());
        cafeReservation.setUseTime(rs.getInt("using_time"));
        cafeReservation.setUseMemberNumber(rs.getInt("people"));
        cafeReservation.setTotalPrice(rs.getInt("total_price"));

        int review = rs.getInt("review");
        boolean isReview = false;
        if (review == 2) {
          isReview = true;
        }
        cafeReservation.setWirteReview(isReview);
        cafeReservation.setReservationStatus(rs.getInt("rsv_status_no"));
        cafeReservation.setReservationStatusName(rs.getString("rsv_name"));

        Cafe cafe = new Cafe();
        cafe.setNo(rs.getInt("cafe_no"));
        cafe.setName(rs.getString("cafe_name"));
        cafeReservation.setCafe(cafe);

        list.add(cafeReservation);
      }

      return list;
    }
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
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into studycafe_reservation(studyroom_no, per_member_no, using_dt, start_time,"
            + " using_time, people, total_price, rsv_status_no, review) values(?,?,?,?,?,?,?,?,?)")) {

      stmt.setInt(1, cafeReservation.getRoomNo());
      stmt.setInt(2, cafeReservation.getMember().getPerNo());
      stmt.setDate(3, cafeReservation.getUseDate());
      stmt.setTime(4, Time.valueOf(cafeReservation.getStartTime()));
      stmt.setInt(5, cafeReservation.getUseTime());
      stmt.setInt(6, cafeReservation.getUseMemberNumber());
      stmt.setInt(7, cafeReservation.getTotalPrice());
      stmt.setInt(8, cafeReservation.getReservationStatus());
      stmt.setInt(9, 1);

      if (stmt.executeUpdate() == 0) {
        throw new Exception("스터디룸 예약 실패!");
      }
    }
  }

  // mariadb에선 필요 없으나 다른 dao에서는 쓰는거라 일단 남겨둠
  @Override
  public void updateWirteReview(int reservationNo) throws Exception {
  }

  @Override
  public void deleteReservation(CafeReservation cafeReservation, int status) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update studycafe_reservation set rsv_status_no = ?"
            + " where studycafe_rsv_no = ?")) {

      stmt.setInt(1, status);
      stmt.setInt(2, cafeReservation.getReservationNo());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("예약 취소 실패!");
      }

      if (status == 3) {
        System.out.println(" >> 예약이 취소되었습니다.");
      } else if (status == 5) {
        System.out.println(" >> 예약을 거절하였습니다.");
      }
    }
  }


}
