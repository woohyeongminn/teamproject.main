package com.ogong.pms.handler.myStudy.freeBoard;

import java.util.List;
import com.ogong.pms.dao.FreeBoardDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class FreeBoardDetailHandler implements Command {

  StudyDao studyDao;
  FreeBoardDao freeBoardDao;
  PromptFreeBoard promptFreeBoard;

  public FreeBoardDetailHandler(StudyDao studyDao, PromptFreeBoard promptFreeBoard, FreeBoardDao freeBoardDao) {
    this.studyDao = studyDao;
    this.promptFreeBoard = promptFreeBoard;
    this.freeBoardDao = freeBoardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 게시글 상세보기");
    System.out.println();

    int inputNo = (int) request.getAttribute("inputNo");

    Study myStudy = studyDao.findByNo(inputNo);

    List<FreeBoard> freeBoardList = freeBoardDao.findAll(myStudy.getStudyNo());

    if (freeBoardList.isEmpty()) {
      System.out.println("자유게시판 게시글 목록이 없습니다!");
      return;
    }

    int inputBoardNo = Prompt.inputInt(" 번호 : ");

    FreeBoard freeBoard = freeBoardDao.findByNo(inputBoardNo, myStudy.getStudyNo());

    if (freeBoard == null) {
      System.out.println(" >> 해당 번호의 게시글이 없습니다.\n");
      return;
    }

    if (freeBoard.getFreeBoardNo() == inputBoardNo) {
      System.out.printf(" [%s]\n", freeBoard.getFreeBoardTitle());
      System.out.printf(" >> 내용 : %s\n", freeBoard.getFreeBoardContent());
      System.out.printf(" >> 첨부파일 : %s\n", freeBoard.getFileNames());
      System.out.printf(" >> 작성자 : %s\n", freeBoard.getFreeBoardWriter().getPerNickname());
      System.out.printf(" >> 등록일 : %s\n", freeBoard.getFreeBoardRegisteredDate());

      // 조회수 증가 안됨
      freeBoard.setFreeBoardViewcount(freeBoard.getFreeBoardViewcount() + 1);
      System.out.printf(" >> 조회수 : %d\n", freeBoard.getFreeBoardViewcount());
      //promptFreeBoard.printComments(freeBoard); // 댓글호출
    }

    request.setAttribute("boardNo", inputBoardNo);

    System.out.println("\n----------------------");
    System.out.println("1. 수정");
    System.out.println("2. 삭제");
    System.out.println("3. 댓글 등록");
    //if (!freeBoardList.get(arry[1]).getComment().isEmpty()) {
    System.out.println("4. 댓글 수정");
    System.out.println("5. 댓글 삭제");
    //}
    System.out.println("0. 이전");
    int selectNo = Prompt.inputInt("선택> ");
    switch (selectNo) {
      case 1 : request.getRequestDispatcher("/myStudy/freeBoardUpdate").forward(request); return;
      case 2 : request.getRequestDispatcher("/myStudy/freeBoardDelete").forward(request); return;
      case 3 : request.getRequestDispatcher("/myStudy/freeBoard/commentAdd").forward(request); return;
      case 4 : request.getRequestDispatcher("/myStudy/freeBoard/commentUpdate").forward(request); return;
      case 5 : request.getRequestDispatcher("/myStudy/freeBoard/commentDelete").forward(request); return;
      case 0 : request.getRequestDispatcher("/myStudy/freeBoardList").forward(request); return;
      default : return;
    }
  }
}

