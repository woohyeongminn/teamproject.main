package com.ogong.pms.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ogong.pms.vo.Calendar;

public interface CalendarDao {

  // --------------- [ 캘린더 ] ----------------------------------------
  void insert(@Param("calender")Calendar calender, @Param("studyNo")int studyNo) throws Exception;
  void update(@Param("calender")Calendar calender, @Param("studyNo")int studyNo) throws Exception;
  void delete(@Param("studyNo")int studyNo, @Param("calendarNo")int calendarNo) throws Exception;
  List<Calendar> findAll(int studyNo) throws Exception;
  Calendar findByNo(int calendarNo) throws Exception;
}
