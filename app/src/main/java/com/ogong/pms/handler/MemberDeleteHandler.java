package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class MemberDeleteHandler extends AbstractMemberHandler {

  PromptPerMember promptPerMember; 

  public MemberDeleteHandler(List<Member> memberList, PromptPerMember promptPerMember) {
    super(memberList);
    this.promptPerMember = promptPerMember;
  }

  // 개인
  @Override
  public void execute() {

    //    for (Member member : memberList) {
    //      if (member.getPerNickname().equals(AuthPerMemberLoginHandler.getLoginUser().getPerNickname())) {

    System.out.println();
    System.out.println("▶ 회원 탈퇴");

    System.out.println("<본인 확인>");
    String inputEmail = Prompt.inputString("이메일 입력하세요 : ");
    Member member = promptPerMember.getMemberByPerEmail(inputEmail);
    if (member == null) {
      System.out.println("이메일을 다시 입력해주세요.");
      return;
    }

    String input = Prompt.inputString("정말 탈퇴하시겠습니까? (네 /아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println("회원 탈퇴를 취소하였습니다.");
      return;
    } else {
      String inputPW = Prompt.inputString("비밀번호를 입력하세요 : ");
      if (member.getPerPassword().equals(inputPW)) {
        memberList.remove(member);
        AuthPerMemberLoginHandler.loginUser = null;
        System.out.println("회원이 탈퇴되었습니다.");
        return;
      }
      //        }
      //      } 
      //      else if (!member.getPerNickname().equals(
      //          AuthAdminLoginHandler.getLoginAdmin().getMasterNickname())) {
      //
      //        System.out.println();
      //        System.out.println("▶ 회원 삭제");
      //
      //        String selectMember = Prompt.inputString("회원을 선택하세요. ");
      //
      //        member = promptPerMember.getMemberByPerNick(selectMember);
      //
      //        String input = Prompt.inputString("정말 탈퇴시키겠습니까? (네 /아니오) ");
      //
      //        if (!input.equalsIgnoreCase("네")) {
      //          System.out.println("회원 삭제를 취소하였습니다.");
      //          return;
      //        }
      //
      //        memberList.remove(member);
      //        AuthPerMemberLoginHandler.loginUser = null;
      //        System.out.println("회원이 삭제되었습니다.");
      //        return;
      //      }

    }
  }
}
