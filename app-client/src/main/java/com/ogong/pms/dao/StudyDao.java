package com.ogong.pms.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;

public interface StudyDao {

  // --------------- [ 스터디 ] ----------------------------------------
  void insert(Study study) throws Exception;
  void update(Study study) throws Exception;
  void deleteAllBookmark(int studyNo) throws Exception;
  void deleteStudy(@Param("studyNo")int studyNo, @Param("memberNo")int memberNo) throws Exception;
  List<Study> findAll() throws Exception;
  List<Study> findByKeyword(String keyword) throws Exception;
  Study findByNo(int studyinputNo) throws Exception;

  //--------------- [ 내 스터디 ] ----------------------------------------
  Study findByMyNo(@Param("studyNo")int studyNo, @Param("memberNo")int memberNo) throws Exception;

  //--------------- [ 구성원 ] ----------------------------------------
  List<Member> findByWaitingGuilderAll(int studyNo) throws Exception;
  List<Member> findByGuildersAll(int studyNo) throws Exception;
  void insertGuilder(@Param("studyNo")int studyNo, @Param("memberNo")int memberNo) throws Exception;
  void updateOwner(@Param("studyNo")int studyNo, @Param("memberNo")int memberNo) throws Exception;
  void updateGuilder(@Param("studyNo")int studyNo, @Param("memberNo")int memberNo) throws Exception;
  void deleteGuilder(@Param("studyNo")int studyNo, @Param("memberNo")int memberNo) throws Exception;
  void deleteAllGuilder(int studyNo) throws Exception;

  //--------------- [ 북마크 ] ----------------------------------------
  void insertBookmark(@Param("studyNo")int studyNo, @Param("memberNo")int memberNo) throws Exception;
  void deleteBookmark(@Param("studyNo")int studyNo, @Param("memberNo")int memberNo) throws Exception;


}
