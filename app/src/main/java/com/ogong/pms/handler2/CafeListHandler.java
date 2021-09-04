package com.ogong.pms.handler2;

import java.util.List;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CafeReview;

public class CafeListHandler extends AbstractCafeHandler {


  public CafeListHandler (List<Cafe> cafeList, List<CafeReview> reviewList, List<CafeReservation> reserList) {
    super (cafeList, reviewList, reserList);
  }

  public void list() {
    System.out.println();
    System.out.println("▶ 장소 목록");
    System.out.println();
    for(Cafe cafe : cafeList) {
      System.out.println();
      System.out.printf(" (%s)\n 이름 : %s\n 주소 : %s\n 예약가능인원 : %d\n"
          , cafe.getNo(), cafe.getName(), cafe.getLocation(), cafe.getBookable());
    }
  }
}
