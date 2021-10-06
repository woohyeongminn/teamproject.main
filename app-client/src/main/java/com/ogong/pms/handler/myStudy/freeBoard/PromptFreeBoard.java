package com.ogong.pms.handler.myStudy.freeBoard;

import java.util.Collection;
import com.ogong.pms.domain.Comment;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.request.RequestAgent;

public class PromptFreeBoard {

  protected RequestAgent requestAgent;

  public PromptFreeBoard(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  public void printComments(FreeBoard freeComment) throws Exception {
    System.out.println();
    System.out.println("============= 댓글 =============");

    int countFreeBoard = 0;

    for (Comment comment : freeComment.getComment()) {
      System.out.printf(" (%d) | 내용 : %s | 작성자 : %s | 등록일 : %s\n",
          comment.getCommentNo(),
          comment.getCommentText(),
          comment.getCommentWiter().getPerNickname(),
          comment.getCommentRegisteredDate());
      countFreeBoard++;
    }

    if (countFreeBoard == 0) {
      System.out.println(" >> 등록된 댓글이 없습니다.");
    }
  }

  //
  //  public FreeBoard promptFreeBoard() throws Exception {
  //    System.out.println("게시글:");
  //
  //    requestAgent.request("freeBoard.selectList", null);
  //
  //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
  //      System.out.println("목록 조회 실패!");
  //      return null;
  //    }
  //
  //    Collection<FreeBoard> freeBoardList = requestAgent.getObjects(FreeBoard.class);
  //
  //    for (FreeBoard freeBoard : freeBoardList) {
  //      System.out.printf("  %d. %s\n", freeBoard.getFreeBoardNo(), freeBoard.getFreeBoardTitle());
  //    }
  //
  //    while (true) {
  //      int freeBoardNo = Prompt.inputInt("게시글 번호 선택? (취소: 0) ");
  //      if (freeBoardNo == 0) {
  //        return null;
  //      }
  //      FreeBoard selectedFreeBoard = findByNo(freeBoardNo, freeBoardList);
  //      if (selectedFreeBoard != null) {
  //        return selectedFreeBoard;
  //      }
  //      System.out.println("게시글 번호가 옳지 않습니다.");
  //    }
  //  }

  protected FreeBoard findByNo(int no, Collection<FreeBoard> freeBoardList) {
    for (FreeBoard freeBoard : freeBoardList) {
      if (freeBoard.getFreeBoardNo() == no) {
        return freeBoard;
      }
    }
    return null;
  }
}