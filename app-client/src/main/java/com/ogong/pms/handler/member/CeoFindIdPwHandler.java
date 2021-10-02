package com.ogong.pms.handler.member;

import java.util.HashMap;
import com.ogong.pms.domain.CeoMember;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class CeoFindIdPwHandler implements Command {

  //List<CeoMember> ceoMemberList;
  RequestAgent requestAgent;

  public CeoFindIdPwHandler(/* List<CeoMember> ceoMemberList, */RequestAgent requestAgent) {
    //this.ceoMemberList = ceoMemberList;
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("1. 이메일 찾기");
    System.out.println("2. 비밀번호 찾기");

    int selectNo = Prompt.inputInt("선택> ");
    switch (selectNo) {
      case 1 : wantCeoEmail(); break;
      case 2 : wantCeoPw(); break;
      default : return;
    }
  }


  public void wantCeoEmail() throws Exception {
    System.out.println();
    System.out.println("▶ 이메일 찾기");

    //    requestAgent.request("ceoMember.selectOne", null);
    //
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println("목록 조회 실패!");
    //      return;
    //    }

    while (true) {
      System.out.println();
      String inputNick =  Prompt.inputString(" 닉네임 : ");

      HashMap<String,String> params = new HashMap<>();
      params.put("ceoMemberNick", inputNick);

      requestAgent.request("ceoMember.selectOneByNickname", params);

      if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
        CeoMember ceoMember = requestAgent.getObject(CeoMember.class);
        System.out.println();
        System.out.printf(" '%s님'의 이메일 >> ", ceoMember.getCeoBossName());
        System.out.println(ceoMember.getCeoEmail());

      } else {
        System.out.println(" >> 해당 닉네임이 존재하지 않습니다.");
        return;
      }
      break;
    }
    String input = Prompt.inputString(" 비밀번호 찾기로 넘어가시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 찾기를 종료합니다.");
      return;
    } 
    wantCeoPw();
  }

  public void wantCeoPw() throws Exception {
    System.out.println();
    System.out.println("▶ 임시 비밀번호 발급");
    while (true) {
      System.out.println();
      String inputEmail =  Prompt.inputString(" 이메일 : ");

      HashMap<String,String> params = new HashMap<>();
      params.put("ceoMemberEmail", inputEmail);

      requestAgent.request("ceoMember.selectOneByEmail", params);

      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println(" >> 해당 이메일이 존재하지 않습니다.");
        continue;
      } else {
        CeoMember ceoMember = requestAgent.getObject(CeoMember.class); 
        System.out.printf(" '%s님'의 임시 비밀번호 : ", ceoMember.getCeoBossName());
        System.out.println(ceoMember.getCeoPassword().hashCode());
        System.out.println();
        System.out.println(" >> 로그인 후 비밀번호를 변경해 주세요.");
        String hashPW = String.valueOf(ceoMember.getCeoPassword().hashCode());
        ceoMember.setCeoPassword(hashPW);
      }
      break;
    }
  }
}
