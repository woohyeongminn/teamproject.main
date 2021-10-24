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
    return sqlSession.selectList("StudyMapper.findByKeyword", keyword);

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