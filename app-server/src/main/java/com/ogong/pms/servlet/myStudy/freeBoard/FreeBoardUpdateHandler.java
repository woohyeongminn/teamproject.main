package com.ogong.pms.handler.myStudy.freeBoard;

import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.FreeBoardDao;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class FreeBoardUpdateHandler implements Command {

  FreeBoardDao freeBoardDao;
  PromptFreeBoard promptFreeBoard;
  SqlSession sqlSession;

  public FreeBoardUpdateHandler(FreeBoardDao freeBoardDao, PromptFreeBoard promptFreeBoard, SqlSession sqlSession) {
    this.freeBoardDao = freeBoardDao;
    this.promptFreeBoard = promptFreeBoard;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 게시글 수정");
    System.out.println();

    // inputNo = 내 스터디 상세에서 선택한 스터디 번호
    int inputNo = (int) request.getAttribute("inputNo");

    int inputBoardNo = (int) request.getAttribute("boardNo");

    FreeBoard freeBoard = freeBoardDao.findByNo(inputBoardNo, inputNo);

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

    System.out.println("1. 제목");
    System.out.println("2. 내용");
    System.out.println("3. 첨부파일 추가");
    if (!freeBoard.getFreeBoardFile().isEmpty()) {
      System.out.println("4. 첨부파일 삭제");
    }
    System.out.println("0. 이전");
    System.out.println();
    int selectNo = Prompt.inputInt(" 수정하고 싶은 정보를 선택해 주세요. > ");

    switch (selectNo) {
      case 1:
        String freeBoardTitle = Prompt.inputString(" 제목(" + freeBoard.getFreeBoardTitle()  + ") : ");
        freeBoard.setFreeBoardTitle(freeBoardTitle);
        freeBoardDao.updateTitle(freeBoard);
        sqlSession.commit();
        break;
      case 2:
        String freeBoardContent = Prompt.inputString(" 내용(" + freeBoard.getFreeBoardContent() + ") : ");
        freeBoard.setFreeBoardContent(freeBoardContent);
        freeBoardDao.updateContent(freeBoard);
        sqlSession.commit();
        break;
      case 3:
        promptFreeBoard.addFile(freeBoard);
        break;
      case 4:
        // 첨부파일 각각 하나씩 삭제
        promptFreeBoard.deleteFile(freeBoard);
        break;
    }

    System.out.println("\n >> 게시글을 수정하였습니다.");
    request.getRequestDispatcher("/myStudy/freeBoardList").forward(request);
    return;
  }
}