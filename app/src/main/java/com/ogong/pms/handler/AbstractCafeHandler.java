package com.ogong.pms.handler;

import java.sql.Date;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public abstract class AbstractCafeHandler implements Command {

  List<Cafe> cafeList;
  List<CafeReview> reviewList;
  List<CafeReservation> reserList;
  int reviewNo = 1; // 리뷰번호
  int reservationNo = 4; // 예약번호

  public AbstractCafeHandler (List<Cafe> cafeList, List<CafeReview> reviewList,
      List<CafeReservation> reserList) {
    this.cafeList = cafeList;
    this.reviewList = reviewList;
    this.reserList = reserList;
  }

  protected String getReviewStatusLabel(String status) {
    switch (status) {
      case "true": return "작성완료";
      case "false": return "작성대기";
      default: return "오류";
    }
  }

  protected String getReviewGradeStatusLabel(int status) {
    switch (status) {
      case 0: return "☆☆☆☆☆";
      case 1: return "★☆☆☆☆";
      case 2: return "★★☆☆☆";
      case 3: return "★★★☆☆";
      case 4: return "★★★★☆";
      case 5: return "★★★★★";
      default: return "오류";
    }
  }

  protected Cafe findByNo(int no) {
    for (Cafe cafe : cafeList) {
      if (cafe.getNo() == no && cafe.getCafeStatus() != 0 && cafe.getCafeStatus() != 3) {
        return cafe;
      }
    }
    return null;
  }

  protected Cafe findByName(String name) {
    for (Cafe cafe : cafeList) {
      if (cafe.getName().equals(name) && cafe.getCafeStatus() != 0 && cafe.getCafeStatus() != 3) {
        return cafe;
      }
    }
    return null;
  }

  protected void addReservation(Cafe cafe) {
    System.out.println();
    System.out.println("▶ 예약하기");

    Member member = AuthPerMemberLoginHandler.getLoginUser();
    if (member == null) {
      System.out.println(" >> 로그인 한 회원만 예약 가능합니다.");
      return;
    }

    CafeReservation reservation = new CafeReservation();

    Date today = new Date(System.currentTimeMillis());
    Date reservationDate = Prompt.inputDate(" 예약 날짜 : ");
    while (today.toLocalDate().compareTo(reservationDate.toLocalDate()) > 0) {
      System.out.println(" >> 이전 날짜는 예약 불가능합니다.");
      System.out.println("    날짜를 다시 입력해 주세요.\n");
      reservationDate = Prompt.inputDate(" 예약 날짜 : ");
    }

    //    String[] openTime = cafe.getOpenTime().split(":");
    //    String[] lastTime = cafe.getCloseTime().split(":");
    //    int openTimeHour = Integer.valueOf(openTime[0]);
    //    int lastTimeMinus1 = Integer.valueOf(lastTime[0]) - 1;
    //String lastOrderTime = String.valueOf(lastTimeMinus1) + ":" + lastTime[1];

    String openTime = cafe.getOpenTime().toString();    
    String closeTime = cafe.getCloseTime().toString();
    String closeTimeMinus1 = cafe.getCloseTime().minusHours(1).toString();

    LocalTime startTime;
    while (true) {
      startTime = LocalTime.parse(Prompt.inputString(
          String.format(" 시작 시간 (%s~%s) : ", openTime, closeTimeMinus1)));
      if (startTime.isBefore(cafe.getOpenTime())) {
        System.out.println(" >> 오픈 시간 전입니다.");
        System.out.println("    시작 시간을 다시 입력해 주세요.\n");
        continue;
      } else if (startTime.isAfter(cafe.getCloseTime().minusHours(1))) {
        System.out.println(" >> " + closeTimeMinus1 + " 까지만 가능합니다.");
        System.out.println("    시작 시간을 다시 입력해 주세요.\n");
        continue;
      }
      break;
    }

    int useTime = Prompt.inputInt(String.format(" 이용 시간 (%s 마감) : ", closeTime));
    int availableTime = (int) ChronoUnit.HOURS.between(startTime, cafe.getCloseTime());
    while (useTime > availableTime) {
      System.out.printf(" >> 마감 시간(%s)을 초과하여 예약할 수 없습니다.\n", closeTime);
      System.out.println("    이용 시간을 다시 입력해 주세요.\n");
      useTime = Prompt.inputInt(String.format(" 이용할 시간 (%s 마감) : ", closeTime));
    }

    int useMemberNumber = Prompt.inputInt(" 사용 인원 : ");
    int totalPrice = useTime * useMemberNumber * cafe.getTimePrice();
    System.out.printf(" 총 금액 : %d원\n" , totalPrice);

    String input = Prompt.inputString(" 정말 예약하시겠습니까? (네 / 아니오) ");

    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 장소 예약을 취소하였습니다.");
      return;
    }

    reservation.setReservationNo(reservationNo++);
    reservation.setMemberNo(member.getPerNo());
    reservation.setCafeNo(cafe.getNo());
    reservation.setReservationDate(reservationDate);
    reservation.setStartTime(startTime);
    reservation.setUseTime(useTime);
    reservation.setUseMemberNumber(useMemberNumber);
    reservation.setTotalPrice(totalPrice);
    reservation.setWirteReview(false);

    cafe.setBookable(cafe.getBookable() - 1);

    reserList.add(reservation);

    System.out.println();
    System.out.println("*** 예약 되었습니다 ***");
  }

  protected void addReview(CafeReservation cafeReser) {
    System.out.println();
    System.out.println("▶ 리뷰 등록하기");

    if (AuthPerMemberLoginHandler.getLoginUser() == null) {
      System.out.println(" >> 로그인 한 회원만 등록 가능합니다.");
    } else {

      Cafe cafe = findByNo(cafeReser.getCafeNo());

      CafeReview cafeReview = new CafeReview();

      String content = Prompt.inputString(" 리뷰 내용 : ");
      int grade = Prompt.inputInt(" 별점(0~5점) : ");
      while (grade < 0 || grade > 5) {
        System.out.println(" 별점을 다시 입력해 주세요.");
        grade = Prompt.inputInt(" 별점(0~5점) : ");
      }
      Member member = AuthPerMemberLoginHandler.getLoginUser();
      Date registeredDate = new Date(System.currentTimeMillis());

      String input = Prompt.inputString(" 정말 등록하시겠습니까? (네 / 아니오) ");
      if (!input.equalsIgnoreCase("네")) {
        System.out.println(" >> 리뷰 등록을 취소하였습니다.");
        return;
      }

      cafeReview.setReviewNo(reviewNo++);
      cafeReview.setContent(content);
      cafeReview.setGrade(grade);
      cafeReview.setCafeNo(cafe.getNo());
      cafeReview.setMemberNo(member.getPerNo());
      cafeReview.setRegisteredDate(registeredDate);
      cafeReview.setReviewStatus(0);

      reviewList.add(cafeReview);
      cafeReser.setWirteReview(true);

      System.out.println(" >> 리뷰가 등록되었습니다.");
    }
  }

  // 0908 추가
  protected CafeReview findByReview (int reviewNo) {
    for (CafeReview cafeReview : reviewList) {
      if (cafeReview.getReviewNo() == reviewNo) {
        return cafeReview;
      }
    }
    return null;
  }
}
