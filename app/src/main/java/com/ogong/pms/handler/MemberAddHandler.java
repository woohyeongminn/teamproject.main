package com.ogong.pms.handler;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class MemberAddHandler extends AbstractMemberHandler {

  public MemberAddHandler(List<Member> memberList) {
    super(memberList);

    Member testMember = new Member();
    testMember.setPerNo(1);
    testMember.setPerNickname("초보초보쌩초보");
    testMember.setPerEmail("naver");
    testMember.setPerPassword("1111");
    testMember.setPerPhoto("jpg");
    testMember.setPerRegisteredDate(new Date(System.currentTimeMillis()));
    testMember.setPerMyStudy(new ArrayList<>());
    memberList.add(testMember);

    testMember = new Member();
    testMember.setPerNo(2);
    testMember.setPerNickname("미술부장");
    testMember.setPerEmail("gmail");
    testMember.setPerPassword("1111");
    testMember.setPerPhoto("jpg");
    testMember.setPerRegisteredDate(new Date(System.currentTimeMillis()));
    testMember.setPerMyStudy(new ArrayList<>());
    memberList.add(testMember);

    testMember = new Member();
    testMember.setPerNo(3);
    testMember.setPerNickname("코딩부장");
    testMember.setPerEmail("kakao");
    testMember.setPerPassword("1111");
    testMember.setPerPhoto("jpg");
    testMember.setPerRegisteredDate(new Date(System.currentTimeMillis()));
    testMember.setPerMyStudy(new ArrayList<>());
    memberList.add(testMember);

    testMember = new Member();
    testMember.setPerNo(4);
    testMember.setPerNickname("음악대장");
    testMember.setPerEmail("daum");
    testMember.setPerPassword("1111");
    testMember.setPerPhoto("jpg");
    testMember.setPerRegisteredDate(new Date(System.currentTimeMillis()));
    testMember.setPerMyStudy(new ArrayList<>());
    memberList.add(testMember);

    testMember = new Member();
    testMember.setPerNo(5);
    testMember.setPerNickname("엄강사님");
    testMember.setPerEmail("hanmail");
    testMember.setPerPassword("1111");
    testMember.setPerPhoto("jpg");
    testMember.setPerRegisteredDate(new Date(System.currentTimeMillis()));
    testMember.setPerMyStudy(new ArrayList<>());
    memberList.add(testMember);

    testMember = new Member();
    testMember.setPerNo(6);
    testMember.setPerNickname("매니저님");
    testMember.setPerEmail("nate");
    testMember.setPerPassword("1111");
    testMember.setPerPhoto("jpg");
    testMember.setPerRegisteredDate(new Date(System.currentTimeMillis()));
    testMember.setPerMyStudy(new ArrayList<>());
    memberList.add(testMember);
  }

  // 개인
  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 회원가입");

    Member member = new Member();

    member.setPerNickname(Prompt.inputString("닉네임 : "));
    member.setPerEmail(Prompt.inputString("이메일 : "));
    member.setPerPassword(Prompt.inputString("비밀번호 : "));
    while (true) {
      String pw =  Prompt.inputString("비밀번호 확인 : ");
      if (!pw.equals(member.getPerPassword())) {
        System.out.println(" >확인 실패");
        continue;
      } else {
        System.out.println(" >확인 완료!");
      }
      break;
    }
    member.setPerPhoto(Prompt.inputString("사진 : "));
    member.setPerMyStudy(new ArrayList<>());
    memberList.add(member);
    System.out.println();
    System.out.println("회원가입이 완료되었습니다.");
  }
}







