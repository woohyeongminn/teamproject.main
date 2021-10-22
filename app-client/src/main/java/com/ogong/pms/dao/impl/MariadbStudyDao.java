package com.ogong.pms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;

public class MariadbStudyDao implements StudyDao {

  Connection con;

  public MariadbStudyDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Study study) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into study"
            + "(name, subject_no, no_people, face_no, introduction, member_no)"
            + " values(?, ?, ?, ?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS)) {

      stmt.setString(1, study.getStudyTitle());
      stmt.setInt(2, study.getSubjectNo());
      stmt.setInt(3, study.getNumberOfPeple());
      stmt.setInt(4, study.getFaceNo());
      stmt.setString(5, study.getIntroduction());
      stmt.setInt(6, study.getOwner().getPerNo());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("스터디 데이터 저장 실패!");
      }

      // 스터디 테이블의 PK 값 꺼내기
      int studyNo = 0;
      try (ResultSet pk = stmt.getGeneratedKeys()) {
        if (pk.next()) {
          studyNo = pk.getInt("study_no");
        }
      }

      try (PreparedStatement stmt2 = con.prepareStatement(
          "insert into study_location(name) values(?)",
          Statement.RETURN_GENERATED_KEYS)) {

        stmt2.setString(1, study.getArea());

        if (stmt2.executeUpdate() == 0) {
          throw new Exception("스터디 지역 데이터 저장 실패!");
        }

        // 지역테이블의 PK 값 꺼내기
        int areaNo = 0;
        try (ResultSet pk = stmt2.getGeneratedKeys()) {
          if (pk.next()) {
            areaNo = pk.getInt("location_no");
          }
        }

        // study_multiple_location 테이블에 추가하기
        try (PreparedStatement stmt3 =
            con.prepareStatement(
                "insert into study_multiple_location(study_no, location_no) values(?,?)")) {

          stmt3.setInt(1, studyNo);
          stmt3.setInt(2, areaNo);
          stmt3.executeUpdate();
        }
      }
    }
  }

  @Override
  public void insertGuilder(int studyNo, int memberNo) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into study_guilder(study_no, member_no) values(?,?)",
        Statement.RETURN_GENERATED_KEYS)) {

      stmt.setInt(1, studyNo);
      stmt.setInt(2, memberNo);

      if (stmt.executeUpdate() == 0) {
        throw new Exception("구성원 데이터 저장 실패!");
      }
    }
  }

  @Override
  public void insertBookmark(Study study, Member member) throws Exception {
    //    try (PreparedStatement stmt = con.prepareStatement(
    //        "insert into study_bookmark(study_no, member_no) values(?,?)",
    //        Statement.RETURN_GENERATED_KEYS)) {
    //
    //      stmt.setInt(1, study.getStudyNo());
    //      stmt.setInt(2, member.getPerNo());
    //
    //      if (stmt.executeUpdate() == 0) {
    //        throw new Exception("구성원 데이터 저장 실패!");
    //      }
    //    }
  }

  // 마이 스터디에서 업데이트
  @Override
  public void update(Study study) throws Exception {
    try (PreparedStatement stmt =
        con.prepareStatement("update study set"
            + " name=?,no_people=?,face_no=?,introduction=?"
            + " where study_no=?")) {

      stmt.setString(1, study.getStudyTitle());
      stmt.setInt(2, study.getNumberOfPeple());
      stmt.setInt(3, study.getFaceNo());
      stmt.setString(4, study.getIntroduction());
      stmt.setInt(5, study.getStudyNo());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("스터디 데이터 변경 실패!");
      }
    }
  }

  // 조장권한
  @Override
  public void updateOwner(int studyNo, int memberNo) throws Exception {
    try (PreparedStatement stmt =
        con.prepareStatement("update study set"
            + " member_no=?"
            + " where study_no=?")) {

      stmt.setInt(1, memberNo);
      stmt.setInt(2, studyNo);

      if (stmt.executeUpdate() == 0) {
        throw new Exception("스터디 조장 데이터 변경 실패!");
      }
    }
  }

  @Override
  public void updateGuilder(int studyNo, int memberNo) throws Exception {
    try (PreparedStatement stmt =
        con.prepareStatement("update study_guilder set"
            + " status=2"
            + " where study_no=? and member_no=?")) {

      stmt.setInt(1, studyNo);
      stmt.setInt(2, memberNo);

      if (stmt.executeUpdate() == 0) {
        throw new Exception("스터디 데이터 변경 실패!");
      }
    }
  }

  @Override
  public void delete(int studyNo, int memberNo) throws Exception {
    try (PreparedStatement stmt = 
        con.prepareStatement("delete from study"
            + " where study_no=? and member_no=?")) {

      stmt.setInt(1, studyNo);
      stmt.setInt(2, memberNo);

      if (stmt.executeUpdate() == 0) {
        throw new Exception("스터디 데이터 삭제 실패!");
      }
    }
  }

  @Override
  public void deleteGuilder(int studyNo, int memberNo) throws Exception {
    try (PreparedStatement stmt = 
        con.prepareStatement("delete from study_guilder"
            + " where study_no=? and member_no=?")) {

      stmt.setInt(1, studyNo);
      stmt.setInt(2, memberNo);

      if (stmt.executeUpdate() == 0) {
        throw new Exception("구성원 데이터 삭제 실패!");
      }
    }
  }

  // 스터디 삭제-(한스터디여러지역 테이블 삭제)
  @Override
  public void deleteMultipleLocation(int studyNo) throws Exception {
    try (PreparedStatement stmt = 
        con.prepareStatement("delete from study_multiple_location"
            + " where study_no=studyNo")) {
      if (stmt.executeUpdate() == 0) {
        throw new Exception("구성원 데이터 삭제 실패!");
      }
    }
  }



  // 승인 대기 중인 회원목록 보기
  @Override 
  public Study findWaitingGuilder(Study study) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select s.study_no,"
            + " sg.status,"
            + " sg.member_no guilder_no"
            + " m.nickname guilder_nickname"
            + " from study s"
            + " left outer join study_guilder sg on s.study_no=sg.study_no"
            + " left outer join member m on sg.member_no=m.member_no"
            + " where sg.stauts=1");
        ResultSet rs = stmt.executeQuery()) {

      Member waitingMember = new Member();
      waitingMember.setPerNo(rs.getInt("guilder_no"));
      waitingMember.setPerNickname(rs.getString("guilder_nickname"));

      study.getWatingMember().add(waitingMember);
    }
    return study;
  }

  //구성원 목록 보기
  @Override 
  public Study findGuilder(Study study) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select s.study_no,"
            + " sg.status,"
            + " sg.member_no guilder_no"
            + " m.nickname guilder_nickname"
            + " from study s"
            + " left outer join study_guilder sg on s.study_no=sg.study_no"
            + " left outer join member m on sg.member_no=m.member_no"
            + " where sg.stauts=2");
        ResultSet rs = stmt.executeQuery()) {

      Member guilder = new Member();
      guilder.setPerNo(rs.getInt("guilder_no"));
      guilder.setPerNickname(rs.getString("guilder_nickname"));

      study.getMembers().add(guilder);
    }
    return study;
  }

  @Override
  public List<Study> findAll() throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select s.study_no,"
            + " s.name,"
            + " ss.name subject_name,"
            + " ss.subject_no subject_no,"
            + " s.no_people,"
            + " sfs.name face_name,"
            + " sfs.face_no face_no,"
            + " sl.name area_name,"
            + " sl.location_no area_no,"
            + " s.introduction,"
            + " s.created_dt,"
            + " s.member_no owner_no,"
            + " m.nickname owner_name,"
            + " sg.status status,"
            + " sg.member_no guilder_no,"
            + " m2.nickname guilder_nickname,"
            + " s.score study_score,"
            + " sb.member_no book_member_no,"
            + " s.score"
            + " from study s"
            + " left outer join study_subject ss on s.subject_no=ss.subject_no"
            + " left outer join study_face_status sfs on s.face_no=sfs.face_no"
            + " left outer join member m on s.member_no=m.member_no"
            + " left outer join study_guilder sg on s.study_no=sg.study_no"
            + " left outer join member m2 on sg.member_no=m2.member_no"
            + " left outer join study_multiple_location sml on sml.study_no=s.study_no"
            + " left outer join study_location sl on sml.location_no=sl.location_no"
            + " left outer join study_bookmark sb on sb.study_no=s.study_no");
        ResultSet rs = stmt.executeQuery()) {

      ArrayList<Study> list = new ArrayList<>();

      int studyNo=0;
      Study study = null;
      while (rs.next()) {
        if (studyNo != rs.getInt("study_no")) {
          study = new Study();
          study.setStudyNo(rs.getInt("study_no"));
          study.setStudyTitle(rs.getString("name"));
          study.setSubjectName(rs.getString("subject_name"));
          study.setArea(rs.getString("area_name"));
          study.setSubjectNo(rs.getInt("subject_no"));
          study.setNumberOfPeple(rs.getInt("no_people"));
          study.setFaceName(rs.getString("face_name"));
          study.setFaceNo(rs.getInt("face_no"));
          study.setIntroduction(rs.getString("introduction"));
          study.setRegisteredDate(rs.getDate("created_dt"));
          study.setScore(rs.getInt("study_score"));

          Member member = new Member();
          member.setPerNo(rs.getInt("owner_no"));
          member.setPerNickname(rs.getString("owner_name"));
          study.setOwner(member);
          studyNo = study.getStudyNo();
          list.add(study);
        }

        Member bookMember = new Member();
        bookMember.setPerNo(rs.getInt("book_member_no"));
        study.getBookMarkMember().add(bookMember);

        int statusNo = rs.getInt("status");
        if (statusNo == 1) {
          Member waitingMember = new Member();
          waitingMember.setPerNo(rs.getInt("guilder_no"));
          waitingMember.setPerNickname("guilder_nickname");
          study.getWatingMember().add(waitingMember);

        } else if (statusNo == 2) {
          Member guilder = new Member();
          guilder.setPerNo(rs.getInt("guilder_no"));
          guilder.setPerNickname("guilder_nickname");
          study.getMembers().add(guilder);
        }
      }

      return list;
    }
  }

  @Override
  public Study findByNo(int studyinputNo) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select s.study_no,"
            + " s.name,"
            + " ss.name subject_name,"
            + " ss.subject_no subject_no,"
            + " s.no_people,"
            + " sfs.name face_name,"
            + " sfs.face_no face_no,"
            + " sl.name area_name,"
            + " s.introduction,"
            + " s.created_dt,"
            + " s.member_no owner_no,"
            + " m.nickname owner_name,"
            + " sg.status status,"
            + " sg.member_no guilder_no,"
            + " m2.nickname guilder_nickname,"
            + " s.score study_score,"
            + " sb.member_no book_member_no,"
            + " s.score"
            + " from study s"
            + " left outer join study_subject ss on s.subject_no=ss.subject_no"
            + " left outer join study_face_status sfs on s.face_no=sfs.face_no"
            + " left outer join member m on s.member_no=m.member_no"
            + " left outer join study_guilder sg on s.study_no=sg.study_no"
            + " left outer join member m2 on sg.member_no=m2.member_no"
            + " left outer join study_multiple_location sml on sml.study_no=s.study_no"
            + " left outer join study_location sl on sml.location_no=sl.location_no"
            + " left outer join study_bookmark sb on sb.study_no=s.study_no"
            + " where s.study_no=" + studyinputNo);
        ResultSet rs = stmt.executeQuery()) {

      Study study = null;
      while (rs.next()) {
        if (study == null) {
          study = new Study();
          study.setStudyNo(rs.getInt("study_no"));
          study.setStudyTitle(rs.getString("name"));
          study.setSubjectName(rs.getString("subject_name"));
          study.setArea(rs.getString("area_name"));
          study.setSubjectNo(rs.getInt("subject_no"));
          study.setNumberOfPeple(rs.getInt("no_people"));
          study.setFaceName(rs.getString("face_name"));
          study.setFaceNo(rs.getInt("face_no"));
          study.setIntroduction(rs.getString("introduction"));
          study.setRegisteredDate(rs.getDate("created_dt"));

          Member member = new Member();
          member.setPerNo(rs.getInt("owner_no"));
          member.setPerNickname(rs.getString("owner_name"));
          study.setOwner(member);
        }

        //        int no = rs.getInt("status");
        //        if (no == 1) {
        //          Member waitingMember = new Member();
        //          waitingMember.setPerNo(rs.getInt("guilder_no"));
        //          waitingMember.setPerNickname(rs.getString("guilder_nickname"));
        //
        //          study.getWatingMember().add(waitingMember);
        //
        //        } else if (no == 2) {
        //          Member guilder = new Member();
        //          guilder.setPerNo(rs.getInt("guilder_no"));
        //          guilder.setPerNickname(rs.getString("guilder_nickname"));
        //
        //          study.getMembers().add(guilder);
        //        } else {
        //
        //        }
      }
      return study;
    }
  }

  @Override
  public List<Study> findByKeyword(String keyword) throws Exception {
    return null;
  }

  // 내 스터디에서 찾기 - MyStudyDetail
  @Override
  public Study findMyStudy(int memberNo, int studyNo) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select s.study_no,"
            + " s.name,"
            + " ss.name subject_name,"
            + " ss.subject_no subject_no,"
            + " s.no_people,"
            + " sfs.name face_name,"
            + " sfs.face_no face_no,"
            + " sl.name area_name,"
            + " s.introduction,"
            + " s.created_dt,"
            + " m.nickname owner_name,"
            + " s.score"
            + " from study s"
            + " join study_subject ss on s.subject_no=ss.subject_no"
            + " join member m on m.member_no=pm.member_no"
            + " join study_face_status sfs on s.face_no=sfs.face_no"
            + " left outer join study_multiple_location sml on sml.study_no=s.study_no"
            + " left outer join study_location sl on sml.location_no=sl.location_no"
            + " where s.study_no=" + studyNo);
        ResultSet rs = stmt.executeQuery()) {

      if (!rs.next()) {
        return null;
      }

      Study study = new Study();
      study.setStudyNo(rs.getInt("study_no"));
      study.setStudyTitle(rs.getString("name"));
      study.setSubjectName(rs.getString("subject_name"));
      study.setArea(rs.getString("area_name"));
      study.setSubjectNo(rs.getInt("subject_no"));
      study.setNumberOfPeple(rs.getInt("no_people"));
      study.setFaceName(rs.getString("face_name"));
      study.setFaceNo(rs.getInt("face_no"));
      study.setIntroduction(rs.getString("introduction"));
      study.setRegisteredDate(rs.getDate("created_dt"));

      Member member = new Member();
      member.setPerNickname(rs.getString("owner_name"));
      study.setOwner(member);

      return study;
    }
  }
}