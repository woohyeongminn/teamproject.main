package com.ogong.pms.handler2;

import java.util.List;
import com.ogong.pms.domain.Admin;
import com.ogong.util.Prompt;

public class AuthAdminLoginHandler {

  List<Admin> adminList;

  static Admin loginAdmin;
  public static Admin getLoginAdmin() {
    return loginAdmin;
  }

  public AuthAdminLoginHandler(List<Admin> adminList) {
    this.adminList = adminList;

    Admin testAdmin = new Admin();
    testAdmin.setMasterNo(1);
    testAdmin.setMasterNickname("관리자");
    testAdmin.setMasterEmail("ogong@ogong.com");
    testAdmin.setMasterPassword("1234");

    adminList.add(testAdmin);
  }

  // ----------------------------------------------------------------------

  public void addAdminLoginPage() {
    System.out.println();
    String inputadminEmail = Prompt.inputString("이메일 : ");
    String inputadminPassword = "";
    Admin admin = findByAdminEmail(inputadminEmail);
    if (admin == null) {
      System.out.println("관리자 이메일이 아닙니다.");
    }
    while (admin != null) {
      inputadminPassword = Prompt.inputString("비밀번호 : ");
      if (admin.getMasterPassword().equals(inputadminPassword)) {
        admin.setMasterEmail(inputadminEmail);
        admin.setMasterPassword(inputadminPassword);
        System.out.printf("%s님 환영합니다!\n", admin.getMasterNickname());
        loginAdmin = admin;
        return;
      }
      String input = Prompt.inputString("비밀번호를 잊어버렸나요? (네 / 아니오) ");
      if (input.equalsIgnoreCase("아니오") || input.length() == 0) {
        System.out.println("비밀번호를 다시 입력하세요.");
        continue;
      } else {
        findAdminPw();
      }
    } 
  }
  public Admin findByAdminEmail(String masterEmail) {
    for (Admin admin : adminList) {
      if (admin.getMasterEmail().equals(masterEmail)) {
        return admin;
      }
    }
    return null;
  }

  private void findAdminPw() {
    System.out.println();
    System.out.println("▶ 임시 비밀번호 발급");
    while (true) {
      String inputEmail =  Prompt.inputString("이메일 : ");
      Admin adminEmail = findByEmail(inputEmail);
      if (adminEmail == null) {
        System.out.printf("%s님 이메일을 다시 입력해 주세요.", adminEmail.getMasterNickname());
        continue;
      } else {
        System.out.printf("%s님의 임시 비밀번호 >> ", adminEmail.getMasterNickname());
        System.out.println(adminEmail.getMasterPassword().hashCode());
        System.out.println("로그인 후 비밀번호를 변경해 주세요.");
        String hashPW = String.valueOf(adminEmail.getMasterPassword().hashCode());
        adminEmail.setMasterPassword(hashPW);
      }
      break;
    }
  }

  private Admin findByEmail(String inputEmail) {
    for (Admin adminEmail : adminList) {
      if (inputEmail.equals(adminEmail.getMasterEmail())) {
        return adminEmail;
      }
    }
    return null;
  }

}
