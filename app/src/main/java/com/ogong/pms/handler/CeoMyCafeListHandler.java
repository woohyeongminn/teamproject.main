package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CeoMember;
import com.ogong.util.Prompt;

public class CeoMyCafeListHandler extends AbstractCeoHandler {

  List<CeoMember> ceoMemberList;
  List<Cafe> cafeList;

  public CeoMyCafeListHandler(List<CeoMember> ceoMemberList, List<Cafe> cafeList) {
    super(ceoMemberList);
    this.cafeList = cafeList;
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 카페 목록");

    try {
      CeoMember CeoMember = AuthCeoMemberLoginHandler.getLoginCeoMember();

      for (Cafe cafe : cafeList) {
        if (cafe.getCeoNo() == CeoMember.getCeoNo()) {
          System.out.printf(" \n(%s)\n 이름 : %s\n 주소 : %s\n 예약가능인원 : %d\n"
              , cafe.getNo(), cafe.getName(), cafe.getLocation(), cafe.getBookable());
        }
      }

    } catch (NullPointerException e) {
      System.out.println("로그인 하세요.");
    }

    System.out.println();
    System.out.println("1. 등록");
    System.out.println("2. 상세");
    System.out.println("3. 수정");
    System.out.println("4. 삭제");
    System.out.println("0. 이전");
    int selectNo = Prompt.inputInt("선택> ");
    switch (selectNo) {
      case 1 : cafeAdd(); break;
      case 2 : break;
      case 3 : break;
      case 4 : break;
      default : return;
    }
  }

  public void cafeAdd () {
    System.out.println();
    System.out.println("▶ 장소 등록");
    System.out.println();

    Cafe cafe = new Cafe();
    //cafe.setNo(Prompt.inputInt(" 번호 : "));
    //cafe.setCafeCeoEmail(Prompt.inputString("사장 아이디 : "));
    //cafe.setCafeceoLicenseNo(Prompt.inputString("사업자번호 : "));
    //cafe.setCafeCeoBossName(Prompt.inputString("대표자명 : "));
    cafe.setName(Prompt.inputString(" 상호명 : "));
    //cafe.setMainImg(Prompt.inputString("대표사진 : "));
    //cafe.setInfo(Prompt.inputString("소개글 : "));
    cafe.setLocation(Prompt.inputString(" 주소 : "));
    //cafe.setPhone(Prompt.inputString("전화번호 : "));
    //cafe.setOpenTime(Prompt.inputString("오픈시간 : "));
    //cafe.setCloseTime(Prompt.inputString("마감시간 : "));
    //cafe.setHoliday(Prompt.inputString("휴무일 : "));
    cafe.setBookable(Prompt.inputInt(" 예약가능인원 : "));
    //cafe.setTimePrice(Prompt.inputInt("시간당금액 : "));

    cafeList.add(cafe);
  }
}
