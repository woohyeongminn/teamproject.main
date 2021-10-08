package com.ogong.pms.handler.board;

import java.sql.Date;
import java.util.HashMap;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.domain.Reply;
import com.ogong.pms.handler.AuthAdminLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class ReplyAddHandler implements Command {

  int replyNo = 1;
  RequestAgent requestAgent;

  public ReplyAddHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println(" ▶ 답변 등록");
    System.out.println();

    int askNo = (int) request.getAttribute("askNo");

    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(askNo));

    requestAgent.request("askBoard.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 해당 번호의 문의글이 없습니다.");
      return;
    }

    AskBoard askBoard = requestAgent.getObject(AskBoard.class);

    if (askBoard.getReply() != null) {
      System.out.println(" >> 이미 등록된 답변이 있습니다.");
      return;
    }

    Reply reply = new Reply();
    reply.setReplyTitle(Prompt.inputString(" 제목: "));
    reply.setReplyContent(Prompt.inputString(" 내용: "));
    reply.setReplyAdminWiter(AuthAdminLoginHandler.getLoginAdmin());
    reply.setReplyRegisteredDate(new Date(System.currentTimeMillis()));

    String input = Prompt.inputString("\n 정말 등록하시겠습니까? (네 / 아니오) ");
    if (!input.equals("네")) {
      System.out.println(" >> 등록을 취소하였습니다.");
      return;
    }

    reply.setReplyNo(replyNo++);
    askBoard.setReply(reply);

    requestAgent.request("askBoard.update", askBoard);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 답글 등록 실패");
      System.out.println(requestAgent.getObject(String.class));
      return;
    }
    System.out.println(" >> 답글이 등록되었습니다.");
  }
}
