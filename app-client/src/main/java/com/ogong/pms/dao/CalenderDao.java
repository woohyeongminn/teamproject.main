package com.ogong.pms.dao;

import java.util.List;
import com.ogong.pms.domain.Calender;
import com.ogong.pms.domain.Study;

public interface CalenderDao {

  // --------------- [ 캘린더 ] ----------------------------------------
  void insert(Calender calender, int studyNo) throws Exception;
  void update(Calender calender, int studyNo) throws Exception;
  void delete(int studyNo, int memberNo, int calenderNo) throws Exception;
  List<Calender> findAll(int studyNo) throws Exception;
  Study findByNo(int studyinputNo, int memberNo, int calenderNo) throws Exception;

}
