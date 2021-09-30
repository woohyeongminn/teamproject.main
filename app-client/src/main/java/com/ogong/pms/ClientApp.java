package com.ogong.pms;

import java.sql.Date;
import com.ogong.pms.domain.Member;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class ClientApp {

  static RequestAgent requestAgent;

  public static void main(String[] args) throws Exception {
    System.out.println("[오늘의 공부 클라이언트]");

    requestAgent = new RequestAgent("127.0.0.1", 5050);

    while (true) {
      String input = Prompt.inputString("명령> ");

      if (input.equals("/member/add")) {
        addMember();
      }
      else {
        requestAgent.request(input, null);

        if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
          String result = requestAgent.getObject(String.class);
          System.out.println(result);

        } else {
          System.out.println("요청 실패!");
        }
      }

      if (input.equalsIgnoreCase("quit")) {
        System.out.println("서버와 연결 종료"); {
          break;
        }
      }

    }
    requestAgent.close();
  }

  private static void addMember() throws Exception {
    Member member = new Member();
    member.setPerNo(1);
    member.setPerNickname("초보초보쌩초보");
    member.setPerEmail("naver");
    member.setPerPassword("1111");
    member.setPerPhoto("jpg");
    member.setPerRegisteredDate(Date.valueOf("2021-7-7"));

    requestAgent.request("/member/insert", member);

    if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      System.out.println("회원 저장 성공!");

    } else {
      System.out.println("회원 저장 실패!");
    }

  }


}