package com.ogong.pms.handler;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import com.ogong.pms.domain.Comment;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.util.Prompt;

public abstract class AbstractFreeBoardHandler implements Command {

  List<FreeBoard> freeBoardList;
  List<Member> memberList;
  List<Comment> commentList;
  List<Study> studyList;
  HashMap<String, Command> commandMap;


  public AbstractFreeBoardHandler(List<FreeBoard> freeBoardList, List<Member> memberList,
      List<Comment> commentList,  HashMap<String, Command> commandMap) {
    this.freeBoardList = freeBoardList;
    this.memberList = memberList;
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
    System.out.println("---------------------");
    System.out.println("1. 목록보기");
    System.out.println("2. 상세보기");
    System.out.println("3. 등록보기");
    System.out.println("4. 수정하기");
    System.out.println("5. 삭제하기");
    System.out.println("0. 뒤로가기");

    int selectAdminNo = Prompt.inputInt("선택> ");

    switch (selectAdminNo) {
      case 1: commandMap.get("/freeBoard/list").execute(); break;
      case 2: commandMap.get("/freeBoard/add").execute(); break;
      case 3: commandMap.get("/freeBoard/add").execute(); break;
      case 4: commandMap.get("/freeBoard/update").execute(); break;
      case 5: commandMap.get("/freeBoard/delete").execute(); break;
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

  protected void addComment(FreeBoard free) {
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
      free.getComment().add(comment);
      System.out.println("댓글이 등록되었습니다.");

      selectPage();
    }
  }
}

