package com.ogong.pms.handler2;

import java.util.List;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.util.Prompt;

public class FreeBoardDeleteHandler extends AbstractFreeBoardHandler {

  public FreeBoardDeleteHandler(List<FreeBoard> freeBoardList, PerLoginHandler loginHandler) {
    super(freeBoardList, loginHandler);
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 게시글 삭제");

    String inputTitle = Prompt.inputString("제목 : ");
    System.out.println();

    FreeBoard free = findByTitle(inputTitle);

    if (free == null) {
      System.out.println("해당 제목의 게시글이 없습니다.");
      return;
    }

    if (free.getFreeBoardWriter().getPerNo() != PerLoginHandler.getLoginUser().getPerNo()) {
      System.out.println("삭제 권한이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까? (네 / 아니오) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("게시글 삭제를 취소하였습니다.");
      return;
    }

    freeBoardList.remove(free);

    System.out.println("게시글이 삭제되었습니다.");
  }
}

