package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CeoMember;

public class CeoReservationListHandler extends AbstractCeoMemberHandler {

  List<CafeReservation> reserList;

  public CeoReservationListHandler(List<CeoMember> ceoMemberList, List<CafeReservation> reserList) {
    super(ceoMemberList);
    this.reserList = reserList;
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 예약 목록");
    // 구현해야됨


  }
}
