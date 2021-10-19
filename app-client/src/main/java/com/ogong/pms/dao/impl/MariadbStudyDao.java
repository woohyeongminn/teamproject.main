//package com.ogong.pms.dao.impl;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//import com.ogong.pms.dao.StudyDao;
//import com.ogong.pms.domain.Study;
//
//public class MariadbStudyDao implements StudyDao {
//
//  Connection con;
//
//  public MariadbStudyDao(Connection con) {
//    this.con = con;
//  }
//
//  @Override
//  public void insert(Study study) throws Exception {
//
//  }
//
//  // 마이 스터디에서 업데이트
//  @Override
//  public void update(Study study) throws Exception {
//
//  }
//
//  @Override
//  public void delete(int no) throws Exception {
//
//  }
//
//  @Override
//  public List<Study> findAll() throws Exception {
//    try (PreparedStatement stmt = con.prepareStatement(
//        "select"
//            + " s.study_no,s.name,s.subject_no,s.no_people,s.fece_no,s.introduction,s.created_dt,s.per_member_no,s.score"
//            + " from study s"
//            + " join per_member pm on s.per_member_no=pm.member_no"
//            + " join study_subject ss on s.subject_no=ss.member_no"
//            + " order by name asc");
//
//        ResultSet rs = stmt.executeQuery()) {
//
//      ArrayList<Study> list = new ArrayList<>();
//
//      while (rs.next()) {
//        Study study = new Study();
//        study.setPerNo(rs.getInt("member_no"));
//        study.setPerName(rs.getString("name"));
//        study.setPerNickname(rs.getString("nickname"));
//        study.setPerEmail(rs.getString("email"));
//        study.setPerTel(rs.getString("tel"));
//        study.setPerPhoto(rs.getString("photo"));
//        study.setPerRegisteredDate(rs.getDate("created_dt"));
//        study.setActive(rs.getInt("active"));
//        study.setPerStatus(rs.getInt("status"));
//
//        list.add(member);
//      }
//
//      return list;
//    }
//  }
//
//  /*
//
//   */
//
//  @Override
//  public Study findByNo(int studyinputNo) throws Exception {
//    return null; 
//  }
//
//  @Override
//  public List<Study> findByKeyword(String keyword) throws Exception {
//    return null;
//  }
//
//  // 내 스터디에서 찾기 - MyStudyDetail
//  @Override
//  public Study findMyStudy(int memberNo, int studyNo) throws Exception {
//    return null;
//  }
//
//}
