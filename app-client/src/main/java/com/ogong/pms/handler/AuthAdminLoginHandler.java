package com.ogong.pms.handler;

import java.util.HashMap;
import com.ogong.menu.Menu;
import com.ogong.pms.domain.Admin;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class AuthAdminLoginHandler extends AbstractLoginHandler implements Command {

  RequestAgent requestAgent;

  static Admin loginAdmin;
  public static Admin getLoginAdmin() {
    return loginAdmin;
  }

  public AuthAdminLoginHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;

    //    Admin testAdmin = new Admin();
    //    testAdmin.setMasterNo(1);
    //    testAdmin.setMasterNickname("관리자");
    //    testAdmin.setMasterEmail("ogong");
    //    testAdmin.setMasterPassword("1234");
    //
    //    adminList.add(testAdmin);
  }

  // ----------------------------------------------------------------------

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println();
    String inputadminEmail = Prompt.inputString(" 이메일 : ");
    String inputadminPassword = "";
    inputadminPassword = Prompt.inputString(" 비밀번호 : ");

    HashMap<String,String> params = new HashMap<>();
    params.put("adminEmail", inputadminEmail);
    params.put("adminPassword", inputadminPassword);

    requestAgent.request("admin.selectOneByEmailPassword", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("이메일과 암호가 일치하는 회원을 찾을 수 없습니다.");
      return;
    }

    Admin admin = requestAgent.getObject(Admin.class);

    System.out.printf("\n >> '%s'님 환영합니다!\n", admin.getMasterNickname());
    loginAdmin = admin;
    accessLevel = Menu.ADMIN_LOGIN;
  }
}