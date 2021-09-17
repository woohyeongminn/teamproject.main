package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.CeoMember;
import com.ogong.util.Prompt;

public class AdminCeoMemberDetailHandler extends AbstractCeoMemberHandler {

  PromptCeoMember promptCeoMember;

  public AdminCeoMemberDetailHandler(List<CeoMember> ceoMemberList, PromptCeoMember promptCeoMember) {
    super(ceoMemberList);
    this.promptCeoMember = promptCeoMember;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println();
    System.out.println("▶ 기업회원 상세");
    int inputceoNo = Prompt.inputInt(" 번호 : ");
    System.out.println();

    CeoMember ceoMember = promptCeoMember.findByCeoMemberNo(inputceoNo);

    if (ceoMember == null) {
      System.out.println(" >> 해당 번호의 기업 회원이 없습니다.");
      return;
    }

    System.out.printf(" [%s]\n", ceoMember.getCeoBossName());
    System.out.printf(" >> 점포명 : %s\n", ceoMember.getCeoEmail());
    System.out.printf(" >> 이메일 : %s\n", ceoMember.getCeoEmail());
    //System.out.printf(" >> 점포주소 : %s\n", ceoMember.getCeoStoreDetailAddress());
    //System.out.printf(" >> 사진 : %s\n", ceoMember.getCeoStoreName());
    System.out.printf(" >> 가입일 : %s\n", ceoMember.getCeoregisteredDate());
  }

}
