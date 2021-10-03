package com.ogong.pms.handler.admin;

import java.util.HashMap;
import com.ogong.pms.domain.Member;
import com.ogong.pms.handler.AuthAdminLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class AdminMemberDeleteHandler implements Command {

  RequestAgent requestAgent;

  public AdminMemberDeleteHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  // 개인
  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 회원 삭제");

    int memberNo = (int) request.getAttribute("memberNo");

    HashMap<String,String> params = new HashMap<>();
    params.put("memberNo", String.valueOf(memberNo));

    requestAgent.request("member.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 해당 회원이 없습니다.");
      return;
    }

    Member user = requestAgent.getObject(Member.class);

    if (user.getPerNickname() != AuthAdminLoginHandler.getLoginAdmin().getMasterNickname()) {

      String input = Prompt.inputString(" 정말 탈퇴시키겠습니까? (네 /아니오) ");

      if (!input.equalsIgnoreCase("네")) {
        System.out.println(" >> 회원 삭제를 취소하였습니다.");
        return;
      }
      //      for (int i = studyList.size() - 1; i >= 0; i--) { 
      //        if (studyList.get(i).getOwner().getPerNo() == member.getPerNo()) {
      //          studyList.remove(studyList.get(i));
      //        }
      //      }

      requestAgent.request("member.delete", params);

      //      int no = (int) request.getAttribute("studyNo");
      //
      //      HashMap<String,String> studyparams = new HashMap<>();
      //      params.put("studyNo", String.valueOf(no));
      //
      //      requestAgent.request("study.selectOne", studyparams);
      //
      //      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      //        System.out.println(" >> 해당 스터디가 없습니다.");
      //        return;
      //      }
      //
      //      Collection<Study> studyList = requestAgent.getObjects(Study.class);
      //      List<Study> s = new ArrayList<>(studyList);
      //
      //      //      for (Study userStudy : studyList) {
      //      //        if (userStudy.getOwner().getPerNo() == user.getPerNo()) {
      //      //          requestAgent.request("study.delete", studyparams);
      //      //        }
      //      //      }
      //
      //      for (int i = s.size() -1; i >= 0; i--) {
      //        if (s.get(i).getOwner().getPerNo() == user.getPerNo()) {
      //          requestAgent.request("study.delete", studyparams);
      //        }
      //      }

      System.out.println(" >> 회원이 삭제되었습니다.");
      return;
    }
  }

}