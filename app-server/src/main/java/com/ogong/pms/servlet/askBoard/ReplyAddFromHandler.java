package com.ogong.pms.servlet.askBoard;

import java.sql.Date;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.AskBoardDao;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.domain.Reply;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class ReplyAddFromHandler implements Command {

  AskBoardDao askBoardDao;
  SqlSession sqlSession;

  public ReplyAddFromHandler(AskBoardDao askBoardDao, SqlSession sqlSession) {
    this.askBoardDao = askBoardDao;
    this.sqlSession = sqlSession;
  }

  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println(" ▶ 답변 등록");
    System.out.println();

    int askNo = (int) request.getAttribute("askNo");

    AskBoard askBoard = askBoardDao.findByNo(askNo);

    if (askBoard.getReply() != null) {
      System.out.println(" >> 이미 등록된 답변이 있습니다.");
      return;
    }

    Reply reply = new Reply();
    reply.setReplyTitle(askBoard.getAskTitle());
    reply.setReplyContent(Prompt.inputString(" 내용: "));
    reply.setReplyRegisteredDate(new Date(System.currentTimeMillis()));

    String input = Prompt.inputString("\n 정말 등록하시겠습니까? (네 / 아니오) ");
    if (!input.equals("네")) {
      System.out.println(" >> 등록을 취소하였습니다.");
      return;
    }

    askBoard.setReply(reply);

    askBoardDao.insertreply(askBoard);
    sqlSession.commit();
    System.out.println(" >> 답글이 등록되었습니다.");
  }
}
