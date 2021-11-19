package com.ogong.pms.dao;

import com.ogong.pms.domain.Study;

public interface StudyGuilderDao {

  // --------------- [ 스터디 길더 ] ----------------------------------------
  void insertGuilder(int studyNo, int memberNo) throws Exception;
  //void insertBookmark(Study study, Member member) throws Exception;
  void updateOwner(int studyNo, int memberNo) throws Exception;
  void updateGuilder(int studyNo, int memberNo) throws Exception;
  void deleteGuilder(int studyNo, int memberNo) throws Exception;
  Study findWaitingGuilder(Study study) throws Exception;
  Study findGuilder(Study study) throws Exception;

}
