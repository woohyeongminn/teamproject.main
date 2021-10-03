package com.ogong.pms.handler.board;

import java.sql.Date;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.domain.Reply;
import com.ogong.pms.handler.AuthAdminLoginHandler;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class ReplyAddHandler {

  int replyNo = 1;
  RequestAgent requestAgent;

  public ReplyAddHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  public void addReply(AskBoard askBoard) throws Exception {
    System.out.println();
    System.out.println(" ▶ 답변 등록");
    System.out.println();

    Reply reply = new Reply();

    reply.setReplyTitle(Prompt.inputString(" 제목: "));
    reply.setReplyContent(Prompt.inputString(" 내용: "));
    reply.setReplyAdminWiter(AuthAdminLoginHandler.getLoginAdmin());
    reply.setReplyRegisteredDate(new Date(System.currentTimeMillis()));

    String input = Prompt.inputString(" 정말 등록하시겠습니까? (네 / 아니오) ");
    if (!input.equals("네")) {
      System.out.println(" >> 등록을 취소하였습니다.");
      return;
    }

    reply.setReplyNo(replyNo++);
    requestAgent.request("askBoard.reply.insert", reply);

    if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      System.out.println(" >> 답글을 저장했습니다.");
    } else {
      System.out.println(" >> 답글 저장 실패");
    }
  }
}
