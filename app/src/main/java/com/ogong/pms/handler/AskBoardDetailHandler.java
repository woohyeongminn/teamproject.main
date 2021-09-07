package com.ogong.pms.handler;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.Admin;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.domain.Comment;
import com.ogong.util.Prompt;

public class AskBoardDetailHandler extends AbstractAskBoardHandler {

  List<Admin> adminList;

  public AskBoardDetailHandler(List<AskBoard> askBoardList, 
      List<Comment> commentList, List<Admin> adminList) {
    super(askBoardList, commentList);
    this.adminList = adminList;
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 문의사항 상세");
    System.out.println();

    int askNo = Prompt.inputInt(" 번호 : ");

    AskBoard askBoard = findByNo(askNo);

    if (askBoard == null) {
      System.out.println(" 해당 번호의 문의글이 없습니다.");
      return;
    }

    System.out.println();
    System.out.printf(" [%s]\n", askBoard.getAskTitle());
    System.out.printf(" >> 내용 : %s\n", askBoard.getAskContent());
    System.out.printf(" >> 작성자 : %s\n", askBoard.getAskWriter().getPerNickname());
    System.out.printf(" >> 작성일 : %s\n", askBoard.getAskRegisteredDate());
    askBoard.setAskVeiwCount(askBoard.getAskVeiwCount() + 1);
    System.out.printf(" >> 조회수 : %d\n", askBoard.getAskVeiwCount());
    listComment(askBoard);  // 댓글호출

    System.out.println("\n---------------------");
    System.out.println("1. 댓글 달기");
    System.out.println("0. 뒤로가기");
    int selectNo = Prompt.inputInt("선택> ");
    switch (selectNo) {
      case 1 : addComment(askBoard); break;
      default : return;
    }
  }

  protected void addComment(AskBoard askBoard) {
    System.out.println();
    System.out.println("▶ 댓글 작성하기");
    System.out.println();

    if (AuthAdminLoginHandler.getLoginAdmin() == null) {
      System.out.println("로그인 한 관리자만 등록 가능합니다.");
    } else {

      Comment comment = new Comment();

      String text = Prompt.inputString("댓글 내용 : ");
      System.out.println();
      Admin adminWiter = AuthAdminLoginHandler.getLoginAdmin();
      Date date = new Date(System.currentTimeMillis());

      String input = Prompt.inputString("정말 등록하시겠습니까? (네 / 아니오) ");
      if (!input.equalsIgnoreCase("네")) {
        System.out.println("댓글 등록을 취소하였습니다.");
        return;
      }

      comment.setCommentText(text);
      comment.setCommentAdminWiter(adminWiter);
      comment.setCommentRegisteredDate(date);

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
      System.out.printf("내용 : %s | 작성자 : %s | 등록일 : %s\n",
          comment.getCommentText(), 
          comment.getCommentAdminWiter().getMasterNickname(),
          comment.getCommentRegisteredDate());
      commentSize++;
    }

    if (commentSize == 0) {
      System.out.println("등록된 댓글이 없습니다.");
    }
  }
}
