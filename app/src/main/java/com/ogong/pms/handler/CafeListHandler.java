package com.ogong.pms.handler;

import java.util.HashMap;
import java.util.List;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CafeReview;
import com.ogong.util.Prompt;

public class CafeListHandler extends AbstractCafeHandler {

  HashMap<String, Command> commandMap;

  public CafeListHandler (List<Cafe> cafeList, List<CafeReview> reviewList,
      List<CafeReservation> reserList, HashMap<String, Command> commandMap) {
    super (cafeList, reviewList, reserList);
    this.commandMap = commandMap;
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 장소 목록");
    System.out.println();

    for(Cafe cafe : cafeList) {
      System.out.printf(" (%s)\n 이름 : %s\n 주소 : %s\n 예약가능인원 : %d\n"
          , cafe.getNo(), cafe.getName(), cafe.getLocation(), cafe.getBookable());
    }

    selectCafeDetailMenu();
  }

  private void selectCafeDetailMenu() {
    System.out.println("\n----------------------");
    System.out.println("1. 상세보기");
    System.out.println("2. 검    색");
    System.out.println("0. 뒤로가기");
    int input = Prompt.inputInt("선택> ");
    switch (input) {
      case 1: commandMap.get("/cafe/detail").execute(); break;
      case 2: commandMap.get("/cafe/search").execute(); break;
      default : return;
    }
  }
}
