package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.CeoMember;
import com.ogong.util.Prompt;

public class AdminCeoMemberDeleteHandler extends AbstractCeoMemberHandler {

  PromptCeoMember promptCeoMember;

  public AdminCeoMemberDeleteHandler(
      List<CeoMember> ceoMemberList, PromptCeoMember promptCeoMember) {
    super(ceoMemberList);
    this.promptCeoMember = promptCeoMember;
  }

  //관리자용
  public void execute(CommandRequest request) {
    System.out.println();
    System.out.println("▶ 기업회원 탈퇴");
    int inputceoNo = Prompt.inputInt(" 번호 : ");
    System.out.println();

    CeoMember ceoMember = promptCeoMember.findByCeoMemberNo(inputceoNo);

    if (ceoMember == null) {
      System.out.println(" >> 해당 번호의 기업 회원이 없습니다.");
      return;
    }

    String input = Prompt.inputString(" 정말 탈퇴하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 기업 회원 탈퇴를 취소하였습니다.");
      return;
    }

    ceoMemberList.remove(ceoMember);

    System.out.println(" >> 기업 회원이 탈퇴되었습니다.");
  }

}
