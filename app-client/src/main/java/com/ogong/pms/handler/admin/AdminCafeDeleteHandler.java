package com.ogong.pms.handler.admin;

import static com.ogong.pms.domain.Cafe.DELETE;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.pms.handler.cafe.PromptCafe;
import com.ogong.util.Prompt;

public class AdminCafeDeleteHandler implements Command {

  PromptCafe promptcafe;

  public AdminCafeDeleteHandler (PromptCafe promptcafe) {
    this.promptcafe = promptcafe;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 장소 삭제");
    System.out.println();

    Cafe cafe = promptcafe.findByCafeNo((int) request.getAttribute("cafeNo"));

    if (cafe == null) {
      System.out.println(" >> 해당 번호의 장소가 존재하지 않습니다.");
      return;
    }

    String input = Prompt.inputString(" 정말 삭제하시겠습니까? (네 / 아니오) ");
    System.out.println();
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 장소 삭제를 취소하였습니다.");
      return;
    }

    cafe.setCafeStatus(DELETE);

    promptcafe.deleteCafe(cafe);
  }
}
