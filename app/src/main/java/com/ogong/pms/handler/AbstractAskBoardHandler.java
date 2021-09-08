package com.ogong.pms.handler;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.Admin;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.domain.Comment;
import com.ogong.util.Prompt;

public abstract class AbstractAskBoardHandler implements Command {

  List<AskBoard> askBoardList;
  List<Comment> commentList;

  public AbstractAskBoardHandler(List<AskBoard> askBoardList, List<Comment> commentList) {
    this.askBoardList = askBoardList;
    this.commentList = commentList;
  }

  protected AskBoard findByNo(int askNo) {
    for (AskBoard askList : askBoardList) {
      if (askList.getAskNo() == askNo) {
        return askList;
      }
    }
    return null;
  }

  protected Comment findByComment (int commentNo) {
    for (Comment comment : commentList) {
      if (comment.getCommentNo() == commentNo) {
        return comment;
      }
    }
    return null;
  }

  protected void addComment(AskBoard askBoard) {
    System.out.println();
    System.out.println("▶ 댓글 작성");
    System.out.println();

    Admin adminWiter = AuthAdminLoginHandler.getLoginAdmin();

    if (AuthAdminLoginHandler.getLoginAdmin() == null) {
      System.out.println("관리자만 등록 가능합니다.");
    } else {

      Comment comment = new Comment();

      comment.setCommentNo(Prompt.inputInt("번호 : "));
      comment.setCommentText(Prompt.inputString("댓글 내용 : "));
      System.out.println();
      comment.setCommentAdminWiter(adminWiter);
      comment.setCommentRegisteredDate(new Date(System.currentTimeMillis()));
      //
      String input = Prompt.inputString("정말 등록하시겠습니까? (네 / 아니오) ");
      if (!input.equalsIgnoreCase("네")) {
        System.out.println("댓글 등록을 취소하였습니다.");
        return;
      }

      commentList.add(comment);
      askBoard.getAdminComment().add(comment);

      System.out.println("댓글이 등록되었습니다.");
    }
  }

  protected void listComment(AskBoard askBoard) {
    System.out.println();
    System.out.println("=============댓글=============");
    int commentSize = 0;

    for (Comment comment : askBoard.getAdminComment()) {
      System.out.printf("(%d) >> 내용 : %s | 작성자 : %s | 등록일 : %s\n",
          comment.getCommentNo(),
          comment.getCommentText(), 
          comment.getCommentAdminWiter().getMasterNickname(),
          comment.getCommentRegisteredDate());
      commentSize++;
    }

    if (askBoard.getAdminComment() == null) {
      System.out.println("등록된 댓글이 없습니다.");
    }

  }

  protected void updateComment() {
    System.out.println();
    System.out.println("▶ 댓글 수정");
    System.out.println();

    if (AuthAdminLoginHandler.getLoginAdmin() == null) {
      System.out.println("관리자만 변경 가능합니다.");
    } else  {

      int admincommentNo = Prompt.inputInt("번호 : ");

      Comment comment = findByComment(admincommentNo);

      String AdmincommentTitle = Prompt.inputString(
          "댓글 내용(" +  comment.getCommentText() + ") : ");
      System.out.println();

      String input = Prompt.inputString("정말 변경하시겠습니까? (네 / 아니오) ");
      if (!input.equalsIgnoreCase("네")) {
        System.out.println("댓글 변경이 취소되었습니다.");
        return;
      }

      comment.setCommentText(AdmincommentTitle);

      System.out.println("댓글을 변경하였습니다.");
    }
  }

  protected void deleteComment(AskBoard askBoard) {
    System.out.println();
    System.out.println("▶ 댓글 삭제");
    System.out.println();

    if (AuthAdminLoginHandler.getLoginAdmin() == null) {
      System.out.println("관리자만 삭제 가능합니다.");
    } else  {

      int admincommentNo = Prompt.inputInt("번호 : ");

      Comment comment = findByComment(admincommentNo);

      System.out.println();
      String inputno = Prompt.inputString("정말 삭제하시겠습니까? (네 / 아니오) ");
      if (!inputno.equalsIgnoreCase("네")) {
        System.out.println("댓글 삭제를 취소하였습니다.");
        return;
      }

      commentList.remove(comment);
      askBoard.getAdminComment().remove(comment);

      System.out.println("댓글이 삭제되었습니다.");

    }
  }
}
