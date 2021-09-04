package com.ogong.pms.handler2;

import java.util.List;
import com.ogong.pms.domain.Admin;

public class AdminInfoHandler extends AbstractAdminHandler {

  public AdminInfoHandler(List<Admin> adminList) {
    super(adminList);
  }

  public void adminInfo() {
    System.out.println();
    System.out.println("▶ 내 프로필");
    System.out.println();

    Admin adminpro = getLoginAdmin();

    System.out.printf("닉네임 : %s\n", adminpro.getMasterNickname());
    System.out.printf("이메일 : %s\n", adminpro.getMasterEmail());
  }
}
