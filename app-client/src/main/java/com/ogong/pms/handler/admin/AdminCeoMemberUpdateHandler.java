package com.ogong.pms.handler.admin;

import java.util.HashMap;
import com.ogong.pms.domain.CeoMember;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class AdminCeoMemberUpdateHandler implements Command {

  RequestAgent requestAgent;

  public AdminCeoMemberUpdateHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  //관리자용
  @Override 
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 기업회원 변경");
    System.out.println();

    int updateCeoNo = (int) request.getAttribute("inputCeoNo");

    HashMap<String,String> params = new HashMap<>();
    params.put("inputCeoNo", String.valueOf(updateCeoNo));

    requestAgent.request("ceoMember.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 해당 번호의 기업 회원이 없습니다.");
      return;
    }

    CeoMember ceoMember = requestAgent.getObject(CeoMember.class);

    String ceoBossName = Prompt.inputString(" 대표자명(" + ceoMember.getCeoBossName()  + ") : ");
    String ceophoto = Prompt.inputString(" 사진(" + ceoMember.getCeoPhoto()  + ") : ");
    String ceoEmail = Prompt.inputString(" 이메일(" + ceoMember.getCeoEmail() + ") : ");
    String ceoPassword = Prompt.inputString(" 비밀번호(" + ceoMember.getCeoPassword() + ") : ");

    System.out.println();
    String input = Prompt.inputString(" 정말 변경하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 기업 회원 변경을 취소하였습니다.");
      return;
    }

    ceoMember.setCeoBossName(ceoBossName);
    ceoMember.setCeoEmail(ceoEmail);
    ceoMember.setCeoPassword(ceoPassword);
    ceoMember.setCeoPhoto(ceophoto);

    requestAgent.request("ceoMember.update", ceoMember);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("기업회원 변경 실패!");
      System.out.println(requestAgent.getObject(String.class));
      return;
    }

    System.out.println(" >> 기업 회원 정보를 변경하였습니다.");
  }

}
