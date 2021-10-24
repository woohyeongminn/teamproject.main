package com.ogong.pms.handler.admin;

import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.AskBoardDao;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.handler.AuthAdminLoginHandler;
import com.ogong.pms.handler.AuthCeoMemberLoginHandler;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class AdminAskBoardDetailHandler implements Command {

  AskBoardDao askBoardDao; 
  SqlSession sqlSession;
  //CeoMemberDao ceoMemberDao;

  public AdminAskBoardDetailHandler(AskBoardDao askBoardDao, SqlSession sqlSession) {
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
      System.out.println(" >> 해당 번호의 문의글이 없습니다.");
      return;
    }

    // 공개 - 문의글 상태
    if (askBoard.getAskStatus() == 1) {
      detailList(askBoard, request);
    }

    // 비공개 - 문의글 상태
    else if (askBoard.getAskStatus() == 2) {

      // 로그인 안했을 때 (비회원)
      if (AuthPerMemberLoginHandler.getLoginUser() == null &&
          AuthCeoMemberLoginHandler.getLoginCeoMember() == null &&
          AuthAdminLoginHandler.getLoginAdmin() == null) {
        System.out.println(" >> 열람 권한이 없습니다.");
        return;
      }

      // 로그인 했을 때 (개인 로그인)
      if (AuthPerMemberLoginHandler.getLoginUser() != null) {

        // 비공개 - 개인 본인이 작성한 문의글이 아닐 때
        if (AuthPerMemberLoginHandler.getLoginUser().getPerNo() !=
            askBoard.getAskMemberWriter().getPerNo()) {
          System.out.println(" >> 열람 권한이 없습니다.");
          return;
        }

        // 비공개 - 개인 본인이 작성한 문의글일 때
        System.out.println();
        int secretPassword = Prompt.inputInt(" 문의게시글 비밀번호(4자리) : ");

        if (askBoard.getAskTempPW() != secretPassword) {
          System.out.println();
          System.out.println(" >> 비밀번호를 다시 입력하세요.");
          return;
        } 
      }

      // 로그인 했을 때 (사장 로그인)
      if (AuthCeoMemberLoginHandler.getLoginCeoMember() != null) {

        // 비공개 - 사장 본인이 작성한 문의글이 아닐 때
        if (AuthCeoMemberLoginHandler.getLoginCeoMember().getCeoNo() !=
            askBoard.getAskCeoWriter().getCeoNo()) {
          System.out.println(" >> 열람 권한이 없습니다.");
          return;
        }

        // 비공개 - 사장 본인이 작성한 문의글일 때
        System.out.println();
        int secretPassword = Prompt.inputInt(" 문의게시글 비밀번호(4자리) : ");

        if (askBoard.getAskTempPW() != secretPassword) {
          System.out.println();
          System.out.println(" >> 비밀번호를 다시 입력하세요.");
          return;
        }
      }
      detailList(askBoard, request);
    }

    request.setAttribute("askNo", askNo);

    // 관리자가 로그인 했을 때
    if (AuthAdminLoginHandler.getLoginAdmin() != null) {
      System.out.println("\n---------------------");
      System.out.println("1. 문의글 삭제");
      System.out.println("2. 답변 등록");
      System.out.println("0. 뒤로 가기");
      int selectNo = Prompt.inputInt("선택> ");
      switch (selectNo) {
        case 1 : request.getRequestDispatcher("/askBoard/delete").forward(request); return;
        case 2 : request.getRequestDispatcher("/reply/add").forward(request); return;
        default : return;
      }

      // 개인, 사장 회원이 로그인 했을 때
    } else if ((AuthPerMemberLoginHandler.getLoginUser() != null && 
        askBoard.getAskMemberWriter().getPerNo() == 
        AuthPerMemberLoginHandler.getLoginUser().getPerNo()) ||

        (AuthCeoMemberLoginHandler.getLoginCeoMember() != null && 
        askBoard.getAskCeoWriter().getCeoNo() == 
        AuthCeoMemberLoginHandler.getLoginCeoMember().getCeoNo())) {

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
  }

  // 문의게시글 상세 보기
  private void detailList(AskBoard askBoard, CommandRequest request) throws Exception {
    System.out.println();
    System.out.printf(" (%d)\n", askBoard.getAskNo());
    System.out.printf(" [%s]\n", askBoard.getAskTitle());
    System.out.printf(" >> 내용 : %s\n", askBoard.getAskContent());

    String writer = "";
    if (askBoard.getAskMemberWriter().getPerNickname() != null) {
      writer = askBoard.getAskMemberWriter().getPerNickname();
    } else if (askBoard.getAskCeoWriter().getCeoNickname() != null) {
      writer = askBoard.getAskCeoWriter().getCeoNickname();
    }
    System.out.printf(" >> 작성자 : %s\n", writer);
    System.out.printf(" >> 작성일 : %s\n", askBoard.getAskRegisteredDate());
    askBoard.setAskVeiwCount(askBoard.getAskVeiwCount() + 1);
    System.out.printf(" >> 조회수 : %d\n", askBoard.getAskVeiwCount());
    System.out.println("---------------------");

    askBoardDao.updateViewCount(askBoard);
    sqlSession.commit();

    if (askBoard.getReply() == null) {
      System.out.println("\n >> 등록된 답변이 없습니다.");
      return;
    }

    // 답변 상세보기 출력 (답변이 등록되어 있을 때)
    request.setAttribute("askNo", askBoard.getAskNo());
    request.getRequestDispatcher("/reply/detail").forward(request);
  }
}
