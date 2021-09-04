package com.ogong.pms.handler2;

import java.util.List;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class MemberDeleteHandler extends AbstractMemberHandler {

  List<Member> memberList;

  public MemberDeleteHandler(List<Member> memberList) {
    super(memberList);
  }

  // 개인
  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 회원 탈퇴");

    //    Member member = LoginHandler.getLoginUser();
    System.out.println("<본인 확인>");
    String inputEmail = Prompt.inputString("이메일 입력하세요 : ");
    Member member = findByEmail(inputEmail);
    if (member == null) {
      System.out.println("이메일을 다시 입력해주세요.");
      return;
    }


    String input = Prompt.inputString("정말 탈퇴하시겠습니까? (네 /아니오) ");
    if (input.equalsIgnoreCase("아니오") || input.length() == 0) {
      System.out.println("회원 탈퇴를 취소하였습니다.");
      return;
    } else {
      String inputPW = Prompt.inputString("비밀번호를 입력하세요 : ");
      if (member.getPerPassword().equals(inputPW)) {
        // memberList에서 현재 로그인된 객체를 삭제
        memberList.remove(member);
        // loginUser 필드 자체를 빈 값으로 초기화(삭제)
        // why? 
        // remove만 하면 다시 로그인 되기 때문에 탈퇴가 아니다. 
        // 새로운 멤버 객체가 로그인하면 다시 로그인유저에 값이 담긴다.
        PerLoginHandler.loginUser = null;
        System.out.println("회원이 탈퇴되었습니다.");
        return;
      }
    }

  }
}







