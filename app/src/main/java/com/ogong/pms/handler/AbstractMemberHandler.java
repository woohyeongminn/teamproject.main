package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public abstract class AbstractMemberHandler implements Command {

  List<Member> memberList;


  public AbstractMemberHandler(List<Member> memberList) {
    this.memberList = memberList;

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


  public Member findByEmail(String inputEmail) {
    for (Member member : memberList) {
      if (inputEmail.equals(member.getPerEmail())) {
        return member;
      }
    }
    return null;
  }

  protected void findEmail() {
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


  public Member findByNick(String inputNick) {
    for (Member member : memberList) {
      if (inputNick.equals(member.getPerNickname())) {
        return member;
      }
    }
    return null;
  }

  //-------------login에서 사용------------------------------------------------

  protected void selectFindEmailPw() {
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







