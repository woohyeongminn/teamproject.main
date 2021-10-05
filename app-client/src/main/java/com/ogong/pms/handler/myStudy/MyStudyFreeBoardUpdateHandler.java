package com.ogong.pms.handler.myStudy;

import java.util.HashMap;
import java.util.List;
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

    // 스터디 상세
    HashMap<String,String> params = new HashMap<>();
    params.put("studyNo",String.valueOf(request.getAttribute("inputNo")));

    requestAgent.request("study.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 스터디 상세 오류.");
      return;
    }

    Study myStudy = requestAgent.getObject(Study.class);

    List<FreeBoard> freeBoardList = myStudy.getMyStudyFreeBoard();

    // 게시글 목록 중 상세
    int no = (int) request.getAttribute("FreeBoardNo");

    HashMap<String,String> paramsFreeBoardNo = new HashMap<>();
    paramsFreeBoardNo.put("FreeBoardNo", String.valueOf(no));

    requestAgent.request("freeBoard.selectOne", paramsFreeBoardNo);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 해당 회원이 없습니다.");
      return;
    }

    FreeBoard detailFreeBoard = requestAgent.getObject(FreeBoard.class);

    for (FreeBoard freeBoard : freeBoardList) {
      if (!freeBoard.getFreeBoardWriter().equals(AuthPerMemberLoginHandler.getLoginUser().getPerNickname())) {
        System.out.println(" >> 수정 권한이 없습니다.");
        return;
      }

      else if (freeBoard.getFreeBoardWriter().equals(AuthPerMemberLoginHandler.getLoginUser().getPerNickname())) {

        String freeBoardTitle = Prompt.inputString(" 제목(" + freeBoard.getFreeBoardTitle()  + ") : ");
        String freeBoardContent = Prompt.inputString(" 내용(" + freeBoard.getFreeBoardContent() + ") : ");
        String freeBoardAtcFile = Prompt.inputString(" 첨부파일(" + freeBoard.getFreeBoardAtcFile() + ") : ");

        String input = Prompt.inputString(" 정말 변경하시겠습니까? (네 / 아니오) ");
        if (!input.equalsIgnoreCase("네")) {
          System.out.println(" >> 변경을 취소되었습니다.");
          return;
        }
        freeBoard.setFreeBoardTitle(freeBoardTitle);
        freeBoard.setFreeBoardContent(freeBoardContent);
        freeBoard.setFreeBoardAtcFile(freeBoardAtcFile);
      }
      return;
    }

    requestAgent.request("study.update", myStudy);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 게시글 수정 실패!");
      return;
    }

    requestAgent.request("freeBoard.update", detailFreeBoard);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 게시글 변경 실패!");
      System.out.println(requestAgent.getObject(String.class));
      return;
    }

    System.out.println(" >> 게시글을 변경하였습니다.");

  }
}