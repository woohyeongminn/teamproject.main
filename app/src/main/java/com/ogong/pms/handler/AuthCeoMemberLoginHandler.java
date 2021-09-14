package com.ogong.pms.handler;

import java.util.List;
import com.ogong.menu.Menu;
import com.ogong.pms.domain.CeoMember;
import com.ogong.util.Prompt;

public class AuthCeoMemberLoginHandler extends AbstractLoginHandler {

  List<CeoMember> ceoMemberList;

  static CeoMember loginCeoMember;
  public static CeoMember getLoginCeoMember() {
    return loginCeoMember;
  }

  public static int getUserAccessLevel() {
    return accessLevel;
  }

  public AuthCeoMemberLoginHandler(List<CeoMember> ceoMemberList) {
    this.ceoMemberList = ceoMemberList;
  }

  // ----------------------------------------------------------------------

  @Override
  public void execute() {

    System.out.println();
    String inputadminEmail = Prompt.inputString(" 이메일 : ");
    String inputadminPassword = "";
    CeoMember ceoMember = findByAdminEmail(inputadminEmail);

    if (ceoMember == null) {
      System.out.println("\n >> 등록된 회원이 아닙니다.");
    }

    while (ceoMember != null) {
      inputadminPassword = Prompt.inputString(" 비밀번호 : ");

      if (ceoMember.getCeoPassword().equals(inputadminPassword)) {
        ceoMember.setCeoEmail(inputadminEmail);
        ceoMember.setCeoPassword(inputadminPassword);
        System.out.printf("\n >> '%s'님 환영합니다!\n", ceoMember.getCeoBossName());
        loginCeoMember = ceoMember;
        accessLevel = Menu.CEO_LOGIN;
        return;
      }

      System.out.println(" >> 비밀번호를 다시 입력하세요.\n");
      return;
    } 
  } 


  private CeoMember findByAdminEmail (String inputEmail) {
    for (CeoMember ceoMember : ceoMemberList) {
      if (inputEmail.equals(ceoMember.getCeoEmail())) {
        return ceoMember;
      }
    }
    return null;
  }
}

