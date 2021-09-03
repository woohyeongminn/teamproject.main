package com.ogong.pms.handler2;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class FreeBoardHandler {

  //------------------------------------------------------------------------------------------------
  List<Member> memberList;
  List<FreeBoard> freeBoardList;
  //0831 eun 로그인핸들러 추가함
  LoginHandler loginHandler;

  public FreeBoardHandler(List<Member> memberList, List<FreeBoard> freeBoardList, LoginHandler loginHandler) {
    this.freeBoardList = freeBoardList;
    this.memberList = memberList;
    this.loginHandler = loginHandler;
  }
  //------------------------------------------------------------------------------------------------


  //------------------------------------------------------------------------------------------------
  public void add() {
    System.out.println();
    System.out.println("▶ 게시글 작성");

    FreeBoard freeBoard = new FreeBoard();

    // 0831 eun 추가함
    int addCount = freeBoard.getFreeBoardNo();

    freeBoard.setFreeBoardTitle(Prompt.inputString("제목 : "));
    freeBoard.setFreeBoardWriter(loginHandler.getLoginUser());
    freeBoard.setFreeBoardContent(Prompt.inputString("내용 : "));
    freeBoard.setFreeBoardAtcFile(Prompt.inputString("첨부파일 : "));
    freeBoard.setFreeBoardRegisteredDate(new Date(System.currentTimeMillis()));

    addCount++;

    freeBoardList.add(freeBoard);
  }

  //freeBoard.setFreeBoardWriter(join.getJoinNickname());
  //------------------------------------------------------------------------------------------------




  //------------------------------------------------------------------------------------------------
  public void list() {
    System.out.println();
    System.out.println("▶ 게시글 목록");

    FreeBoard[] list = freeBoardList.toArray(new FreeBoard[0]);

    for (FreeBoard freeBoard : list) {
      System.out.printf(
          "(%d)\n %s\n %s\n %s\n %s\n %s\n",
          freeBoard.getFreeBoardNo(), 
          freeBoard.getFreeBoardTitle(),
          freeBoard.getFreeBoardContent(),
          // 0831 eun 추가함 (getPerNickname() 실행하면 오류뜸)
          freeBoard.getFreeBoardWriter().getPerNickname(),
          freeBoard.getFreeBoardViewcount(),
          freeBoard.getFreeBoardRegisteredDate()
          );
    }
  }
  //------------------------------------------------------------------------------------------------




  //------------------------------------------------------------------------------------------------
  public void detail() {
    System.out.println();
    System.out.println("▶ 게시글 상세보기");

    // 0831 eun 수정함
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
    // 0831 eun 추가함
    System.out.printf("작성자 : %s\n", free.getFreeBoardWriter().getPerNickname());
    System.out.printf("등록일 : %s\n", free.getFreeBoardRegisteredDate());
    free.setFreeBoardViewcount(free.getFreeBoardViewcount() + 1);
    System.out.printf("조회수 : %d\n", free.getFreeBoardViewcount());
  }
  //------------------------------------------------------------------------------------------------




  //------------------------------------------------------------------------------------------------
  public void update() {
    System.out.println();
    System.out.println("▶ 게시글 수정");

    // 0831 eun 수정함
    String inputTitle = Prompt.inputString("제목 : ");
    System.out.println();

    FreeBoard free = findByTitle(inputTitle);

    if (free == null) {
      System.out.println("해당 제목의 게시글이 없습니다.");
      return;
    }

    // 0831 eun 추가함
    if (free.getFreeBoardWriter().getPerNo() != LoginHandler.getLoginUser().getPerNo()) {
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
  //------------------------------------------------------------------------------------------------




  //------------------------------------------------------------------------------------------------
  public void delete() {
    System.out.println();
    System.out.println("▶ 게시글 삭제");

    // 0831 eun 수정함
    String inputTitle = Prompt.inputString("제목 : ");
    System.out.println();

    FreeBoard free = findByTitle(inputTitle);

    if (free == null) {
      System.out.println("해당 제목의 게시글이 없습니다.");
      return;
    }

    // 0831 eun 추가함
    if (free.getFreeBoardWriter().getPerNo() != LoginHandler.getLoginUser().getPerNo()) {
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
  //------------------------------------------------------------------------------------------------




  //------------------------------------------------------------------------------------------------
  private FreeBoard findByTitle (String title) {
    for (FreeBoard board : freeBoardList) {
      if (board.getFreeBoardTitle().equalsIgnoreCase(title)) {
        return board;
      }
    }
    return null;
  }

}

