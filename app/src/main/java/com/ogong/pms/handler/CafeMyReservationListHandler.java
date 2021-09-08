package com.ogong.pms.handler;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CafeReview;
import com.ogong.util.Prompt;

public class CafeMyReservationListHandler extends AbstractCafeHandler {

  PromptPerMember promptPerMember;

  public CafeMyReservationListHandler (List<Cafe> cafeList, List<CafeReview> reviewList, 
      List<CafeReservation> reserList, PromptPerMember promptPerMember) {
    super (cafeList, reviewList, reserList);
    this.promptPerMember = promptPerMember;

    CafeReservation reservation = new CafeReservation();

    reservation.setReservationNo(1);
    reservation.setMember(promptPerMember.memberList.get(0));
    reservation.setCafe(cafeList.get(0));
    reservation.setReservationDate(Date.valueOf("2021-9-1"));
    reservation.setStartTime(10);
    reservation.setUseTime(1);
    reservation.setUseMemberNumber(1);
    reservation.setTotalPrice(2000);
    reservation.setWirteReview(false);

    reserList.add(reservation);
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
      System.out.println(" >> 예약 내역이 존재하지 않습니다.");
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
    int count = 0;

    for (CafeReservation cafeReser : reserList) {
      if (input == cafeReser.getReservationNo() &&
          cafeReser.getMember().getPerEmail().equalsIgnoreCase(AuthPerMemberLoginHandler.getLoginUser().getPerEmail())) {
        if (!cafeReser.getWirteReview()) {
          count++;
          System.out.println("리뷰 작성 화면으로 이동합니다.");
          addReview(cafeReser);
        } else {
          System.out.println("이미 리뷰를 작성한 예약입니다.");
          return;
        }
      } 
    }
    if (count == 0) {
      System.out.println("예약번호를 잘못 선택하셨습니다.");
    }
  }

  private void cancelReservation() {
    System.out.println();
    int inputNo = Prompt.inputInt("취소할 예약번호 : ");
    int count = 0;

    for (CafeReservation cafeReser : reserList) {
      if (cafeReser.getMember().getPerEmail().equalsIgnoreCase(AuthPerMemberLoginHandler.getLoginUser().getPerEmail())) {
        if (inputNo == cafeReser.getReservationNo()) {
          count++;
          Date today = new Date(System.currentTimeMillis());
          Date reserDate = cafeReser.getReservationDate();

          if (reserDate.toLocalDate().compareTo(today.toLocalDate()) > 0) {

            String input = Prompt.inputString("정말 예약 취소 하시겠습니까? (네 / 아니오) ");

            if (!input.equalsIgnoreCase("네")) {
              System.out.println(" >> 예약 취소를 취소합니다.");
              return;
            }
            reserList.remove(cafeReser);
            System.out.println(" >> 예약이 취소되었습니다.");
            break;
          } else if (reserDate.toLocalDate().compareTo(today.toLocalDate()) == 0) {
            System.out.println(" >> 당일 예약은 취소 불가능 합니다.");
            return;
          } else {
            System.out.println(" >> 지난 예약은 선택 불가");
            return;
          }
        }
      }
    }

    if (count == 0) {
      System.out.println(" >> 예약번호를 잘못 선택하셨습니다.");
    }
  }

}
