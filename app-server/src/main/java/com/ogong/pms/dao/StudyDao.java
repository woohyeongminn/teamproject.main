package com.ogong.pms.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;

public interface StudyDao {

  // --------------- [ 스터디 ] ----------------------------------------
  void insert(Study study) throws Exception;
  void update(Study study) throws Exception;
  void updateStudyTitle(Study study) throws Exception;
  void updateNumberOfPeple(Study study) throws Exception;
  void updateFaceNo(Study study) throws Exception;
  void updateIntroduction(Study study) throws Exception;
  void deleteAllBookmark(int studyNo) throws Exception;
  List<Study> findAll() throws Exception;
  List<Study> findAllIng() throws Exception;
  List<Study> findAllEnd() throws Exception;
  List<Study> findByKeyword(@Param("input")String input, @Param("keyword")String keyword) throws Exception;
  Study findByNo(int studyinputNo) throws Exception;

  //--------------- [ 내 스터디 ] ----------------------------------------
  List<Map<String,String>> findAllByMyNo(int memberNo) throws Exception;
  Study findByMyNo(@Param("studyNo")int studyNo, @Param("memberNo")int memberNo) throws Exception;

  //--------------- [ 구성원 ] ----------------------------------------
  List<Member> findByWaitingGuilderAll(int studyNo) throws Exception;
  List<Member> findByGuildersAll(int studyNo) throws Exception;
  void insertGuilder(@Param("studyNo")int studyNo, @Param("memberNo")int memberNo) throws Exception;
  void updateOwner(@Param("studyNo")int studyNo, @Param("memberNo")int memberNo) throws Exception;
  void updateGuilder(@Param("studyNo")int studyNo, @Param("memberNo")int memberNo) throws Exception;
  void updateGuilderExpulsion(@Param("studyNo")int studyNo, @Param("memberNo")int memberNo) throws Exception;
  void updateGuilderExpulsionAll(int studyNo) throws Exception;
  void deleteGuilder(@Param("studyNo")int studyNo, @Param("memberNo")int memberNo) throws Exception;

  //--------------- [ 북마크 ] ----------------------------------------
  void insertBookmark(@Param("studyNo")int studyNo, @Param("memberNo")int memberNo) throws Exception;
  List<Member> findByBookmarkAll(int studyNo) throws Exception; // 스터디 번호 보내서 가져오기
  List<Study> findByMyBookmark(int memberNo) throws Exception; // 멤버 번호 보내서 북마크 가져오기
  Study findByBookmark(@Param("studyNo")int studyNo, @Param("memberNo")int memberNo) throws Exception; // 상세
  void deleteBookmark(@Param("studyNo")int studyNo, @Param("memberNo")int memberNo) throws Exception;

  //------------------[구현중/스터디 삭제 대신 수정으로]-----------------------------------------
  void updateStatusDelete(Study study) throws Exception;
  void updateStatusEnd(Study study) throws Exception;

}
