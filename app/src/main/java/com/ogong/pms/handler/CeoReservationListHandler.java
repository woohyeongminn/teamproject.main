package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CeoMember;
import com.ogong.util.Prompt;

public class CeoReservationListHandler extends AbstractCeoMemberHandler {

  List<CafeReservation> reserList;
  List<Cafe> cafeList;

  public CeoReservationListHandler(List<CeoMember> ceoMemberList, List<CafeReservation> reserList,
      List<Cafe> cafeList) {
    super(ceoMemberList);
    this.reserList = reserList;
    this.cafeList = cafeList;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println();
    System.out.println("▶ 예약 목록");
    // 구현중

    int cafeListCount = 0;
    CeoMember ceoMember = AuthCeoMemberLoginHandler.getLoginCeoMember();

    for (Cafe cafe : cafeList) {

      if (cafe.getCeoMember().getCeoNo() == ceoMember.getCeoNo()) {
        System.out.printf("\n (%s)\n 이름 : %s\n" , cafe.getNo(), cafe.getName());
        cafeListCount++;
      }
    }

    if (cafeListCount == 0) {
      System.out.println(" >> 등록된 카페가 없습니다. ");
    }

    System.out.println();
    int cafeNo = Prompt.inputInt(" 번호 : ");
    System.out.println();

    myCafeReservationList(cafeNo);
  }

  private void myCafeReservationList(int cafeNo) {
    System.out.println();
    System.out.println("▶ 예약 상세 목록");

    CeoMember ceoMember = AuthCeoMemberLoginHandler.getLoginCeoMember();

    for (CafeReservation myCafeReservation : reserList) {
      if (ceoMember.getCeoNo() == myCafeReservation.getReservationNo()) {
        System.out.println(myCafeReservation);
        System.out.println();
      }
    }
  }
}
