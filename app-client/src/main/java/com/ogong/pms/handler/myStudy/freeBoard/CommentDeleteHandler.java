package com.ogong.pms.handler.myStudy.freeBoard;

import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;

public class CommentDeleteHandler implements Command {

  int freeBoardNo = 4;

  RequestAgent requestAgent;

  public CommentDeleteHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    //    System.out.println();
    //    System.out.println("▶ 댓글 작성");
    //
    //    if (AuthPerMemberLoginHandler.getLoginUser() == null) {
    //      System.out.println(" >> 로그인 한 회원만 등록 가능합니다.");
    //    } else {
    //
    //      Comment comment = new Comment();
    //
    //      comment.setCommentNo(commentNo++);
    //      comment.setCommentText(Prompt.inputString(" 댓글 내용 : "));
    //      comment.setCommentWiter(AuthPerMemberLoginHandler.getLoginUser());
    //      comment.setCommentRegisteredDate(new Date(System.currentTimeMillis()));
    //
    //      String input = Prompt.inputString(" 정말 등록하시겠습니까? (네 / 아니오) ");
    //      if (!input.equalsIgnoreCase("네")) {
    //        System.out.println(" >> 댓글 등록을 취소하였습니다.");
    //      }
    //
    //      commentList.add(comment);
    //      freeBoard.getComment().add(comment);
    //
    //      System.out.println(" >> 댓글이 등록되었습니다.");
    //    }
  }
}

