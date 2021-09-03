package com.ogong.pms.handler2;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class MemberDetailHandler {

  List<Member> memberList;

  public MemberDetailHandler(List<Member> memberList) {
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

  public void detail() {
    System.out.println();
    System.out.println("▶ 내 프로필");

    //    String inputEmail = Prompt.inputString("이메일 입력하세요 ");
    //    Member member = findByEmail(inputEmail);
    //    if (member == null) {
    //      System.out.println("해당 이메일의 회원이 없습니다.");
    //      return;
    //    }

    try {
      Member member = LoginHandler.getLoginUser();

      System.out.printf("닉네임 : %s\n", member.getPerNickname());
      System.out.printf("이메일 : %s\n", member.getPerEmail());
      System.out.printf("사진 : %s\n", member.getPerPhoto());
      System.out.printf("가입일 : %s\n", member.getPerRegisteredDate());
    } catch (NullPointerException e) {
      System.out.println("로그인 하세요.");
    }
  }
}







