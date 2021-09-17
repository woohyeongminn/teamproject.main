package com.ogong.pms.handler;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.CafeRoom;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class CafeMyReservationListHandler extends AbstractCafeHandler {

  PromptPerMember promptPerMember;
  List<CafeRoom> roomList;

  public CafeMyReservationListHandler (List<Cafe> cafeList, List<CafeReview> reviewList, 
      List<CafeReservation> reserList, PromptPerMember promptPerMember,
      List<CafeRoom> roomList) {

    super (cafeList, reviewList, reserList);
    this.promptPerMember = promptPerMember;
    this.roomList = roomList;

    //    CafeReservation reservation = new CafeReservation();
    //    reservation.setReservationNo(1);
    //    reservation.setMemberNo(promptPerMember.memberList.get(0).getPerNo());
    //    reservation.setCafeNo(cafeList.get(0).getNo());
    //    reservation.setReservationDate(Date.valueOf("2021-8-1"));
    //    reservation.setStartTime(LocalTime.of(10, 00));
    //    reservation.setUseTime(1);
    //    reservation.setUseMemberNumber(1);
    //    reservation.setTotalPrice(2000);
    //    reservation.setWirteReview(false);
    //    reserList.add(reservation);
    //
    //    reservation = new CafeReservation();
    //    reservation.setReservationNo(2);
    //    reservation.setMemberNo(promptPerMember.memberList.get(0).getPerNo());
    //    reservation.setCafeNo(cafeList.get(0).getNo());
    //    reservation.setReservationDate(Date.valueOf("2021-10-10"));
    //    reservation.setStartTime(LocalTime.of(10, 00));
    //    reservation.setUseTime(3);
    //    reservation.setUseMemberNumber(0);
    //    reservation.setTotalPrice(45000);
    //    reservation.setWirteReview(false);
    //    reservation.setRoomNo(4);
    //    reserList.add(reservation);
    //
    //    reservation = new CafeReservation();
    //    reservation.setReservationNo(3);
    //    reservation.setMemberNo(promptPerMember.memberList.get(0).getPerNo());
    //    reservation.setCafeNo(cafeList.get(0).getNo());
    //    reservation.setReservationDate(Date.valueOf("2021-10-10"));
    //    reservation.setStartTime(LocalTime.of(15, 00));
    //    reservation.setUseTime(2);
    //    reservation.setUseMemberNumber(0);
    //    reservation.setTotalPrice(30000);
    //    reservation.setWirteReview(false);
    //    reservation.setRoomNo(4);
    //    reserList.add(reservation);
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 내 예약 내역 보기");
    System.out.println();

    Member member = AuthPerMemberLoginHandler.getLoginUser(); 

    if (member == null) {
      System.out.println(" >> 로그인 한 회원만 볼 수 있습니다.");
      return;
    }

    List<CafeReservation> myReserList = printMyReserList(member);

    if (myReserList.size() == 0) {
      System.out.println(" >> 예약 내역이 존재하지 않습니다.");
      return;
    }

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

    int inputNo = Prompt.inputInt(" 리뷰 작성할 예약번호 : ");

    Member member = AuthPerMemberLoginHandler.getLoginUser();

    CafeReservation myReserByNo = getMyReserByNo(member, inputNo);

    if (myReserByNo == null) {
      System.out.println(" >> 예약번호를 잘못 선택하셨습니다.");
      return;
    }

    Date today = new Date(System.currentTimeMillis());
    Date reserDate = myReserByNo.getReservationDate();

    if (reserDate.toLocalDate().compareTo(today.toLocalDate()) < 0) {
      if (!myReserByNo.getWirteReview()) {
        System.out.println(" >> 리뷰 작성 화면으로 이동합니다.");
        addReview(myReserByNo);
      } else {
        System.out.println(" >> 이미 리뷰를 작성한 예약입니다.");
        return;
      }
    } else {
      System.out.println(" >> 이용 후 다음 날부터 작성 가능합니다.");
      return;
    }
  }

  private void cancelReservation() {
    System.out.println();
    int inputNo = Prompt.inputInt(" 취소할 예약 번호 : ");

    Member member = AuthPerMemberLoginHandler.getLoginUser();

    CafeReservation myReserByNo = getMyReserByNo(member, inputNo);

    if (myReserByNo == null) {
      System.out.println(" >> 예약 번호를 잘못 선택하셨습니다.");
      return;
    }

    Date today = new Date(System.currentTimeMillis());
    Date reserDate = myReserByNo.getReservationDate();

    if (reserDate.toLocalDate().compareTo(today.toLocalDate()) > 0) {

      String input = Prompt.inputString(" 정말 예약 취소 하시겠습니까? (네 / 아니오) ");

      if (!input.equalsIgnoreCase(" 네")) {
        System.out.println(" >> 예약 취소를 취소합니다.");
        return;
      }
      reserList.remove(myReserByNo);
      System.out.println(" >> 예약이 취소되었습니다.");
    } else if (reserDate.toLocalDate().compareTo(today.toLocalDate()) == 0) {
      System.out.println(" >> 당일 예약은 취소 불가능합니다.");
      return;
    } else {
      System.out.println(" >> 지난 예약은 선택할 수 없습니다.");
      return;
    }
  }

  private List<CafeReservation> printMyReserList(Member member) {

    List<CafeReservation> myReserList = new ArrayList<>();
    for (CafeReservation cafeReser : reserList) {

      Member cafeReserMember = promptPerMember.getMemberByPerNo(cafeReser.getMemberNo());

      if (cafeReserMember.getPerEmail().equalsIgnoreCase(member.getPerEmail())) {
        Cafe cafeReserCafe = findByNo(cafeReser.getCafeNo());
        CafeRoom cafeRoom = getCafeRoomName(cafeReser.getRoomNo());
        myReserList.add(cafeReser);
        if (cafeReser.getUseMemberNumber() == 0) {
          System.out.printf(" (%d)\n 예약날짜 : %s\n 예약장소 : %s\n"
              + " 시작시간 : %s\n 이용시간 : %s시간\n 스터디룸 : %s\n"
              + " 결제금액 : %d원\n 리뷰작성여부 : %s\n"
              , cafeReser.getReservationNo(), cafeReser.getReservationDate(), cafeReserCafe.getName()
              , cafeReser.getStartTime(), cafeReser.getUseTime(), cafeRoom.getRoomName()  
              , cafeReser.getTotalPrice() ,getReviewStatusLabel(String.valueOf(cafeReser.getWirteReview())));
          System.out.println();  
        } else {
          System.out.printf(" (%d)\n 예약날짜 : %s\n 예약장소 : %s\n"
              + " 시작시간 : %s\n 이용시간 : %s시간\n 사용인원 : %d명\n"
              + " 결제금액 : %d원\n 리뷰작성여부 : %s\n"
              , cafeReser.getReservationNo(), cafeReser.getReservationDate(), cafeReserCafe.getName()
              , cafeReser.getStartTime(), cafeReser.getUseTime(), cafeReser.getUseMemberNumber()   
              , cafeReser.getTotalPrice() ,getReviewStatusLabel(String.valueOf(cafeReser.getWirteReview())));
          System.out.println();
        }
      } 
    }
    return myReserList;
  }

  private CafeReservation getMyReserByNo(Member member, int reserNo) {
    for (CafeReservation cafeReser : reserList) {
      Member cafeReserMember = promptPerMember.getMemberByPerNo(cafeReser.getMemberNo());
      if (reserNo == cafeReser.getReservationNo() &&
          cafeReserMember.getPerEmail().equalsIgnoreCase(member.getPerEmail())) {
        return cafeReser;
      }
    }
    return null;
  }

  private CafeRoom getCafeRoomName(int roomNo) {
    for (CafeRoom cafeRoom : roomList) {
      if (cafeRoom.getRoomNo() == roomNo) {
        return cafeRoom;
      }
    }
    return null;
  }
}
