package com.ogong.pms.handler.Askboard;

import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.AskBoardDao;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.domain.CeoMember;
import com.ogong.pms.handler.AuthCeoMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class AskBoardCeoMyDetailHandler implements Command {

  AskBoardDao askBoardDao;
  SqlSession sqlSession;

  public AskBoardCeoMyDetailHandler(AskBoardDao askBoardDao, SqlSession sqlSession) {
    this.askBoardDao = askBoardDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 문의사항 상세");
    System.out.println();

    int askNo = Prompt.inputInt(" 번호 : ");

    AskBoard askBoard = askBoardDao.findByNo(askNo);

    if (askBoard == null) {
      System.out.println(" >> 해당 번호의 문의글이 없습니다. ");
      return;
    }

    // 공개 - 문의글 상태
    if (askBoard.getAskStatus() == 1) {
      detailList(askBoard, request);
    }

    // 비공개 - 문의글 상태
    else if (askBoard.getAskStatus() == 2) {

      // 비공개 - 개인 본인이 작성한 문의글일 때
      System.out.println();
      int secretPassword = Prompt.inputInt(" 🔑 문의글 비밀번호(4자리) : ");

      if (askBoard.getAskTempPW() != secretPassword) {
        System.out.println();
        System.out.println(" >> 비밀번호를 다시 입력하세요.");
        return;
      } 

      detailList(askBoard, request);
    }

    if (askBoard.getReply() != null) {
      request.setAttribute("askNo", askNo);
      request.getRequestDispatcher("/reply/detail").forward(request); 

    } else {
      System.out.println(" >> 등록된 답변이 없습니다.");
    }

    request.setAttribute("askNo", askNo);
    System.out.println("\n---------------------");
    System.out.println("1. 수정");
    System.out.println("2. 삭제");
    System.out.println("0. 이전");
    int selectNo = Prompt.inputInt("선택> ");
    switch (selectNo) {
      case 1 : request.getRequestDispatcher("/askBoard/update").forward(request); return;
      case 2 : request.getRequestDispatcher("/askBoard/delete").forward(request); return;
      default : return;
    }
  }

  private void detailList(AskBoard askBoard, CommandRequest request) throws Exception {

    CeoMember ceoMember = AuthCeoMemberLoginHandler.getLoginCeoMember();

    if (askBoard.getAskCeoWriter().getCeoNo() != ceoMember.getCeoNo()) {
      System.out.println(" >> 열람 권한이 없습니다.");
      return;
    }

    System.out.println();
    System.out.printf(" (%d)\n", askBoard.getAskNo());
    System.out.printf(" [%s]\n", askBoard.getAskTitle());
    System.out.printf(" >> 내용 : %s\n", askBoard.getAskContent());
    System.out.printf(" >> 작성자 : %s\n", askBoard.getAskCeoWriter().getCeoNickname());
    System.out.printf(" >> 작성일 : %s\n", askBoard.getAskRegisteredDate());
    askBoard.setAskVeiwCount(askBoard.getAskVeiwCount() + 1);
    System.out.printf(" >> 조회수 : %d\n", askBoard.getAskVeiwCount());
    System.out.println("---------------------");

    askBoardDao.updateViewCount(askBoard);
    sqlSession.commit();
  }
}
