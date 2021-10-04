package com.ogong.pms.handler.myStudy;

import java.util.HashMap;
<<<<<<< HEAD
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class MyStudyDeleteHandler implements Command {

  RequestAgent requestAgent;

  public MyStudyDeleteHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 스터디 삭제");

    //String str = (String) request.getAttribute("inputNo");

    int no = (int) request.getAttribute("inputNo");
    HashMap<String,String> params = new HashMap<>();
    params.put("studyNo",String.valueOf(no));

    requestAgent.request("study.selecetOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 스터디를 찾을 수 없습니다.");
      return;
    }

    Study myStudy = requestAgent.getObject(Study.class);

    // 조건내용 조금 수정해서 '해당 번호의 스터디가 없습니다' 출력되도록 수정해야함
    //    if (myStudy == null) {
    //      return;
    //    }

    if (myStudy.getOwner().getPerNo() != AuthPerMemberLoginHandler.getLoginUser().getPerNo()) {
      System.out.println(" >> 삭제 권한이 없습니다.");
      return;
    }

    String input = Prompt.inputString(" 정말 삭제하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 스터디 삭제를 취소하였습니다.");
      return;
    }

    requestAgent.request("study.delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 스터디 삭제 실패.");
      return;
    }
=======
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class MyStudyDeleteHandler implements Command {

  RequestAgent requestAgent;

  public MyStudyDeleteHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 스터디 삭제");

    int inputNo = (int) request.getAttribute("studyNo");

    //Study myStudy = promptStudy.findByMyStudyNo(inputNo);

    HashMap<String,String> params = new HashMap<>();
    params.put("studyNo", String.valueOf(inputNo));

    requestAgent.request("study.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 해당 스터디가 없습니다.");
      return;
    }

    Study myStudy = requestAgent.getObject(Study.class);

    // 로그인 멤버 데이터 요청
    int no = AuthPerMemberLoginHandler.getLoginUser().getPerNo();

    HashMap<String,String> paramsMember = new HashMap<>();
    paramsMember.put("memberNo", String.valueOf(no));

    requestAgent.request("member.selectOne", paramsMember);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    Member member = requestAgent.getObject(Member.class);

    if (myStudy.getOwner().getPerNo() != member.getPerNo()) {
      System.out.println(" >> 삭제 권한이 없습니다.");
      return;
    }

    String input = Prompt.inputString(" 정말 삭제하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 스터디 삭제를 취소하였습니다.");
      return;
    }

    requestAgent.request("study.delete", myStudy);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("스터디 삭제 실패!");
    }

>>>>>>> branch 'main' of https://github.com/woohyeongminn/teamproject.main.git
    System.out.println(" >> 스터디를 삭제하였습니다.");
  }
}