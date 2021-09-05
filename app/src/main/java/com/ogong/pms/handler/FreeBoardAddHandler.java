package com.ogong.pms.handler;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class FreeBoardAddHandler extends AbstractFreeBoardHandler {

  public FreeBoardAddHandler(List<FreeBoard> freeBoardList, List<Member> memberList) {
    super(freeBoardList, memberList);

    FreeBoard test = new FreeBoard();
    test.setFreeBoardNo(1);
    test.setFreeBoardTitle("다음 모임 시간");
    test.setFreeBoardContent("5월 10일에 만나요");
    test.setFreeBoardWriter(memberList.get(0));
    test.setFreeBoardAtcFile("지도");
    test.setFreeBoardRegisteredDate(new Date(System.currentTimeMillis()));
    test.setFreeBoardViewcount(test.getFreeBoardViewcount() + 1);
    freeBoardList.add(test);

    test = new FreeBoard();
    test.setFreeBoardNo(2);
    test.setFreeBoardTitle("오늘 스터디 현황");
    test.setFreeBoardContent("아주아주 잘하고 있습니다");
    test.setFreeBoardWriter(memberList.get(1));
    test.setFreeBoardAtcFile("jpg");
    test.setFreeBoardRegisteredDate(new Date(System.currentTimeMillis()));
    test.setFreeBoardViewcount(0);
    freeBoardList.add(test);

    test = new FreeBoard();
    test.setFreeBoardNo(1);
    test.setFreeBoardTitle("땡땡문제집 이부분 질문이요");
    test.setFreeBoardContent("땡땡땡땡이 이상합니다");
    test.setFreeBoardWriter(memberList.get(2));
    test.setFreeBoardAtcFile("문제집");
    test.setFreeBoardRegisteredDate(new Date(System.currentTimeMillis()));
    test.setFreeBoardViewcount(0);
    freeBoardList.add(test);
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 게시글 작성");

    FreeBoard freeBoard = new FreeBoard();

    int addCount = freeBoard.getFreeBoardNo();  //되는지 모르겠음

    freeBoard.setFreeBoardTitle(Prompt.inputString("제목 : "));
    freeBoard.setFreeBoardWriter(AuthPerMemberLoginHandler.getLoginUser());
    freeBoard.setFreeBoardContent(Prompt.inputString("내용 : "));
    freeBoard.setFreeBoardAtcFile(Prompt.inputString("첨부파일 : "));
    freeBoard.setFreeBoardRegisteredDate(new Date(System.currentTimeMillis()));

    addCount++;

    freeBoardList.add(freeBoard);
  }
}

