package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CafeReview;
import com.ogong.util.Prompt;

public class CafeUpdateHandler extends AbstractCafeHandler {

  public CafeUpdateHandler (List<Cafe> cafeList, List<CafeReview> reviewList, List<CafeReservation> reserList) {
    super (cafeList, reviewList, reserList);
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 장소 정보 변경");

    Cafe cafe = findByNo(Prompt.inputInt(" 장소 번호 : "));

    if (cafe == null) {
      System.out.println(" 해당 번호의 장소가 존재하지 않습니다.");
      return;
    }

    String name = Prompt.inputString(String.format(" 장소명(%s) : ", cafe.getName()));
    String mainImg = Prompt.inputString(String.format(" 대표이미지(%s) : ", cafe.getMainImg()));
    String Info = Prompt.inputString(String.format(" 소개글(%s) : ", cafe.getInfo()));
    String location = Prompt.inputString(String.format(" 주소(%s) : ", cafe.getLocation()));
    String phone = Prompt.inputString(String.format(" 전화번호(%s) : ", cafe.getPhone()));
    String openTime = Prompt.inputString(String.format(" 오픈시간(%s) : ", cafe.getOpenTime()));
    String closeTime = Prompt.inputString(String.format(" 마감시간(%s) : ", cafe.getCloseTime()));
    String holiday = Prompt.inputString(String.format(" 휴무일(%s) : ", cafe.getHoliday()));
    int bookable = Prompt.inputInt(String.format(" 예약가능인원(%d) : ", cafe.getBookable()));
    int timePrice = Prompt.inputInt(String.format(" 예약가능인원(%d) : ", cafe.getTimePrice()));

    String input = Prompt.inputString("정말 변경하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println("작업 변경을 취소하였습니다.");
      return;
    }

    cafe.setName(name);
    cafe.setMainImg(mainImg);
    cafe.setInfo(Info);
    cafe.setLocation(location);
    cafe.setPhone(phone);
    cafe.setOpenTime(openTime);
    cafe.setCloseTime(closeTime);
    cafe.setHoliday(holiday);
    cafe.setBookable(bookable);
    cafe.setTimePrice(timePrice);

    System.out.println("작업을 변경하였습니다.");
  }

}
