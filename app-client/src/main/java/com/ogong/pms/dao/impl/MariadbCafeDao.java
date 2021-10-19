package com.ogong.pms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.CafeRoom;
import com.ogong.pms.domain.Member;

public class MariadbCafeDao implements CafeDao {

  Connection con;

  public MariadbCafeDao (Connection con) {
    this.con = con;
  }

  // -----------------------Cafe--------------------------------------

  @Override
  public List<Cafe> getCafeList() throws Exception {
    //    requestAgent.request("cafe.selectList", null);
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println(" >> 장소 목록 조회를 실패하였습니다.");
    //      return null;
    //    }
    //    return new ArrayList<>(requestAgent.getObjects(Cafe.class));
    return null;
  }

  @Override
  public List<Cafe> getCafeListByMember() throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select c.cafe_no, c.name, c.location, c.open_time, c.close_time"
            + " from studycafe c"
            + " where c.operating_status_no != 1 and c.operating_status_no != 4"
            + " order by cafe_no asc");

        ResultSet rs = stmt.executeQuery()) {

      ArrayList<Cafe> list = new ArrayList<>();

      while(rs.next()) {
        Cafe cafe = new Cafe();

        cafe.setNo(rs.getInt("cafe_no"));
        cafe.setName(rs.getString("name"));
        cafe.setLocation(rs.getString("location"));
        cafe.setOpenTime(rs.getTime("open_time").toLocalTime());
        cafe.setCloseTime(rs.getTime("close_time").toLocalTime());

        list.add(cafe);
      }

      return list;
    }
  }

  public List<Cafe> getCafeListByCeoMember(int ceoNo) throws Exception {
    return null;
  }

  @Override
  public List<Cafe> findCafeListByLocation(String input) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select c.cafe_no, c.name, c.location, c.open_time, c.close_time"
            + " from studycafe c"
            + " where c.operating_status_no != 1 and c.operating_status_no != 4"
            + " and c.location like (concat('%',?,'%'))"
            + " order by cafe_no asc")) {

      stmt.setString(1, input);

      ArrayList<Cafe> list = new ArrayList<>();

      try (ResultSet rs = stmt.executeQuery()){

        while(rs.next()) {
          Cafe cafe = new Cafe();

          cafe.setNo(rs.getInt("cafe_no"));
          cafe.setName(rs.getString("name"));
          cafe.setLocation(rs.getString("location"));
          cafe.setOpenTime(rs.getTime("open_time").toLocalTime());
          cafe.setCloseTime(rs.getTime("close_time").toLocalTime());

          list.add(cafe);
        }      
      }
      return list;
    }
  }

  @Override
  public Cafe findByCafeNo(int cafeNo) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select c.cafe_no, c.name, c.info, c.location, c.phone, c.open_time, c.close_time,"
            + " c.view_cnt, sp.name, sh.date"
            + " from studycafe c"
            + " left outer join studycafe_photo sp on c.cafe_no = sp.cafe_no"
            + " left outer join studycafe_holiday sh on c.cafe_no = sh.cafe_no"
            + " and c.cafe_no = ?")) {

      stmt.setInt(1, cafeNo);

      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        }

        Cafe cafe = new Cafe();

        cafe.setNo(rs.getInt("cafe_no"));
        cafe.setName(rs.getString("name"));
        cafe.setInfo(rs.getString("info"));
        cafe.setLocation(rs.getString("location"));
        cafe.setPhone(rs.getString("phone"));
        cafe.setOpenTime(rs.getTime("open_time").toLocalTime());
        cafe.setCloseTime(rs.getTime("close_time").toLocalTime());
        cafe.setViewCount(rs.getInt("view_cnt"));
        cafe.setMainImg(rs.getString("sp.name"));
        cafe.setHoliday(rs.getString("date"));

        return cafe;
      }
    }
  }

  @Override
  public Cafe findByCafeNoMember(int cafeNo) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select c.cafe_no, c.name, c.info, c.location, c.phone, c.open_time, c.close_time,"
            + " c.view_cnt, sp.name, sh.date"
            + " from studycafe c"
            + " left outer join studycafe_photo sp on c.cafe_no = sp.cafe_no"
            + " left outer join studycafe_holiday sh on c.cafe_no = sh.cafe_no"
            + " where c.operating_status_no != 1 and c.operating_status_no !=4"
            + " and c.cafe_no = ?")) {

      stmt.setInt(1, cafeNo);

      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        }

        Cafe cafe = new Cafe();

        cafe.setNo(rs.getInt("cafe_no"));
        cafe.setName(rs.getString("name"));
        cafe.setInfo(rs.getString("info"));
        cafe.setLocation(rs.getString("location"));
        cafe.setPhone(rs.getString("phone"));
        cafe.setOpenTime(rs.getTime("open_time").toLocalTime());
        cafe.setCloseTime(rs.getTime("close_time").toLocalTime());
        cafe.setViewCount(rs.getInt("view_cnt"));
        cafe.setMainImg(rs.getString("sp.name"));
        cafe.setHoliday(rs.getString("date"));

        return cafe;
      }
    }
  }

  @Override
  public void insertCafe(Cafe cafe) throws Exception {
    //    requestAgent.request("cafe.insert", cafe);
    //
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println(" >> 장소 등록 실패하였습니다.");
    //    } else {
    //      System.out.println(" >> 등록되었습니다.");
    //    }
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

  @Override
  public void deleteCafe(Cafe cafe) throws Exception {
    //    requestAgent.request("cafe.update", cafe);
    //
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println(" >> 카페 삭제를 실패하였습니다.");
    //    } else {
    //      System.out.println(" >> 카페를 삭제하였습니다.");
    //    }
  }

  //-----------------------CafeReview--------------------------------------

  @Override
  public List<CafeReview> getCafeReviewList() throws Exception {
    //    requestAgent.request("cafeReview.selectList", null);
    //
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println(" >> 리뷰 목록 조회를 실패하였습니다.");
    //      return null;
    //    }
    //    return new ArrayList<>(requestAgent.getObjects(CafeReview.class));
    return null;
  }

  @Override
  public List<CafeReview> findReviewListByCafeNo(int cafeNo) throws Exception {
    return null;
  }

  @Override
  public List<CafeReview> findReviewListByMember(int memberNo) throws Exception {
    //    HashMap<String,String> params = new HashMap<>();
    //    params.put("memberNo", String.valueOf(memberNo));
    //    requestAgent.request("cafeReview.selectListByMember", params);
    //
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println(" >> 리뷰 목록 조회를 실패하였습니다.");
    //      return null;
    //    }
    //    return new ArrayList<>(requestAgent.getObjects(CafeReview.class));
    return null;
  }

  @Override
  public CafeReview findByReviewNo(int reviewNo) throws Exception {
    //    HashMap<String,String> params = new HashMap<>();
    //    params.put("reviewNo", String.valueOf(reviewNo));
    //    requestAgent.request("cafeReview.selectOne", params);
    //
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println(" >> 해당 번호의 리뷰가 존재하지 않습니다.");
    //    } else {
    //    }
    //    return requestAgent.getObject(CafeReview.class);
    return null;
  }

  @Override
  public void insertCafeReview(CafeReview cafeReview) throws Exception {
    //    requestAgent.request("cafeReview.insert", cafeReview);
    //
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println(" >> 리뷰 등록 실패");
    //    } else {
    //      System.out.println();
    //      System.out.println(" >> 리뷰가 등록되었습니다.");
    //    }
  }

  @Override
  public void deleteCafeReview(int reviewNo) throws Exception {
    //    HashMap<String,String> params = new HashMap<>();
    //    params.put("reviewNo", String.valueOf(reviewNo));
    //    requestAgent.request("cafeReview.updateReviewStatus", params);
    //
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println(" >> 리뷰 삭제 실패!");
    //    } else {
    //      System.out.println(" >> 삭제를 완료하였습니다.");
    //    }
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

          if (rs.getString("photo_name") != null) {
            cafeRoom.getRoomImgs().put(rs.getInt("photo_no"), rs.getString("photo_name"));
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
    try (PreparedStatement stmt = con.prepareStatement(
        "select rs.studycafe_rsv_no, rs.studyroom_no, rs.per_member_no, rs.rsv_dt, rs.using_dt,"
            + " rs.start_time, rs.using_time, rs.people, rs.total_price, rs.review, rst.rsv_status_no,"
            + " rst.rsv_name, c.cafe_no, c.name cafe_name"
            + " from studycafe_reservation rs"
            + " join studycafe_reservation_status rst on rs.rsv_status_no=rst.rsv_status_no"
            + " join studycafe_room sr on rs.studyroom_no=sr.studyroom_no"
            + " join studycafe c on sr.cafe_no=c.cafe_no"
            + " join per_member pm on rs.per_member_no=pm.per_member_no"
            + " where pm.member_no=" + memberNo
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
  public List<CafeReservation> findReservationListByCeoMember(int ceoNo, int cafeNo) throws Exception {
    //    HashMap<String, String> params = new HashMap<>();
    //    params.put("ceoNo", String.valueOf(ceoNo));
    //    params.put("cafeNo", String.valueOf(cafeNo));
    //    requestAgent.request("cafeReservation.selectListByCeoMember", params);
    //
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println(" >> 예약 목록 조회를 실패하였습니다.");
    //      return null;
    //    }
    //    return new ArrayList<>(requestAgent.getObjects(CafeReservation.class));
    return null;
  }

  @Override
  public CafeReservation findReservationByMember(int memberNo, int reserNo) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select rs.studycafe_rsv_no, rs.studyroom_no, rs.per_member_no, rs.rsv_dt, rs.using_dt,"
            + " rs.start_time, rs.using_time, rs.people, rs.total_price, rs.review, rst.rsv_status_no,"
            + " rst.rsv_name, c.cafe_no, c.name cafe_name"
            + " from studycafe_reservation rs"
            + " join studycafe_reservation_status rst on rs.rsv_status_no=rst.rsv_status_no"
            + " join studycafe_room sr on rs.studyroom_no=sr.studyroom_no"
            + " join studycafe c on sr.cafe_no=c.cafe_no"
            + " join per_member pm on rs.per_member_no=pm.per_member_no"
            + " where pm.member_no=? and rs.studycafe_rsv_no=?"
            + " order by rs.studycafe_rsv_no asc");) {

      stmt.setInt(1, memberNo);
      stmt.setInt(2, reserNo);

      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        }

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

        return cafeReservation;
      }
    }
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

  @Override
  public void updateWirteReview(int reservationNo) throws Exception {
    //    HashMap<String,String> params = new HashMap<>();
    //    params.put("reservationNo", String.valueOf(reservationNo));
    //
    //    requestAgent.request("cafeReservation.updateWirteReview", params);
    //
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println(" >> 상태 변경 실패");
    //    } else {
    //      //        System.out.println();
    //      //        System.out.println(" >> 상태 변경 성공");
    //    }
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
