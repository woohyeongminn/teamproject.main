package com.ogong.pms.handler;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class MemberHandler {

  List<Member> memberList;

  public MemberHandler(List memberList) {
    this.memberList = memberList;
  }

  public void add() {
    System.out.println("[개인 회원가입]");

    Member member = new Member();

    member.setPernickname(Prompt.inputString("닉네임? "));
    member.setPeremail(Prompt.inputString("이메일(아이디)? "));
    member.setPerpassword(Prompt.inputString("암호? "));
    member.setPerphoto(Prompt.inputString("사진? "));
    member.setPerregisteredDate(new Date(System.currentTimeMillis()));

    memberList.add(member);
  }

  public void list() {
    System.out.println("[개인회원 가입확인]");

    Member[] list = memberList.toArray(new Member[0]);

    for (Member member : list) {
      System.out.printf("닉네임 : %s, 이메일 : %s, 가입일 : %s\n",
          member.getPernickname(), 
          member.getPeremail(),
          member.getPerregisteredDate());
    }
  }

  public void detail() {
    System.out.println("[개인회원 상세보기]");
    String peremail = Prompt.inputString("아이디? ");

    Member member = findByEmail(peremail);

    if (member == null) {
      System.out.println("해당 이메일의 회원이 없습니다.");
      return;
    }

    System.out.printf("닉네임: %s\n", member.getPernickname());
    System.out.printf("이메일: %s\n", member.getPeremail());
    System.out.printf("사진: %s\n", member.getPerphoto());
    System.out.printf("등록일: %s\n", member.getPerregisteredDate());
  }

  public void update() {
    System.out.println("[개인회원 정보변경]");
    String peremail = Prompt.inputString("이메일? ");

    Member member = findByEmail(peremail);

    if (member == null) {
      System.out.println("해당 이메일의 회원이 없습니다.");
      return;
    }

    String nickname = Prompt.inputString("닉네임(" + member.getPernickname()  + ")? ");
    String email = Prompt.inputString("이메일(" + member.getPeremail() + ")? ");
    String password = Prompt.inputString("암호? ");
    String photo = Prompt.inputString("사진(" + member.getPerphoto() + ")? ");

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("개인회원 변경을 취소하였습니다.");
      return;
    }

    member.setPernickname(nickname);
    member.setPeremail(email);
    member.setPerpassword(password);
    member.setPerphoto(photo);

    System.out.println("개인회원 정보를 변경하였습니다.");
  }

  public void delete() {
    System.out.println("[개인회원 탈퇴]");
    String peremail = Prompt.inputString("이메일? ");

    Member member = findByEmail(peremail);

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

  private Member findByNo(int no) {
    Member[] arr = memberList.toArray(new Member[0]);
    for (Object obj : arr) {
      Member member = (Member) obj;
      if (member.getPerno() == no) {
        return member;
      }
    }
    return null;
  }


  private Member findByEmail(String peremail) {
    Member[] arr = memberList.toArray(new Member[0]);
    for (Object obj : arr) {
      Member member = (Member) obj;
      if (member.getPeremail().equals(peremail)) {
        return member;
      }
    }
    return null;
  }



  public boolean exist(String nickname) {
    Member[] list = memberList.toArray(new Member[0]);
    for (Member member : list) {
      if (member.getPernickname().equals(nickname)) {
        return true;
      }
    }
    return false;
  }

  public String promptMember(String label) {
    while (true) {
      String owner = Prompt.inputString(label);
      if (this.exist(owner)) {
        return owner;
      } else if (owner.length() == 0) {
        return null;
      }
      System.out.println("등록된 회원이 아닙니다.");
    }
  }

  public String promptMembers(String label) {
    String members = "";
    while (true) {
      String member = Prompt.inputString(label);
      if (this.exist(member)) {
        if (members.length() > 0) {
          members += ",";
        }
        members += member;
        continue;
      } else if (member.length() == 0) {
        break;
      } 
      System.out.println("등록된 회원이 아닙니다.");
    }
    return members;
  }
}







