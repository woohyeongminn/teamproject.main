package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.util.Prompt;

public class FreeBoardUpdateHandler extends AbstractFreeBoardHandler {

  public FreeBoardUpdateHandler(List<FreeBoard> freeBoardList, AuthPerMemberLoginHandler loginHandler) {
    super(freeBoardList, loginHandler);
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 게시글 수정");

    String inputTitle = Prompt.inputString("제목 : ");
    System.out.println();

    FreeBoard free = findByTitle(inputTitle);

    if (free == null) {
      System.out.println("해당 제목의 게시글이 없습니다.");
      return;
    }

    if (free.getFreeBoardWriter().getPerNo() != AuthPerMemberLoginHandler.getLoginUser().getPerNo()) {
      System.out.println("변경 권한이 없습니다.");
      return;
    }

    String freeBoardTitle = Prompt.inputString("제목(" + free.getFreeBoardTitle()  + ") : ");
    String freeBoardContent = Prompt.inputString("내용(" + free.getFreeBoardContent() + ") : ");
    String freeBoardAtcFile = Prompt.inputString("첨부파일(" + free.getFreeBoardAtcFile() + ") : ");

    String input = Prompt.inputString("정말 변경하시겠습니까? (네 / 아니오) ");
    if (input.equalsIgnoreCase("아니오") || input.length() == 0) {
      System.out.println("게시글 변경이 취소되었습니다.");
      return;
    }

    free.setFreeBoardTitle(freeBoardTitle);
    free.setFreeBoardContent(freeBoardContent);
    free.setFreeBoardAtcFile(freeBoardAtcFile);

    System.out.println("게시글을 변경하였습니다.");
  }
}

