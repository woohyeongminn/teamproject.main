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

  // ------------------------- [ 스터디 ] -----------------------------------
  @Override
  public void insert(Study study) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into study"
            + "(name, subject_no, no_people, face_no, introduction, area, member_no)"
            + " values(?, ?, ?, ?, ?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS)) {

      stmt.setString(1, study.getStudyTitle());
      stmt.setInt(2, study.getSubjectNo());
      stmt.setInt(3, study.getNumberOfPeple());
      stmt.setInt(4, study.getFaceNo());
      stmt.setString(5, study.getIntroduction());
      stmt.setString(6, study.getArea());
      stmt.setInt(7, study.getOwner().getPerNo());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("스터디 데이터 저장 실패!");
      }
    }
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
  public List<Study> findAll() throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " s.study_no,"
            + " s.name study_title,"
            + " ss.subject_no subject_no,"
            + " ss.name subject_name,"
            + " s.area,"
            + " s.no_people,"
            + " sfs.face_no face_no,"
            + " sfs.name face_name,"
            + " s.introduction,"
            + " s.created_dt,"
            + " s.score study_score,"
            // 작성자(조장)
            + " s.member_no owner_no,"
            + " m.nickname owner_name,"
            // 구성원
            + " sg.status guilder_status,"
            + " sg.member_no guilder_no,"
            + " m2.nickname guilder_nickname,"
            // 북마크
            + " sb.member_no book_member_no"
            + " from study s"
            + " left outer join study_subject ss on s.subject_no=ss.subject_no"
            + " left outer join study_face_status sfs on s.face_no=sfs.face_no"
            + " left outer join member m on s.member_no=m.member_no"
            + " left outer join study_guilder sg on s.study_no=sg.study_no"
            + " left outer join member m2 on sg.member_no=m2.member_no"
            + " left outer join study_bookmark sb on s.study_no=sb.study_no");
        ResultSet rs = stmt.executeQuery()) {

      ArrayList<Study> list = new ArrayList<>();

      int studyNo=0;
      Study study = null;

      while (rs.next()) {
        if (studyNo != rs.getInt("study_no")) {
          study = new Study();
          study.setStudyNo(rs.getInt("study_no"));
          study.setStudyTitle(rs.getString("study_title"));
          study.setSubjectNo(rs.getInt("subject_no"));
          study.setSubjectName(rs.getString("subject_name"));
          study.setArea(rs.getString("area"));
          study.setNumberOfPeple(rs.getInt("no_people"));
          study.setFaceNo(rs.getInt("face_no"));
          study.setFaceName(rs.getString("face_name"));
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

        // 구성원
        int statusNo = rs.getInt("guilder_status");
        if (statusNo == 1) {        /*승인대기중*/
          Member waitingMember = new Member();
          waitingMember.setPerNo(rs.getInt("guilder_no"));
          waitingMember.setPerNickname("guilder_nickname");

          study.getWatingMember().add(waitingMember);

        } else if (statusNo == 2) {     /*참여중*/
          Member guilder = new Member();
          guilder.setPerNo(rs.getInt("guilder_no"));
          guilder.setPerNickname("guilder_nickname");

          study.getMembers().add(guilder);
        }

        // 북마크
        if (rs.getInt("book_member_no") != 0) {
          Member bookMember = new Member();
          bookMember.setPerNo(rs.getInt("book_member_no"));
          study.getBookMarkMember().add(bookMember);
        }
      }
      return list;
    }
  }

  @Override
  public Study findByNo(int studyinputNo) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " s.study_no,"
            + " s.name study_title,"
            + " ss.subject_no subject_no,"
            + " ss.name subject_name,"
            + " s.area,"
            + " s.no_people,"
            + " sfs.face_no face_no,"
            + " sfs.name face_name,"
            + " s.introduction,"
            + " s.created_dt,"
            + " s.score study_score,"
            // 작성자(조장)
            + " s.member_no owner_no,"
            + " m.nickname owner_name,"
            // 구성원
            + " sg.status guilder_status,"
            + " sg.member_no guilder_no,"
            + " m2.nickname guilder_nickname,"
            // 북마크
            + " sb.member_no book_member_no"
            + " from study s"
            + " left outer join study_subject ss on s.subject_no=ss.subject_no"
            + " left outer join study_face_status sfs on s.face_no=sfs.face_no"
            + " left outer join member m on s.member_no=m.member_no"
            + " left outer join study_guilder sg on s.study_no=sg.study_no"
            + " left outer join member m2 on sg.member_no=m2.member_no"
            + " left outer join study_bookmark sb on s.study_no=sb.study_no"
            + " where s.study_no=" + studyinputNo);
        ResultSet rs = stmt.executeQuery()) {

      Study study = null;
      while (rs.next()) {
        if (study == null) {
          study = new Study();
          study.setStudyNo(rs.getInt("study_no"));
          study.setStudyTitle(rs.getString("study_title"));
          study.setSubjectNo(rs.getInt("subject_no"));
          study.setSubjectName(rs.getString("subject_name"));
          study.setArea(rs.getString("area"));
          study.setNumberOfPeple(rs.getInt("no_people"));
          study.setFaceNo(rs.getInt("face_no"));
          study.setFaceName(rs.getString("face_name"));
          study.setIntroduction(rs.getString("introduction"));
          study.setRegisteredDate(rs.getDate("created_dt"));
          study.setScore(rs.getInt("study_score"));

          Member member = new Member();
          member.setPerNo(rs.getInt("owner_no"));
          member.setPerNickname(rs.getString("owner_name"));
          study.setOwner(member);
        }

        // 구성원
        int no = rs.getInt("guilder_status");
        if (no == 1) {      /*승인대기중*/
          Member waitingMember = new Member();
          waitingMember.setPerNo(rs.getInt("guilder_no"));
          waitingMember.setPerNickname(rs.getString("guilder_nickname"));

          study.getWatingMember().add(waitingMember);

        } else if (no == 2) {      /*참여중*/
          Member guilder = new Member();
          guilder.setPerNo(rs.getInt("guilder_no"));
          guilder.setPerNickname(rs.getString("guilder_nickname"));

          study.getMembers().add(guilder);
        }

        // 북마크
        if (rs.getInt("book_member_no") != 0) {
          Member bookMember = new Member();
          bookMember.setPerNo(rs.getInt("book_member_no"));
          study.getBookMarkMember().add(bookMember);
        }
      }
      return study;
    }
  }

  // 스터디 검색
  @Override
  public List<Study> findByKeyword(String keyword) throws Exception {
    return null;
  }

  // 내 스터디 상세 MyStudyDetail
  @Override
  public Study findMyStudy(int studyNo, int memberNo) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " s.study_no,"
            + " s.name study_title,"
            + " ss.subject_no subject_no,"
            + " ss.name subject_name,"
            + " s.area,"
            + " s.no_people,"
            + " sfs.face_no face_no,"
            + " sfs.name face_name,"
            + " s.introduction,"
            + " s.created_dt,"
            + " s.score study_score,"
            // 작성자(조장)
            + " s.member_no owner_no,"
            + " m.nickname owner_name,"
            // 구성원
            + " sg.status guilder_status,"
            + " sg.member_no guilder_no,"
            + " m2.nickname guilder_nickname,"
            // 북마크
            + " sb.member_no book_member_no"
            + " from study s"
            + " left outer join study_subject ss on s.subject_no=ss.subject_no"
            + " left outer join study_face_status sfs on s.face_no=sfs.face_no"
            + " left outer join member m on s.member_no=m.member_no"
            + " left outer join study_guilder sg on s.study_no=sg.study_no"
            + " left outer join member m2 on sg.member_no=m2.member_no"
            + " left outer join study_bookmark sb on s.study_no=sb.study_no"
            + " where s.study_no=" + studyNo + " and (s.member_no=" + memberNo + " or sg.member_no=" + memberNo + ")");
        ResultSet rs = stmt.executeQuery()) {

      Study study = null;
      while (rs.next()) {
        if (study == null) {
          study = new Study();
          study.setStudyNo(rs.getInt("study_no"));
          study.setStudyTitle(rs.getString("study_title"));
          study.setSubjectNo(rs.getInt("subject_no"));
          study.setSubjectName(rs.getString("subject_name"));
          study.setArea(rs.getString("area"));
          study.setNumberOfPeple(rs.getInt("no_people"));
          study.setFaceNo(rs.getInt("face_no"));
          study.setFaceName(rs.getString("face_name"));
          study.setIntroduction(rs.getString("introduction"));
          study.setRegisteredDate(rs.getDate("created_dt"));
          study.setScore(rs.getInt("study_score"));

          Member member = new Member();
          member.setPerNo(rs.getInt("owner_no"));
          member.setPerNickname(rs.getString("owner_name"));
          study.setOwner(member);
        }

        // 구성원
        int no = rs.getInt("guilder_status");
        if (no == 1) {      /*승인대기중*/
          Member waitingMember = new Member();
          waitingMember.setPerNo(rs.getInt("guilder_no"));
          waitingMember.setPerNickname(rs.getString("guilder_nickname"));

          study.getWatingMember().add(waitingMember);

        } else if (no == 2) {      /*참여중*/
          Member guilder = new Member();
          guilder.setPerNo(rs.getInt("guilder_no"));
          guilder.setPerNickname(rs.getString("guilder_nickname"));

          study.getMembers().add(guilder);
        }

        // 북마크
        if (rs.getInt("book_member_no") != 0) {
          Member bookMember = new Member();
          bookMember.setPerNo(rs.getInt("book_member_no"));
          study.getBookMarkMember().add(bookMember);
        }
      }
      return study;
    }
  }


  // ------------------------- [ 구성원 ] 구현 완료 -----------------------------------
  // 신청하기(joinHandler)
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

  //조장권한 위임
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

  // 승인대기중인 구성원 승인하기
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

  // 구성원에서 삭제하기
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

  //------------------------- [ 북마크 ] 구현 완료 -----------------------------------
  // 북마크 하기
  @Override
  public void insertBookmark(int studyNo, int memberNo) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into study_bookmark(study_no, member_no) values(?,?)")) {

      stmt.setInt(1, studyNo);
      stmt.setInt(2, memberNo);

      if (stmt.executeUpdate() == 0) {
        throw new Exception("구성원 데이터 저장 실패!");
      }
    }
  }

  @Override
  public void deleteBookmark(int studyNo, int memberNo) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from study_bookmark"
            + " where study_no=? and member_no=?")) {

      stmt.setInt(1, studyNo);
      stmt.setInt(2, memberNo);

      if (stmt.executeUpdate() == 0) {
        throw new Exception("구성원 데이터 삭제 실패!");
      }
    }
  }
}