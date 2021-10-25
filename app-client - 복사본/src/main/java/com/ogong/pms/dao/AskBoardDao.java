package com.ogong.pms.dao;

import java.util.List;
import com.ogong.pms.domain.AskBoard;

public interface AskBoardDao {

  void insertPer(AskBoard askBoard) throws Exception;
  void insertCeo(AskBoard askBoard) throws Exception;
  void update(AskBoard askBoard) throws Exception;
  void delete(int no) throws Exception;
  List<AskBoard> findAll() throws Exception;
  AskBoard findByNo(int no) throws Exception;

  // 답변 전용
  void insertreply(AskBoard askBoard) throws Exception;
  void updateViewCount(AskBoard askBoard) throws Exception;

}
