package com.ogong.pms.handler2;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class MemberUpdateHandler {

  List<Member> memberList;

  public MemberUpdateHandler(List<Member> memberList) {
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

  public void update() {
    System.out.println();
    System.out.println("▶ 프로필 수정");

    Member member = LoginHandler.getLoginUser();

    String perNickName = Prompt.inputString("닉네임(" + member.getPerNickname()  + ") : ");
    String perEmail = Prompt.inputString("이메일(" + member.getPerEmail() + ") : ");
    String perPassword = Prompt.inputString("암호(" + member.getPerPassword() + ") : ");
    String perPhoto = Prompt.inputString("사진(" + member.getPerPhoto() + ") : ");

    String input = Prompt.inputString("정말 변경하시겠습니까? (네 / 아니오) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("개인회원 변경을 취소하였습니다.");
      return;
    }

    member.setPerNickname(perNickName);
    member.setPerEmail(perEmail);
    member.setPerPassword(perPassword);
    member.setPerPhoto(perPhoto);

    System.out.println("개인회원 정보를 변경하였습니다.");
  }
}







