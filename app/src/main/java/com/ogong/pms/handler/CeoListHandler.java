package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.CeoMember;
import com.ogong.util.Prompt;

public class CeoListHandler extends AbstractCeoHandler  {

  public CeoListHandler(List<CeoMember> ceoMemberList) {
    super(ceoMemberList);
  }

  // 관리자가 사용
  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 기업회원 목록");
    System.out.println();

    for (CeoMember ceoMember : ceoMemberList) {
      System.out.printf(" (%d)\n 대표자명 : %s\n 이메일 : %s\n 가입일 : %s\n",
          ceoMember.getCeoNo(),
          ceoMember.getCeoBossName(),
          ceoMember.getCeoEmail(),
          ceoMember.getCeoregisteredDate());

      System.out.println();
    }
    selectUserModifyPage();
  }

  private void selectUserModifyPage() {
    System.out.println("1. 상세");
    System.out.println("2. 수정");
    System.out.println("3. 탈퇴");
    System.out.println("0. 이전");

    int selectAdminNo = Prompt.inputInt("선택> ");
    switch (selectAdminNo) {
      case 1: adminCeoMemberdetail(); break;
      case 2: adminCeoMemberupdate(); break;
      case 3: adminCeoMemberdelete(); break;
      default : return;
    }
  }
}
