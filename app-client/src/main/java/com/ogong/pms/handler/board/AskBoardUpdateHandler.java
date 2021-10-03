package com.ogong.pms.handler.board;

import java.util.HashMap;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.handler.AuthCeoMemberLoginHandler;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class AskBoardUpdateHandler implements Command {

  //  public AskBoardUpdateHandler(List<AskBoard> askBoardList, List<Member> memberList,
  //      List<CeoMember> ceoMemberList, List<Reply> replyList) {
  //    super(askBoardList, replyList, memberList, ceoMemberList);
  //  }

  RequestAgent requestAgent;

  public AskBoardUpdateHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }


  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 문의사항 변경");
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

    if (AuthPerMemberLoginHandler.getLoginUser() != null) {

      if (AuthPerMemberLoginHandler.getLoginUser().getPerNo() !=
          askBoard.getAskMemberWriter().getPerNo()) {
        System.out.println(" >> 수정 권한이 없습니다.");
        return;
      }

      else if (askBoard.getReply() != null) {
        System.out.println(" >> 이미 답변된 글에는 수정 권한이 없습니다.");
        return;
      }

      String askTitle = Prompt.inputString(String.format(" 제목(%s) : ", askBoard.getAskTitle()));
      String askContent = Prompt.inputString(String.format(" 내용(%s) : ", askBoard.getAskContent()));

      String input = Prompt.inputString(" 정말 변경하시겠습니까? (네 / 아니오) ");
      if (!input.equalsIgnoreCase("네")) {
        System.out.println(" >> 문의글 변경을 취소하였습니다.");
        return;
      }


      askBoard.setAskTitle(askTitle);
      askBoard.setAskContent(askContent);
      System.out.println(" >> 문의글을 변경하였습니다.");
    }

    else if (AuthCeoMemberLoginHandler.getLoginCeoMember() != null) {

      if (AuthCeoMemberLoginHandler.getLoginCeoMember().getCeoNo() !=
          askBoard.getAskCeoWriter().getCeoNo()) {
        System.out.println(" >> 수정 권한이 없습니다.");
        return;
      }

      else if (askBoard.getReply() != null) {
        System.out.println(" >> 이미 답변된 글에는 수정 권한이 없습니다.");
        return;
      }

      String askTitle = Prompt.inputString(String.format(" 제목(%s) : ", askBoard.getAskTitle()));
      String askContent = Prompt.inputString(String.format(" 내용(%s) : ", askBoard.getAskContent()));

      String input = Prompt.inputString(" 정말 변경하시겠습니까? (네 / 아니오) ");
      if (!input.equalsIgnoreCase("네")) {
        System.out.println(" >> 문의글 변경을 취소하였습니다.");
        return;
      }

      askBoard.setAskTitle(askTitle);
      askBoard.setAskContent(askContent);

      requestAgent.request("askBoard.update", params);

      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println(" >> 문의글 변경 실패!");
        System.out.println(requestAgent.getObject(String.class));
        return;
      }
      System.out.println(" >> 문의글을 변경하였습니다.");
    }


  }

}







