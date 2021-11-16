package com.ogong.pms.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ogong.pms.domain.CeoMember;

public interface CeoMemberDao {

  List<CeoMember> findAll() throws Exception;

  CeoMember findByNo(int no) throws Exception;
  CeoMember findByName(String inputNick) throws Exception;
  CeoMember findByEmail(String inputEmail) throws Exception;
  CeoMember findByTel(String inputTel) throws Exception;
  CeoMember findByEmailAndPassword(@Param("email") String email, @Param("password") String password) throws Exception;

  void insert(CeoMember ceoMember) throws Exception;
  void insertCeo(CeoMember ceoMember) throws Exception;

  void updateName(CeoMember ceoMember) throws Exception;
  void updateNickName(CeoMember ceoMember) throws Exception;
  void updatePhoto(CeoMember ceoMember) throws Exception;
  void updateTel(CeoMember ceoMember) throws Exception;
  void updateEmail(CeoMember ceoMember) throws Exception;
  void updatePassword(CeoMember ceoMember) throws Exception;

  void updateCeoMember(CeoMember ceoMember) throws Exception;

  void updateActive(CeoMember ceoMember) throws Exception;

  void delete(int no) throws Exception;

  String idOverlap(String id) throws Exception;

}

