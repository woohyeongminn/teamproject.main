package com.ogong.pms.handler.myStudy.freeBoard;

import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.FreeBoardDao;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class FreeBoardDetailHandler implements Command {

  FreeBoardDao freeBoardDao;
  PromptFreeBoard promptFreeBoard;
  SqlSession sqlSession;

  public FreeBoardDetailHandler
  (FreeBoardDao freeBoardDao, PromptFreeBoard promptFreeBoard, SqlSession sqlSession) {
    this.freeBoardDao = freeBoardDao;
    this.promptFreeBoard = promptFreeBoard;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 게시글 상세보기");
    System.out.println();

    // inputNo = 내 스터디 상세에서 선택한 스터디 번호
    int inputNo = (int) request.getAttribute("inputNo");

    int inputBoardNo = Prompt.inputInt(" 번호 : ");

    FreeBoard freeBoard = freeBoardDao.findByNo(inputBoardNo, inputNo);

    if (freeBoard == null) {
      System.out.println(" >> 해당 번호의 게시글이 없습니다.\n");
      request.getRequestDispatcher("/myStudy/freeBoardList").forward(request);
      return;
    }

    if (freeBoard.getFreeBoardNo() == inputBoardNo) {
      System.out.printf(" [%s]\n", freeBoard.getFreeBoardTitle());
      System.out.printf(" >> 내용 : %s\n", freeBoard.getFreeBoardContent());
      System.out.printf(" >> 첨부파일 : %s\n", freeBoard.getFileNames());
      System.out.printf(" >> 작성자 : %s\n", freeBoard.getFreeBoardWriter().getPerNickname());
      System.out.printf(" >> 등록일 : %s\n", freeBoard.getFreeBoardRegisteredDate());
      freeBoard.setFreeBoardViewcount(freeBoard.getFreeBoardViewcount() + 1);
      System.out.printf(" >> 조회수 : %d\n", freeBoard.getFreeBoardViewcount());
      promptFreeBoard.printComments(freeBoard); // 댓글호출
    }

    // 게시글 수정,삭제에서 사용
    request.setAttribute("boardNo", inputBoardNo);

    // 댓글 등록,수정,삭제에서 사용
    request.setAttribute("freeBoard", freeBoard);

    System.out.println("\n----------------------");
    System.out.println("1. 수정");
    System.out.println("2. 삭제");
    System.out.println("3. 댓글 등록");

    if (freeBoard.getCountComment() > 0) {
      System.out.println("4. 댓글 수정");
      System.out.println("5. 댓글 삭제");
    }

    System.out.println("0. 이전");
    int selectNo = Prompt.inputInt("선택> ");

    freeBoardDao.updateViewCount(freeBoard, freeBoard.getStudyNo());
    sqlSession.commit();

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

