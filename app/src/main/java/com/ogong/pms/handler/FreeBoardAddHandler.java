package com.ogong.pms.handler;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.ogong.pms.domain.Comment;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.util.Prompt;

public class FreeBoardAddHandler extends AbstractFreeBoardHandler {

  int freeBoardNo;

  public FreeBoardAddHandler(List<FreeBoard> freeBoardList, List<Member> memberList,
      List<Comment> commentList, HashMap<String, Command> commandMap) {
    super(freeBoardList, memberList, commentList, commandMap);

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

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 게시글 작성");

    Member member = AuthPerMemberLoginHandler.getLoginUser();
    if (member == null ) {
      System.out.println("로그인 한 회원만 조회 가능합니다.");
      return;
    }

    Member memeber = AuthPerMemberLoginHandler.getLoginUser();
    for(Study study : memeber.getPerMyStudy()) {
      if (study.getStudyTitle() == null) {
        System.out.println("가입된 스터디가 없습니다.");
        return;
      }
    }

    FreeBoard freeBoard = new FreeBoard();

    freeBoard.setFreeBoardNo(freeBoardNo++);
    freeBoard.setFreeBoardTitle(Prompt.inputString("제목 : "));
    freeBoard.setFreeBoardContent(Prompt.inputString("내용 : "));
    freeBoard.setFreeBoardAtcFile(Prompt.inputString("첨부파일 : "));
    freeBoard.setFreeBoardWriter(member);
    freeBoard.setFreeBoardViewcount(freeBoard.getFreeBoardViewcount());
    freeBoard.setFreeBoardRegisteredDate(new Date(System.currentTimeMillis()));

    String input = Prompt.inputString("게시글을 등록하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println("게시글 등록을 취소하였습니다.");
      selectPage();
      return;
    }

    freeBoardList.add(freeBoard);
    System.out.println("게시글이 등록되었습니다.");
    selectPage();
  }
}

