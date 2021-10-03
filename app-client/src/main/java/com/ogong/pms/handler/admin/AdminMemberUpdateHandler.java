package com.ogong.pms.handler.admin;

import java.util.HashMap;
import com.ogong.pms.domain.Member;
import com.ogong.pms.handler.AuthAdminLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class AdminMemberUpdateHandler implements Command {

  RequestAgent requestAgent;

  public AdminMemberUpdateHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 회원 프로필 수정");

    int no = (int) request.getAttribute("memberNo");

    HashMap<String,String> params = new HashMap<>();
    params.put("memberNo", String.valueOf(no));

    requestAgent.request("member.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 해당 회원이 없습니다.");
      return;
    }

    Member user = requestAgent.getObject(Member.class);

    if (user.getPerNickname() != AuthAdminLoginHandler.getLoginAdmin().getMasterNickname()) {

      String perNickName = Prompt.inputString(" 닉네임(" + user.getPerNickname()  + ") : ");
      String perEmail = Prompt.inputString(" 이메일(" + user.getPerEmail() + ") : ");
      String perPassword = Prompt.inputString(" 비밀번호(" + user.getPerPassword() + ") : ");
      String perPhoto = Prompt.inputString(" 사진(" + user.getPerPhoto() + ") : ");

      System.out.println();
      String input = Prompt.inputString(" 정말 변경하시겠습니까? (네 / 아니오) ");
      if (!input.equalsIgnoreCase("네")) {
        System.out.println(" >> 회원 변경을 취소하였습니다.");
        return;
      }

      user.setPerNickname(perNickName);
      user.setPerEmail(perEmail);
      user.setPerPassword(perPassword);
      user.setPerPhoto(perPhoto);

      requestAgent.request("member.update", user);

      System.out.println(" >> 회원 정보를 변경하였습니다.");
      return;
    }
  }

}







