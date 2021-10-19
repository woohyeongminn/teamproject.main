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
            + "(name, subject_no, no_people, face_no, introduction,per_member_no)"
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

      int studyNo = 0;
      try (ResultSet pkS = stmt.getGeneratedKeys()) {
        if (pkS.next()) {
          studyNo = pkS.getInt("study_no");
        }
      }

      try (PreparedStatement stmt2 =
          con.prepareStatement("insert into study_guilder(study_no,per_member_no) values(?,?)")) {

        stmt2.setInt(1, studyNo); 
        stmt2.setInt(2, study.getOwner().getPerNo()); 
        stmt2.executeUpdate();
      }
    }
  }

  // 마이 스터디에서 업데이트
  @Override
  public void update(Study study) throws Exception {

  }

  @Override
  public void delete(int no) throws Exception {

  }

  @Override
  public List<Study> findAll() throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " s.study_no,"
            + " s.name,"
            + " ss.name subject_name,"
            + " ss.subject_no subject_no,"
            + " s.no_people,"
            + " sfs.name face_name,"
            + " sfs.face_no face_no,"
            + " s.introduction,"
            + " s.created_dt,"
            + " m.name owner_name,"
            + " s.score"
            + " from study s"
            + " left outer join per_member pm on s.per_member_no=pm.per_member_no"
            + " left outer join study_subject ss on s.subject_no=ss.subject_no"
            + " left outer join member m on m.member_no=pm.member_no"
            + " left outer join study_face_status sfs on s.face_no=sfs.face_no");
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

        Member member = new Member();
        member.setPerNickname(rs.getString("owner_name"));
        study.setOwner(member);
        list.add(study);
      }

      return list;
    }
  }

  @Override
  public Study findByNo(int studyinputNo) throws Exception {
    //    try (PreparedStatement stmt = con.prepareStatement(
    //        "select"
    //            + " s.study_no,"
    //            + " s.name,"
    //            + " ss.name subject_name,"
    //            + " s.no_people,"
    //            + " sfs.name face_name,"
    //            + " s.introduction,"
    //            + " s.created_dt,"
    //            + " m.name owner_name,"
    //            + " s.score"
    //            + " from study s"
    //            + " left outer join per_member pm on s.per_member_no=pm.per_member_no"
    //            + " left outer join study_subject ss on s.subject_no=ss.subject_no"
    //            + " left outer join member m on m.member_no=pm.member_no"
    //            + " left outer join study_face_status sfs on s.face_no=sfs.face_no"
    //            + " where study_no=" + studyinputNo);
    //        ResultSet rs = stmt.executeQuery()) {
    //
    //      if (!rs.next()) {
    //        return null;
    //      }
    //      Study study = new Study();
    //      study.setStudyNo(rs.getInt("study_no"));
    //      study.setStudyTitle(rs.getString("name"));
    //      study.setSubject(rs.getString("subject_name"));
    //      study.setNumberOfPeple(rs.getInt("no_people"));
    //      study.setFace(rs.getString("face_name"));
    //
    //      Member member = new Member();
    //      member.setPerNickname(rs.getString("owner_name"));
    //      study.setOwner(member);
    //
    //      return study;
    //    }
    return null;
  }

  @Override
  public List<Study> findByKeyword(String keyword) throws Exception {
    return null;
  }

  // 내 스터디에서 찾기 - MyStudyDetail
  @Override
  public Study findMyStudy(int memberNo, int studyNo) throws Exception {
    return null;
  }

}
