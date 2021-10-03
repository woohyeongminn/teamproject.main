package com.ogong.pms.handler.admin;

import java.util.Collection;
import com.ogong.pms.domain.CeoMember;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;

public class AdminCeoMemberListHandler implements Command  {

  //HashMap<String, Command> commandMap;
  RequestAgent requestAgent;

  public AdminCeoMemberListHandler(RequestAgent requestAgent /*, HashMap<String, Command> commandMap*/) {
    this.requestAgent = requestAgent;
  }

  // 관리자가 사용
  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 기업회원 목록");
    System.out.println();

    requestAgent.request("ceoMember.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("목록 조회 실패!");
      return;
    }

    Collection<CeoMember> ceoMemberList = requestAgent.getObjects(CeoMember.class);

    for (CeoMember ceoMember : ceoMemberList) {
      System.out.printf(" (%d)\n 대표자명 : %s\n 이메일 : %s\n 가입일 : %s\n",
          ceoMember.getCeoNo(),
          ceoMember.getCeoBossName(),
          ceoMember.getCeoEmail(),
          ceoMember.getCeoregisteredDate());

      System.out.println();
    }
    //    selectUserModifyPage();
  }

  //  private void selectUserModifyPage() {
  //    System.out.println("1. 상세");
  //    System.out.println("2. 탈퇴");
  //    System.out.println("0. 이전");
  //
  //    int selectAdminNo = Prompt.inputInt("선택> ");
  //    switch (selectAdminNo) {
  //      //      case 1: commandMap.get("/adminCeoMember/detail").execute(); break;
  //      //      case 2: commandMap.get("/adminCeoMember/delete").execute(); break;
  //      default : return;
  //    }
  //  }
}
