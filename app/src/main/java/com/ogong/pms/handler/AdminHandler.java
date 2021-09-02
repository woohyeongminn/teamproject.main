package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Admin;
import com.ogong.util.Prompt;

public class AdminHandler {

  List<Admin> adminList;

  static Admin loginAdmin;
  public static Admin getLoginAdmin() {
    return loginAdmin;
  }

  public AdminHandler(List<Admin> adminList) {
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
    String inputadminEmail = Prompt.inputString("이메일: ");
    String inputadminPassword = "";
    Admin admin = findByAdminEmail(inputadminEmail);
    if (admin == null) {
      System.out.println("관리자 이메일이 아닙니다.");
    }
    while (admin != null) {
      inputadminPassword = Prompt.inputString("비밀번호: ");
      if (admin.getMasterPassword().equals(inputadminPassword)) {
        admin.setMasterEmail(inputadminEmail);
        admin.setMasterPassword(inputadminPassword);
        System.out.printf("%s님 환영합니다!\n", admin.getMasterNickname());
        loginAdmin = admin;
        return;
      }
      String input = Prompt.inputString("비밀번호를 잊어버렸나요?(y/N) ");
      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("비밀번호를 다시 입력하세요.");
        continue;
      } else {
        findAdminPw();
      }
    } 
  }

  public void logOut() {
    loginAdmin = null;
    System.out.println("로그아웃 되었습니다.");
  }

  public Admin findByAdminEmail(String masterEmail) {
    for (Admin admin : adminList) {
      if (admin.getMasterEmail().equals(masterEmail)) {
        return admin;
      }
    }
    return null;
  }

  // ----------------------------------------------------------------------

  public void detail() {
    System.out.println();
    System.out.println("▶ 내 프로필");

    Admin adminpro = getLoginAdmin();

    System.out.printf("닉네임: %s\n", adminpro.getMasterNickname());
    System.out.printf("이메일: %s\n", adminpro.getMasterEmail());
  }

  public void update() {
    System.out.println();
    System.out.println("▶ 프로필 수정하기");

    Admin adminmodify = getLoginAdmin();

    String adminModifyNickName = Prompt.inputString("닉네임(" + adminmodify.getMasterNickname()  + ")? ");
    String adminModifyEmail = Prompt.inputString("이메일(" + adminmodify.getMasterEmail() + ")? ");
    String adminModifyPassword = Prompt.inputString("비밀번호(" + adminmodify.getMasterPassword() + ")? ");

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("취소되었습니다.");
      return;
    }

    adminmodify.getMasterNickname();
    adminmodify.getMasterEmail();
    adminmodify.getMasterPassword();

    System.out.printf("%s님의 정보가 변경되었습니다.", adminmodify.getMasterNickname());
  }

  // ----------------------------------------------------------------------


  // ----------------------------------------------------------------------

  private void findAdminPw() {
    System.out.println();
    System.out.println("▶ 임시 비밀번호 발급");
    while (true) {
      String inputEmail =  Prompt.inputString("이메일: ");
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

  public void selectAdminPage() {
    System.out.println();
    System.out.println("1. 수정하기");
    System.out.println("2. 뒤로가기");

    int selectAdminNo = Prompt.inputInt("선택> ");
    switch (selectAdminNo) {
      case 1: update(); break;
      default : return;
    }
  }

}
