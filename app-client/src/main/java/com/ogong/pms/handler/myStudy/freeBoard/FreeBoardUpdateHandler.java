package com.ogong.pms.handler.myStudy.freeBoard;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.FreeBoardDao;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.FreeBoardFile;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class FreeBoardUpdateHandler implements Command {

  FreeBoardDao freeBoardDao;
  SqlSession sqlSession;

  public FreeBoardUpdateHandler(FreeBoardDao freeBoardDao, SqlSession sqlSession) {
    this.freeBoardDao = freeBoardDao;
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

    String freeBoardTitle = Prompt.inputString(" 제목(" + freeBoard.getFreeBoardTitle()  + ") : ");
    String freeBoardContent = Prompt.inputString(" 내용(" + freeBoard.getFreeBoardContent() + ") : ");

    // 파일 수정 다시 해야함
    List<FreeBoardFile> fileList = new ArrayList<>();
    String inputFileName = null;

    for (int i = 0; i < freeBoard.getFreeBoardFile().size(); i++) {
      FreeBoardFile fileName = new FreeBoardFile();

      inputFileName =
          Prompt.inputString(" 첨부파일(" + freeBoard.getFreeBoardFile().get(i).getAtcFileName() + ") (삭제:Enter) : ");

      if (inputFileName.equals("")) {
        freeBoard.getFreeBoardFile().remove(i);
        //        freeBoard.setCountFile(freeBoard.getFreeBoardFile().size() - 1);
        //        System.out.println("삭제 후" + freeBoard.getCountFile());
        System.out.println(" >> 첨부파일 삭제가 완료되었습니다.");
      }

      fileName.setAtcFileName(inputFileName);
      fileList.add(fileName);
    }
    freeBoard.setFreeBoardFile(fileList);


    String input = Prompt.inputString("\n 정말 변경하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 변경을 취소되었습니다.");
      return;
    }

    freeBoard.setFreeBoardTitle(freeBoardTitle);
    freeBoard.setFreeBoardContent(freeBoardContent);

//  freeBoardDao.deleteFile(freeBoard.getFreeBoardNo());
  //    freeBoardDao.insertFile(fileName, freeBoard.getFreeBoardNo());
    
    try {
      if (!freeBoard.getFreeBoardFile().isEmpty()) {
        for (FreeBoardFile fileName : freeBoard.getFreeBoardFile()) {
          freeBoardDao.updateFile(fileName, freeBoard.getFreeBoardNo());
          sqlSession.commit();
        }
      }
      freeBoardDao.update(freeBoard, freeBoard.getStudyNo());
      sqlSession.commit();
    } catch (Exception e) {
      sqlSession.rollback();
    }



    System.out.println(" >> 게시글을 수정하였습니다.");
    request.getRequestDispatcher("/myStudy/freeBoardList").forward(request);
    return;
  }
}