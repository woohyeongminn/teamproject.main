package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Comment;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class FreeBoardDetailHandler extends AbstractFreeBoardHandler {

  public FreeBoardDetailHandler(List<FreeBoard> freeBoardList, List<Member> memberList, List<Comment> commentList) {
    super(freeBoardList, memberList, commentList);
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 게시글 상세보기");

    System.out.println();

    FreeBoard free = findByTitle(Prompt.inputString("제목 : "));
    System.out.println();
    if (free == null) {
      System.out.println("해당 제목의 게시글이 없습니다.");
      return;
    }

    //    if (!inputTitle.equals(free.getFreeBoardTitle())) {
    //      System.out.println("해당 제목의 게시글이 없습니다.");
    //      return;
    //    }

    //(inputTitle != free.getFreeBoardTitle())

    //    for(FreeBoard freeBoard : freeBoardList) {
    //      if (!free.equals(freeBoard.getFreeBoardTitle())) {
    //        System.out.println("해당 제목의 게시글이 없습니다.");
    //        return;
    //      }
    //    }


    System.out.printf(">> 제목 : %s\n", free.getFreeBoardTitle());
    System.out.printf(">> 내용 : %s\n", free.getFreeBoardContent());
    System.out.printf(">> 첨부파일 : %s\n", free.getFreeBoardAtcFile());
    System.out.printf(">> 작성자 : %s\n", free.getFreeBoardWriter().getPerNickname());
    System.out.printf(">> 등록일 : %s\n", free.getFreeBoardRegisteredDate());
    free.setFreeBoardViewcount(free.getFreeBoardViewcount() + 1);
    System.out.printf(">> 조회수 : %d\n", free.getFreeBoardViewcount());

    System.out.println("=============댓글=============");
    int commentSize = 0;

    for (Comment comment : commentList) {
      System.out.printf("내용 : %s, 작성자 : %s, 등록일 : %s\n",
          comment.getCommentText(), comment.getCommentWiter().getPerNickname(), comment.getCommentRegisteredDate());
      commentSize++;
    }

    if (commentSize == 0) {
      System.out.println("등록된 댓글이 없습니다.");
    }

    System.out.println();
    System.out.println();

    System.out.println("1. 댓글 달기");
    System.out.println("0. 뒤로가기");
    int selectNo = Prompt.inputInt("선택> ");
    switch (selectNo) {
      case 1 : addComment(free); break;
      default : return;
    }
  }

}