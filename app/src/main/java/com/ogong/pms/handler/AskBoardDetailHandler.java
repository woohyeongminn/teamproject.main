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

    System.out.println("\n---------------------");
    System.out.println("1. 댓글 달기");
    System.out.println("2. 댓글 수정");
    System.out.println("3. 댓글 삭제");
    System.out.println("0. 뒤로 가기");
    int selectNo = Prompt.inputInt("선택> ");
    switch (selectNo) {
      case 1 : addComment(askBoard); break;
      case 2 : updateComment(); break;
      case 3 : deleteComment(); break;
      default : return;
    }
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

      //      String text = Prompt.inputString(" 댓글 내용 : ");
      //      System.out.println();
      //      Admin adminWiter = AuthAdminLoginHandler.getLoginAdmin();
      //      Date date = new Date(System.currentTimeMillis());
      //
      String input = Prompt.inputString("정말 등록하시겠습니까? (네 / 아니오) ");
      if (!input.equalsIgnoreCase("네")) {
        System.out.println("댓글 등록을 취소하였습니다.");
        return;
      }

      //      comment.setCommentText(text);
      //      comment.setCommentAdminWiter(adminWiter);
      //      comment.setCommentRegisteredDate(date);

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

    if (commentSize == 0) {
      System.out.println("등록된 댓글이 없습니다.");
    }
  }

  protected void updateComment() {
    System.out.println();
    System.out.println("▶ 댓글 수정");
    System.out.println();

    int admincommentNo = Prompt.inputInt("번호 : ");

    Comment updatecomment = findByComment(admincommentNo);

    // 등록된 댓글이 없습니다가 나와야 하는데 안 나옴 ㅜㅜ

    if (updatecomment == null) {
      System.out.println("변경할 댓글 번호를 다시 선택해 주세요.");
      return;
    }

    if (AuthAdminLoginHandler.getLoginAdmin() == null) {
      System.out.println("\n관리자만 변경 가능합니다.");
      return;
    } 

    //    if (updatecomment.getCommentAdminWiter().getMasterNo() != AuthPerMemberLoginHandler.getLoginUser().getPerNo()) {
    //      System.out.println("변경 권한이 없습니다.");
    //      return;
    //    }

    String AdmincommentTitle = Prompt.inputString(
        "댓글 내용(" +  updatecomment.getCommentText() + ") : ");
    System.out.println();

    String input = Prompt.inputString("정말 변경하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println("댓글 변경이 취소되었습니다.");
      return;
    }

    updatecomment.setCommentText(AdmincommentTitle);

    System.out.println("댓글을 변경하였습니다.");
  }

  protected void deleteComment() {
    System.out.println();
    System.out.println("▶ 댓글 삭제");
    System.out.println();
    int admincommentNo = Prompt.inputInt("번호 : ");

    Comment updatecomment = findByComment(admincommentNo);

    if (updatecomment == null) {
      System.out.println("삭제할 댓글 번호를 다시 선택해 주세요.");
      return;
    }

    if (AuthAdminLoginHandler.getLoginAdmin() == null) {
      System.out.println("\n관리자만 삭제 가능합니다.");
      return;
    }

    System.out.println();
    String inputno = Prompt.inputString("정말 삭제하시겠습니까? (네 / 아니오) ");
    if (!inputno.equalsIgnoreCase("네")) {
      System.out.println("댓글 삭제를 취소하였습니다.");
      return;
    }

    commentList.remove(updatecomment);

    System.out.println("댓글이 삭제되었습니다.");
  }

}
