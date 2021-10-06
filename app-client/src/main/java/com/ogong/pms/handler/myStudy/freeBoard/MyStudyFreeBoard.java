package com.ogong.pms.handler.myStudy.freeBoard;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.Comment;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class MyStudyFreeBoard implements Command {

  int freeBoardNo = 4;
  List<FreeBoard> freeBoardList;
  List<Comment> commentList;
  List<Member> memberList;
  List<Study> studyList;
  Study study;

  RequestAgent requestAgent;

  public MyStudyFreeBoard(List<FreeBoard> freeBoardList, List<Comment> commentList,
      List<Member> memberList, List<Study> studyList, RequestAgent requestAgent) {
    this.freeBoardList = freeBoardList;
    this.commentList = commentList;
    this.memberList = memberList;
    this.studyList = studyList;
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {


  }
  int commentNo = 1;

  // 댓글 등록
  private void addComment(FreeBoard freeBoard, List<Comment> commentList) {
    System.out.println();
    System.out.println("▶ 댓글 작성");

    if (AuthPerMemberLoginHandler.getLoginUser() == null) {
      System.out.println(" >> 로그인 한 회원만 등록 가능합니다.");
    } else {

      Comment comment = new Comment();

      comment.setCommentNo(commentNo++);
      comment.setCommentText(Prompt.inputString(" 댓글 내용 : "));
      comment.setCommentWiter(AuthPerMemberLoginHandler.getLoginUser());
      comment.setCommentRegisteredDate(new Date(System.currentTimeMillis()));

      String input = Prompt.inputString(" 정말 등록하시겠습니까? (네 / 아니오) ");
      if (!input.equalsIgnoreCase("네")) {
        System.out.println(" >> 댓글 등록을 취소하였습니다.");
      }

      commentList.add(comment);
      freeBoard.getComment().add(comment);

      System.out.println(" >> 댓글이 등록되었습니다.");
    }
  }

  // 댓글 출력
  private void listComment(FreeBoard freeBoard) {
    System.out.println();
    System.out.println("============= 댓글 =============");

    int countFreeBoard = 0;

    for (Comment comment : freeBoard.getComment()) {
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

  // 댓글 수정
  static int commentNo1;

  private void updateComment() {
    System.out.println();
    System.out.println("▶ 댓글 수정");
    System.out.println();

    if (AuthPerMemberLoginHandler.getLoginUser() == null) {
      System.out.println(" >> 변경 권한이 없습니다.");

    } else  {

      try {
        commentNo1 = Prompt.inputInt(" 번호 : ");
      } catch (Exception e){
        System.out.println(" >> 번호를 입력해 주세요.");
      }

      Comment comment = findByComment(commentNo1);

      String commentTitle = Prompt.inputString(
          "댓글 내용(" +  comment.getCommentText() + ") : ");
      System.out.println();

      String input = Prompt.inputString(" 정말 변경하시겠습니까? (네 / 아니오) ");
      if (!input.equalsIgnoreCase("네")) {
        System.out.println(" >> 댓글 변경이 취소되었습니다.");
        return;
      }

      comment.setCommentText(commentTitle);

      System.out.println(" >> 댓글을 변경하였습니다.");
    }
  }

  // 댓글 삭제
  private void deleteComment(FreeBoard freeBoard) {
    System.out.println();
    System.out.println("▶ 댓글 삭제");
    System.out.println();

    if (AuthPerMemberLoginHandler.getLoginUser() == null) {
      System.out.println(" >> 삭제 권한이 없습니다.");
    } else  {

      try {
        commentNo1 = Prompt.inputInt(" 번호 : ");
      } catch (Exception e) {
        System.out.println(" >> 번호를 입력해 주세요");
      }

      Comment comment = findByComment(commentNo1);

      System.out.println();
      String inputno = Prompt.inputString(" 정말 삭제하시겠습니까? (네 / 아니오) ");
      if (!inputno.equalsIgnoreCase("네")) {
        System.out.println(" >> 댓글 삭제를 취소하였습니다.");
        return;
      }

      commentList.remove(comment);
      freeBoard.getComment().remove(comment);

      System.out.println(" >> 댓글이 삭제되었습니다.");
    }
  }

  //게시글 번호로 찾기
  private FreeBoard findByNo(int inputNo, List<FreeBoard> freeBoardArrayList) {
    for (FreeBoard freeBoard : freeBoardArrayList) {
      if (freeBoard.getFreeBoardNo() == inputNo) {
        return freeBoard;
      }
    }
    return null;
  }

  //댓글 번호로 찾기
  private Comment findByComment (int commentNo) {
    for (Comment comment : commentList) {
      if (comment.getCommentNo() == commentNo) {
        return comment;
      }
    }
    return null;
  }
}

// 번호 입력
//    int inputTitle = Prompt.inputInt("번호 : ");
//    System.out.println();
//
//    FreeBoard free = findByNo(inputTitle);
//
//    if (free == null) {
//      System.out.println("해당 번호의 게시글이 없습니다.");
//      return;
//    }
//
//    if (free.getFreeBoardWriter().getPerNo() != AuthPerMemberLoginHandler.getLoginUser().getPerNo()) {
//      System.out.println("삭제 권한이 없습니다.");
//      return;
//    }


