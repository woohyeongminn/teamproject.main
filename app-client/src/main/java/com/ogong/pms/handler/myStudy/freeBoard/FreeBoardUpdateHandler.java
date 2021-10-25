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

public class FreeBoardUpdateHandler implements Command {

  StudyDao studyDao;
  FreeBoardDao freeBoardDao;

  public FreeBoardUpdateHandler(StudyDao studyDao, FreeBoardDao freeBoardDao) {
    this.studyDao = studyDao;
    this.freeBoardDao = freeBoardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 게시글 수정");
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
      System.out.println(" >> 수정 권한이 없습니다.");
      request.getRequestDispatcher("/myStudy/freeBoardList").forward(request);
      return;
    }

    String freeBoardTitle = Prompt.inputString(" 제목(" + freeBoard.getFreeBoardTitle()  + ") : ");
    String freeBoardContent = Prompt.inputString(" 내용(" + freeBoard.getFreeBoardContent() + ") : ");



    // 파일 update 메서드 추가해야함
    //    List<FreeBoardFile> fileList = new ArrayList<>();
    //    while (true) {
    //      FreeBoardFile fileName = new FreeBoardFile();
    //      String inputFileName = Prompt.inputString(" 첨부파일(" + freeBoard.getFileNames() + ") / (완료:Enter) : ");
    //
    //      if (inputFileName.equals("")) {
    //        System.out.println(" >> 첨부파일 수정이 완료되었습니다.");
    //        break;
    //      }
    //      fileName.setAtcFileName(inputFileName);
    //      fileList.add(fileName);
    //    }
    //    freeBoard.setFreeBoardFile(fileList);




    String input = Prompt.inputString(" 정말 변경하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 변경을 취소되었습니다.");
      return;
    }

    freeBoard.setFreeBoardTitle(freeBoardTitle);
    freeBoard.setFreeBoardContent(freeBoardContent);
    freeBoard.setFreeBoardNo(freeBoard.getFreeBoardNo());

    freeBoardDao.update(freeBoard, myStudy.getStudyNo());

    System.out.println(" >> 게시글을 수정하였습니다.");
    request.getRequestDispatcher("/myStudy/freeBoardList").forward(request);
    return;
  }
}