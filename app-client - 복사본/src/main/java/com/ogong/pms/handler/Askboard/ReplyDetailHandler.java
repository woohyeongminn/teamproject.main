package com.ogong.pms.handler.Askboard;

import com.ogong.pms.dao.AskBoardDao;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;

public class ReplyDetailHandler implements Command {

  AskBoardDao askBoardDao;

  public ReplyDetailHandler(AskBoardDao askBoardDao) {
    this.askBoardDao = askBoardDao;
  }

  public void execute(CommandRequest request) throws Exception {

    int askNo = (int) request.getAttribute("askNo");

    AskBoard askBoard = askBoardDao.findByNo(askNo);

    System.out.println();
    System.out.println(" ▶ 답변 ");
    System.out.println();

    System.out.printf(" [RE : %s]\n", askBoard.getReply().getReplyTitle());
    System.out.printf(" >> 내용 : %s\n", askBoard.getReply().getReplyContent());
    System.out.printf(" >> 작성일 : %s\n", askBoard.getReply().getReplyRegisteredDate());
  }
}
