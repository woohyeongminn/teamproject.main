package com.ogong.pms.handler.admin;

import java.util.Collection;
import com.ogong.pms.domain.Member;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;

public class AdminMemberListHandler implements Command {

  RequestAgent requestAgent;

  public AdminMemberListHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  // 관리자
  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 회원 목록");
    System.out.println();

    requestAgent.request("member.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 목록 조회 실패");
      return;
    }

    Collection<Member> memberList = requestAgent.getObjects(Member.class);

    for (Member member : memberList) {
      System.out.printf(" (%d)\n 닉네임 : %s\n 이메일 : %s\n 가입일 : %s\n",
          member.getPerNo(),
          member.getPerNickname(), 
          member.getPerEmail(),
          member.getPerRegisteredDate());
      System.out.println();
    }
  }
}






