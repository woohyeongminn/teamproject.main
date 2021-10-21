package com.ogong.pms.dao.impl;

import java.util.HashMap;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.AdminDao;
import com.ogong.pms.domain.Admin;

public class MybatisAdminDao implements AdminDao {

  SqlSession sqlSession;

  public MybatisAdminDao(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  // ----------- [ 관리자 ] ------------------------------------------------------

  @Override
  public void updateNickname(Admin admin) throws Exception {
    sqlSession.update("AdminMapper.updateNickname", admin);
    sqlSession.commit();
  }

  @Override
  public void updateEmail(Admin admin) throws Exception {
    sqlSession.update("AdminMapper.updateEmail", admin);
    sqlSession.commit();
  }

  @Override
  public void updatePassword(Admin admin) throws Exception {
    sqlSession.update("AdminMapper.updatePassword", admin);
    sqlSession.commit();
  }

  @Override
  public Admin findByAdminNo(int no) throws Exception {
    return sqlSession.selectOne("AdminMapper.findByAdminNo", no);
  }

  @Override
  public Admin findByEmailAndPassword(String email, String password) throws Exception {
    HashMap<String,Object> params = new HashMap<>();
    params.put("email", email);
    params.put("password", password);

    return sqlSession.selectOne("AdminMapper.findByEmailAndPassword", params);
  }
}
