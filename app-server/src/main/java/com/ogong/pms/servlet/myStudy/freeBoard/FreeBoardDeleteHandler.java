package com.ogong.pms.handler.myStudy.freeBoard;

import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.FreeBoardDao;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class FreeBoardDeleteHandler implements Command {

  FreeBoardDao freeBoardDao;
  SqlSession sqlSession;

  public FreeBoardDeleteHandler(FreeBoardDao freeBoardDao, SqlSession sqlSession) {
    this.freeBoardDao = freeBoardDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 게시글 삭제");
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
      System.out.println(" >> 삭제 권한이 없습니다.");
      request.getRequestDispatcher("/myStudy/freeBoardList").forward(request);
      return;
    }

    if (!freeBoard.getFreeBoardFile().isEmpty()) {
      System.out.println(" >> 게시글의 첨부파일이 모두 삭제됩니다.");
    }

    String input = Prompt.inputString(" 정말 삭제하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 삭제를 취소하였습니다.");
      request.getRequestDispatcher("/myStudy/freeBoardList").forward(request);
      return;
    }

    System.out.println(freeBoard.getFreeBoardNo());
    System.out.println(freeBoard.getStudyNo());

    try {
      freeBoardDao.deleteComment(freeBoard.getFreeBoardNo());
      freeBoardDao.deleteFile(freeBoard.getFreeBoardNo());
      freeBoardDao.delete(freeBoard.getFreeBoardNo(), freeBoard.getStudyNo());
      sqlSession.commit();
    } catch (Exception e) {
      sqlSession.rollback();
    }

    System.out.println(" >> 게시글이 삭제되었습니다.");
    request.getRequestDispatcher("/myStudy/freeBoardList").forward(request);
    return;
  }
}


