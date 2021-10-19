package com.ogong.pms.dao.impl;

import java.sql.Connection;
import java.util.List;
import com.ogong.pms.dao.AskBoardDao;
import com.ogong.pms.domain.AskBoard;

public class MariadbAskBoardDao implements AskBoardDao {

  Connection con;

  public MariadbAskBoardDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(AskBoard askBoard) throws Exception {
  }

  @Override
  public void update(AskBoard askBoard) throws Exception {
  }

  @Override
  public void delete(int no) throws Exception {
  }

  @Override
  public List<AskBoard> findAll() throws Exception {
    return null;
  }

  @Override
  public AskBoard findByNo(int no) throws Exception {
    return null;
  }

  @Override
  public AskBoard findByPerAskBoard(int askNo, int perMemberNo) throws Exception {
    return null;
  }

  @Override
  public AskBoard findByCeoAskBoard(int askNo, int ceoMemberNo) throws Exception {
    return null;
  }

}
