package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Admin;
import com.ogong.util.Prompt;

public class AdminInfoHandler extends AbstractAdminHandler {

  AdminUpdateHandler adminUpdateHandler;

  public AdminInfoHandler(List<Admin> adminList, AdminUpdateHandler adminUpdateHandler) {
    super(adminList);
    this.adminUpdateHandler = adminUpdateHandler;
  }

  public void execute() {
    System.out.println();
    System.out.println("▶ 내 프로필");
    System.out.println();

    Admin adminpro = AuthAdminLoginHandler.getLoginAdmin();

    System.out.printf("닉네임 : %s\n", adminpro.getMasterNickname());
    System.out.printf("이메일 : %s\n", adminpro.getMasterEmail());

    System.out.println();
    System.out.println("1. 수정하기");
    System.out.println("2. 뒤로가기");

    int selectAdminNo = Prompt.inputInt("선택> ");
    switch (selectAdminNo) {
      case 1: adminUpdateHandler.execute(); break;
      default : return;
    }
  }


}
