package com.ogong.pms.handler;

import java.util.HashMap;
import java.util.List;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CafeReview;
import com.ogong.util.Prompt;

public class CafeSearchHandler extends AbstractCafeHandler {

  HashMap<String, Command> commandMap;

  public CafeSearchHandler (List<Cafe> cafeList, List<CafeReview> reviewList, 
      List<CafeReservation> reserList, HashMap<String, Command> commandMap) {
    super (cafeList, reviewList, reserList);
    this.commandMap = commandMap;
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 장소 검색");
    System.out.println();
    int count = 0;
    String input = Prompt.inputString(" 지역 : ");
    for (Cafe cafe : cafeList) {
      if (cafe.getLocation().contains(input)) {
        System.out.printf("\n (%s)\n 이름 : %s\n 주소 : %s\n 예약가능인원 : %d\n"
            , cafe.getNo(), cafe.getName(), cafe.getLocation(), cafe.getBookable());
        count++;
      }
    }
    if (count == 0) {
      System.out.println(" >> 검색 결과가 존재하지 않습니다.");
      System.out.println();
      System.out.println("1. 검    색");
      System.out.println("0. 뒤로가기");
      int selectInput = Prompt.inputInt("선택> ");
      switch (selectInput) {
        case 1: commandMap.get("/cafe/search").execute(); break;
        default : commandMap.get("/cafe/list").execute(); break;
      }
    } else {
      System.out.println();
      System.out.println("1. 상세");
      System.out.println("0. 이전");
      int selectInput = Prompt.inputInt("선택> ");
      switch (selectInput) {
        case 1: commandMap.get("/cafe/detail").execute(); break;
        default : commandMap.get("/cafe/list").execute(); return;
      }
    }
  }
}
