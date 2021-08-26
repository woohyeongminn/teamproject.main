package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class MemberHandler {

  List<Member> memberList;

  public MemberHandler(List memberList) {
    this.memberList = memberList;
  }

  //  public void add() {
  //    System.out.println("[개인 회원가입]");
  //
  //    Member member = new Member();
  //
  //    member.setPerNickname(Prompt.inputString("닉네임? "));
  //    member.setPerEmail(Prompt.inputString("이메일? "));
  //    member.setPerPassword(Prompt.inputString("암호? "));
  //    member.setPerPhoto(Prompt.inputString("사진? "));
  //    member.setPerRegisteredDate(new Date(System.currentTimeMillis()));
  //
  //    memberList.add(member);
  //  }

  public void list() {
    System.out.println("[개인회원 가입확인]");

    Member[] list = memberList.toArray(new Member[0]);

    for (Member member : list) {
      System.out.printf("닉네임 : %s, 이메일 : %s, 가입일 : %s\n",
          member.getPerNickname(), 
          member.getPerEmail(),
          member.getPerRegisteredDate());
    }
  }

  public void detail() {
    System.out.println("[개인회원 상세보기]");
    String peremail = Prompt.inputString("이메이? ");

    Member member = findByEmail(peremail);

    if (member == null) {
      System.out.println("해당 이메일의 회원이 없습니다.");
      return;
    }

    System.out.printf("닉네임: %s\n", member.getPerNickname());
    System.out.printf("이메일: %s\n", member.getPerEmail());
    System.out.printf("사진: %s\n", member.getPerPhoto());
    System.out.printf("등록일: %s\n", member.getPerRegisteredDate());
  }

  public void update() {
    System.out.println("[개인회원 수정하기]");
    String inputEmail = Prompt.inputString("이메일? ");

    Member member = findByEmail(inputEmail);

    if (member == null) {
      System.out.println("해당 이메일의 회원이 없습니다.");
      return;
    }

    String perNickName = Prompt.inputString("닉네임(" + member.getPerNickname()  + ")? ");
    String perEmail = Prompt.inputString("이메일(" + member.getPerEmail() + ")? ");
    String perPassword = Prompt.inputString("암호? ");
    String perPhoto = Prompt.inputString("사진(" + member.getPerNo() + ")? ");

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

  public void delete() {
    System.out.println("[개인회원 탈퇴]");
    String inputEmail = Prompt.inputString("이메일? ");

    Member member = findByEmail(inputEmail);

    if (member == null) {
      System.out.println("해당 이메일의 회원이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 탈퇴하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("회원 탈퇴를 취소하였습니다.");
      return;
    }

    memberList.remove(member);

    System.out.println("회원이 탈퇴되었습니다.");
  }

  //  private Member findByNo(int no) {
  //    Member[] arr = memberList.toArray(new Member[0]);
  //    for (Object obj : arr) {
  //      Member member = (Member) obj;
  //      if (member.getPerno() == no) {
  //        return member;
  //      }
  //    }
  //    return null;
  //  }


  public Member findByEmail(String perEmail) {
    Member[] arr = memberList.toArray(new Member[0]);
    for (Object obj : arr) {
      Member member = (Member) obj;
      if (member.getPerEmail().equals(perEmail)) {
        return member;
      }
    }
    return null;
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







