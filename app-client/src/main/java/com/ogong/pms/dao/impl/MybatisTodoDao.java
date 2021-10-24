package com.ogong.pms.dao.impl;

import java.sql.Connection;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.ToDoDao;
import com.ogong.pms.domain.ToDo;

public class MybatisTodoDao implements ToDoDao {

  Connection con;
  SqlSession sqlSession;

  public MybatisTodoDao(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  @Override
  public void insert(ToDo todo) throws Exception {
    sqlSession.insert("ToDoMapper.insert", todo);
    sqlSession.commit();
  }

  @Override
  public List<ToDo> findAll(int studyNo) throws Exception {
    return sqlSession.selectList("ToDoMapper.findAll", studyNo);
  }

  @Override
  public ToDo findByNo(int todoNo) throws Exception {
    return sqlSession.selectOne("ToDoMapper.findByNo", todoNo);
  }

  @Override
  public void update(ToDo todo) throws Exception {
    sqlSession.update("ToDoMapper.update", todo);
    sqlSession.commit();
  }

  @Override
  public void delete(int todoNo) throws Exception {
    sqlSession.delete("ToDoMapper.delete", todoNo);
    sqlSession.commit();
  }
}
