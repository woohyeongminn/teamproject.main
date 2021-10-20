package com.ogong.pms.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.CafeRoom;
import com.ogong.pms.domain.CeoMember;
import com.ogong.pms.domain.Member;

public class MariadbCafeDao implements CafeDao {

  Connection con;

  public MariadbCafeDao (Connection con) {
    this.con = con;
  }

  // -----------------------Cafe--------------------------------------

  @Override
  public List<Cafe> getCafeList() throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select c.cafe_no, c.name, c.location, c.open_time, c.close_time, c.operating_status_no"
            + " from studycafe c"
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
        cafe.setCafeStatus(rs.getInt("operating_status_no"));

        list.add(cafe);
      }

      return list;
    }
  }

  @Override
  public List<Cafe> getCafeListByMember() throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select c.cafe_no, c.name, c.location, c.open_time, c.close_time, c.operating_status_no"
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
        cafe.setCafeStatus(rs.getInt("operating_status_no"));

        list.add(cafe);
      }

      return list;
    }
  }

  @Override
  public List<Cafe> findCafeListByLocation(String input) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select c.cafe_no, c.name, c.location, c.open_time, c.close_time, c.operating_status_no"
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
          cafe.setCafeStatus(rs.getInt("operating_status_no"));

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
            + " c.view_cnt, sp.name photo_name, sh.date, c.operating_status_no, c.ceo_member_no,"
            + " cm.bossname, cm.license_no"
            + " from studycafe c"
            + " left outer join studycafe_photo sp on c.cafe_no = sp.cafe_no"
            + " left outer join studycafe_holiday sh on c.cafe_no = sh.cafe_no"
            + " join ceo_member cm on c.ceo_member_no=cm.ceo_member_no"
            + " where c.cafe_no = ?")) {

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
        cafe.setCafeStatus(rs.getInt("operating_status_no"));

        CeoMember ceoMember = new CeoMember();
        ceoMember.setCeoNo(rs.getInt("ceo_member_no"));
        ceoMember.setCeoBossName(rs.getString("bossname"));
        ceoMember.setCeoLicenseNo(rs.getString("license_no"));
        cafe.setCeoMember(ceoMember);

        return cafe;
      }
    }
  }

  @Override
  public Cafe findByCafeNoMember(int cafeNo) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select c.cafe_no, c.name, c.info, c.location, c.phone, c.open_time, c.close_time,"
            + " c.view_cnt, sp.name photo_name, sh.date, c.operating_status_no"
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
        cafe.setCafeStatus(rs.getInt("operating_status_no"));

        return cafe;
      }
    }
  }

  @Override
  public Cafe findByCeoMember(int ceoNo) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select c.cafe_no, c.name, c.info, c.location, c.phone, c.open_time, c.close_time,"
            + " c.view_cnt, sp.name photo_name, sh.date, c.operating_status_no, c.ceo_member_no,"
            + " cm.bossname, cm.license_no"
            + " from studycafe c"
            + " left outer join studycafe_photo sp on c.cafe_no = sp.cafe_no"
            + " left outer join studycafe_holiday sh on c.cafe_no = sh.cafe_no"
            + " join ceo_member cm on c.ceo_member_no=cm.ceo_member_no"
            + " where c.ceo_member_no = ? and c.operating_status_no !=4")) {

      stmt.setInt(1, ceoNo);

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
        cafe.setCafeStatus(rs.getInt("operating_status_no"));

        CeoMember ceoMember = new CeoMember();
        ceoMember.setCeoNo(rs.getInt("ceo_member_no"));
        ceoMember.setCeoBossName(rs.getString("bossname"));
        ceoMember.setCeoLicenseNo(rs.getString("license_no"));
        cafe.setCeoMember(ceoMember);

        return cafe;
      }
    }
  }

  @Override
  public void insertCafe(Cafe cafe, ArrayList<String> fileNames, ArrayList<Date> holidays) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into studycafe("
            + " name,info,location,phone,open_time,close_time,ceo_member_no,operating_status_no)"
            + " values(?,?,?,?,?,?,?,1)",
            Statement.RETURN_GENERATED_KEYS)) {

      stmt.setString(1, cafe.getName());
      stmt.setString(2, cafe.getInfo());
      stmt.setString(3, cafe.getLocation());
      stmt.setString(4, cafe.getPhone());
      stmt.setTime(5, Time.valueOf(cafe.getOpenTime()));
      stmt.setTime(6, Time.valueOf(cafe.getCloseTime()));
      stmt.setInt(7, cafe.getCeoMember().getCeoNo());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("카페 등록 실패!");
      }

      int cafeNo = 0;
      try (ResultSet pkRS = stmt.getGeneratedKeys()) {
        if (pkRS.next()) {
          cafeNo = pkRS.getInt("cafe_no");
        }
      }

      if (!fileNames.isEmpty()) {
        try (PreparedStatement stmt2 = con.prepareStatement(
            "insert into studycafe_photo(name, cafe_no) values(?,?)")) {
          for (String fileName : fileNames) {
            stmt2.setString(1, fileName);
            stmt2.setInt(2, cafeNo);
            stmt2.executeUpdate();
          }
        }
      }

      if (!holidays.isEmpty()) {
        try (PreparedStatement stmt3 = con.prepareStatement(
            "insert into studycafe_holiday(cafe_no, date) values(?,?)")) {
          for (Date date : holidays) {
            stmt3.setInt(1, cafeNo);
            stmt3.setDate(2, date);
            stmt3.executeUpdate();
          }
        }
      }

      System.out.println(" >> 카페 등록 완료!");
    }
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
    try (PreparedStatement stmt = con.prepareStatement(
        "update studycafe set operating_status_no = 2"
            + " where cafe_no = ?")) {

      stmt.setInt(1, cafe.getNo());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("장소 승인 실패!");
      }
    }
  }

  @Override
  public void deleteCafe(Cafe cafe) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update studycafe set operating_status_no = 4"
            + " where cafe_no = ?")) {

      stmt.setInt(1, cafe.getNo());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("장소 삭제 실패!");
      }
    }
  }

  //-----------------------CafeReview--------------------------------------

  @Override
  public List<CafeReview> getCafeReviewList() throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select r.review_no, r.grade, r.content, r.create_dt, r.status, sr.cafe_no, rs.per_member_no"
            + " from studycafe_review r"
            + " join studycafe_reservation rs on r.studycafe_rsv_no=rs.studycafe_rsv_no"
            + " join studycafe_room sr on rs.studyroom_no=sr.studyroom_no");

        ResultSet rs = stmt.executeQuery()) {

      ArrayList<CafeReview> list = new ArrayList<>();

      while(rs.next()) {
        CafeReview cafeReview = new CafeReview();

        Member member = new Member();
        member.setPerNo(rs.getInt("per_member_no"));
        cafeReview.setMember(member);

        cafeReview.setReviewNo(rs.getInt("review_no"));
        cafeReview.setContent(rs.getString("content"));
        cafeReview.setGrade(rs.getInt("grade"));
        cafeReview.setRegisteredDate(rs.getDate("create_dt"));
        cafeReview.setReviewStatus(rs.getInt("status"));

        Cafe cafe = new Cafe();
        cafe.setNo(rs.getInt("cafe_no"));
        cafeReview.setCafe(cafe);

        list.add(cafeReview);
      }
      return list;
    }
  }

  @Override
  public List<CafeReview> findReviewListByCafeNo(int cafeNo) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select r.grade, r.content, r.create_dt, r.status, sr.cafe_no, m.nickname"
            + " from studycafe_review r"
            + " join studycafe_reservation rs on r.studycafe_rsv_no=rs.studycafe_rsv_no"
            + " join studycafe_room sr on rs.studyroom_no=sr.studyroom_no"
            + " join per_member pm on rs.per_member_no=pm.per_member_no"
            + " join member m on pm.member_no=m.member_no"
            + " where sr.cafe_no=?")) {

      stmt.setInt(1, cafeNo);

      ArrayList<CafeReview> list = new ArrayList<>();

      try (ResultSet rs = stmt.executeQuery()) {
        while(rs.next()) {
          CafeReview cafeReview = new CafeReview();

          Member member = new Member();
          member.setPerNickname(rs.getString("nickname"));
          cafeReview.setMember(member);

          cafeReview.setContent(rs.getString("content"));
          cafeReview.setGrade(rs.getInt("grade"));
          cafeReview.setRegisteredDate(rs.getDate("create_dt"));
          cafeReview.setReviewStatus(rs.getInt("status"));

          Cafe cafe = new Cafe();
          cafe.setNo(rs.getInt("cafe_no"));
          cafeReview.setCafe(cafe);

          list.add(cafeReview);
        }
      }
      return list;
    }
  }

  @Override
  public List<CafeReview> findReviewListByMember(int memberNo) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select r.review_no, r.grade, r.content, r.create_dt, r.status, sr.cafe_no"
            + " from studycafe_review r"
            + " join studycafe_reservation rs on r.studycafe_rsv_no=rs.studycafe_rsv_no"
            + " join studycafe_room sr on rs.studyroom_no=sr.studyroom_no"
            + " where rs.per_member_no=?")) {

      stmt.setInt(1, memberNo);

      ArrayList<CafeReview> list = new ArrayList<>();

      try (ResultSet rs = stmt.executeQuery()) {
        while(rs.next()) {
          CafeReview cafeReview = new CafeReview();

          cafeReview.setReviewNo(rs.getInt("review_no"));
          cafeReview.setContent(rs.getString("content"));
          cafeReview.setGrade(rs.getInt("grade"));
          cafeReview.setRegisteredDate(rs.getDate("create_dt"));
          cafeReview.setReviewStatus(rs.getInt("status"));

          Cafe cafe = new Cafe();
          cafe.setNo(rs.getInt("cafe_no"));
          cafeReview.setCafe(cafe);

          list.add(cafeReview);
        }
      }
      return list;
    }
  }

  @Override
  public CafeReview findByReviewNo(int reviewNo) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select r.review_no, r.grade, r.content, r.create_dt, r.status, sr.cafe_no, rs.per_member_no"
            + " from studycafe_review r"
            + " join studycafe_reservation rs on r.studycafe_rsv_no=rs.studycafe_rsv_no"
            + " join studycafe_room sr on rs.studyroom_no=sr.studyroom_no"
            + " where r.review_no=" + reviewNo);

        ResultSet rs = stmt.executeQuery()) {

      while(!rs.next()) {
        return null;
      }

      CafeReview cafeReview = new CafeReview();

      Member member = new Member();
      member.setPerNo(rs.getInt("per_member_no"));
      cafeReview.setMember(member);

      cafeReview.setReviewNo(rs.getInt("review_no"));
      cafeReview.setContent(rs.getString("content"));
      cafeReview.setGrade(rs.getInt("grade"));
      cafeReview.setRegisteredDate(rs.getDate("create_dt"));
      cafeReview.setReviewStatus(rs.getInt("status"));

      Cafe cafe = new Cafe();
      cafe.setNo(rs.getInt("cafe_no"));
      cafeReview.setCafe(cafe);

      return cafeReview;
    }
  }

  @Override
  public void insertCafeReview(CafeReview cafeReview) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into studycafe_review(studycafe_rsv_no, content, grade, status) values(?,?,?,1)")) {

      stmt.setInt(1, cafeReview.getReservationNo());
      stmt.setString(2, cafeReview.getContent());
      stmt.setInt(3, cafeReview.getGrade());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("0.리뷰 등록 실패!");
      }

      try (PreparedStatement stmt2 = con.prepareStatement(
          "update studycafe_reservation set review=2 where studycafe_rsv_no=?")) {

        stmt2.setInt(1, cafeReview.getReservationNo());

        if (stmt2.executeUpdate() == 0) {
          throw new Exception("1.리뷰 등록 실패!");
        }
      }
    }
  }

  @Override
  public void deleteCafeReview(int reviewNo) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update studycafe_review set status = 2"
            + " where review_no = ?")) {

      stmt.setInt(1, reviewNo);

      if (stmt.executeUpdate() == 0) {
        throw new Exception("리뷰 삭제 실패!");
      }
    }
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
    try (PreparedStatement stmt = con.prepareStatement(
        "select rs.studycafe_rsv_no, rs.studyroom_no, rs.per_member_no, rs.rsv_dt, rs.using_dt,"
            + " rs.start_time, rs.using_time, rs.people, rs.total_price, rs.review, rst.rsv_status_no,"
            + " rst.rsv_name, c.cafe_no, c.name cafe_name"
            + " from studycafe_reservation rs"
            + " join studycafe_reservation_status rst on rs.rsv_status_no=rst.rsv_status_no"
            + " join studycafe_room sr on rs.studyroom_no=sr.studyroom_no"
            + " join studycafe c on sr.cafe_no=c.cafe_no"
            + " where rs.per_member_no=" + memberNo
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
    try (PreparedStatement stmt = con.prepareStatement(
        "select rs.studycafe_rsv_no, rs.studyroom_no, rs.per_member_no, rs.rsv_dt, rs.using_dt,"
            + " rs.start_time, rs.using_time, rs.people, rs.total_price, rs.review, rst.rsv_status_no,"
            + " rst.rsv_name, c.cafe_no, c.name cafe_name"
            + " from studycafe_reservation rs"
            + " join studycafe_reservation_status rst on rs.rsv_status_no=rst.rsv_status_no"
            + " join studycafe_room sr on rs.studyroom_no=sr.studyroom_no"
            + " join studycafe c on sr.cafe_no=c.cafe_no"
            + " where rs.per_member_no=? and rs.studycafe_rsv_no=?"
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
