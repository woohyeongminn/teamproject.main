package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Admin;

public class AuthAdminLogoutHandler implements Command {

  List<Admin> adminList;

  static Admin loginAdmin;
  public static Admin getLoginAdmin() {
    return loginAdmin;
  }

  public AuthAdminLogoutHandler(List<Admin> adminList) {
    this.adminList = adminList;

  }

  public void execute() {
    System.out.println();
    loginAdmin = null;
    System.out.println("로그아웃 되었습니다.");
  }


}
