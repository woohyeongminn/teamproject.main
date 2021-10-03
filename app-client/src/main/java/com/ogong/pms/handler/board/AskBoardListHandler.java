package com.ogong.pms.handler.board;

import java.util.Collection;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;

public class AskBoardListHandler implements Command {

  //  public AskBoardListHandler(List<AskBoard> askBoardList, List<Member> memberList,
  //      List<CeoMember> ceoMemberList, List<Reply> replyList) {
  //    super(askBoardList, replyList, memberList, ceoMemberList);
  //  }

  RequestAgent requestAgent;

  public AskBoardListHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 문의사항 목록");
    System.out.println();

    //HashMap<String,String> params = new HashMap<>();
    //params.put("askBoard.selectList", null);

    requestAgent.request("askBoard.selectList", null);;

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 게시글 목록 조회 실패");
    }

    Collection<AskBoard> aksBoards = requestAgent.getObjects(AskBoard.class);

    if (aksBoards == null) {
      System.out.println(" >> 등록된 글이 없습니다.");
      return;
    }

    for (AskBoard askList : aksBoards) {

      // 기업
      if (askList.getAskMemberWriter().getPerNickname() == null) {

        if (askList.getAskStatus() == 1) {
          System.out.printf("\n (%d)\n 제목 : %s\n 작성자 : %s\n 작성일 : %s\n 조회수 : %d\n", 
              askList.getAskNo(), 
              askList.getAskTitle(), 
              askList.getAskCeoWriter().getCeoBossName(),
              askList.getAskRegisteredDate(),
              askList.getAskVeiwCount());
        }

        else if (askList.getAskStatus() == 2) {
          System.out.printf("\n (%d)\n 작성자 : %s\n", 
              askList.getAskNo(), 
              askList.getAskCeoWriter().getCeoBossName());
          System.out.println(" 비밀글입니다.");
        }
      }

      // 개인
      else if (askList.getAskCeoWriter().getCeoBossName() == null) {

        if (askList.getAskStatus() == 1) {
          System.out.printf("\n (%d)\n 제목 : %s\n 작성자 : %s\n 작성일 : %s\n 조회수 : %d\n", 
              askList.getAskNo(), 
              askList.getAskTitle(), 
              askList.getAskMemberWriter().getPerNickname(),
              askList.getAskRegisteredDate(),
              askList.getAskVeiwCount());
        }

        else if (askList.getAskStatus() == 2) {
          System.out.printf("\n (%d)\n 작성자 : %s\n", 
              askList.getAskNo(), 
              askList.getAskMemberWriter().getPerNickname());
          System.out.println();
          System.out.println(" 비밀글입니다.");
        }
      }
    }
  }
}
