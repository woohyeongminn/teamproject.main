package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CafeReview;
import com.ogong.util.Prompt;

public class CafeReservationListHandler extends AbstractCafeHandler {

  public CafeReservationListHandler (List<Cafe> cafeList, List<CafeReview> reviewList, List<CafeReservation> reserList) {
    super (cafeList, reviewList, reserList);
  }

  public void execute() {
    System.out.println();
    System.out.println("▶ 내 예약 내역 보기");

    if (AuthPerMemberLoginHandler.getLoginUser() == null) {
      System.out.println("로그인 한 회원만 볼 수 있습니다.");
      return;
    }

    int count = 0;
    for (CafeReservation cafeReser : reserList) {
      if (cafeReser.getMember().getPerEmail().equalsIgnoreCase(AuthPerMemberLoginHandler.getLoginUser().getPerEmail())) {
        System.out.printf(" 예약번호 : %d, 예약날짜 : %s, 예약장소 : %s,"
            + "\n 시작시간 : %d시, 이용시간 : %d시간, 사용인원 : %d명, "
            + "\n 결제금액 : %d원, 리뷰작성여부 : %s\n"
            , cafeReser.getReservationNo(), cafeReser.getReservationDate(), cafeReser.getCafe().getName()
            , cafeReser.getStartTime(), cafeReser.getUseTime(), cafeReser.getUseTime()
            , cafeReser.getTotalPrice() ,getReviewStatusLabel(String.valueOf(cafeReser.getWirteReview())));
        count++;
      } 
    }

    if (count == 0) {
      System.out.println("예약 내역이 존재하지 않습니다.");
      return;
    }

    if (Prompt.inputString("리뷰를 작성하시겠습니까? (네 / 아니오) ").equalsIgnoreCase("아니오")) {
      System.out.println("리뷰 작성을 취소합니다.");
      return;
    }

    System.out.println();
    int input = Prompt.inputInt("리뷰 작성할 예약번호 : ");
    for (CafeReservation cafeReser : reserList) {
      if (input == cafeReser.getReservationNo() &&
          cafeReser.getMember().getPerEmail().equalsIgnoreCase(AuthPerMemberLoginHandler.getLoginUser().getPerEmail())) {
        if (!cafeReser.getWirteReview()) {
          System.out.println("리뷰 작성 화면으로 이동합니다.");
          addReview(cafeReser);
        } else {
          System.out.println("이미 리뷰를 작성한 예약입니다.");
        }
      }
    }
  }


}
