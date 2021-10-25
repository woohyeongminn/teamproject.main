package com.ogong.pms.dao.impl;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.FreeBoardDao;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.FreeBoardFile;

public class MybatisFreeBoardDao implements FreeBoardDao {

  SqlSession sqlSession;

  public MybatisFreeBoardDao(SqlSession sqlSession){
    this.sqlSession = sqlSession;
  }

  @Override
  public void insert(FreeBoard freeBoard) throws Exception {
    sqlSession.insert("FreeBoardMapper.insert", freeBoard);
    sqlSession.commit();
  }

  @Override
  public void insertFile(FreeBoardFile file, int boardNo) throws Exception {

    if (file != null) {
      HashMap<String,Object> params = new HashMap<>();
      params.put("atcFileName", file.getAtcFileName());
      params.put("boardNo", boardNo);

      sqlSession.insert("FreeBoardMapper.insertFile", params);
    }

    sqlSession.commit();
  }

  @Override
  public List<FreeBoard> findAll(int studyNo) throws Exception {
    return sqlSession.selectList("FreeBoardMapper.findAll", studyNo);
  }

  @Override
  public FreeBoard findByNo(int boardNo, int studyNo) throws Exception {
    HashMap<String,Object> params = new HashMap<>();
    params.put("boardNo", boardNo);
    params.put("studyNo", studyNo);

    return sqlSession.selectOne("FreeBoardMapper.findByNo", params);
  }

  //
  //  // 탈퇴
  //  @Override
  //  public void updateActive(CeoMember ceoMember) throws Exception {
  //    sqlSession.update("CeoMemberMapper.updateActive", ceoMember);
  //    sqlSession.commit();
  //  }
  //
  //  @Override
  //  public void delete(int no) throws Exception {
  //
  //  }
}