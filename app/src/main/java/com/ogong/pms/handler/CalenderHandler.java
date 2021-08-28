package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Calender;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.util.Prompt;

public class CalenderHandler {

  List<Calender> calenderList;

  public CalenderHandler( List<Calender> calenderList) {
    this.calenderList = calenderList;
  }

  public void add() {
    System.out.println("[캘린더 등록]");
    Calender calender = new Calender();

    calender.setMonth(Prompt.inputInt("월 :"));
    calender.setDay(Prompt.inputInt("일 :"));
    calender.setDayOftheWeek(Prompt.inputString("요일 :"));
    calender.setContents(Prompt.inputString("내용 :"));
    calender.setStart(Prompt.inputDate("시작일"));
    calender.setEnd(Prompt.inputDate("종료일"));

    calenderList.add(calender);
  }

  public void list() {
    System.out.println("[자유게시판 게시글 목록]");

    FreeBoard[] list = freeBoardList.toArray(new FreeBoard[0]);

    for (FreeBoard freeBoard : list) {
      System.out.printf(
          "%d, %s, %s, %s, %s, %s\n",
          freeBoard.getFreeBoardNo(), 
          freeBoard.getFreeBoardTitle(),
          freeBoard.getFreeBoardContent(),
          freeBoard.getFreeBoardWriter(),
          freeBoard.getFreeBoardViewcount(),
          freeBoard.getFreeBoardRegisteredDate()
          );
    }
  }

  //------------------------------------------------------------------------------------------------
  public void detail() {
    System.out.println("[자유게시판 게시글 상세보기]");
    int freeNo = Prompt.inputInt("번호? ");

    FreeBoard free = findByNo(freeNo);

    if (free == null) {
      System.out.println("해당 번호의 문의글이 없습니다.");
      return;
    }


    System.out.printf("제목: %s\n", free.getFreeBoardTitle());
    System.out.printf("내용: %s\n", free.getFreeBoardContent());
    System.out.printf("첨부파일: %s\n", free.getFreeBoardAtcFile());
    System.out.printf("작성자: %s\n", free.getFreeBoardWriter());
    System.out.printf("등록일: %s\n", free.getFreeBoardRegisteredDate());
    free.setFreeBoardViewcount(free.getFreeBoardViewcount() + 1);
    System.out.printf("조회수: %d\n", free.getFreeBoardViewcount());
  }

  //------------------------------------------------------------------------------------------------






  public void update() {
    System.out.println("[자유게시판 게시글 수정]");

    int inputNo = Prompt.inputInt("번호? ");
    FreeBoard freeBoard = findByNo(inputNo);

    if (freeBoard == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    String freeBoardTitle = Prompt.inputString("제목(" + freeBoard.getFreeBoardTitle()  + ")? ");
    String freeBoardContent = Prompt.inputString("내용(" + freeBoard.getFreeBoardContent() + ")? ");
    String freeBoardAtcFile = Prompt.inputString("첨부파일(" + freeBoard.getFreeBoardAtcFile() + ")? ");

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("게시글 변경이 취소되었습니다.");
      return;
    }

    freeBoard.setFreeBoardTitle(freeBoardTitle);
    freeBoard.setFreeBoardContent(freeBoardContent);
    freeBoard.setFreeBoardAtcFile(freeBoardAtcFile);

    System.out.println("게시글을 변경하였습니다.");
  }

  public void delete() {
    System.out.println("[자유게시판 게시글 삭제]");

    int inputNo = Prompt.inputInt("번호? ");
    FreeBoard freeBoard = findByNo(inputNo);

    if (freeBoard == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("게시글 삭제를 취소하였습니다.");
      return;
    }

    freeBoardList.remove(freeBoard);

    System.out.println("게시글이 삭제되었습니다.");
  }

  private FreeBoard findByNo(int freeBoardNo) {
    FreeBoard[] arr = freeBoardList.toArray(new FreeBoard[0]);
    for (Object obj : arr) {
      FreeBoard freeBoard = (FreeBoard) obj;
      if (freeBoard.getFreeBoardNo() == freeBoardNo) {
        return freeBoard;
      }
    }
    return null;
  }


}
