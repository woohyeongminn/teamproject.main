package com.ogong.pms.handler2;

import java.util.List;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CafeReview;
import com.ogong.util.Prompt;

public class CafeAddHandler extends AbstractCafeHandler {


  public CafeAddHandler (List<Cafe> cafeList, List<CafeReview> reviewList, List<CafeReservation> reserList) {
    super (cafeList, reviewList, reserList);

    Cafe cafe = new Cafe();

    cafe.setNo(11);
    cafe.setCafeCeoEmail("ceo@test.com");
    cafe.setCafeceoLicenseNo("111-11-12345");
    cafe.setCafeCeoBossName("ceo1");
    cafe.setName("에이스터디카페");
    cafe.setMainImg("a.jpg");
    cafe.setInfo("testtest");
    cafe.setLocation("서울 강남");
    cafe.setPhone("02-111-1111");
    cafe.setOpenTime("08:00");
    cafe.setCloseTime("24:00");
    cafe.setHoliday("매주 일요일");
    cafe.setBookable(30);
    cafe.setTimePrice(2000);

    cafeList.add(cafe);

    cafe = new Cafe();

    cafe.setNo(12);
    cafe.setCafeCeoEmail("ceo2@test.com");
    cafe.setCafeceoLicenseNo("111-11-12345");
    cafe.setCafeCeoBossName("ceo2");
    cafe.setName("해피해피스터디카페");
    cafe.setMainImg("bbb.jpg");
    cafe.setInfo("test~~~!");
    cafe.setLocation("서울 잠실");
    cafe.setPhone("010-1234-1234");
    cafe.setOpenTime("07:00");
    cafe.setCloseTime("22:00");
    cafe.setHoliday("휴무일 없음");
    cafe.setBookable(24);
    cafe.setTimePrice(1500);

    cafeList.add(cafe);

    cafe = new Cafe();

    cafe.setNo(13);
    cafe.setCafeCeoEmail("ceo3@test.com");
    cafe.setCafeceoLicenseNo("123-14-12345");
    cafe.setCafeCeoBossName("ceo3");
    cafe.setName("광명스터디카페");
    cafe.setMainImg("ccccc.jpg");
    cafe.setInfo("ㅎㅎㅎㅎㅎ");
    cafe.setLocation("경기도 광명");
    cafe.setPhone("010-5055-1111");
    cafe.setOpenTime("09:00");
    cafe.setCloseTime("23:00");
    cafe.setHoliday("휴무일 없음");
    cafe.setBookable(40);
    cafe.setTimePrice(3000);

    cafeList.add(cafe);
  }

  public void add () {
    System.out.println();
    System.out.println("▶ 장소 등록");
    System.out.println();

    Cafe cafe = new Cafe();
    cafe.setNo(Prompt.inputInt("번호 : "));
    //cafe.setCafeCeoEmail(Prompt.inputString("사장 아이디 : "));
    //cafe.setCafeceoLicenseNo(Prompt.inputString("사업자번호 : "));
    //cafe.setCafeCeoBossName(Prompt.inputString("대표자명 : "));
    cafe.setName(Prompt.inputString("상호명 : "));
    //cafe.setMainImg(Prompt.inputString("대표사진 : "));
    //cafe.setInfo(Prompt.inputString("소개글 : "));
    cafe.setLocation(Prompt.inputString("주소 : "));
    //cafe.setPhone(Prompt.inputString("전화번호 : "));
    //cafe.setOpenTime(Prompt.inputString("오픈시간 : "));
    //cafe.setCloseTime(Prompt.inputString("마감시간 : "));
    //cafe.setHoliday(Prompt.inputString("휴무일 : "));
    cafe.setBookable(Prompt.inputInt("예약가능인원 : "));
    //cafe.setTimePrice(Prompt.inputInt("시간당금액 : "));

    cafeList.add(cafe);
  }

}
