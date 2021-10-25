package com.ogong.pms.dao;

import java.util.List;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.FreeBoardFile;

public interface FreeBoardDao {
  void insert(FreeBoard freeBoard) throws Exception;
  void insertFile(FreeBoardFile fileName, int boardNo) throws Exception;
  List<FreeBoard> findAll(int studyNo) throws Exception;
  FreeBoard findByNo(int boardNo, int studyNo) throws Exception;
}
