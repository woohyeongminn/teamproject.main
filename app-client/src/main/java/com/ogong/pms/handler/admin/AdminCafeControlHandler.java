package com.ogong.pms.handler.admin;

import static com.ogong.pms.domain.Cafe.DELETE;
import static com.ogong.pms.domain.Cafe.WAIT;
import java.util.Collection;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.pms.handler.cafe.PromptCafe;
import com.ogong.util.Prompt;

public class AdminCafeControlHandler implements Command {

  PromptCafe promptcafe;

  public AdminCafeControlHandler (PromptCafe promptcafe) {
    this.promptcafe = promptcafe;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 장소 목록");

    Collection<Cafe> cafeList = promptcafe.getCafeList();

    if (cafeList.isEmpty()) {
      System.out.println(" >> 등록된 장소가 없습니다.");
      return;
    }

    for(Cafe cafe : cafeList) {
      if (cafe.getCafeStatus() == DELETE) {
        System.out.printf(" \n (%s)\n", cafe.getNo());
        System.out.println(" 삭제된 장소입니다.");
        continue;
      }
      System.out.printf(" \n (%s)\n 이름 : %s\n 주소 : %s\n 예약가능인원 : %d\n"
          , cafe.getNo(), cafe.getName(), cafe.getLocation(), cafe.getBookable());
      if (cafe.getCafeStatus() == WAIT) {
        System.out.println(" * 승인 대기 중인 카페입니다.");
      }
    }

    selectCafeDetailMenu(request);
  }

  private void selectCafeDetailMenu(CommandRequest request) throws Exception {
    System.out.println("\n----------------------");
    System.out.println("1. 상세");
    System.out.println("2. 승인");
    System.out.println("0. 이전");
    int input = Prompt.inputInt("선택> ");
    switch (input) {
      case 1: request.getRequestDispatcher("/cafe/controlDetail").forward(request); return;
      case 2: request.getRequestDispatcher("/cafe/controlApproval").forward(request); return;
      case 0: return;
      default :
        System.out.println(" >> 번호를 다시 선택해 주세요.");
    }
  }
}
