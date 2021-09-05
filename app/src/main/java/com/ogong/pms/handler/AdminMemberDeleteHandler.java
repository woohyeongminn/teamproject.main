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

    //    Member member = LoginHandler.getLoginUser();
    //    System.out.println("<본인 확인>");
    //    String inputEmail = Prompt.inputString("이메일 입력하세요 : ");
    //    Member member = promptPerMember.getMemberByPerEmail(inputEmail);
    //    if (member == null) {
    //      System.out.println("이메일을 다시 입력해주세요.");
    //      return;
    //    }


    //    Member member = promptPerMember.getMemberByPerNick(member.getPerNickname());

    for (Member member : memberList) {
      if (member.getPerNickname() != AuthAdminLoginHandler.getLoginAdmin().getMasterNickname()) {

        //    if (member.getPerNickname() != AuthAdminLoginHandler.getLoginAdmin().getMasterNickname()) {

        String selectMember = Prompt.inputString("회원을 선택하세요. ");

        member = promptPerMember.getMemberByPerNick(selectMember);

        String input = Prompt.inputString("정말 탈퇴시키겠습니까? (네 /아니오) ");

        if (!input.equalsIgnoreCase("네")) {
          System.out.println("회원 삭제를 취소하였습니다.");
          return;
        }

        //      Admin admin = AuthAdminLoginHandler.getLoginAdmin();
        //      Member member = AuthPerMemberLoginHandler.getLoginUser();

        memberList.remove(member);
        AuthPerMemberLoginHandler.loginUser = null;
        System.out.println("회원이 삭제되었습니다.");
        return;
        //      return member;
      }
    }
  }

}







