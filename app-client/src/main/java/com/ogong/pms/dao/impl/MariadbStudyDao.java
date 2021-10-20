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
            + "(name, subject_no, no_people, face_no, introduction, per_member_no)"
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

    }
  }

  public void insertGuilder(Study study, Member member) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into study_guilder(study_no, per_member_no) values(?,?)",
        Statement.RETURN_GENERATED_KEYS)) {

      stmt.setInt(1, study.getStudyNo());
      stmt.setInt(2, member.getPerNo());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("구성원 데이터 저장 실패!");
      }
    }
  }

  public void insertBookmark(Study study, Member member) throws Exception {
    //    try (PreparedStatement stmt = con.prepareStatement(
    //        "insert into study_bookmark(study_no, per_member_no) values(?,?)",
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

  @Override
  public void updateGuilder(Study study) throws Exception {
    try (PreparedStatement stmt =
        con.prepareStatement("update study_guilder set"
            + " status=?"
            + " where study_no=?")) {

      stmt.setInt(1, study.getStatus());
      stmt.setInt(2, study.getStudyNo());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("스터디 데이터 변경 실패!");
      }
    }
  }

  @Override
  public void delete(int no) throws Exception {

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
            + " s.introduction,"
            + " s.created_dt,"
            + " pm.per_member_no owner_no,"
            + " m.nickname owner_name,"
            + " sg.status status,"
            + " sg.per_member_no guilder_no,"
            + " m2.nickname guilder_nickname,"
            + " s.score"
            + " from study s"
            + " left outer join per_member pm on s.per_member_no=pm.per_member_no"
            + " left outer join study_subject ss on s.subject_no=ss.subject_no"
            + " left outer join member m on m.member_no=pm.member_no"
            + " left outer join study_face_status sfs on s.face_no=sfs.face_no"
            + " left outer join study_guilder sg on s.study_no=sg.study_no"
            + " left outer join per_member pm2 on pm2.per_member_no=sg.per_member_no"
            + " left outer join member m2 on m2.member_no=pm2.member_no");
        ResultSet rs = stmt.executeQuery()) {

      ArrayList<Study> list = new ArrayList<>();

      while (rs.next()) {
        Study study = new Study();
        study.setStudyNo(rs.getInt("study_no"));
        study.setStudyTitle(rs.getString("name"));
        study.setSubjectName(rs.getString("subject_name"));
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

        list.add(study);
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
            + " s.introduction,"
            + " s.created_dt,"
            + " pm.per_member_no owner_no,"
            + " m.nickname owner_name,"
            + " sg.per_member_no,"
            + " sg.status status,"
            + " m2.nickname guilder," 
            + " s.score"
            + " from study s"
            + " left outer join per_member pm on s.per_member_no=pm.per_member_no"
            + " left outer join study_subject ss on s.subject_no=ss.subject_no"
            + " left outer join member m on m.member_no=pm.member_no"
            + " left outer join study_face_status sfs on s.face_no=sfs.face_no"
            + " left outer join study_guilder sg on s.study_no=sg.study_no"
            + " left outer join per_member pm2 on pm2.per_member_no=sg.per_member_no"
            + " left outer join member m2 on m2.member_no=pm2.member_no"
            + " where s.study_no=" + studyinputNo);
        ResultSet rs = stmt.executeQuery()) {

      //      if (!rs.next()) {
      //        return null;
      //      }

      Study study = null;
      while (rs.next()) {
        if (study == null) {
          study = new Study();
          study.setStudyNo(rs.getInt("study_no"));
          study.setStudyTitle(rs.getString("name"));
          study.setSubjectName(rs.getString("subject_name"));
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

        int no = rs.getInt("status");
        if (no == 1) {
          Member waitingMember = new Member();
          waitingMember.setPerNickname(rs.getString("guilder"));

          study.getWatingMember().add(waitingMember);

        } else if (no == 2) {
          Member guilder = new Member();
          guilder.setPerNickname(rs.getString("guilder"));

          study.getMembers().add(guilder);
        }  
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
            + " s.introduction,"
            + " s.created_dt,"
            + " m.nickname owner_name,"
            + " s.score"
            + " from study s"
            + " join per_member pm on s.per_member_no=pm.per_member_no"
            + " join study_subject ss on s.subject_no=ss.subject_no"
            + " join member m on m.member_no=pm.member_no"
            + " join study_face_status sfs on s.face_no=sfs.face_no"
            + " where s.study_no=" + studyNo);
        ResultSet rs = stmt.executeQuery()) {

      if (!rs.next()) {
        return null;
      }

      Study study = new Study();
      study.setStudyNo(rs.getInt("study_no"));
      study.setStudyTitle(rs.getString("name"));
      study.setSubjectName(rs.getString("subject_name"));
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
