package com.ogong.pms.handler;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class AskBoardHandler {

  List<AskBoard> askBoardList;
  List<Member> memberList;

  public AskBoardHandler(List<AskBoard> askBoardList, List<Member> memberList) {
    this.askBoardList = askBoardList;
    this.memberList = memberList;

    AskBoard askList = new AskBoard();
    askList.setAskNo(1);
    askList.setAskTitle("문의합니다.");
    askList.setAskContent("예약 방법에 대해 알고 싶습니다.");
    askList.setAskWriter(memberList.get(0));
    askList.setAskRegisteredDate(new Date(System.currentTimeMillis()));
    askList.setAskVeiwCount(askList.getAskVeiwCount() + 1);

    askBoardList.add(askList);
  }

  public void add() {
    System.out.println();
    System.out.println("▶ 문의사항");

    AskBoard askList = new AskBoard();

    askList.setAskNo(Prompt.inputInt("번호? "));
    askList.setAskTitle(Prompt.inputString("제목? "));
    askList.setAskContent(Prompt.inputString("내용? "));
    askList.setAskWriter(LoginHandler.getLoginUser());
    askList.setAskRegisteredDate(new Date(System.currentTimeMillis()));

    askBoardList.add(askList);
  }

  public void list() {
    System.out.println();
    System.out.println("▶ 문의사항 목록");

    for (AskBoard askList : askBoardList) {
      System.out.printf("%d. %s %s %s %d\n", 
          askList.getAskNo(), 
          askList.getAskTitle(), 
          askList.getAskWriter().getPerNickname(),
          askList.getAskRegisteredDate(),
          askList.getAskVeiwCount());
    }
  }

  public void detail() {
    System.out.println();
    System.out.println("▶ 문의사항 상세");
    int askNo = Prompt.inputInt("번호? ");

    AskBoard askList = findByNo(askNo);

    if (askList == null) {
      System.out.println("해당 번호의 문의글이 없습니다.");
      return;
    }

    System.out.printf(">> %s\n", askList.getAskTitle());
    System.out.printf(">> %s\n", askList.getAskContent());
    System.out.printf(">> %s\n", askList.getAskWriter().getPerNickname());
    System.out.printf(">> %s\n", askList.getAskRegisteredDate());

    askList.setAskVeiwCount(askList.getAskVeiwCount() + 1);
    System.out.printf(">> %d\n", askList.getAskVeiwCount());
  }

  public void update() {
    System.out.println();
    System.out.println("▶ 문의사항 변경");
    int askNo = Prompt.inputInt("번호? ");

    AskBoard askList = findByNo(askNo);

    if (askList == null) {
      System.out.println("해당 번호의 문의글이 없습니다.");
      return;
    }

    String askTitle = Prompt.inputString(String.format("제목(%s)? ", askList.getAskTitle()));
    String askContent = Prompt.inputString(String.format("내용(%s)? ", askList.getAskContent()));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("문의글 변경을 취소하였습니다.");
      return;
    }

    askList.setAskTitle(askTitle);
    askList.setAskContent(askContent);
    System.out.println("문의글을 변경하였습니다.");
  }

  public void delete() {
    System.out.println();
    System.out.println("▶ 문의사항 삭제");
    int askNo = Prompt.inputInt("번호? ");

    AskBoard askList = findByNo(askNo);

    if (askList == null) {
      System.out.println("해당 번호의 문의글이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("문의글 삭제를 취소하였습니다.");
      return;
    }

    askBoardList.remove(askList);

    System.out.println("문의글을 삭제하였습니다.");
  }

  private AskBoard findByNo(int askNo) {
    for (AskBoard askList : askBoardList) {
      if (askList.getAskNo() == askNo) {
        return askList;
      }
    }
    return null;
  }
}







