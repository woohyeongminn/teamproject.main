package com.ogong.pms.dao.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.domain.CeoMember;

public class MybatisCeoMemberDao implements CeoMemberDao {

  Connection con;
  SqlSession sqlSession;

  public MybatisCeoMemberDao(SqlSession sqlSession){
    this.sqlSession = sqlSession;
  }

  @Override
  public void insert(CeoMember ceoMember) throws Exception {
    sqlSession.insert("CeoMemberMapper.insert", ceoMember);

    HashMap<String,Object> params = new HashMap<>();
    params.put("ceoNo", ceoMember.getCeoNo());
    params.put("ceoBossname", ceoMember.getCeoBossName());
    params.put("ceoLicenseNo", ceoMember.getCeoLicenseNo());

    sqlSession.insert("CeoMemberMapper.insertCeo", params);

    sqlSession.commit();
  }


  @Override
  public List<CeoMember> findAll() throws Exception {
    return sqlSession.selectList("CeoMemberMapper.findAll");
  }

  @Override
  public CeoMember findByNo(int no) throws Exception {
    return sqlSession.selectOne("CeoMemberMapper.findByNo", no);
  }

  // 아이디 찾기
  @Override
  public CeoMember findByName(String inputName) throws Exception {
    List<CeoMember> list = sqlSession.selectList("CeoMemberMapper.findByName", inputName);
    if (list.size() > 0) {
      return list.get(0);
    } else {
      return null;
    }
  }

  // 비밀번호 찾기
  @Override
  public CeoMember findByEmail(String inputEmail) throws Exception {
    return sqlSession.selectOne("CeoMemberMapper.findByEmail", inputEmail);
  }

  // 로그인
  @Override
  public CeoMember findByEmailAndPassword(String inputEmail, String inputPassword) throws Exception {
    HashMap<String,Object> params = new HashMap<>();
    params.put("email", inputEmail);
    params.put("password", inputPassword);

    return sqlSession.selectOne("CeoMemberMapper.findByEmailAndPassword", params);
  }

  // 개인정보 수정 - 이름
  @Override
  public void updateName(CeoMember ceoMember) throws Exception {
    sqlSession.update("CeoMemberMapper.updateName", ceoMember);
    sqlSession.commit();
  }

  //개인정보 수정 - 닉네임
  @Override
  public void updateNickName(CeoMember ceoMember) throws Exception {
    sqlSession.update("CeoMemberMapper.updateNickName", ceoMember);
    sqlSession.commit();
  }

  //개인정보 수정 - 사진
  @Override
  public void updatePhoto(CeoMember ceoMember) throws Exception {
    sqlSession.update("CeoMemberMapper.updatePhoto", ceoMember);
    sqlSession.commit();
  }

  //개인정보 수정 - 전화번호
  @Override
  public void updateTel(CeoMember ceoMember) throws Exception {
    sqlSession.update("CeoMemberMapper.updateTel", ceoMember);
    sqlSession.commit();
  }

  //개인정보 수정 - 이메일
  @Override
  public void updateEmail(CeoMember ceoMember) throws Exception {
    sqlSession.update("CeoMemberMapper.updateEmail", ceoMember);
    sqlSession.commit();
  }

  //개인정보 수정 - 비밀번호
  @Override
  public void updatePassword(CeoMember ceoMember) throws Exception {
    sqlSession.update("CeoMemberMapper.updatePassword", ceoMember);
    sqlSession.commit();
  }

  // 탈퇴
  @Override
  public void updateActive(CeoMember ceoMember) throws Exception {
    sqlSession.update("CeoMemberMapper.updateActive", ceoMember);
    sqlSession.commit();
  }

  @Override
  public void delete(int no) throws Exception {

  }
}