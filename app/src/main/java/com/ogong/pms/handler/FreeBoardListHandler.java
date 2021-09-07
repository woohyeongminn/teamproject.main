package com.ogong.pms.handler;

import java.util.HashMap;
import java.util.List;
import com.ogong.pms.domain.Comment;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.util.Prompt;

public class FreeBoardListHandler extends AbstractFreeBoardHandler {

  public FreeBoardListHandler(List<FreeBoard> freeBoardList,
      List<Comment> commentList, HashMap<String, Command> commandMap) {
    super(freeBoardList, commentList, commandMap);
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 게시글 목록");
    System.out.println();

    if (freeBoardList == null ) {
      System.out.println("게시글이 없습니다");
      return;
    }

    for (FreeBoard freeBoard : freeBoardList) {
      System.out.printf(
          "(%d)\n 제목 : %s\n 내용 : %s\n 첨부파일 : %s\n 작성자 : %s\n 조회수 : %s\n 작성일 : %s\n",
          freeBoard.getFreeBoardNo(), 
          freeBoard.getFreeBoardTitle(),
          freeBoard.getFreeBoardContent(),
          freeBoard.getFreeBoardAtcFile(),
          freeBoard.getFreeBoardWriter().getPerNickname(),
          freeBoard.getFreeBoardViewcount(),
          freeBoard.getFreeBoardRegisteredDate());
      System.out.println();
    }
    selectPage();
  }

  @Override
  protected void selectPage() {
    System.out.println("---------------------");
    System.out.println("1. 게시글 상세");
    System.out.println("2. 게시글 등록");
    System.out.println("3. 게시글 수정");
    System.out.println("4. 게시글 삭제");
    System.out.println("0. 뒤로가기");

    int selectAdminNo = Prompt.inputInt("선택> ");

    switch (selectAdminNo) {
      case 1: commandMap.get("/freeBoard/detail").execute(); break;
      case 2: commandMap.get("/freeBoard/add").execute(); break;
      case 3: commandMap.get("/freeBoard/update").execute(); break;
      case 4: commandMap.get("/freeBoard/delete").execute(); break;
      default : return;
    }
  }
}

