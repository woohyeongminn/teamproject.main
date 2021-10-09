package com.ogong.pms.handler.member;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.ogong.pms.domain.CeoMember;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class CeoAddHandler implements Command {

  int ceoMemberNo = 100;

  RequestAgent requestAgent;

  public CeoAddHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  // 기업 개인
  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 기업 회원가입");
    System.out.println();

    CeoMember ceoMember = new CeoMember();

    requestAgent.request("ceoMember.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("목록 조회 실패!");
      return;
    }

    Collection<CeoMember> ceoMemberList = requestAgent.getObjects(CeoMember.class);
    List<CeoMember> arrayCeoMember = new ArrayList<>(ceoMemberList);

    String inputNewEmail;
    while (true) {
      inputNewEmail = Prompt.inputString(" 이메일 : ");
      if (!inputNewEmail.contains("@") ||
          !inputNewEmail.contains(".com") ||
          inputNewEmail.length() < 6) {
        System.out.println(" >> 정확한 이메일 양식으로 입력해주세요.\n");
        continue;
      }
      break;
    }
    ceoMember.setCeoEmail(inputNewEmail);

    String inputNewPW;
    while (true) {
      System.out.println();
      inputNewPW = Prompt.inputString(" 비밀번호 : ");
      if (inputNewPW.length() < 8 || (!inputNewPW.contains("!") && !inputNewPW.contains("@")
          && !inputNewPW.contains("#") && !inputNewPW.contains("$")
          && !inputNewPW.contains("^") && !inputNewPW.contains("%")
          && !inputNewPW.contains("&") && !inputNewPW.contains("*"))) {
        System.out.println(" >> 8자 이상 특수문자를 포함시켜 주세요.");
        continue;
      }
      break;
    }
    ceoMember.setCeoPassword(inputNewPW);

    while (true) {
      System.out.println();
      String pw =  Prompt.inputString(" 비밀번호 확인 : ");
      if (!pw.equals(ceoMember.getCeoPassword())) {
        System.out.println(" >> 확인 실패!");
        continue;
      } else {
        System.out.println(" >> 확인 완료!");
      }
      break;
    }

    String inputLicenseNo;
    while (true) {
      System.out.println();
      inputLicenseNo = Prompt.inputString(" 사업자 등록번호 : ");
      if (inputLicenseNo.length() != 10) {
        System.out.println(" >> 10자리 숫자를 입력해주세요.");
        continue;
      }
      break;
    }
    ceoMember.setCeoLicenseNo(inputLicenseNo);
    System.out.println();
    ceoMember.setCeoBossName(Prompt.inputString(" 대표자명 : "));
    System.out.println();
    ceoMember.setCeoPhoto(Prompt.inputString(" 사진 : "));
    ceoMember.setCeoregisteredDate(new Date(System.currentTimeMillis()));

    // 고유번호 +1
    CeoMember lastCeoMember = null;
    if (!arrayCeoMember.isEmpty()) {
      lastCeoMember = arrayCeoMember.get(arrayCeoMember.size() - 1);
      ceoMember.setCeoNo(lastCeoMember.getCeoNo() +1);
    } else {
      ceoMember.setCeoNo(1);
    }

    ceoMember.setCeoStatus(CeoMember.INUSER);

    requestAgent.request("ceoMember.insert", ceoMember);

    if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      System.out.println(" >> 회원가입이 완료되었습니다.");
    } else {
      System.out.println(" >> 회원가입이 실패되었습니다.");
    }

  }
}