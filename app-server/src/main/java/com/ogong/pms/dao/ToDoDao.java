package com.ogong.pms.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ogong.pms.vo.ToDo;

public interface ToDoDao {
  void insert(ToDo todo) throws Exception;

  List<ToDo> findAll(int studyNo) throws Exception;

  ToDo findByNo(@Param("studyNo") int studyNo, @Param("todoNo") int todoNo) throws Exception;

  void delete(int todoNo) throws Exception;

  void update(ToDo todo) throws Exception;
}
