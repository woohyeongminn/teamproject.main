package com.ogong.pms.handler.board;

import java.sql.Date;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.handler.AuthCeoMemberLoginHandler;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class AskBoardAddHandler implements Command {

  int askNo = 100;
  RequestAgent requestAgent;

  public AskBoardAddHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 문의사항");
    System.out.println();

    AskBoard askList = new AskBoard();

    int statusNo = 0;

    if (AuthPerMemberLoginHandler.getLoginUser() != null) {

      askList.setAskTitle(Prompt.inputString(" 제목 : "));
      askList.setAskContent(Prompt.inputString(" 내용 : "));
      askList.setAskMemberWriter(AuthPerMemberLoginHandler.getLoginUser());
      askList.setAskRegisteredDate(new Date(System.currentTimeMillis()));

      while (true) {

        try {
          statusNo = Prompt.inputInt("\n 1: 공개 / 2: 비공개 > ");
          System.out.println();
          if (statusNo >= 3) {
            System.out.println(" >> 번호를 다시 입력하세요.\n");
            continue;
          }
          else if ((statusNo > 0) && (statusNo < 3)) {
            String input = Prompt.inputString(" 정말 등록하시겠습니까? (네 / 아니오) ");
            if (!input.equalsIgnoreCase("네")) {
              System.out.println(" >> 문의글 등록을 취소하였습니다.");
              return;
            }    
            askList.setAskNo(askNo++);
            break;
          }
        } catch (NumberFormatException e) {
          System.out.println(" >> 번호만 입력 가능합니다.\n");
          continue;
        }
        break;
      } 

      askList.setAskStatus(statusNo);
    }

    else if (AuthCeoMemberLoginHandler.getLoginCeoMember() != null) {

      askList.setAskTitle(Prompt.inputString(" 제목 : "));
      askList.setAskContent(Prompt.inputString(" 내용 : "));
      askList.setAskCeoWriter(AuthCeoMemberLoginHandler.getLoginCeoMember());
      askList.setAskRegisteredDate(new Date(System.currentTimeMillis()));

      while (true) {

        try {
          statusNo = Prompt.inputInt("\n 1: 공개 / 2: 비공개 > ");
          System.out.println();
          if (statusNo >= 3) {
            System.out.println(" >> 번호를 다시 입력하세요.\n");
            continue;
          }
          else if ((statusNo > 0) && (statusNo < 3)) {
            String input = Prompt.inputString(" 정말 등록하시겠습니까? (네 / 아니오) ");
            if (!input.equalsIgnoreCase("네")) {
              System.out.println(" >> 문의글 등록을 취소하였습니다.");
              return;
            }     
            askList.setAskNo(askNo++);
            break;
          }
        } catch (NumberFormatException e) {
          System.out.println(" >> 번호만 입력 가능합니다.\n");
          continue;
        }
        break;
      } 

      askList.setAskStatus(statusNo);
    }

    if (statusNo == 0) {
      System.out.println(" >> 이전 화면으로 돌아갑니다.");
      return;
    } 

    else if ((statusNo > 0) && (statusNo < 3)) {
      requestAgent.request("askBoard.insert", askList);
      if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
        System.out.println(" >> 문의글이 등록되었습니다.");
      } else {
        System.out.println(" >> 문의글 등록이 실패되었습니다.");
      }

    }

  }

}
