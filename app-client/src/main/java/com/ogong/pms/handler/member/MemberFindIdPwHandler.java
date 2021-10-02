package com.ogong.pms.handler.member;

import java.util.HashMap;
import com.ogong.pms.domain.Member;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;
import com.ogong.util.RandomPw;
import com.ogong.util.SendMail;

public class MemberFindIdPwHandler implements Command {

  RandomPw randomPw;
  RequestAgent requestAgent;
  //PromptPerMember promptPerMember;

  public MemberFindIdPwHandler(RandomPw randomPw, RequestAgent requestAgent
      /*, PromptPerMember promptPerMember*/) {
    this.randomPw = randomPw;
    this.requestAgent = requestAgent;
    //this.promptPerMember = promptPerMember;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println();
    System.out.println("1. 이메일 찾기");
    System.out.println("2. 비밀번호 찾기");
    int selectNo = Prompt.inputInt("선택> ");
    switch (selectNo) {
      case 1 : wantPerEmail(); break;
      case 2 : wantByPerPw(); break;
      default : return;
    }
  }


  public void wantPerEmail() throws Exception {
    System.out.println();
    System.out.println("▶ 이메일 찾기");

    //    HashMap<String,String> params = new HashMap<>();
    //    params.put("memberNo", String.valueOf(no));
    //
    //    requestAgent.request("member.selectOne", params);
    //    Member member = requestAgent.getObject(Member.class);

    while (true) {
      System.out.println();
      String inputNick =  Prompt.inputString(" 닉네임 : ");

      HashMap<String,String> params = new HashMap<>();
      params.put("memberNick", inputNick);

      requestAgent.request("member.selectOnByNickname", params);

      if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
        Member member = requestAgent.getObject(Member.class);
        System.out.println();
        System.out.printf(" '%s님'의 이메일 >> ", member.getPerNickname());
        System.out.println(member.getPerEmail());

      } else {
        System.out.println(" >> 해당 닉네임이 존재하지 않습니다.");
        return;
      }
      break;
    }

    String input = Prompt.inputString(" 비밀번호 찾기로 넘어가시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 찾기를 종료합니다.");
      return;
    } 
    wantByPerPw();
  }
  //--------------------------------------------------------------------------------------------
  public void wantByPerPw() throws Exception {
    System.out.println();
    System.out.println("▶ 임시 비밀번호 발급");

    SendMail sendMail = new SendMail();

    while (true) {
      System.out.println();
      String inputEmail =  Prompt.inputString(" 이메일 : ");

      HashMap<String,String> params = new HashMap<>();
      params.put("email", inputEmail);

      requestAgent.request("member.selectOneByEmail", params);

      if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
        Member member = requestAgent.getObject(Member.class);
        String pw = randomPw.randomPw();
        member.setPerPassword(pw);
        System.out.println(" >> 처리 중입니다. 잠시만 기다려 주세요.");
        sendMail.sendMail(inputEmail, pw);
        System.out.println();
        System.out.printf(" '%s님'의 임시 비밀번호가 메일로 전송되었습니다.\n", member.getPerNickname());
        System.out.println(" >> 로그인 후 비밀번호를 변경해 주세요.");
        requestAgent.request("member.update", member);
        return;

      } else if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println(" >> 해당 이메일이 존재하지 않습니다.");
        continue;
      }
    }
  }
}
