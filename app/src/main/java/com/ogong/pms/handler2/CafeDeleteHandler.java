package com.ogong.pms.handler2;

import java.util.List;
import com.ogong.pms.domain.Cafe;
import com.ogong.util.Prompt;

public class CafeDeleteHandler {

  List<Cafe> cafeList;

  public CafeDeleteHandler (List<Cafe> cafeList) {
    this.cafeList = cafeList;
  }

  public void delete() {
    System.out.println();
    System.out.println("▶ 장소 삭제");
    Cafe cafe = findByNo(Prompt.inputInt("장소 번호 : "));

    if (cafe == null) {
      System.out.println("해당 번호의 장소가 존재하지 않습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까? (네 / 아니오) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("장소 삭제를 취소하였습니다.");
      return;
    }

    cafeList.remove(cafe);

    System.out.println("장소를 삭제하였습니다.");
  }

  private Cafe findByNo(int no) {
    Cafe[] arr = cafeList.toArray(new Cafe[0]);

    for (Cafe cafe : arr) {
      if (cafe.getNo() == no) {
        return cafe;
      }
    }
    return null;
  }
}
