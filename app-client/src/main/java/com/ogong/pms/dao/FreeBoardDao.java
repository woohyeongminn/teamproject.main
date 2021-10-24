package com.ogong.pms.dao;

import java.util.List;
import com.ogong.pms.domain.FreeBoard;

public interface FreeBoardDao {
  void insert(FreeBoard freeBoard) throws Exception;
  //void insertFile(FreeBoardFile file) throws Exception;
  List<FreeBoard> findAll(int studyNo) throws Exception;
  FreeBoard findByNo(int boardNo) throws Exception;
}
