package com.ogong.pms.handler;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.ogong.pms.domain.Comment;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.util.Prompt;

public class MyStudyFreeBoard {

  int freeBoardNo;
  List<FreeBoard> freeBoardList;
  List<Comment> commentList;
  List<Member> memberList;
  List<Study> studyList;
  Study study;

  public MyStudyFreeBoard(List<FreeBoard> freeBoardList, List<Comment> commentList,
      List<Member> memberList, List<Study> studyList) {
    this.freeBoardList = freeBoardList;
    this.commentList = commentList;
    this.memberList = memberList;
    this.studyList = studyList;

    FreeBoard test = new FreeBoard();
    test.setFreeBoardNo(freeBoardNo++);
    test.setFreeBoardTitle("게시글1");
    test.setFreeBoardContent("5월 10일에 만나요");
    test.setFreeBoardAtcFile("지도");
    test.setFreeBoardWriter(memberList.get(0));
    test.setFreeBoardViewcount(test.getFreeBoardViewcount());
    test.setFreeBoardRegisteredDate(new Date(System.currentTimeMillis()));
    test.setComment(new ArrayList<>());
    freeBoardList.add(test);

    test = new FreeBoard();
    test.setFreeBoardNo(freeBoardNo++);
    test.setFreeBoardTitle("게시글2");
    test.setFreeBoardContent("아주아주 잘하고 있습니다");
    test.setFreeBoardAtcFile("jpg");
    test.setFreeBoardWriter(memberList.get(1));
    test.setFreeBoardViewcount(test.getFreeBoardViewcount());
    test.setFreeBoardRegisteredDate(new Date(System.currentTimeMillis()));
    test.setComment(new ArrayList<>());
    freeBoardList.add(test);

    test = new FreeBoard();
    test.setFreeBoardNo(freeBoardNo++);
    test.setFreeBoardTitle("게시글3");
    test.setFreeBoardContent("159p 이상합니다");
    test.setFreeBoardAtcFile("문제집");
    test.setFreeBoardWriter(memberList.get(2));
    test.setFreeBoardViewcount(test.getFreeBoardViewcount());
    test.setFreeBoardRegisteredDate(new Date(System.currentTimeMillis()));
    test.setComment(new ArrayList<>());
    freeBoardList.add(test);
  }

  // 등록
  private void addFreeBoard(Study study) {
    System.out.println();
    System.out.println("▶ 게시글 작성");
    System.out.println();

    Member member = AuthPerMemberLoginHandler.getLoginUser();
    if (member == null ) {
      System.out.println("로그인 한 회원만 조회 가능합니다.");
      return;
    }

    if (study.getStudyTitle() == null) {
      System.out.println("가입된 스터디가 없습니다.");
      return;
    }

    FreeBoard freeBoard = new FreeBoard();

    freeBoard.setFreeBoardNo(freeBoardNo++);
    freeBoard.setFreeBoardTitle(Prompt.inputString(" 제목 : "));
    freeBoard.setFreeBoardContent(Prompt.inputString(" 내용 : "));
    freeBoard.setFreeBoardAtcFile(Prompt.inputString(" 첨부파일 : "));
    freeBoard.setFreeBoardWriter(member);
    freeBoard.setFreeBoardViewcount(freeBoard.getFreeBoardViewcount());
    freeBoard.setFreeBoardRegisteredDate(new Date(System.currentTimeMillis()));

    String input = Prompt.inputString("게시글을 등록하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println("게시글 등록을 취소하였습니다.");
      seleteMene(freeBoardList);
      return;
    }

    freeBoardList.add(freeBoard);
    study.getMyStudyFreeBoard().add(freeBoard);

    System.out.println("게시글이 등록되었습니다.");
    listFreeBoard(study); 
    return;
  }

  // 목록
  public void listFreeBoard(Study study) {
    System.out.println();
    System.out.println("▶ 게시글 목록");
    System.out.println();

    List<FreeBoard> freeBoardArrayList = new ArrayList<>();

    for (FreeBoard freeBoard : freeBoardList) {
      System.out.printf(
          "(%d)\n 제목 : %s\n 내용 : %s\n 첨부파일 : %s\n 작성자 : %s\n 조회수 : %s\n 작성일 : %s\n",
          freeBoard.getFreeBoardNo(), 
          freeBoard.getFreeBoardTitle(),
          freeBoard.getFreeBoardContent(),
          freeBoard.getFreeBoardAtcFile(),
          freeBoard.getFreeBoardWriter().getPerNickname(),
          freeBoard.getFreeBoardViewcount(),
          freeBoard.getFreeBoardRegisteredDate());
      System.out.println();

      freeBoardArrayList.add(freeBoard); 
    }

    if (freeBoardArrayList.isEmpty()) {
      System.out.println("등록된 게시글이 없습니다");
      return;
    }

    System.out.println("---------------------");
    System.out.println("1. 상세");
    System.out.println("2. 등록");
    System.out.println("0. 이전");
    int selete = Prompt.inputInt("선택> ");
    switch (selete) {
      case 1 : detailFreeBoard(freeBoardArrayList, study); break;
      case 2 : addFreeBoard(study); break;
      default : return;
    }

  }

  // 상세
  private void detailFreeBoard(List<FreeBoard> freeBoardArrayList, Study study) {
    System.out.println();
    System.out.println("▶ 게시글 상세보기");
    System.out.println();

    int freeBoardNo;
    FreeBoard detailfreeBoard = new FreeBoard();

    while (true) {
      freeBoardNo = Prompt.inputInt("번호 : ");
      System.out.println();

      for (int i = 0; i < freeBoardArrayList.size(); i++) {

        if (freeBoardArrayList.get(i).getFreeBoardNo() != freeBoardNo) {
          System.out.println("해당 번호의 게시글이 없습니다.");
          listFreeBoard(study);
          continue;
        }

        if (freeBoardArrayList.get(i).getFreeBoardNo() == freeBoardNo) {
          System.out.printf(" [%s]\n", freeBoardArrayList.get(i).getFreeBoardTitle());
          System.out.printf(" >> 내용 : %s\n", freeBoardArrayList.get(i).getFreeBoardContent());
          System.out.printf(" >> 첨부파일 : %s\n", freeBoardArrayList.get(i).getFreeBoardAtcFile());
          System.out.printf(" >> 작성자 : %s\n", freeBoardArrayList.get(i).getFreeBoardWriter().getPerNickname());
          System.out.printf(" >> 등록일 : %s\n", freeBoardArrayList.get(i).getFreeBoardRegisteredDate());
          freeBoardArrayList.get(i).setFreeBoardViewcount(freeBoardArrayList.get(i).getFreeBoardViewcount() + 1);
          System.out.printf(" >> 조회수 : %d\n", freeBoardArrayList.get(i).getFreeBoardViewcount());
          detailfreeBoard = freeBoardArrayList.get(i);

          listComment(detailfreeBoard); // 댓글호출
        }
      }
      break;
    }

    System.out.println("\n----------------------");
    System.out.println("1. 수정");
    System.out.println("2. 삭제");
    System.out.println("3. 댓글 등록");
    System.out.println("0. 이전");
    int selectNo = Prompt.inputInt("선택> ");
    switch (selectNo) {
      case 1 : updateFreeBoard(detailfreeBoard, study); break;
      case 2 : deleteFreeBoard(detailfreeBoard, study); break;
      case 3 : addComment(detailfreeBoard); break;
      case 0 : listFreeBoard(study); break;
      default : 
    }
  }

  // 수정
  private void updateFreeBoard(FreeBoard detailFreeBoard, Study study) {
    System.out.println();
    System.out.println("▶ 게시글 수정");
    System.out.println();

    if (detailFreeBoard.getFreeBoardWriter().getPerNo() != AuthPerMemberLoginHandler.getLoginUser().getPerNo()) {
      System.out.println("수정 권한이 없습니다.");
      listFreeBoard(study);
      return;
    }

    String freeBoardTitle = Prompt.inputString("제목(" + detailFreeBoard.getFreeBoardTitle()  + ") : ");
    String freeBoardContent = Prompt.inputString("내용(" + detailFreeBoard.getFreeBoardContent() + ") : ");
    String freeBoardAtcFile = Prompt.inputString("첨부파일(" + detailFreeBoard.getFreeBoardAtcFile() + ") : ");

    String input = Prompt.inputString("정말 변경하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println("변경을 취소되었습니다.");
      listFreeBoard(study);
      return;
    }

    detailFreeBoard.setFreeBoardTitle(freeBoardTitle);
    detailFreeBoard.setFreeBoardContent(freeBoardContent);
    detailFreeBoard.setFreeBoardAtcFile(freeBoardAtcFile);

    System.out.println("게시글을 변경하였습니다.");
    listFreeBoard(study);
    return;
  }

  // 삭제
  private void deleteFreeBoard(FreeBoard detailFreeBoard, Study study) {
    System.out.println();
    System.out.println("▶ 게시글 삭제");
    System.out.println();

    if (detailFreeBoard.getFreeBoardWriter().getPerNo() != AuthPerMemberLoginHandler.getLoginUser().getPerNo()) {
      System.out.println("삭제 권한이 없습니다.");
      listFreeBoard(study);
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println("삭제를 취소하였습니다.");
      listFreeBoard(study);
      return;
    }

    freeBoardList.remove(detailFreeBoard);
    study.getMyStudyFreeBoard().remove(detailFreeBoard);

    System.out.println("게시글이 삭제되었습니다.");

    listFreeBoard(study);
    return;
  }

  //--------------------------댓글--------------------------
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
        return;
      }

      comment.setCommentText(text);
      comment.setCommentWiter(witer);
      comment.setCommentRegisteredDate(date);

      commentList.add(comment);
      freeBoard.getComment().add(comment);

      System.out.println("댓글이 등록되었습니다.");

      listFreeBoard(study);
      return;
    }
  }

  protected void listComment(FreeBoard freeBoard) {
    System.out.println();
    System.out.println("=============댓글=============");

    int countFreeBoard = 0;

    //    if (freeBoard.getComment().isEmpty()) {
    //      System.out.println("등록된 댓글이 없습니다.");
    //    }

    if (countFreeBoard == 0) {
      System.out.println("등록된 댓글이 없습니다.");
    }


    for (Comment comment : freeBoard.getComment()) {
      System.out.printf("내용 : %s | 작성자 : %s | 등록일 : %s\n",
          comment.getCommentText(),
          comment.getCommentWiter().getPerNickname(),
          comment.getCommentRegisteredDate());
      countFreeBoard++;
    }
  }

  private void seleteMene(List<FreeBoard> freeBoardArrayList) {
    System.out.println("---------------------");
    System.out.println("1. 목록");
    System.out.println("2. 상세");
    System.out.println("3. 등록");
    System.out.println("0. 이전");
    int selete = Prompt.inputInt("선택> ");
    switch (selete) {
      case 1 : listFreeBoard(study); break;
      case 2 : detailFreeBoard(freeBoardArrayList, study); break;
      case 3 : addFreeBoard(study); break;
      case 0 : listFreeBoard(study); break;
      default : return;
    }
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

// 번호 찾기
//  protected FreeBoard findByNo(int inputNo) {
//    for (FreeBoard freeBoard : freeBoardList) {
//      if (freeBoard.getFreeBoardNo() == inputNo) {
//        return freeBoard;
//      }
//    }
//    return null;
//  }
