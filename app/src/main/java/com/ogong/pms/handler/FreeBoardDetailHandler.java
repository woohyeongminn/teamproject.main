package com.ogong.pms.handler;

import java.util.HashMap;
import java.util.List;
import com.ogong.pms.domain.Comment;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.util.Prompt;

public class FreeBoardDetailHandler extends AbstractFreeBoardHandler {

  public FreeBoardDetailHandler(List<FreeBoard> freeBoardList,
      List<Comment> commentList, HashMap<String, Command> commandMap) {
    super(freeBoardList, commentList, commandMap);
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 게시글 상세보기");

    System.out.println();

    FreeBoard freeBoard = findByTitle(Prompt.inputString("제목 : "));
    System.out.println();
    if (freeBoard == null) {
      System.out.println("해당 제목의 게시글이 없습니다.");
      return;
    }

    System.out.printf(">> 제목 : %s\n", freeBoard.getFreeBoardTitle());
    System.out.printf(">> 내용 : %s\n", freeBoard.getFreeBoardContent());
    System.out.printf(">> 첨부파일 : %s\n", freeBoard.getFreeBoardAtcFile());
    System.out.printf(">> 작성자 : %s\n", freeBoard.getFreeBoardWriter().getPerNickname());
    System.out.printf(">> 등록일 : %s\n", freeBoard.getFreeBoardRegisteredDate());
    freeBoard.setFreeBoardViewcount(freeBoard.getFreeBoardViewcount() + 1);
    System.out.printf(">> 조회수 : %d\n", freeBoard.getFreeBoardViewcount());
    listComment(freeBoard); // 댓글호출

    System.out.println("\n----------------------");
    System.out.println("1. 댓글 달기");
    System.out.println("0. 뒤로가기");
    int selectNo = Prompt.inputInt("선택> ");
    switch (selectNo) {
      case 1 : addComment(freeBoard); break;
      default : selectPage();
    }
  }

}