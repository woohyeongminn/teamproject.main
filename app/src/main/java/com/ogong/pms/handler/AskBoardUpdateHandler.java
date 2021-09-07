package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.domain.Comment;
import com.ogong.util.Prompt;

public class AskBoardUpdateHandler extends AbstractAskBoardHandler {

  public AskBoardUpdateHandler(List<AskBoard> askBoardList, List<Comment> commentList) {
    super(askBoardList, commentList);
  }

  public void execute() {
    System.out.println();
    System.out.println("▶ 문의사항 변경");
    int askNo = Prompt.inputInt("번호? ");

    AskBoard askList = findByNo(askNo);

    if (askList == null) {
      System.out.println("해당 번호의 문의글이 없습니다.");
      return;
    }

    String askTitle = Prompt.inputString(String.format(" 제목(%s) : ", askList.getAskTitle()));
    String askContent = Prompt.inputString(String.format(" 내용(%s) : ", askList.getAskContent()));

    String input = Prompt.inputString("정말 변경하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println("문의글 변경을 취소하였습니다.");
      return;
    }

    askList.setAskTitle(askTitle);
    askList.setAskContent(askContent);
    System.out.println("문의글을 변경하였습니다.");
  }
}







