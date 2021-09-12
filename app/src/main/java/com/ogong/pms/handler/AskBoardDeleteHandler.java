package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.domain.Comment;
import com.ogong.util.Prompt;

public class AskBoardDeleteHandler extends AbstractAskBoardHandler {

  public AskBoardDeleteHandler(List<AskBoard> askBoardList, List<Comment> commentList) {
    super(askBoardList, commentList);
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 문의사항 삭제");
    System.out.println();

    //    Member member = AuthPerMemberLoginHandler.getLoginUser();
    //    Admin admin = AuthAdminLoginHandler.getLoginAdmin();
    //
    //    if (member == null || admin == null) {
    //      System.out.println(" >> 로그인 한 회원만 조회 가능합니다.");
    //      return;
    //    }

    int askNo = Prompt.inputInt(" 번호 : ");

    AskBoard askList = findByNo(askNo);

    if (askList == null) {
      System.out.println(" >> 해당 번호의 문의글이 없습니다.");
      return;
    }

    String input = Prompt.inputString(" 정말 삭제하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 문의글 삭제를 취소하였습니다.");
      return;
    }

    askBoardList.remove(askList);

    System.out.println(" >> 문의글을 삭제하였습니다.");
  }

}







