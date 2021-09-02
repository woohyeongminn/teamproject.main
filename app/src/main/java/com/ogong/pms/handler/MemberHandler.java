package com.ogong.pms.handler;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class MemberHandler {

  List<Member> memberList;


  public MemberHandler(List<Member> memberList) {
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
    testMember.setPerPassword("2222");
    testMember.setPerPhoto("jpg");
    testMember.setPerRegisteredDate(new Date(System.currentTimeMillis()));

    memberList.add(testMember);
    testMember = new Member();
    testMember.setPerNickname("코딩부장");
    testMember.setPerEmail("kakao");
    testMember.setPerPassword("3333");
    testMember.setPerPhoto("jpg");
    testMember.setPerRegisteredDate(new Date(System.currentTimeMillis()));
    memberList.add(testMember);

    testMember = new Member();
    testMember.setPerNickname("음악대장");
    testMember.setPerEmail("naver");
    testMember.setPerPassword("4444");
    testMember.setPerPhoto("jpg");
    testMember.setPerRegisteredDate(new Date(System.currentTimeMillis()));
    memberList.add(testMember);
  }

  // 개인
  public void add() {
    System.out.println();
    System.out.println("▶ 회원가입");

    Member member = new Member();

    member.setPerNickname(Prompt.inputString("닉네임? "));
    member.setPerEmail(Prompt.inputString("이메일? "));
    member.setPerPassword(Prompt.inputString("비밀번호? "));
    while (true) {
      String pw =  Prompt.inputString("비밀번호 확인? ");
      if (!pw.equals(member.getPerPassword())) {
        System.out.println("비밀번호가 일치하지 않습니다.");
        continue;
      } else {
        System.out.println("확인되었습니다.");
      }
      break;
    }
    member.setPerPhoto(Prompt.inputString("사진? "));
    memberList.add(member);
  }

  // 관리자
  public void list() {
    System.out.println();
    System.out.println("[개인회원 가입확인]");

    for (Member member : memberList) {
      System.out.printf("닉네임 : %s, 이메일 : %s, 가입일 : %s\n",
          member.getPerNickname(), 
          member.getPerEmail(),
          member.getPerRegisteredDate());
    }
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

      System.out.printf("닉네임: %s\n", member.getPerNickname());
      System.out.printf("이메일: %s\n", member.getPerEmail());
      System.out.printf("사진: %s\n", member.getPerPhoto());
      System.out.printf("가입일: %s\n", member.getPerRegisteredDate());
    } catch (NullPointerException e) {
      System.out.println("로그인 하세요.");
    }
  }

  public void update() {
    System.out.println();
    System.out.println("▶ 프로필 수정하기");

    Member member = LoginHandler.getLoginUser();

    String perNickName = Prompt.inputString("닉네임(" + member.getPerNickname()  + ")? ");
    String perEmail = Prompt.inputString("이메일(" + member.getPerEmail() + ")? ");
    String perPassword = Prompt.inputString("암호(" + member.getPerPassword() + ")? ");
    String perPhoto = Prompt.inputString("사진(" + member.getPerPhoto() + ")? ");

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
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

  // 개인
  public void delete() {
    System.out.println();
    System.out.println("▶ 회원 탈퇴");

    //    Member member = LoginHandler.getLoginUser();
    System.out.println("-본인 확인-");
    String inputEmail = Prompt.inputString("이메일 입력하세요 ");
    Member member = findByEmail(inputEmail);
    if (member == null) {
      System.out.println("이메일을 다시 입력해주세요.");
      return;
    }


    String input = Prompt.inputString("정말 탈퇴하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("회원 탈퇴를 취소하였습니다.");
      return;
    } else {
      String inputPW = Prompt.inputString("비밀번호를 입력하세요. ");
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

  public void selectFindEmailPw() {
    System.out.println();
    System.out.println("1. 이메일 찾기");
    System.out.println("2. 비밀번호 찾기");
    int selectNo = Prompt.inputInt("선택> ");
    switch (selectNo) {
      case 1 : findEmail(); break;
      case 2 : findPw(); break;
      default : return;
    }
  }

  public void findEmail() {
    System.out.println();
    System.out.println("☞ 이메일 찾기");
    while (true) {
      String inputNick =  Prompt.inputString("닉네임: ");
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
    String input = Prompt.inputString("비밀번호 찾기로 넘어가시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("찾기를 종료합니다.");
      return;
    } 
    findPw();
  }

  public void findPw() {
    System.out.println();
    System.out.println("☞ 임시 비밀번호 발급");
    while (true) {
      String inputEmail =  Prompt.inputString("이메일: ");
      Member member = findByEmail(inputEmail);
      if (member == null) {
        // 엄강사님찬스
        System.out.println("해당 이메일이 존재하지 않습니다.");
        continue;
      } else {
        System.out.printf("%s님의 임시 비밀번호 >> ", member.getPerNickname());
        System.out.println(member.getPerPassword().hashCode());
        System.out.println("로그인 후 비밀번호를 변경해 주세요.");
        String hashPW = String.valueOf(member.getPerPassword().hashCode());
        member.setPerPassword(hashPW);
      }
      break;
    }
  }

  public Member findByInputEmail(String perEmail) {
    for (Member member : memberList) {
      if (member.getPerEmail().equals(perEmail)) {
        return member;
      }
    }
    return null;
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

  //-------------prompt는 login에서 사용(은채)------------------------------------------------
  public boolean exist(String perEmail) {
    Member[] list = memberList.toArray(new Member[0]);
    for (Member member : list) {
      if (member.getPerEmail().equals(perEmail)) {
        return true;
      }
    }
    return false;
  }

  public String promptMemberEmail(String email) {
    while (true) {
      String emailBox = Prompt.inputString(email);
      if (this.exist(emailBox)) {
        return emailBox;
      } else if (emailBox.length() == 0) {
        return null;
      }
      System.out.println("등록된 회원이 아닙니다.");
    }
  }

  public String promptMemberPassword(String passWord) {
    while (true) {
      String passwordBox = Prompt.inputString(passWord);
      if (this.exist(passwordBox)) {
        return passwordBox;
      } else if (passwordBox.length() == 0) {
        return null;
      }
      System.out.println("등록된 회원이 아닙니다.");
    }
  }
}







