package com.ogong.pms.handler.board;

import java.util.HashMap;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;

public class ReplyDetailHandler implements Command {

  RequestAgent requestAgent;

  public ReplyDetailHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  public void execute(CommandRequest request) throws Exception {

    int askNo = (int) request.getAttribute("askNo");

    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(askNo));

    requestAgent.request("askBoard.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 해당 번호의 문의글이 없습니다.");
      return;
    }

    AskBoard askBoard = requestAgent.getObject(AskBoard.class);

    System.out.println();
    System.out.println(" ▶ 답변 ");
    System.out.println();

    System.out.printf(" (%d)\n", askBoard.getReply().getReplyNo());
    System.out.printf(" [%s]\n", askBoard.getReply().getReplyTitle());
    System.out.printf(" >> 내용 : %s\n", askBoard.getReply().getReplyContent());
    System.out.printf(" >> 작성자 : %s\n", 
        askBoard.getReply().getReplyAdminWiter().getMasterNickname());
    System.out.printf(" >> 작성일 : %s\n", askBoard.getReply().getReplyRegisteredDate());
  }
}
