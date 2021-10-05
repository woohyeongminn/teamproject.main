package com.ogong.pms.handler.myStudy.freeBoard;

import java.util.HashMap;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class MyStudyFreeBoardUpdateHandler implements Command {

  RequestAgent requestAgent;

  public MyStudyFreeBoardUpdateHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 게시글 수정");
    System.out.println();

    //    int freeBoardNo = (int) request.getAttribute("FreeBoardNo");
    //    int studyNo = (int) request.getAttribute("studyNo");
    //    int memberNo = (int) request.getAttribute("memberNo");

    HashMap<String,String> paramsFreeBoardNo = new HashMap<>();
    paramsFreeBoardNo.put("FreeBoardNo", String.valueOf(request.getAttribute("FreeBoardNo")));
    paramsFreeBoardNo.put("studyNo", String.valueOf(request.getAttribute("studyNo")));
    paramsFreeBoardNo.put("memberNo", String.valueOf(request.getAttribute("memberNo")));

    requestAgent.request("study.freeBoard.selectOne", paramsFreeBoardNo);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("내 스터디 자유게시판 상세 실패!");
      return;
    }

    Study myStudy = requestAgent.getObject(Study.class);
    FreeBoard detailFreeBoard = requestAgent.getObject(FreeBoard.class);

    if (detailFreeBoard.getFreeBoardWriter().getPerNo() != AuthPerMemberLoginHandler.getLoginUser().getPerNo()) {
      System.out.println(" >> 수정 권한이 없습니다.");
      return;
    }

    String freeBoardTitle = Prompt.inputString(" 제목(" + detailFreeBoard.getFreeBoardTitle()  + ") : ");
    String freeBoardContent = Prompt.inputString(" 내용(" + detailFreeBoard.getFreeBoardContent() + ") : ");
    String freeBoardAtcFile = Prompt.inputString(" 첨부파일(" + detailFreeBoard.getFreeBoardAtcFile() + ") : ");

    String input = Prompt.inputString(" 정말 변경하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 변경을 취소되었습니다.");
      return;
    }

    detailFreeBoard.setFreeBoardTitle(freeBoardTitle);
    detailFreeBoard.setFreeBoardContent(freeBoardContent);
    detailFreeBoard.setFreeBoardAtcFile(freeBoardAtcFile);

    requestAgent.request("study.update", detailFreeBoard);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 게시글 수정 실패!");
      return;
    }

    requestAgent.request("study.freeBoard.update", myStudy);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 게시글 수정 실패!");
      return;
    }

    System.out.println(" >> 게시글을 변경하였습니다.");
  }
}