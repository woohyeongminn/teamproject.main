package com.ogong.pms.dao;

import com.ogong.pms.domain.Admin;

public interface AdminDao {

  // ----------- [ 관리자 ] ------------------------------------------------------

  void updateNickname(Admin admin) throws Exception;
  void updateEmail(Admin admin) throws Exception;
  void updatePassword(Admin admin) throws Exception;
  Admin findByAdminNo(int no) throws Exception;
  Admin findByEmailAndPassword(String email, String password) throws Exception;

}

