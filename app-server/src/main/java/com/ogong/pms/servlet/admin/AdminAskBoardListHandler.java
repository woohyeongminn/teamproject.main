package com.ogong.pms.servlet.admin;

import java.util.List;
import com.ogong.pms.dao.AskBoardDao;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.domain.CeoMember;
import com.ogong.pms.domain.Member;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;

public class AdminAskBoardListHandler implements Command {

  AskBoardDao askBoardDao;

  public AdminAskBoardListHandler(AskBoardDao askBoardDao) {
    this.askBoardDao = askBoardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 문의사항 목록");
    System.out.println();

    List<AskBoard> askBoardList = askBoardDao.findAll();

    if (askBoardList.isEmpty()) {
      System.out.println(" >> 등록된 글이 없습니다.");
      return;
    }

    for (AskBoard askBoard : askBoardList) {

      // 기업
      if (askBoard.getAskCeoWriter().getCeoStatus() == CeoMember.CEO) {

        System.out.printf("\n (%d)\n 제목 : %s\n 작성자 : %s\n 작성일 : %s\n 조회수 : %d\n", 
            askBoard.getAskNo(), 
            askBoard.getAskTitle(), 
            askBoard.getAskCeoWriter().getCeoNickname(),
            askBoard.getAskRegisteredDate(),
            askBoard.getAskVeiwCount());

        if (askBoard.getReply() == null) {
          System.out.println("\n 📕 > 등록된 답변이 없습니다.");
        } else {
          System.out.println("\n 📖 > 등록된 답변이 있습니다.");
        }
      }

      // 개인
      else if (askBoard.getAskMemberWriter().getPerStatus() == Member.PER) {

        System.out.printf("\n (%d)\n 제목 : %s\n 작성자 : %s\n 작성일 : %s\n 조회수 : %d\n", 
            askBoard.getAskNo(), 
            askBoard.getAskTitle(), 
            askBoard.getAskMemberWriter().getPerNickname(),
            askBoard.getAskRegisteredDate(),
            askBoard.getAskVeiwCount());

        if (askBoard.getReply() == null) {
          System.out.println("\n 📕 > 등록된 답변이 없습니다.");
        } else {
          System.out.println("\n 📖 > 등록된 답변이 있습니다.");
        }
      }

    }
  }
}
