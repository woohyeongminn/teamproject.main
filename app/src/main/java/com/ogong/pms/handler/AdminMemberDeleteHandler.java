package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class AdminMemberDeleteHandler extends AbstractMemberHandler {

  PromptPerMember promptPerMember; 

  public AdminMemberDeleteHandler(List<Member> memberList, PromptPerMember promptPerMember) {
    super(memberList);
    this.promptPerMember = promptPerMember;
  }

  // 개인
  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 회원 삭제");

    for (Member member : memberList) {
      if (member.getPerNickname() != AuthAdminLoginHandler.getLoginAdmin().getMasterNickname()) {

        int inputMemberNo = Prompt.inputInt(" 번호 : ");

        member = promptPerMember.findByMemberNo(inputMemberNo);

        String input = Prompt.inputString(" 정말 탈퇴시키겠습니까? (네 /아니오) ");

        if (!input.equalsIgnoreCase("네")) {
          System.out.println(" >> 회원 삭제를 취소하였습니다.");
          return;
        }

        memberList.remove(member);
        AuthPerMemberLoginHandler.loginUser = null;
        System.out.println(" >> 회원이 삭제되었습니다.");
        return;
      }
    }
  }

}