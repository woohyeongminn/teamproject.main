package com.ogong.pms.handler;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.AskBoard;
import com.ogong.util.Prompt;

public class AskBoardHandler {

  List<AskBoard> askBoardList;

  public AskBoardHandler(List<AskBoard> askBoardList) {
    this.askBoardList = askBoardList;
  }

  public void add() {
    System.out.println("[문의게시판]");

    AskBoard ask = new AskBoard();

    ask.setAskNo(Prompt.inputInt("번호? "));
    ask.setAskTitle(Prompt.inputString("제목? "));
    ask.setAskContent(Prompt.inputString("내용? "));
    ask.setAskWriter(Prompt.inputString("작성자? "));
    ask.setAskRegisteredDate(new Date(System.currentTimeMillis()));

    askBoardList.add(ask);
  }

  public void list() {
    System.out.println("[문의사항 목록]");

    AskBoard[] asks = new AskBoard[askBoardList.size()];

    askBoardList.toArray(asks);

    for (AskBoard ask : asks) {
      System.out.printf("%d, %s, %s, %s, %d\n", 
          ask.getAskNo(), 
          ask.getAskTitle(), 
          ask.getAskWriter(),
          ask.getAskRegisteredDate(),
          ask.getAskVeiwCount());
    }
  }

  public void detail() {
    System.out.println("[문의사항 상세보기]");
    int askNo = Prompt.inputInt("번호? ");

    AskBoard ask = findByNo(askNo);

    if (ask == null) {
      System.out.println("해당 번호의 문의글이 없습니다.");
      return;
    }

    System.out.printf("제목: %s\n", ask.getAskTitle());
    System.out.printf("내용: %s\n", ask.getAskContent());
    System.out.printf("작성자: %s\n", ask.getAskWriter());
    System.out.printf("등록일: %s\n", ask.getAskRegisteredDate());

    ask.setAskVeiwCount(ask.getAskVeiwCount() + 1);
    System.out.printf("조회수: %d\n", ask.getAskVeiwCount());
  }

  public void update() {
    System.out.println("[문의사항 변경]");
    int askNo = Prompt.inputInt("번호? ");

    AskBoard ask = findByNo(askNo);

    if (ask == null) {
      System.out.println("해당 번호의 문의글이 없습니다.");
      return;
    }

    String askTitle = Prompt.inputString(String.format("제목(%s)? ", ask.getAskTitle()));
    String askContent = Prompt.inputString(String.format("내용(%s)? ", ask.getAskContent()));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("문의글 변경을 취소하였습니다.");
      return;
    }

    ask.setAskTitle(askTitle);
    ask.setAskContent(askContent);
    System.out.println("문의글을 변경하였습니다.");
  }

  public void delete() {
    System.out.println("[문의사항 삭제]");
    int askNo = Prompt.inputInt("번호? ");

    AskBoard ask = findByNo(askNo);

    if (ask == null) {
      System.out.println("해당 번호의 문의글이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("문의글 삭제를 취소하였습니다.");
      return;
    }

    askBoardList.remove(ask);

    System.out.println("문의글을 삭제하였습니다.");
  }

  private AskBoard findByNo(int askNo) {
    AskBoard[] arr = askBoardList.toArray(new AskBoard[0]);
    for (AskBoard ask : arr) {
      if (ask.getAskNo() == askNo) {
        return ask;
      }
    }
    return null;
  }
}







