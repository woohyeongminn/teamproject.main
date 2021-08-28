package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Join;
import com.ogong.pms.domain.Login;
import com.ogong.util.Prompt;

public class LoginHandler {

  List<Login> loginList;
  //  <기존 코드>
  //MemberHandler memberHandler;
  JoinHandler joinHandler;

  //   <기존 코드>
  //  public LoginHandler(List<Login> loginList, MemberHandler memberHandler) {
  //    this.loginList = loginList;
  //    this.memberHandler = memberHandler;
  //  }

  //<2021-08-26 : 추가된 코드(woo)>
  public LoginHandler(List<Login> loginList, JoinHandler joinHandler) {
    this.loginList = loginList;
    this.joinHandler = joinHandler;
  }

  //  <기존코드old>
  //  public void addLoginPage() {
  //    String inputEmail = Prompt.inputString("이메일: ");
  //    Member member = memberHandler.findByEmail(inputEmail);
  //    if (member == null) {
  //      System.out.println("등록된 회원이 아닙니다.");
  //      return;
  //    }
  //    while (member != null) {
  //      String inputPassword = Prompt.inputString("비밀번호: ");
  //      if (member.getPerPassword().equals(inputPassword)) {
  //        System.out.println("로그인되었습니다.");
  //        break;
  //      }
  //      System.out.println("비밀번호를 다시 입력하세요.");
  //      continue;
  //    }
  //}

  // <8/26 pm09 기존코드>
  //  public void addLoginPage() {
  //    String inputEmail = Prompt.inputString("이메일: ");
  //    Join join = joinHandler.findByEmail(inputEmail);
  //    if (join == null) {
  //      System.out.println("등록된 회원이 아닙니다.");
  //      return;
  //    }
  //    while (join != null) {
  //      String inputPassword = Prompt.inputString("비밀번호: ");
  //      if (join.getJoinPassword().equals(inputPassword)) {
  //        System.out.println("로그인되었습니다.");
  //        break;
  //      }
  //      System.out.println("비밀번호를 다시 입력하세요.");
  //      continue;
  //    }
  //  }

  //<2021-08-27 : 추가된 코드(song)>
  public Login addLoginPage() {
    Login login = new Login();
    String inputEmail = Prompt.inputString("이메일: ");
    String inputPassword = "";
    Join join = joinHandler.findByEmail(inputEmail);
    if (join == null) {
      System.out.println("등록된 회원이 아닙니다.");
      return login;
    }
    while (join != null) {
      inputPassword = Prompt.inputString("비밀번호: ");
      if (join.getJoinPassword().equals(inputPassword)) {
        login.setUserEmail(inputEmail);
        login.setUserPassword(inputPassword);
        System.out.println("로그인되었습니다.");
        return login;
      }
      System.out.println("비밀번호를 다시 입력하세요.");
      continue;
    } 
    return login;
  }

  public void logOut() {
    System.out.println("-------------------");
    System.out.println("로그아웃 되었습니다.");
  }

  public void findInfo() {
    System.out.println("!!!찾기 페이지 이동합니다.!!!");
  }


}
