package com.ogong.pms.handler.myStudy.freeBoard;

import java.util.List;
import com.ogong.pms.dao.FreeBoardDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class FreeBoardDeleteHandler implements Command {

  StudyDao studyDao;
  FreeBoardDao freeBoardDao;

  public FreeBoardDeleteHandler(StudyDao studyDao, FreeBoardDao freeBoardDao) {
    this.studyDao = studyDao;
    this.freeBoardDao = freeBoardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 게시글 삭제");
    System.out.println();

    // 게시글 데이터 가져오기---
    int inputNo = (int) request.getAttribute("inputNo");

    Study myStudy = studyDao.findByNo(inputNo);

    List<FreeBoard> freeBoardList = freeBoardDao.findAll(myStudy.getStudyNo());

    if (freeBoardList.isEmpty()) {
      System.out.println("자유게시판 게시글 목록이 없습니다!");
      return;
    }

    int inputBoardNo = (int) request.getAttribute("boardNo");

    FreeBoard freeBoard = freeBoardDao.findByNo(inputBoardNo, myStudy.getStudyNo());

    if (freeBoard == null) {
      System.out.println(" >> 해당 번호의 게시글이 없습니다.\n");
      return;
    }
    //-----------------------------

    if (freeBoard.getFreeBoardWriter().getPerNo() != AuthPerMemberLoginHandler.getLoginUser().getPerNo()) {
      System.out.println(" >> 삭제 권한이 없습니다.");
      request.getRequestDispatcher("/myStudy/freeBoardList").forward(request);
      return;
    }

    String input = Prompt.inputString(" 정말 삭제하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 삭제를 취소하였습니다.");
      request.getRequestDispatcher("/myStudy/freeBoardList").forward(request);
      return;
    }

    freeBoardDao.deleteFile(freeBoard.getFreeBoardNo()/*,파일번호 */);
    freeBoardDao.delete(freeBoard.getFreeBoardNo(), myStudy.getStudyNo());

    System.out.println(" >> 게시글이 삭제되었습니다.");
    request.getRequestDispatcher("/myStudy/freeBoardList").forward(request);
    return;
  }
}


