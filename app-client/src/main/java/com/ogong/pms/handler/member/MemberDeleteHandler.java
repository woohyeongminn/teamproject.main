package com.ogong.pms.handler.member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import com.ogong.menu.Menu;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class MemberDeleteHandler implements Command {

  RequestAgent requestAgent;

  public MemberDeleteHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  // 개인
  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println();
    System.out.println("▶ 회원 탈퇴");
    System.out.println();

    if (AuthPerMemberLoginHandler.getLoginUser() == null) {
      System.out.println(" >> 로그인 하세요.");
      return;
    }

    int no;
    try {
      no = (int)request.getAttribute("memberNo");
    } catch (NullPointerException e) {
      no = AuthPerMemberLoginHandler.getLoginUser().getPerNo();
    }

    HashMap<String,String> params = new HashMap<>();
    params.put("memberNo", String.valueOf(no));

    requestAgent.request("member.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    Member member = requestAgent.getObject(Member.class);

    System.out.println(" << 이메일 확인 >>");
    String inputEmail = Prompt.inputString(" 이메일을 입력하세요 : ");

    if (!inputEmail.equals(member.getPerEmail())) {
      System.out.println();
      System.out.println(" >> 이메일이 일치하지 않습니다.");
      return;
    }

    System.out.println();
    System.out.println(" << 비밀번호 확인 >>");
    String inputPassword = Prompt.inputString(" 비밀번호를 입력하세요 : ");

    if (!inputPassword.equals(member.getPerPassword())) {
      System.out.println();
      System.out.println(" >> 비밀번호가 일치하지 않습니다.");
      return;
    }

    System.out.println();
    String input = Prompt.inputString(" 정말 탈퇴하시겠습니까? (네 /아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 회원 탈퇴를 취소하였습니다.");
      return;
    }

    requestAgent.request("study.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 해당 스터디가 없습니다.");
      return;
    }

    Collection<Study> studyList = requestAgent.getObjects(Study.class);
    List<Study> s = new ArrayList<>(studyList);

    for (int i = s.size() -1; i >= 0; i--) {
      if (s.get(i).getOwner().getPerNo() == member.getPerNo()) {

        HashMap<String,String> studyParams = new HashMap<>();
        studyParams.put("studyNo", String.valueOf(s.get(i).getStudyNo()));

        requestAgent.request("study.delete", studyParams);

        if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
          System.out.println(" >> 스터디 삭제가 실패되었습니다.");
          return;
        }
      }
    }

    requestAgent.request("member.delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("회원 삭제 실패!");
      System.out.println(requestAgent.getObject(String.class));
      return;
    }
    AuthPerMemberLoginHandler.loginUser = null;
    AuthPerMemberLoginHandler.accessLevel = Menu.LOGOUT;
    System.out.println();
    System.out.println(" >> 회원 탈퇴를 완료하였습니다.");
    return;
  }
}