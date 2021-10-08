package com.ogong.pms.handler.board;

import java.util.Collection;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.handler.AuthCeoMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class AskBoardCeoMyListHandler implements Command {

  RequestAgent requestAgent;

  public AskBoardCeoMyListHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  // 마이페이지 - 내가 쓴 문의내역(기업)
  @Override
  public void execute(CommandRequest request) throws Exception {

    requestAgent.request("askBoard.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 문의글 목록 조회 실패");
      return;
    }
    Collection<AskBoard> askBoardList = requestAgent.getObjects(AskBoard.class);

    if (AuthCeoMemberLoginHandler.getLoginCeoMember() != null) {

      int count = 0;
      for (AskBoard askBoard : askBoardList) {
        int ceoMemberNo = AuthCeoMemberLoginHandler.getLoginCeoMember().getCeoNo();
        if(askBoard.getAskCeoWriter().getCeoNo() == ceoMemberNo) {
          System.out.println();
          System.out.printf(" (%d)\n 제목 : %s\n 작성자 : %s\n 작성일 : %s\n 조회수 : %d\n", 
              askBoard.getAskNo(), 
              askBoard.getAskTitle(), 
              askBoard.getAskCeoWriter().getCeoBossName(),
              askBoard.getAskRegisteredDate(),
              askBoard.getAskVeiwCount());

          if (askBoard.getReply() != null) {
            request.setAttribute("askNo", askBoard.getAskNo());
            request.getRequestDispatcher("/reply/detail").forward(request);  // 답변 호출
          } else {
            System.out.println("---------------------");
            System.out.println();
            System.out.println(" >> 등록된 답변이 없습니다.");
          }
          count++;
        } 
      }

      if (count == 0) {
        System.out.println("\n >> 내가 등록한 문의글이 없습니다.");
        return;
      }
    }

    else {
      System.out.println();
      System.out.println(" >> 로그인 한 회원만 볼 수 있습니다.");
      return;
    }

    System.out.println("1. 상세");
    System.out.println("2. 이전");
    int selectNo = Prompt.inputInt("선택> ");

    switch (selectNo) {
      case 1: request.getRequestDispatcher("/askBoard/CeoMydetail").forward(request); return;
      case 2: return;
      default : System.out.println(" >> 번호를 다시 선택해 주세요.");
    }
  }

}
