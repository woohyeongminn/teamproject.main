package com.ogong.pms.handler.myStudy.freeBoard;

import java.util.List;
import com.ogong.pms.dao.FreeBoardDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class FreeBoardListHandler implements Command {

  StudyDao studyDao;
  FreeBoardDao freeBoardDao;

  public FreeBoardListHandler(StudyDao studyDao,FreeBoardDao freeBoardDao) {
    this.studyDao = studyDao;
    this.freeBoardDao = freeBoardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 게시글 목록");
    System.out.println();

    int inputNo = (int) request.getAttribute("inputNo");

    Study myStudy = studyDao.findByNo(inputNo);

    List<FreeBoard> freeBoardList = freeBoardDao.findAll(myStudy.getStudyNo());

    for (FreeBoard freeBoard : freeBoardList) {
      System.out.printf(
          " (%d)\n 제목 : %s\n 내용 : %s\n 첨부파일(%d) : %s\n 작성자 : %s\n 조회수 : %s\n 작성일 : %s\n"
              + " 댓글수 : %d\n 좋아요 : %d",
              freeBoard.getFreeBoardNo(), 
              freeBoard.getFreeBoardTitle(),
              freeBoard.getFreeBoardContent(),
              freeBoard.getFileNames(),
              freeBoard.getCountFile(),
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
        //        default :request.getRequestDispatcher("/myStudy/detail").forward(request); return;
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
        //default :request.getRequestDispatcher("/myStudy/detail").forward(request); return;
      }
    }
  }
}


