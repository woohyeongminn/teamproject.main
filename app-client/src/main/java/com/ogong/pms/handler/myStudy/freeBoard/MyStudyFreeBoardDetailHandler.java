package com.ogong.pms.handler.myStudy.freeBoard;

import java.util.HashMap;
import java.util.List;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class MyStudyFreeBoardDetailHandler implements Command {

  RequestAgent requestAgent;

  public MyStudyFreeBoardDetailHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 게시글 상세보기");
    System.out.println();

    HashMap<String,String> params = new HashMap<>();
    params.put("studyNo",String.valueOf(request.getAttribute("inputNo")));

    requestAgent.request("study.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 스터디 상세 오류.");
      return;
    }

    Study myStudy = requestAgent.getObject(Study.class);

    List<FreeBoard> freeBoardList = myStudy.getMyStudyFreeBoard();

    if (freeBoardList.isEmpty()) {
      System.out.println("자유게시판 게시글 목록이 없습니다!");
      return;
    }

    int inputNo = Prompt.inputInt(" 번호 : ");
    System.out.println();

    FreeBoard freeBoard = null;
    for (FreeBoard myfreeBoard : freeBoardList) {
      if (myfreeBoard.getFreeBoardNo() == inputNo) {
        freeBoard = myStudy.findFreeBoardByNo(inputNo);
      }
    }

    if (freeBoard == null) {
      System.out.println(" >> 해당 번호의 게시글이 없습니다.\n");
      return;
    }

    System.out.printf(" [%s]\n", freeBoard.getFreeBoardTitle());
    System.out.printf(" >> 내용 : %s\n", freeBoard.getFreeBoardContent());
    System.out.printf(" >> 첨부파일 : %s\n", freeBoard.getFreeBoardAtcFile());
    System.out.printf(" >> 작성자 : %s\n", freeBoard.getFreeBoardWriter().getPerNickname());
    System.out.printf(" >> 등록일 : %s\n", freeBoard.getFreeBoardRegisteredDate());
    freeBoard.setFreeBoardViewcount(freeBoard.getFreeBoardViewcount() + 1);
    System.out.printf(" >> 조회수 : %d\n", freeBoard.getFreeBoardViewcount());

    //listComment(free); // 댓글호출

    request.setAttribute("freeBoardNo", freeBoard.getFreeBoardNo());

    System.out.println("\n----------------------");
    System.out.println("1. 수정");
    System.out.println("2. 삭제");
    System.out.println("3. 댓글 등록");
    System.out.println("4. 댓글 수정");
    System.out.println("5. 댓글 삭제");
    System.out.println("0. 이전");
    int selectNo = Prompt.inputInt("선택> ");
    switch (selectNo) {
      case 1 : request.getRequestDispatcher("/myStudy/freeBoardUpdate").forward(request); return;
      case 2 : request.getRequestDispatcher("/myStudy/freeBoardDelete").forward(request); return;
      //      case 3 : addComment(free, commentList); break;
      //      case 4 : updateComment(); break;
      //      case 5 : deleteComment(free); break;
      case 0 : return;
      default : 
    }


  }
}

