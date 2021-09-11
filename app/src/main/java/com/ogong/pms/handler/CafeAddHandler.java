package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.CeoMember;
import com.ogong.util.Prompt;

public class CafeAddHandler extends AbstractCafeHandler {

  List<CeoMember> ceoMemberList;

  public CafeAddHandler (List<Cafe> cafeList, List<CafeReview> reviewList, 
      List<CafeReservation> reserList, List<CeoMember> ceoMemberList) {
    super (cafeList, reviewList, reserList);
    this.ceoMemberList = ceoMemberList;

    //    Cafe cafe = new Cafe();
    //
    //    cafe.setNo(11);
    //    cafe.setCeoNo(ceoMemberList.get(0).getCeoNo());
    //    cafe.setName("에이스터디카페");
    //    cafe.setMainImg("a.jpg");
    //    cafe.setInfo("testtest");
    //    cafe.setLocation("서울 강남");
    //    cafe.setPhone("02-111-1111");
    //    cafe.setOpenTime(LocalTime.of(8, 00));
    //    cafe.setCloseTime(LocalTime.of(21, 00));
    //    cafe.setHoliday("매주 일요일");
    //    cafe.setBookable(30);
    //    cafe.setTimePrice(2000);
    //    cafe.setCafeStatus(1);
    //
    //    cafeList.add(cafe);
    //
    //    cafe = new Cafe();
    //
    //    cafe.setNo(12);
    //    cafe.setCeoNo(ceoMemberList.get(0).getCeoNo());
    //    cafe.setName("해피해피스터디카페");
    //    cafe.setMainImg("bbb.jpg");
    //    cafe.setInfo("test~~~!");
    //    cafe.setLocation("서울 잠실");
    //    cafe.setPhone("010-1234-1234");
    //    cafe.setOpenTime(LocalTime.of(7, 00));
    //    cafe.setCloseTime(LocalTime.of(22, 00));
    //    cafe.setHoliday("휴무일 없음");
    //    cafe.setBookable(24);
    //    cafe.setTimePrice(1500);
    //    cafe.setCafeStatus(1);
    //
    //    cafeList.add(cafe);
    //
    //    cafe = new Cafe();
    //
    //    cafe.setNo(13);
    //    cafe.setCeoNo(ceoMemberList.get(1).getCeoNo());
    //    cafe.setName("광명스터디카페");
    //    cafe.setMainImg("ccccc.jpg");
    //    cafe.setInfo("ㅎㅎㅎㅎㅎ");
    //    cafe.setLocation("경기도 광명");
    //    cafe.setPhone("010-5055-1111");
    //    cafe.setOpenTime(LocalTime.of(9, 00));
    //    cafe.setCloseTime(LocalTime.of(23, 00));
    //    cafe.setHoliday("휴무일 없음");
    //    cafe.setBookable(40);
    //    cafe.setTimePrice(3000);
    //    cafe.setCafeStatus(1);
    //
    //    cafeList.add(cafe);
    //
    //    cafe = new Cafe();
    //
    //    cafe.setNo(14);
    //    cafe.setCeoNo(ceoMemberList.get(1).getCeoNo());
    //    cafe.setName("비트스터디카페");
    //    cafe.setMainImg("bit.jpg");
    //    cafe.setInfo("비트주세요");
    //    cafe.setLocation("경기도 용인");
    //    cafe.setPhone("010-1245-1111");
    //    cafe.setOpenTime(LocalTime.of(05, 00));
    //    cafe.setCloseTime(LocalTime.of(23, 00));
    //    cafe.setHoliday("휴무일 없음");
    //    cafe.setBookable(100);
    //    cafe.setTimePrice(3500);
    //    cafe.setCafeStatus(0);
    //
    //    cafeList.add(cafe);
  }

  @Override
  public void execute () {
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
