package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.util.Prompt;

public class FreeBoardDetailHandler extends AbstractFreeBoardHandler {

  public FreeBoardDetailHandler(List<FreeBoard> freeBoardList, PerLoginHandler loginHandler) {
    super(freeBoardList, loginHandler);
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 게시글 상세보기");

    String inputTitle = Prompt.inputString("제목 : ");
    System.out.println();

    FreeBoard free = findByTitle(inputTitle);

    if (free == null) {
      System.out.println("해당 제목의 게시글이 없습니다.");
      return;
    }

    System.out.printf("제목 : %s\n", free.getFreeBoardTitle());
    System.out.printf("내용 : %s\n", free.getFreeBoardContent());
    System.out.printf("첨부파일 : %s\n", free.getFreeBoardAtcFile());
    System.out.printf("작성자 : %s\n", free.getFreeBoardWriter().getPerNickname());
    System.out.printf("등록일 : %s\n", free.getFreeBoardRegisteredDate());
    free.setFreeBoardViewcount(free.getFreeBoardViewcount() + 1);
    System.out.printf("조회수 : %d\n", free.getFreeBoardViewcount());
  }
}

