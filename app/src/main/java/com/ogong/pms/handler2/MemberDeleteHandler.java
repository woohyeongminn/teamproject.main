package com.ogong.pms.handler2;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class MemberDeleteHandler {

  List<Member> memberList;

  public MemberDeleteHandler(List<Member> memberList) {
    this.memberList = memberList;

    Member testMember = new Member();
    testMember.setPerNickname("초보초보쌩초보");
    testMember.setPerEmail("naver");
    testMember.setPerPassword("1111");
    testMember.setPerPhoto("jpg");
    testMember.setPerRegisteredDate(new Date(System.currentTimeMillis()));
    memberList.add(testMember);

    testMember = new Member();
    testMember.setPerNickname("미술부장");
    testMember.setPerEmail("gmail");
    testMember.setPerPassword("1111");
    testMember.setPerPhoto("jpg");
    testMember.setPerRegisteredDate(new Date(System.currentTimeMillis()));

    memberList.add(testMember);
    testMember = new Member();
    testMember.setPerNickname("코딩부장");
    testMember.setPerEmail("kakao");
    testMember.setPerPassword("1111");
    testMember.setPerPhoto("jpg");
    testMember.setPerRegisteredDate(new Date(System.currentTimeMillis()));
    memberList.add(testMember);

    testMember = new Member();
    testMember.setPerNickname("음악대장");
    testMember.setPerEmail("daum");
    testMember.setPerPassword("1111");
    testMember.setPerPhoto("jpg");
    testMember.setPerRegisteredDate(new Date(System.currentTimeMillis()));
    memberList.add(testMember);

    memberList.add(testMember);
    testMember = new Member();
    testMember.setPerNickname("엄강사님");
    testMember.setPerEmail("hanmail");
    testMember.setPerPassword("1111");
    testMember.setPerPhoto("jpg");
    testMember.setPerRegisteredDate(new Date(System.currentTimeMillis()));
    memberList.add(testMember);

    testMember = new Member();
    testMember.setPerNickname("매니저님");
    testMember.setPerEmail("nate");
    testMember.setPerPassword("1111");
    testMember.setPerPhoto("jpg");
    testMember.setPerRegisteredDate(new Date(System.currentTimeMillis()));
    memberList.add(testMember);
  }

  // 개인
  public void delete() {
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
        LoginHandler.loginUser = null;
        System.out.println("회원이 탈퇴되었습니다.");
        return;
      }
    }

  }

  public void findEmail() {
    System.out.println();
    System.out.println("▶ 이메일 찾기");
    while (true) {
      String inputNick =  Prompt.inputString("닉네임 : ");
      Member member = findByNick(inputNick);
      if (member == null) {
        System.out.println("해당 닉네임이 존재하지 않습니다.");
        continue;
      } else {
        System.out.printf("%s님의 이메일 >> ", member.getPerNickname());
        System.out.println(member.getPerEmail());
      }
      break;
    }
    String input = Prompt.inputString("비밀번호 찾기로 넘어가시겠습니까? (네 / 아니오) ");
    if (input.equalsIgnoreCase("아니오") || input.length() == 0) {
      System.out.println("찾기를 종료합니다.");
      return;
    } 
    findPw();
  }

  public void findPw() {
    System.out.println();
    System.out.println("▶ 임시 비밀번호 발급");
    while (true) {
      String inputEmail =  Prompt.inputString("이메일 : ");
      Member member = findByEmail(inputEmail);
      if (member == null) {
        // 엄강사님찬스
        System.out.println("해당 이메일이 존재하지 않습니다.");
        continue;
      } else {
        System.out.printf("%s님의 임시 비밀번호 : ", member.getPerNickname());
        System.out.println(member.getPerPassword().hashCode());
        System.out.println("로그인 후 비밀번호를 변경해 주세요.");
        String hashPW = String.valueOf(member.getPerPassword().hashCode());
        member.setPerPassword(hashPW);
      }
      break;
    }
  }

  private Member findByNick(String inputNick) {
    for (Member member : memberList) {
      if (inputNick.equals(member.getPerNickname())) {
        return member;
      }
    }
    return null;
  }

  private Member findByEmail(String inputEmail) {
    for (Member member : memberList) {
      if (inputEmail.equals(member.getPerEmail())) {
        return member;
      }
    }
    return null;
  }
}







