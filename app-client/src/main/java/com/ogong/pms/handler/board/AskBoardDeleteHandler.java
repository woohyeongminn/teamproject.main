package com.ogong.pms.handler.board;

import java.util.HashMap;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.handler.AuthCeoMemberLoginHandler;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class AskBoardDeleteHandler implements Command {

  RequestAgent requestAgent;

  public AskBoardDeleteHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 문의사항 삭제");
    System.out.println();

    int askNo = (int) request.getAttribute("askNo"); 

    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(askNo));

    requestAgent.request("askBoard.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 해당 번호의 문의글이 없습니다.");
      return;
    }

    AskBoard askList = requestAgent.getObject(AskBoard.class);

    if (AuthPerMemberLoginHandler.getLoginUser() != null) {

      if (AuthPerMemberLoginHandler.getLoginUser().getPerNo() !=
          askList.getAskMemberWriter().getPerNo()) {
        System.out.println(" >> 삭제 권한이 없습니다.");
        return;
      }
    }

    else if (AuthCeoMemberLoginHandler.getLoginCeoMember() != null) {

      if (AuthCeoMemberLoginHandler.getLoginCeoMember().getCeoNo() !=
          askList.getAskCeoWriter().getCeoNo()) {
        System.out.println(" >> 삭제 권한이 없습니다.");
        return;
      }

    }

    String input = Prompt.inputString(" 정말 삭제하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 문의글 삭제를 취소하였습니다.");
      return;
    }

    requestAgent.request("askBoard.delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 문의글 삭제 실패.");
      System.out.println(requestAgent.getObject(String.class));
      return;
    }

    System.out.println(" >> 문의글을 삭제하였습니다.");
  }

}







