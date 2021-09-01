package com.ogong.pms.handler;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class CafeReservationHandler {

  static List<CafeReservation> reserList;

  public CafeReservationHandler (List<CafeReservation> reserList) {
    this.reserList = reserList;
  }

  public static void add(Cafe cafe) {
    System.out.println("[예약하기]");

    Member member = LoginHandler.getLoginUser();
    if (member == null) {
      System.out.println("로그인 한 유저만 예약 가능합니다.");
      //            return;
    }

    CafeReservation reservation = new CafeReservation();

    Date reservationDate = Prompt.inputDate("예약 날짜? ");
    Date currentDate = new Date(System.currentTimeMillis());

    //    while (true) {
    //
    //
    //      if (currentDate.toString().equals(reservationDate.toString())) {
    //        return;
    //      } else if (!reservationDate.before(currentDate)) {
    //        return;
    //      }
    //
    //      System.out.println("지난 날짜를 입력하셨습니다. ");
    //      System.out.println("날짜를 다시 입력해주세요.\n");
    //    }

    int startTime = Prompt.inputInt("시작시간? ");
    int useTime = Prompt.inputInt("이용할 시간? ");
    int useMemberNumber = Prompt.inputInt("사용할 인원? ");

    String input = Prompt.inputString("정말 예약하시겠습니까?(y/n) ");

    if (input.equalsIgnoreCase("n")) {
      System.out.println("장소예약을 취소하였습니다.");
      return;
    }

    int reservationNo = 1;
    reservation.setReservationNo(reservationNo++);
    reservation.setMember(member);
    reservation.setCafe(cafe);
    reservation.setReservationDate(reservationDate);
    reservation.setStartTime(startTime);
    reservation.setUseTime(useTime);
    reservation.setUseMemberNumber(useMemberNumber);
    reservation.setTotalPrice(useTime * useMemberNumber * cafe.getTimePrice());
    reservation.setWirteReview(false);

    reserList.add(reservation);

    System.out.println("*** 예약 되었습니다. ***");

  }

  public static List<CafeReservation> getReserList() {
    return reserList;
  }


}
