package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.CeoMember;
import com.ogong.util.Prompt;

public class CeoDetailHandler extends AbstractCeoHandler {

  List<CeoMember> ceoMemberList;

  public CeoDetailHandler(List<CeoMember> ceoMemberList) {
    super(ceoMemberList);
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 프로필");

    try {
      CeoMember CeoMember = AuthCeoMemberLoginHandler.getLoginCeoMember();
      System.out.println();
      System.out.printf(" [%s]\n", CeoMember.getCeoBossName());
      System.out.printf(" >> 이메일 : %s\n", CeoMember.getCeoEmail());
      System.out.printf(" >> 사  진 : %s\n", CeoMember.getCeoPhoto());
      System.out.printf(" >> 가입일 : %s\n", CeoMember.getCeoregisteredDate());

    } catch (NullPointerException e) {
      System.out.println("로그인 하세요.");
    }

    System.out.println();
    System.out.println("1. 수정하기");
    System.out.println("0. 뒤로가기");
    int selectNo = Prompt.inputInt("선택> ");
    switch (selectNo) {
      //      case 1 : commandMap.get("/member/update").execute(); break;
      default : return;
    }
  }
}
