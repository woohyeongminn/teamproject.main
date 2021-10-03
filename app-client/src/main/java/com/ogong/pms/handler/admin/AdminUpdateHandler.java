package com.ogong.pms.handler.admin;

import java.util.HashMap;
import com.ogong.pms.domain.Admin;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class AdminUpdateHandler implements Command {

  RequestAgent requestAgent;

  public AdminUpdateHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 프로필 수정하기");
    System.out.println();

    int adminNo = (int) request.getAttribute("inputNo");

    HashMap<String,String> params = new HashMap<>();
    params.put("adminNo", String.valueOf(adminNo));

    requestAgent.request("admin.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" 해당 번호의 관리자가 없습니다.");
      return;
    }

    Admin admin = requestAgent.getObject(Admin.class);

    String adminEmail = Prompt.inputString(
        " 이메일(" + admin.getMasterEmail() + ") : ");
    String adminPassword = Prompt.inputString(
        " 비밀번호(" + admin.getMasterPassword() + ") : ");

    System.out.println();
    String input = Prompt.inputString(" 정말 변경하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 취소되었습니다.");
      return;
    } 

    admin.setMasterEmail(adminEmail);
    admin.setMasterPassword(adminPassword);

    requestAgent.request("admin.update", admin);

    System.out.printf("\n >> '%s'님의 정보가 변경되었습니다.", admin.getMasterNickname());
    System.out.println();
  }

}
