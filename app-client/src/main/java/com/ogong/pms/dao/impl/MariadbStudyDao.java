package com.ogong.pms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            + " s.no_people,"
            + " sfs.name face_name,"
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
        study.setSubject(rs.getString("subject_name"));
        study.setNumberOfPeple(rs.getInt("no_people"));
        study.setFace(rs.getString("face_name"));

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
