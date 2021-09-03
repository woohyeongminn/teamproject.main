package com.ogong.pms.handler2;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class CafeDetailHandler extends AbstractCafeHandler {

  public CafeDetailHandler (List<Cafe> cafeList, List<CafeReview> reviewList, List<CafeReservation> reserList) {
    super (cafeList, reviewList, reserList);
  }

  public void detail() {
    System.out.println();
    System.out.println("▶ 장소 상세보기");
    Cafe cafe = findByName(Prompt.inputString("장소명 : "));
    System.out.println();
    if (cafe == null) {
      System.out.println("해당 이름의 장소가 존재하지 않습니다.");
      return;
    }
    System.out.printf("(%s)\n", cafe.getNo());
    System.out.printf(">> 상호명 : %s\n", cafe.getName());
    System.out.printf(">> 대표이미지 : %s\n", cafe.getMainImg());
    System.out.printf(">> 소개글 : %s\n", cafe.getInfo());
    System.out.printf(">> 주소 : %s\n", cafe.getLocation());
    System.out.printf(">> 번호 : %s\n", cafe.getPhone());
    System.out.printf(">> 오픈시간 : %s\n", cafe.getOpenTime());
    System.out.printf(">> 마감시간 : %s\n", cafe.getCloseTime());
    System.out.printf(">> 휴무일 : %s\n", cafe.getHoliday());
    System.out.printf(">> 예약가능 인원 : %d\n", cafe.getBookable());
    System.out.printf(">> 시간당 금액 : %d원\n", cafe.getTimePrice());
    System.out.println();
    System.out.println("=============리뷰=============");
    int reviewSize = 0;
    for (CafeReview review : reviewList) {
      if (review.getCafe().getNo() == cafe.getNo()) {
        String nickname = review.getMember().getPerNickname();
        System.out.printf("닉네임 : %s, 별점 : %d, 내용 : %s, 등록일 : %s\n",
            nickname, review.getGrade(), review.getContent(), review.getRegisteredDate());
        reviewSize++;
      }
    }
    if (reviewSize == 0) {
      System.out.println("등록된 리뷰가 없습니다.");
    }

    System.out.println();

    System.out.println("1. 예약하기");
    System.out.println("2. 뒤로가기");
    int selectNo = Prompt.inputInt("선택> ");
    switch (selectNo) {
      case 1 : addReservation(cafe); break;
      default : return;
    }
  }

  public void addReservation(Cafe cafe) {
    System.out.println();
    System.out.println("▶ 예약하기");

    Member member = LoginHandler.getLoginUser();
    if (member == null) {
      System.out.println("로그인 한 회원만 예약 가능합니다.");
      return;
    }

    CafeReservation reservation = new CafeReservation();

    Date reservationDate = Prompt.inputDate("예약 날짜 : ");

    int startTime = Prompt.inputInt("시작시간(0시~24시) : ");
    int useTime = Prompt.inputInt("이용할 시간 : ");
    int useMemberNumber = Prompt.inputInt("사용할 인원 : ");
    int totalPrice = useTime * useMemberNumber * cafe.getTimePrice();
    System.out.printf("총금액 : %d원\n" , totalPrice);

    String input = Prompt.inputString("정말 예약하시겠습니까? (네 / 아니오) ");

    if (input.equalsIgnoreCase("아니오")) {
      System.out.println("장소예약을 취소하였습니다.");
      return;
    }

    reservation.setReservationNo(reservationNo++);
    reservation.setMember(member);
    reservation.setCafe(cafe);
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
}
