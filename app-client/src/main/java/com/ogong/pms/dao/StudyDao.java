package com.ogong.pms.dao;

import java.util.List;
import com.ogong.pms.domain.Guilder;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;

public interface StudyDao {

  // --------------- [ 스터디 ] ----------------------------------------
  void insert(Study study) throws Exception;
  void update(Study study) throws Exception;
  void delete(int studyNo, int memberNo) throws Exception;
  List<Study> findAll() throws Exception;
  List<Study> findByKeyword(String keyword) throws Exception;
  Study findByNo(int studyinputNo) throws Exception;

  //--------------- [ 내 스터디 ] ----------------------------------------
  Study findByMyNo(int studyNo, int memberNo) throws Exception;

  //--------------- [ 구성원 ] ----------------------------------------
  List<Member> findByWaitingGuilderAll(int studyNo) throws Exception;
  List<Member> findByGuildersAll(int studyNo) throws Exception;

  List<Guilder> findByGuilderMyAll(int memberNo) throws Exception;

  void insertGuilder(int studyNo, int memberNo) throws Exception;
  void updateOwner(int studyNo, int memberNo) throws Exception;
  void updateGuilder(int studyNo, int memberNo) throws Exception;
  void deleteGuilder(int studyNo, int memberNo) throws Exception;
  void deleteAllGuilder(int studyNo) throws Exception;

  //--------------- [ 북마크 ] ----------------------------------------
  void insertBookmark(int studyNo, int memberNo) throws Exception;
  void deleteBookmark(int studyNo, int memberNo) throws Exception;


}
