package com.ogong.pms.handler2;

import java.util.List;
import com.ogong.pms.domain.Cafe;

public class CafeListHandler {

  List<Cafe> cafeList;

  public CafeListHandler (List<Cafe> cafeList) {
    this.cafeList = cafeList;
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
