package com.ogong.pms.handler.board;

import java.util.HashMap;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.handler.AuthAdminLoginHandler;
import com.ogong.pms.handler.AuthCeoMemberLoginHandler;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class AskBoardDetailHandler implements Command {

  RequestAgent requestAgent; 

  public AskBoardDetailHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 문의사항 상세");
    System.out.println();

    int askNo = Prompt.inputInt(" 번호 : ");

    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(askNo));

    requestAgent.request("askBoard.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 해당 번호의 문의글이 없습니다. ");
      return;
    }

    AskBoard askBoard = requestAgent.getObject(AskBoard.class);

    if (askBoard.getAskStatus() == 1) {
      detailList(askBoard, request);
    }

    else if (askBoard.getAskStatus() == 2) {

      if (AuthPerMemberLoginHandler.getLoginUser() == null &&
          AuthCeoMemberLoginHandler.getLoginCeoMember() == null &&
          AuthAdminLoginHandler.getLoginAdmin() == null) {
        System.out.println(" >> 열람 권한이 없습니다.");
        return;
      }

      if (AuthPerMemberLoginHandler.getLoginUser() != null) {

        if (AuthPerMemberLoginHandler.getLoginUser().getPerNo() !=
            askBoard.getAskMemberWriter().getPerNo()) {
          System.out.println(" >> 열람 권한이 없습니다.");
          return;
        }

        System.out.println();
        String secretPassword = Prompt.inputString(" 비밀번호 : ");

        if (!AuthPerMemberLoginHandler.loginUser.getPerPassword().equals(secretPassword)) {
          System.out.println();
          System.out.println(" >> 비밀번호를 다시 입력하세요.");
          return;
        } 
      }

      if (AuthCeoMemberLoginHandler.getLoginCeoMember() != null) {

        if (AuthCeoMemberLoginHandler.getLoginCeoMember().getCeoNo() !=
            askBoard.getAskCeoWriter().getCeoNo()) {
          System.out.println(" >> 열람 권한이 없습니다.");
          return;
        }

        System.out.println();
        String secretPassword = Prompt.inputString(" 비밀번호 : ");

        if (!AuthCeoMemberLoginHandler.loginCeoMember.getCeoPassword().equals(secretPassword)) {
          System.out.println();
          System.out.println(" >> 비밀번호를 다시 입력하세요.");
          return;
        }
      }
      detailList(askBoard, request);
    }

    request.setAttribute("askNo", askNo);

    if (AuthAdminLoginHandler.getLoginAdmin() != null) {
      System.out.println("\n---------------------");
      System.out.println("1. 문의글 삭제");
      System.out.println("2. 답변 등록");
      //      System.out.println("3. 답변 수정");
      //      System.out.println("4. 답변 삭제");
      System.out.println("0. 뒤로 가기");
      int selectNo = Prompt.inputInt("선택> ");
      switch (selectNo) {
        case 1 : request.getRequestDispatcher("/askBoard/delete").forward(request); return;
        case 2 : request.getRequestDispatcher("/reply/add").forward(request); return;
        //case 2 : replyAddHandler.execute(request); return;
        //case 3 : updateComment(); break;
        //case 4 : deleteComment(askBoard); break;
        default : return;
      }
    }

    else if (AuthPerMemberLoginHandler.getLoginUser() != null ||
        AuthCeoMemberLoginHandler.getLoginCeoMember() != null) {
      System.out.println("\n---------------------");
      System.out.println("1. 수정");
      System.out.println("2. 삭제");
      System.out.println("0. 이전");
      int selectNo = Prompt.inputInt("선택> ");
      switch (selectNo) {
        case 1 : request.getRequestDispatcher("/askBoard/update").forward(request); return;
        case 2 : request.getRequestDispatcher("/askBoard/delete").forward(request); return;
        default : return;
      }
    }
  }

  //  private void reply(AskBoard askBoard) {
  //
  //    if (askBoard.getReply() == null) {
  //      System.out.println();
  //      System.out.println(" >> 등록된 답변이 없습니다.");
  //    }
  //    else if (askBoard.getReply() != null) {
  //      replyDetailHandler.detailReply(askBoard);  // 답변 호출
  //    }
  //    return;
  //  }

  private void detailList(AskBoard askBoard, CommandRequest request) throws Exception {

    System.out.println();
    System.out.printf(" (%d)\n", askBoard.getAskNo());
    System.out.printf(" [%s]\n", askBoard.getAskTitle());
    System.out.printf(" >> 내용 : %s\n", askBoard.getAskContent());

    String writer = "";
    if (askBoard.getAskMemberWriter().getPerNickname() != null) {
      writer = askBoard.getAskMemberWriter().getPerNickname();
    } else if (askBoard.getAskCeoWriter().getCeoBossName() != null) {
      writer = askBoard.getAskCeoWriter().getCeoBossName();
    }
    System.out.printf(" >> 작성자 : %s\n", writer);
    System.out.printf(" >> 작성일 : %s\n", askBoard.getAskRegisteredDate());
    askBoard.setAskVeiwCount(askBoard.getAskVeiwCount() + 1);
    System.out.printf(" >> 조회수 : %d\n", askBoard.getAskVeiwCount());
    System.out.println("---------------------");

    if (askBoard.getReply() == null) {
      System.out.println("등록된 답변이 없습니다.");
      return;
    }
    request.setAttribute("askNo", askBoard.getAskNo());
    request.getRequestDispatcher("/reply/detail").forward(request); 
  }
}
