package com.ogong.pms.handler.ceoCafe;

import java.util.Collection;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CeoMember;
import com.ogong.pms.handler.AuthCeoMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.pms.handler.cafe.CafeHandlerHelper;
import com.ogong.pms.handler.cafe.PromptCafe;
import com.ogong.util.Prompt;

public class CeoReservationListHandler implements Command {

  PromptCafe promptcafe;

  public CeoReservationListHandler(PromptCafe promptcafe) {
    this.promptcafe = promptcafe;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 카페 선택");

    CeoMember ceoMember = null;
    try {
      ceoMember = AuthCeoMemberLoginHandler.getLoginCeoMember();
    } catch (NullPointerException e) {
      System.out.println(" >> 로그인 하세요.");
    }

    Collection<Cafe> cafeList = promptcafe.getCafeListByCeoMember(ceoMember.getCeoNo());

    if (cafeList.isEmpty()) {
      System.out.println(" >> 등록된 카페가 없습니다.");
    } else {
      for (Cafe cafe : cafeList) {
        System.out.printf(" (%s)\n [%s] | %s\n" , cafe.getNo(), cafe.getName()
            , CafeHandlerHelper.getCafeStatusLabel(cafe.getCafeStatus()));
      }
    }

    System.out.println();

    Cafe cafe = promptcafe.findByCafeNo(Prompt.inputInt(" 번호 : "));
    System.out.println();
    if (cafe == null || ceoMember.getCeoNo() != cafe.getCeoMember().getCeoNo() ||
        cafe.getCafeStatus() == Cafe.DELETE) {
      System.out.println(" >> 번호를 다시 선택하세요.");
      return;
    }

    request.setAttribute("cafeNo", cafe.getNo());
    request.getRequestDispatcher("/ceoMember/ReservationDetail").forward(request);
  }
}
