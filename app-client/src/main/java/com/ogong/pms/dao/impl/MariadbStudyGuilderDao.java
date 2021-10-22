package com.ogong.pms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import com.ogong.pms.dao.StudyGuilderDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;

public class MariadbStudyGuilderDao implements StudyGuilderDao {

  Connection con;

  public MariadbStudyGuilderDao(Connection con) {
    this.con = con;
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

  //  @Override
  //  public void insertBookmark(Study study, Member member) throws Exception {
  //    //    try (PreparedStatement stmt = con.prepareStatement(
  //    //        "insert into study_bookmark(study_no, member_no) values(?,?)",
  //    //        Statement.RETURN_GENERATED_KEYS)) {
  //    //
  //    //      stmt.setInt(1, study.getStudyNo());
  //    //      stmt.setInt(2, member.getPerNo());
  //    //
  //    //      if (stmt.executeUpdate() == 0) {
  //    //        throw new Exception("구성원 데이터 저장 실패!");
  //    //      }
  //    //    }
  //  }

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

}