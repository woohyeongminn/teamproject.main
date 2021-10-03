package com.ogong.pms.handler.admin;

import java.util.HashMap;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class AdminCeoMemberDeleteHandler implements Command {

  //List<Cafe> cafeList;
  RequestAgent requestAgent;

  public AdminCeoMemberDeleteHandler(RequestAgent requestAgent/* , List<Cafe> cafeList */) {
    this.requestAgent = requestAgent;
    //this.cafeList = cafeList;
  }

  //관리자용
  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 기업회원 탈퇴");
    System.out.println();

    int deleteNo = (int) request.getAttribute("inputCeoNo");

    HashMap<String,String> params = new HashMap<>();
    params.put("inputCeoNo", String.valueOf(deleteNo));

    requestAgent.request("ceoMember.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 해당 번호의 기업 회원이 없습니다.");
      return;
    }

    String input = Prompt.inputString(" 정말 탈퇴시키겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 기업 회원 삭제를 취소하였습니다.");
      return;
    }

    //    for (int i = cafeList.size() - 1; i >= 0; i--) {
    //      if (cafeList.get(i).getCeoMember().getCeoNo() == ceoMember.getCeoNo()) {
    //        cafeList.remove(cafeList.get(i));
    //      }
    //    }

    requestAgent.request("ceoMember.delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("회원 삭제 실패!");
      System.out.println(requestAgent.getObject(String.class));
      return;
    }

    System.out.println(" >> 기업 회원이 삭제되었습니다.");
  }

}
