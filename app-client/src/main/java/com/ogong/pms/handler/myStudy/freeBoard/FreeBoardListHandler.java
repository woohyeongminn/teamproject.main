package com.ogong.pms.handler.myStudy.freeBoard;

import java.util.List;
import com.ogong.pms.dao.FreeBoardDao;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class FreeBoardListHandler implements Command {

  FreeBoardDao freeBoardDao;

  public FreeBoardListHandler(FreeBoardDao freeBoardDao) {
    this.freeBoardDao = freeBoardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 게시글 목록");
    System.out.println();

    // inputNo = 내 스터디 상세에서 선택한 스터디 번호
    int inputNo = (int) request.getAttribute("inputNo");

    List<FreeBoard> freeBoardList = freeBoardDao.findAll(inputNo);

    for (FreeBoard freeBoard : freeBoardList) {

      System.out.printf(
          " (%d) 제목 : %s | 내용 : %s | 첨부파일(%d) : %s | 작성자 : %s | 조회수 : %d | 작성일 : %s |"
              + " 댓글수 : %d | 좋아요 : %d\n",
              freeBoard.getFreeBoardNo(), 
              freeBoard.getFreeBoardTitle(),
              freeBoard.getFreeBoardContent(),
              freeBoard.getCountFile(),
              freeBoard.getFileNames(),
              freeBoard.getFreeBoardWriter().getPerNickname(),
              freeBoard.getFreeBoardViewcount(),
              freeBoard.getFreeBoardRegisteredDate(),
              freeBoard.getCountComment(),
              freeBoard.getCountLike());
      System.out.println();
    }

    if (!freeBoardList.isEmpty()) {
      System.out.println("---------------------");
      System.out.println("1. 상세");
      System.out.println("2. 등록");
      System.out.println("0. 이전");
      int selete = Prompt.inputInt("선택> ");
      switch (selete) {
        case 1 :request.getRequestDispatcher("/myStudy/freeBoardDetail").forward(request); return;
        case 2 : request.getRequestDispatcher("/myStudy/freeBoardAdd").forward(request); return;
        default : return;
      }
    }

    if (freeBoardList.isEmpty()) {
      System.out.println(" >> 등록된 게시글이 없습니다");
      System.out.println("---------------------");
      System.out.println("1. 등록");
      System.out.println("0. 이전");
      int selete = Prompt.inputInt("선택> ");
      switch (selete) {
        case 1 : request.getRequestDispatcher("/myStudy/freeBoardAdd").forward(request); return;
        default : return;
      }
    }
  }
}