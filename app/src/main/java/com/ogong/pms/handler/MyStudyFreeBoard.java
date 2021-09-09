package com.ogong.pms.handler;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.Comment;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.util.Prompt;

public class MyStudyFreeBoard {


  List<FreeBoard> freeBoardList;
  List<Comment> commentList;
  int freeBoardNo;
  Study study;


  public MyStudyFreeBoard(List<FreeBoard> freeBoardList, List<Comment> commentList, Study study) {
    this.freeBoardList = freeBoardList;
    this.commentList = commentList;
    this.study = study;
  }


  // 등록
  private void addFreeBoard() {
    System.out.println();
    System.out.println("▶ 게시글 작성");

    Member member = AuthPerMemberLoginHandler.getLoginUser();
    if (member == null ) {
      System.out.println("로그인 한 회원만 조회 가능합니다.");
      return;
    }

    Member memeber = AuthPerMemberLoginHandler.getLoginUser();
    for(Study study : memeber.getPerMyStudy()) {
      if (study.getStudyTitle() == null) {
        System.out.println("가입된 스터디가 없습니다.");
        return;
      }
    }

    FreeBoard freeBoard = new FreeBoard();

    freeBoard.setFreeBoardNo(freeBoardNo++);
    freeBoard.setFreeBoardTitle(Prompt.inputString(" 제목 : "));
    freeBoard.setFreeBoardContent(Prompt.inputString(" 내용 : "));
    freeBoard.setFreeBoardAtcFile(Prompt.inputString(" 첨부파일 : "));
    freeBoard.setFreeBoardWriter(member);
    freeBoard.setFreeBoardViewcount(freeBoard.getFreeBoardViewcount());
    freeBoard.setFreeBoardRegisteredDate(new Date(System.currentTimeMillis()));

    String input = Prompt.inputString("게시글을 등록하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println("게시글 등록을 취소하였습니다.");
      return;
    }

    freeBoardList.add(freeBoard);
    System.out.println("게시글이 등록되었습니다.");
  }

  // 목록
  private void listFreeBoard() {
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

  }

  // 상세
  private void detailFreeBoard() {
    System.out.println();
    System.out.println("▶ 게시글 상세보기");

    System.out.println();

    FreeBoard freeBoard = findByNo(Prompt.inputInt("번호 : "));
    System.out.println();
    if (freeBoard == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    System.out.printf(" [%s]\n", freeBoard.getFreeBoardTitle());
    System.out.printf(" >> 내용 : %s\n", freeBoard.getFreeBoardContent());
    System.out.printf(" >> 첨부파일 : %s\n", freeBoard.getFreeBoardAtcFile());
    System.out.printf(" >> 작성자 : %s\n", freeBoard.getFreeBoardWriter().getPerNickname());
    System.out.printf(" >> 등록일 : %s\n", freeBoard.getFreeBoardRegisteredDate());
    freeBoard.setFreeBoardViewcount(freeBoard.getFreeBoardViewcount() + 1);
    System.out.printf(" >> 조회수 : %d\n", freeBoard.getFreeBoardViewcount());
    listComment(freeBoard); // 댓글호출

    System.out.println("\n----------------------");
    System.out.println("1. 댓글 달기");
    System.out.println("0. 뒤로 가기");
    int selectNo = Prompt.inputInt("선택> ");
    switch (selectNo) {
      case 1 : addComment(freeBoard); break;
      default : 
    }
  }

  // 수정
  private void updateFreeBoard() {
    System.out.println();
    System.out.println("▶ 게시글 수정");

    int inputTitle = Prompt.inputInt("번호 : ");
    System.out.println();

    FreeBoard free = findByNo(inputTitle);

    if (free == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
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
    if (!input.equalsIgnoreCase("네")) {
      System.out.println("게시글 변경이 취소되었습니다.");
      return;
    }

    free.setFreeBoardTitle(freeBoardTitle);
    free.setFreeBoardContent(freeBoardContent);
    free.setFreeBoardAtcFile(freeBoardAtcFile);

    System.out.println("게시글을 변경하였습니다.");
  }

  // 삭제
  private void deleteFreeBoard() {
    System.out.println();
    System.out.println("▶ 게시글 삭제");

    int inputTitle = Prompt.inputInt("번호 : ");
    System.out.println();

    FreeBoard free = findByNo(inputTitle);

    if (free == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    if (free.getFreeBoardWriter().getPerNo() != AuthPerMemberLoginHandler.getLoginUser().getPerNo()) {
      System.out.println("삭제 권한이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println("게시글 삭제를 취소하였습니다.");
      return;
    }

    freeBoardList.remove(free);

    System.out.println("게시글이 삭제되었습니다.");
  }



  protected FreeBoard findByNo(int inputNo) {
    for (FreeBoard freeBoard : freeBoardList) {
      if (freeBoard.getFreeBoardNo() == inputNo) {
        return freeBoard;
      }
    }
    return null;
  }


  //---------------댓글------------------------------------------------------
  protected void addComment(FreeBoard freeBoard) {
    System.out.println();
    System.out.println("▶ 댓글 작성하기");

    if (AuthPerMemberLoginHandler.getLoginUser() == null) {
      System.out.println("로그인 한 회원만 등록 가능합니다.");
    } else {

      Comment comment = new Comment();

      String text = Prompt.inputString("댓글 내용 : ");
      Member witer = AuthPerMemberLoginHandler.getLoginUser();
      Date date = new Date(System.currentTimeMillis());

      String input = Prompt.inputString("정말 등록하시겠습니까? (네 / 아니오) ");
      if (!input.equalsIgnoreCase("네")) {
        System.out.println("댓글 등록을 취소하였습니다.");
        return;
      }

      comment.setCommentText(text);
      comment.setCommentWiter(witer);
      comment.setCommentRegisteredDate(date);

      commentList.add(comment);
      freeBoard.getComment().add(comment);
      System.out.println("댓글이 등록되었습니다.");

    }
  }

  protected void listComment(FreeBoard freeBoard) {
    System.out.println();
    System.out.println("=============댓글=============");
    int commentSize = 0;

    for (Comment comment : freeBoard.getComment()) {
      System.out.printf("내용 : %s | 작성자 : %s | 등록일 : %s\n",
          comment.getCommentText(),
          comment.getCommentWiter().getPerNickname(),
          comment.getCommentRegisteredDate());
      commentSize++;
    }

    if (commentSize == 0) {
      System.out.println("등록된 댓글이 없습니다.");
    }
  }
}
