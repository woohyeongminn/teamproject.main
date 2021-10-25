//package com.ogong.pms.dao.impl;
//
//import java.util.List;
//import org.apache.ibatis.session.SqlSession;
//import com.ogong.pms.dao.AskBoardDao;
//import com.ogong.pms.domain.AskBoard;
//
//public class MybatisAskBoardDao implements AskBoardDao {
//
//  SqlSession sqlSession;
//
//  public MybatisAskBoardDao(SqlSession sqlSession) {
//    this.sqlSession = sqlSession;
//  }
//
//  @Override
//  public void insertPer(AskBoard askBoard) throws Exception {
//    sqlSession.insert("AskBoardMapper.insertPer", askBoard);
//  }
//
//  @Override
//  public void insertCeo(AskBoard askBoard) throws Exception {
//    sqlSession.insert("AskBoardMapper.insertCeo", askBoard);
//  }
//
//  @Override
//  public void insertreply(AskBoard askBoard) throws Exception {
//    sqlSession.insert("AskBoardMapper.insertreply", askBoard);
//  }
//
//  @Override
//  public void update(AskBoard askBoard) throws Exception {
//    sqlSession.update("AskBoardMapper.update", askBoard);
//  }
//
//  @Override
//  public void updateViewCount(AskBoard askBoard) throws Exception {
//    sqlSession.update("AskBoardMapper.updateViewCount", askBoard);
//  }
//
//  @Override
//  public void delete(int no) throws Exception {
//    sqlSession.delete("AskBoardMapper.deletereply", no);
//    sqlSession.delete("AskBoardMapper.delete", no);
//
//  }
//
//  @Override
//  public List<AskBoard> findAll() throws Exception {
//    return sqlSession.selectList("AskBoardMapper.findAll");
//  }
//
//  @Override
//  public AskBoard findByNo(int no) throws Exception {
//    return sqlSession.selectOne("AskBoardMapper.findByNo", no);
//  }
//}
