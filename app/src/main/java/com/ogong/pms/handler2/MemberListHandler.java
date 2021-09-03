package com.ogong.pms.handler2;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class MemberListHandler {

  List<Member> memberList;

  public MemberListHandler(List<Member> memberList) {
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

  // 관리자
  public void list() {
    System.out.println();
    System.out.println("▶ 회원 가입 확인");

    for (Member member : memberList) {
      System.out.printf("닉네임 : %s\n 이메일 : %s\n 가입일 : %s\n",
          member.getPerNickname(), 
          member.getPerEmail(),
          member.getPerRegisteredDate());
    }
  }
}







