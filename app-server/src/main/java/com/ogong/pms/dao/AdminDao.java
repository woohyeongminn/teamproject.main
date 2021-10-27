package com.ogong.pms.dao;

import org.apache.ibatis.annotations.Param;
import com.ogong.pms.domain.Admin;

public interface AdminDao {

  // ----------- [ 관리자 ] ------------------------------------------------------

  void updateNickname(Admin admin) throws Exception;
  void updateEmail(Admin admin) throws Exception;
  void updatePassword(Admin admin) throws Exception;
  Admin findByAdminNo(int no) throws Exception;
  Admin findByEmailAndPassword(@Param("email") String email,@Param("password") String password) throws Exception;

}

