package com.ogong.pms.handler.admin;

import java.util.HashMap;
import com.ogong.pms.domain.CeoMember;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class AdminCeoMemberDetailHandler implements Command {

  RequestAgent requestAgent;

  public AdminCeoMemberDetailHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 기업회원 상세");
    int inputCeoNo = Prompt.inputInt(" 번호 : ");
    System.out.println();

    HashMap<String,String> params = new HashMap<>();
    params.put("inputCeoNo", String.valueOf(inputCeoNo));

    requestAgent.request("ceoMember.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(">> 해당 번호의 기업 회원이 없습니다.");
      return;
    }

    CeoMember ceoMember = requestAgent.getObject(CeoMember.class);

    System.out.printf(" [%s]\n", ceoMember.getCeoBossName());
    System.out.printf(" >> 점포명 : %s\n", ceoMember.getCeoEmail());
    System.out.printf(" >> 이메일 : %s\n", ceoMember.getCeoEmail());
    System.out.printf(" >> 사업자등록번호 : %s\n", ceoMember.getCeoLicenseNo());
    System.out.printf(" >> 사진 : %s\n", ceoMember.getCeoPhoto());
    System.out.printf(" >> 가입일 : %s\n", ceoMember.getCeoregisteredDate());

    request.setAttribute("inputCeoNo", inputCeoNo);

    System.out.println();
    System.out.println("1. 수정");
    System.out.println("2. 탈퇴");
    System.out.println("0. 이전");

    while (true) {
      int selectNo = Prompt.inputInt("선택> ");
      switch (selectNo) {
        case 1: request.getRequestDispatcher("/adminCeoMember/update").forward(request); return;
        case 2: request.getRequestDispatcher("/adminCeoMember/delete").forward(request); return;
        case 0: return;
        default :
          System.out.println(" >> 번호를 다시 선택해 주세요.");
      }
    }
  }
}
