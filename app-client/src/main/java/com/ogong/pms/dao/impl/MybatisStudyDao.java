package com.ogong.pms.dao.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Guilder;
import com.ogong.pms.domain.Study;

public class MybatisStudyDao implements StudyDao {

  Connection con;
  SqlSession sqlSession;

  public MybatisStudyDao(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  // ------------------------- [ 스터디 ] -----------------------------------
  @Override
  public void insert(Study study) throws Exception {
    sqlSession.insert("StudyMapper.insert", study);
    sqlSession.commit();
  }

  // 마이 스터디에서 업데이트
  @Override
  public void update(Study study) throws Exception {
    sqlSession.update("StudyMapper.update", study);
    sqlSession.commit();
  }

  @Override
  public void delete(int studyNo, int memberNo) throws Exception {
    sqlSession.delete("StudyMapper.deleteAllBookmark", studyNo);

    HashMap<String,Object> params = new HashMap<>();
    params.put("studyNo", studyNo);
    params.put("memberNo", memberNo);

    sqlSession.delete("StudyMapper.deleteStudy", params);
    sqlSession.commit();
  }

  @Override
  public List<Study> findAll() throws Exception {
    return sqlSession.selectList("StudyMapper.findAll");
  }

  @Override
  public Study findByNo(int studyinputNo) throws Exception {
    return sqlSession.selectOne("StudyMapper.findByNo", studyinputNo);
  }

  // 스터디 검색
  @Override
  public List<Study> findByKeyword(String keyword) throws Exception {
    //    try (PreparedStatement stmt = con.prepareStatement(
    //        "select"
    //            + " s.study_no,"
    //            + "s.name study_title,"
    //            + "ss.subject_no subject_no,"
    //            + "ss.name subject_name,"
    //            + "s.area area,"
    //            + "s.no_people,"
    //            + "sfs.face_no face_no,"
    //            + "sfs.name face_name,"
    //            + "s.introduction,"
    //            + "s.created_dt,"
    //            + "s.score study_score,"
    //            + "s.member_no owner_no,"
    //            + "m.nickname owner_name,"
    //            + "(select count(*) from study_guilder where study_no=s.study_no and status=2) count_guilder,"
    //            + "(select count(*) from study_guilder where study_no=s.study_no and status=1) count_wating_guilder,"
    //            + "(select count(*) from study_bookmark where study_no=s.study_no) count_bookmark"
    //            + " from study s"
    //            + " left outer join study_subject ss on s.subject_no=ss.subject_no"
    //            + " left outer join study_face_status sfs on s.face_no=sfs.face_no"
    //            + " left outer join member m on s.member_no=m.member_no"
    //            + " left outer join study_guilder sg on s.study_no=sg.study_no"
    //            + " left outer join member m2 on sg.member_no=m2.member_no"
    //            + " left outer join study_bookmark sb on s.study_no=sb.study_no"
    //            + " group by s.study_no"
    //            + " order by s.study_no"
    //            + " where study_title like(concat('%',?,'%'))"
    //            + " or subject_name like (concat('%',?,'%'))"
    //            + " or area like (concat('%',?,'%'))"
    //            + " order by b.board_no desc")) {
    //
    //      stmt.setString(1, keyword);
    //      stmt.setString(2, keyword);
    //      stmt.setString(3, keyword);
    //
    //      try (ResultSet rs = stmt.executeQuery()) {
    //
    //        ArrayList<Study> list = new ArrayList<>();
    //
    //        int studyNo=0;
    //        Study study = null;
    //
    //        while (rs.next()) {
    //          if (studyNo != rs.getInt("study_no")) {
    //            study = new Study();
    //            study.setStudyNo(rs.getInt("study_no"));
    //            study.setStudyTitle(rs.getString("study_title"));
    //            study.setSubjectNo(rs.getInt("subject_no"));
    //            study.setSubjectName(rs.getString("subject_name"));
    //            study.setArea(rs.getString("area"));
    //            study.setNumberOfPeple(rs.getInt("no_people"));
    //            study.setFaceNo(rs.getInt("face_no"));
    //            study.setFaceName(rs.getString("face_name"));
    //            study.setIntroduction(rs.getString("introduction"));
    //            study.setRegisteredDate(rs.getDate("created_dt"));
    //            study.setScore(rs.getInt("study_score"));
    //            study.setCountMember(rs.getInt("count_guilder"));
    //            study.setWatingCountMember(rs.getInt("count_wating_guilder"));
    //            study.setCountBookMember(rs.getInt("count_bookmark"));
    //
    //            Member member = new Member();
    //            member.setPerNo(rs.getInt("owner_no"));
    //            member.setPerNickname(rs.getString("owner_name"));
    //            study.setOwner(member);
    //            studyNo = study.getStudyNo();
    //            list.add(study);
    //          }
    //        }
    //        return list;
    //      }
    //    }
    return null;
  }



  // 내 스터디 상세
  @Override
  public Study findByMyNo(int studyNo, int memberNo) throws Exception {
    HashMap<String,Object> params = new HashMap<>();
    params.put("studyNo", studyNo);
    params.put("memberNo", memberNo);

    return sqlSession.selectOne("StudyMapper.findByMyNo", params);
  }

  // ------------------------- [ 구성원 ] -----------------------------------

  //해당 스터디의 구성원 목록
  @Override
  public List<Guilder> findByGuilderAll(int studyNo) throws Exception {
    return sqlSession.selectList("StudyMapper.findByGuilderAll", studyNo);
  }

  // 내가 들어가있는 스터디 목록
  @Override
  public List<Guilder> findByGuilderMyAll(int memberNo) throws Exception {
    return sqlSession.selectList("StudyMapper.findByGuilderMyAll", memberNo);
  }

  // 내가 어떤 스터디에 들어가 있는지 상세
  @Override
  public Guilder findByGuilderMyNo(int studyNo, int memberNo) throws Exception {
    HashMap<String,Object> params = new HashMap<>();
    params.put("studyNo", studyNo);
    params.put("memberNo", memberNo);
    return sqlSession.selectOne("StudyMapper.findByGuilderMyNo", params);
  }

  // 신청하기(joinHandler)
  @Override
  public void insertGuilder(int studyNo, int memberNo) throws Exception {
    HashMap<String,Object> params = new HashMap<>();
    params.put("studyNo", studyNo);
    params.put("memberNo", memberNo);

    sqlSession.insert("StudyMapper.insertGuilder", params);
    sqlSession.commit();

  }

  //조장권한 위임
  @Override
  public void updateOwner(int studyNo, int memberNo) throws Exception {
    HashMap<String,Object> params = new HashMap<>();
    params.put("memberNo", memberNo);
    params.put("studyNo", studyNo);
    sqlSession.update("StudyMapper.updateOwner", params);
    sqlSession.commit();
  }

  // 승인대기중인 구성원 승인하기
  @Override
  public void updateGuilder(int studyNo, int memberNo) throws Exception {
    HashMap<String,Object> params = new HashMap<>();
    params.put("memberNo", memberNo);
    params.put("studyNo", studyNo);
    sqlSession.update("StudyMapper.updateGuilder", params);
    sqlSession.commit();
  }

  // 구성원에서 삭제하기
  @Override
  public void deleteGuilder(int studyNo, int memberNo) throws Exception {
    HashMap<String,Object> params = new HashMap<>();
    params.put("memberNo", memberNo);
    params.put("studyNo", studyNo);
    sqlSession.update("StudyMapper.deleteGuilder", params);
    sqlSession.commit();
  }

  // 구성원 전체 삭제하기
  @Override
  public void deleteAllGuilder(int studyNo) throws Exception {
    sqlSession.delete("StudyMapper.deleteAllGuilder", studyNo);
    sqlSession.commit();
  }

  //------------------------- [ 북마크 ] 구현 완료 -----------------------------------
  // 북마크 하기
  @Override
  public void insertBookmark(int studyNo, int memberNo) throws Exception {
    HashMap<String,Object> params = new HashMap<>();
    params.put("studyNo", studyNo);
    params.put("memberNo", memberNo);
    sqlSession.insert("StudyMapper.insertBookmark", params);
    sqlSession.commit();
  }

  @Override
  public void deleteBookmark(int studyNo, int memberNo) throws Exception {
    HashMap<String,Object> params = new HashMap<>();
    params.put("memberNo", memberNo);
    params.put("studyNo", studyNo);
    sqlSession.insert("StudyMapper.deleteBookmark", params);
    sqlSession.commit();
  }
}