package com.ogong.pms.handler.member;

import java.util.Collection;
import java.util.HashMap;
import com.ogong.pms.domain.Member;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class MemberUpdateHandler implements Command {

  RequestAgent requestAgent;

  public MemberUpdateHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 프로필 수정");
    System.out.println();

    int no = (int) request.getAttribute("memberNo");

    requestAgent.request("member.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("목록 조회 실패!");
      return;
    }

    Collection<Member> memberList = requestAgent.getObjects(Member.class);

    HashMap<String,String> params = new HashMap<>();
    params.put("memberNo", String.valueOf(no));

    requestAgent.request("member.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 해당 회원이 없습니다.");
      return;
    }

    Member member = requestAgent.getObject(Member.class);

    System.out.println("1. 닉네임");
    System.out.println("2. 사진");
    System.out.println("3. 이메일");
    System.out.println("4. 비밀번호");
    System.out.println();
    int selectNo = Prompt.inputInt(" 수정하고 싶은 정보를 선택해 주세요. > ");

    String perNickName = member.getPerNickname();
    String perPhoto = member.getPerPhoto();
    String perEmail = member.getPerEmail();
    String perPassword = member.getPerPassword();

    switch (selectNo) {
      case 1: 
        perNickName = Prompt.inputString(" 닉네임(" + member.getPerNickname()  + ") : ");
        while (true) {
          for (Member comparisonMember : memberList) {
            if (perNickName.equals(comparisonMember.getPerNickname())) {
              System.out.println(" >> 이미 사용중인 닉네임입니다.");
              continue;
            }
          }
          break;
        }
        break;

      case 2: 
        perPhoto = Prompt.inputString(" 사  진(" + member.getPerPhoto() + ") : ");
        break;

      case 3:
        while (true) {
          perEmail = Prompt.inputString(" 이메일(" + member.getPerEmail() + ") : ");
          if (!perEmail.contains("@") ||
              !perEmail.contains(".com") || perEmail.length() < 6) {
            System.out.println(" >> 정확한 이메일 양식으로 입력해 주세요.");
            continue;
          }
          break;
        }
        break;

      case 4:
        while (true) {
          perPassword = Prompt.inputString(" 비밀번호(" + member.getPerPassword() + ") : ");
          if (perPassword.length() < 8 || (!perPassword.contains("!") && !perPassword.contains("@")
              && !perPassword.contains("#") && !perPassword.contains("$")
              && !perPassword.contains("^") && !perPassword.contains("%")
              && !perPassword.contains("&") && !perPassword.contains("*"))) {
            System.out.println(" >> 8자 이상 특수문자를 포함시켜 주세요.");
            continue;
          }
          break;
        }

        while (true) {
          String pw =  Prompt.inputString(" 비밀번호 확인 : ");
          if (!pw.equals(member.getPerPassword())) {
            System.out.println("\n >> 확인 실패!\n");
            continue;
          } else {
            System.out.println("\n >> 확인 완료!\n");
          }
          break;
        }
        break;

      default : 
        System.out.println(" >> 올바른 번호를 입력해 주세요.");
        return;
    }

    System.out.println();
    String input = Prompt.inputString(" 정말 변경하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 회원 변경을 취소하였습니다.");
      return;
    }
    if (selectNo == 1) {
      member.setPerNickname(perNickName);
    } else if (selectNo == 2) {
      member.setPerPhoto(perPhoto);
    } else if (selectNo == 3) {
      member.setPerEmail(perEmail);
    } else if (selectNo == 4) {
      member.setPerPassword(perPassword);
    }

    requestAgent.request("member.update", member);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 회원 변경 실패!");
      System.out.println(requestAgent.getObject(String.class));
      return;
    }

    System.out.println(" >> 회원 정보를 변경하였습니다.");

  }
}
