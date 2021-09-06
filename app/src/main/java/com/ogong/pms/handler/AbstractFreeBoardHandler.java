package com.ogong.pms.handler;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import com.ogong.pms.domain.Comment;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public abstract class AbstractFreeBoardHandler implements Command {

  List<FreeBoard> freeBoardList;
  List<Comment> commentList;
  HashMap<String, Command> commandMap;


  public AbstractFreeBoardHandler(List<FreeBoard> freeBoardList,
      List<Comment> commentList,  HashMap<String, Command> commandMap) {
    this.freeBoardList = freeBoardList;
    this.commentList = commentList;
    this.commandMap = commandMap;
  }

  //  protected String findByTitleString (String title) {
  //    for (FreeBoard freeBoard : freeBoardList) {
  //      String fString = freeBoard.getFreeBoardTitle();
  //      if (fString.equals(title)) {
  //        return fString;
  //      }
  //    }
  //    return null;
  //  }

  protected void selectPage() {
    System.out.println("\n---------------------");
    System.out.println("1. 게시글 목록");
    System.out.println("2. 게시글 등록");
    System.out.println("0. 뒤로가기");

    int selectAdminNo = Prompt.inputInt("선택> ");

    switch (selectAdminNo) {
      case 1: commandMap.get("/freeBoard/list").execute(); break;
      case 2: commandMap.get("/freeBoard/add").execute(); break;
      default : return;
    }
  }

  protected FreeBoard findByTitle (String title) {
    for (FreeBoard freeBoard : freeBoardList) {
      if (freeBoard.getFreeBoardTitle().equals(title)) {
        return freeBoard;
      }
    }
    return null;
  }

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
        selectPage();
        return;
      }

      comment.setCommentText(text);
      comment.setCommentWiter(witer);
      comment.setCommentRegisteredDate(date);

      commentList.add(comment);
      freeBoard.getComment().add(comment);
      System.out.println("댓글이 등록되었습니다.");

      selectPage();
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

