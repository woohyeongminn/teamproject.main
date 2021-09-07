package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Admin;

public class AdminInfoHandler extends AbstractAdminHandler {

  public AdminInfoHandler(List<Admin> adminList) {
    super(adminList);
  }

  public void execute() {
    System.out.println();
    System.out.println("▶ 내 프로필");
    System.out.println();

    Admin adminpro = AuthAdminLoginHandler.getLoginAdmin();

    System.out.printf(" 닉네임 : %s\n", adminpro.getMasterNickname());
    System.out.printf( "이메일 : %s\n", adminpro.getMasterEmail());

    goAdminProUpdate();
  }
}
