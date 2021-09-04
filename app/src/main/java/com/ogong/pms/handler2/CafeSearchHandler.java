package com.ogong.pms.handler2;

import java.util.List;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CafeReview;
import com.ogong.util.Prompt;

public class CafeSearchHandler extends AbstractCafeHandler {

  public CafeSearchHandler (List<Cafe> cafeList, List<CafeReview> reviewList, List<CafeReservation> reserList) {
    super (cafeList, reviewList, reserList);
  }

  public void execute() {
    System.out.println();
    System.out.println("▶ 장소 검색");

    int count = 0;
    String input = Prompt.inputString("지역 : ");
    for (Cafe cafe : cafeList) {
      if (cafe.getLocation().contains(input)) {
        System.out.printf(" 번호 : %s, 이름 : %s, 주소 : %s, 예약가능인원 : %d\n"
            , cafe.getNo(), cafe.getName(), cafe.getLocation(), cafe.getBookable());
        count++;
      }
    }
    if (count == 0) {
      System.out.println("검색 결과가 존재하지 않습니다.");
    }
  }


}
