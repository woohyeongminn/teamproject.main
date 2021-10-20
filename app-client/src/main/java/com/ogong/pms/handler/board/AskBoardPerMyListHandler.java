package com.ogong.pms.handler.board;

import java.util.List;
import com.ogong.pms.dao.AskBoardDao;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class AskBoardPerMyListHandler implements Command {

  AskBoardDao askBoardDao;

  public AskBoardPerMyListHandler(AskBoardDao askBoardDao) {
    this.askBoardDao = askBoardDao;
  }

  // 마이페이지 - 내가 쓴 문의내역(개인)
  @Override
  public void execute(CommandRequest request) throws Exception {

    List<AskBoard> askBoardList = askBoardDao.findAll();

    if (AuthPerMemberLoginHandler.getLoginUser() != null) {

      // 개인이 쓴 문의글
      int count = 0;
      for (AskBoard askBoard : askBoardList) {
        int memberNo = AuthPerMemberLoginHandler.getLoginUser().getPerNo();
        if(askBoard.getAskMemberWriter().getPerNo() == memberNo) {

          System.out.println();
          String reply = "";
          if (askBoard.getReply() != null) {
            reply = "📖 > 등록된 답변이 있습니다.";
          } else {
            reply = "📕 > 등록된 답변이 없습니다.";
          }

          System.out.printf(" (%d)\n 제목 : %s\n 작성일 : %s\n 조회수 : %d\n %s\n", 
              askBoard.getAskNo(), 
              askBoard.getAskTitle(), 
              askBoard.getAskRegisteredDate(),
              askBoard.getAskVeiwCount(),
              reply);
          count++;
        } 

      }
      if (count == 0) {
        System.out.println("\n >> 내가 등록한 문의글이 없습니다.");
        return;
      }

    } else {
      System.out.println();
      System.out.println(" >> 로그인 한 회원만 볼 수 있습니다.");
      return;
    }
    System.out.println();
    System.out.println("---------------------");
    System.out.println("1. 상세");
    System.out.println("0. 이전");
    int selectNo = Prompt.inputInt("선택> ");

    switch (selectNo) {
      case 1: request.getRequestDispatcher("/askBoard/PerMydetail").forward(request); return;
      case 2: return;
      default : System.out.println(" >> 번호를 다시 선택해 주세요.");
    }
  }

}
