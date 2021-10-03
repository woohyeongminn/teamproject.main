package com.ogong.pms.handler.member;

import java.util.HashMap;
import com.ogong.menu.Menu;
import com.ogong.pms.domain.CeoMember;
import com.ogong.pms.handler.AuthCeoMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class CeoDeleteHandler implements Command {

  RequestAgent requestAgent;

  //List<Cafe> cafeList;

  public CeoDeleteHandler(
      /*List<CeoMember> ceoMemberList ,*/ RequestAgent requestAgent) {
    //this.cafeList = cafeList;
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 기업회원 탈퇴");

    if (AuthCeoMemberLoginHandler.getLoginCeoMember() == null) {
      System.out.println(" >> 로그인 하세요.");
      return;
    }

    int no = (int) request.getAttribute("ceoMemberNo");

    HashMap<String,String> params = new HashMap<>();
    params.put("ceoMemberNo", String.valueOf(no));

    requestAgent.request("ceoMember.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 해당 회원이 없습니다.");
      return;
    }

    CeoMember ceoMember = requestAgent.getObject(CeoMember.class);

    System.out.println(" << 이메일 확인 >>");
    String inputEmail = Prompt.inputString(" 이메일을 입력하세요 : ");

    if (!ceoMember.getCeoEmail().equals(inputEmail)) {
      System.out.println();
      System.out.println(" >> 이메일이 일치하지 않습니다.");
      return;
    } 

    System.out.println();
    System.out.println(" << 비밀번호 확인 >>");
    String inputPW = Prompt.inputString(" 비밀번호를 입력하세요 : ");
    if (!ceoMember.getCeoPassword().equals(inputPW)) {
      System.out.println();
      System.out.println(" >> 비밀번호가 일치하지 않습니다.");
      return;
    }

    System.out.println();
    String input = Prompt.inputString(" 정말 탈퇴하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 회원 탈퇴를 취소하였습니다.");
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

    AuthCeoMemberLoginHandler.loginCeoMember = null;
    AuthCeoMemberLoginHandler.accessLevel = Menu.LOGOUT;
    System.out.println();
    System.out.println(" >> 회원 탈퇴를 완료하였습니다.");
    return;
  }
}
