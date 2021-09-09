package com.ogong.pms.handler;

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

    int askNo = Prompt.inputInt("번호 : ");

    AskBoard askBoard = findByNo(askNo);

    if (askBoard == null) {
      System.out.println("해당 번호의 문의글이 없습니다.");
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

    if (AuthAdminLoginHandler.getLoginAdmin() != null) {
      System.out.println("\n---------------------");
      System.out.println("1. 댓글 달기");
      System.out.println("2. 댓글 수정");
      System.out.println("3. 댓글 삭제");
      System.out.println("0. 뒤로 가기");
      int selectNo = Prompt.inputInt("선택> ");
      switch (selectNo) {
        case 1 : addComment(askBoard); break;
        case 2 : updateComment(); break;
        case 3 : deleteComment(askBoard); break;
        default : return;
      }
    }

  }


}
