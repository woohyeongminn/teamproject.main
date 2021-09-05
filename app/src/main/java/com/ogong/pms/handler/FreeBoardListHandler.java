package com.ogong.pms.handler;

import java.util.HashMap;
import java.util.List;
import com.ogong.pms.domain.Comment;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class FreeBoardListHandler extends AbstractFreeBoardHandler {

  HashMap<String, Command> commandMap;

  public FreeBoardListHandler(List<FreeBoard> freeBoardList, List<Member> memberList, List<Comment> commentList, HashMap<String, Command> commandMap) {
    super(freeBoardList, memberList, commentList);
    this.commandMap = commandMap;
  }



  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 게시글 목록");
    System.out.println();

    if (freeBoardList == null ) {
      System.out.println("게시글이 없습니다");
      return;
    }

    // 0905 실행안됨
    //    for() {
    //      if(!member.getPerMyStudy().equals(AuthPerMemberLoginHandler.getLoginUser()getPerMyStudy())) {
    //        System.out.println("가입한 스터디가 없습니다.");
    //        return;
    //      }
    //    }

    for (FreeBoard freeBoard : freeBoardList) {
      System.out.printf(
          "(%d)\n %s\n %s\n 첨부파일 : %s\n %s\n 조회수 : %s\n %s\n",
          freeBoard.getFreeBoardNo(), 
          freeBoard.getFreeBoardTitle(),
          freeBoard.getFreeBoardContent(),
          freeBoard.getFreeBoardAtcFile(),
          freeBoard.getFreeBoardWriter().getPerNickname(),
          freeBoard.getFreeBoardViewcount(),
          freeBoard.getFreeBoardRegisteredDate());
      System.out.println();
    }
    selectUserModifyPage();
  }

  private void selectUserModifyPage() {
    System.out.println("---------------------");
    System.out.println("1. 상세보기");
    System.out.println("2. 수정하기");
    System.out.println("3. 삭제하기");
    System.out.println("0. 뒤로가기");

    int selectAdminNo = Prompt.inputInt("선택> ");
    switch (selectAdminNo) {
      case 1: commandMap.get("/freeBoard/detail").execute(); break;
      case 2: commandMap.get("/freeBoard/update").execute(); break;
      case 3: commandMap.get("/freeBoard/delete").execute(); break;
      default : return;
    }
  }
}

