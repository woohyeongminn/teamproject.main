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

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 내 예약 내역 보기");
    System.out.println();

    if (AuthPerMemberLoginHandler.getLoginUser() == null) {
      System.out.println("로그인 한 회원만 볼 수 있습니다.");
      return;
    }

    int count = 0;
    for (CafeReservation cafeReser : reserList) {
      if (cafeReser.getMember().getPerEmail().equalsIgnoreCase(AuthPerMemberLoginHandler.getLoginUser().getPerEmail())) {
        System.out.printf(" (%d)\n 예약날짜 : %s\n 예약장소 : %s\n"
            + " 시작시간 : %d시\n 이용시간 : %d시간\n 사용인원 : %d명\n"
            + " 결제금액 : %d원\n 리뷰작성여부 : %s\n"
            , cafeReser.getReservationNo(), cafeReser.getReservationDate(), cafeReser.getCafe().getName()
            , cafeReser.getStartTime(), cafeReser.getUseTime(), cafeReser.getUseTime()
            , cafeReser.getTotalPrice() ,getReviewStatusLabel(String.valueOf(cafeReser.getWirteReview())));
        System.out.println();
        count++;
      } 
    }

    if (count == 0) {
      System.out.println("예약 내역이 존재하지 않습니다.");
      return;
    }

    //    if (Prompt.inputString("리뷰를 작성하시겠습니까? (네 / 아니오) ").equalsIgnoreCase("!네")) {
    //      System.out.println("리뷰 작성을 취소합니다.");
    //      return;
    //    }
    System.out.println("----------------------");
    System.out.println("1. 리뷰 작성");
    System.out.println("2. 예약 취소");
    System.out.println("0. 뒤로 가기");

    int selectNo = Prompt.inputInt("선택> ");
    switch (selectNo) {
      case 1: goToAddReview(); break;
      case 2: cancelReservation(); break;
      default : return;
    }
  }

  private void goToAddReview() {
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
          return;
        }
      } else {
        System.out.println("예약번호를 잘못 선택하셨습니다.");
      }
    }
  }

  private void cancelReservation() {
    // 작성하기
  }

}
