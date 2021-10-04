package com.ogong.pms.handler.board;

import java.util.Collection;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.handler.AuthCeoMemberLoginHandler;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;

public class AskBoardMyListHandler implements Command {

  ReplyDetailHandler replyDetailHandler;
  //
  //  public AskBoardMyListHandler(List<AskBoard> askBoardList, List<Member> memberList,
  //      List<CeoMember> ceoMemberList, List<Reply> replyList, ReplyDetailHandler replyDetailHandler) {
  //    super(askBoardList, replyList, memberList, ceoMemberList);
  //    this.replyDetailHandler = replyDetailHandler;
  //  }

  RequestAgent requestAgent;

  public AskBoardMyListHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  // 마이페이지 - 내가 쓴 문의내역
  @Override
  public void execute(CommandRequest request) throws Exception {

    requestAgent.request("askBoard.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 문의글 목록 조회 실패");
      return;
    }
    Collection<AskBoard> askBoardList = requestAgent.getObjects(AskBoard.class);

    if (AuthPerMemberLoginHandler.getLoginUser() != null) {

      // 개인이 쓴 문의글
      int count = 0;
      AskBoard myAskBoard = new AskBoard();
      for (AskBoard askBoard : askBoardList) {
        String member = AuthPerMemberLoginHandler.getLoginUser().getPerNickname();
        if(member.equals(askBoard.getAskMemberWriter().getPerNickname())) {
          System.out.println();
          System.out.printf(" (%d)\n 제목 : %s\n 작성자 : %s\n 작성일 : %s\n 조회수 : %d\n", 
              askBoard.getAskNo(), 
              askBoard.getAskTitle(), 
              askBoard.getAskMemberWriter().getPerNickname(),
              askBoard.getAskRegisteredDate(),
              askBoard.getAskVeiwCount());
          myAskBoard = askBoard;
          count++;
        } 

      }
      System.out.println("---------------------");

      if (count == 0) {
        System.out.println("\n >> 내가 등록한 문의글이 없습니다.");
        return;
      }

      if (myAskBoard.getReply() == null) {
        System.out.println();
        System.out.println(" >> 등록된 답변이 없습니다.");
      }
      else if (myAskBoard.getReply() != null) {

        request.setAttribute("askNo", myAskBoard.getAskNo());
        request.getRequestDispatcher("/reply/detail").forward(request);  // 답변 호출
      }

    } 

    else if (AuthCeoMemberLoginHandler.getLoginCeoMember() != null) {

      // 기업이 쓴 문의글
      int count = 0;
      AskBoard ceoAskBoard = new AskBoard();
      for (AskBoard askBoard : askBoardList) {
        String ceoMember = AuthCeoMemberLoginHandler.getLoginCeoMember().getCeoBossName();
        if(ceoMember.equals(askBoard.getAskCeoWriter().getCeoBossName())) {
          System.out.println();
          System.out.printf(" (%d)\n 제목 : %s\n 작성자 : %s\n 작성일 : %s\n 조회수 : %d\n", 
              askBoard.getAskNo(), 
              askBoard.getAskTitle(), 
              askBoard.getAskCeoWriter().getCeoBossName(),
              askBoard.getAskRegisteredDate(),
              askBoard.getAskVeiwCount());
          ceoAskBoard = askBoard;
          count++;
        } 
      }

      System.out.println("---------------------");

      if (count == 0) {
        System.out.println("\n >> 내가 등록한 문의글이 없습니다.");
        return;
      }

      if (ceoAskBoard.getReply() == null) {
        System.out.println();
        System.out.println(" >> 등록된 답변이 없습니다.");
      }
      else if (ceoAskBoard.getReply() != null) {
        request.setAttribute("askNo", ceoAskBoard.getAskNo());
        request.getRequestDispatcher("/reply/detail").forward(request); 
      }

    }

    else {
      System.out.println();
      System.out.println(" >> 로그인 하세요.");
      return;
    }
  }

}
