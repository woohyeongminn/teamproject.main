package com.ogong.pms.dao;

import java.util.Calendar;
import java.util.List;
import com.ogong.pms.domain.Study;

public interface CalendarDao {

  // --------------- [ 캘린더 ] ----------------------------------------
  void insert(Calendar calendar, int studyNo) throws Exception;
  void update(Calendar calendar, int studyNo) throws Exception;
  void delete(int studyNo, int memberNo, int calenderNo) throws Exception;
  List<Calendar> findAll(int studyNo) throws Exception;
  Study findByNo(int studyinputNo, int memberNo, int calendarNo) throws Exception;

}
