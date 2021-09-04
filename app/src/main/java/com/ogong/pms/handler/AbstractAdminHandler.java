package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Admin;
import com.ogong.util.Prompt;

public abstract class AbstractAdminHandler implements Command {

  List<Admin> adminList;

  static Admin loginAdmin;
  public static Admin getLoginAdmin() {
    return loginAdmin;
  }

  public AbstractAdminHandler(List<Admin> adminList) {
    this.adminList = adminList;

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
