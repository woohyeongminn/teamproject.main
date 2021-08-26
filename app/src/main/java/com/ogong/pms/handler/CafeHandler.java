package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Cafe;
import com.ogong.util.Prompt;

public class CafeHandler {

  List<Cafe> cafeList;
  int no;

  public CafeHandler (List<Cafe> cafeList) {
    this.cafeList = cafeList;
  }

  public void add () {
    System.out.println("[장소 등록]");

    Cafe cafe = new Cafe();

    cafe.setName(Prompt.inputString("장소명? "));
    cafe.setMainImg(Prompt.inputString("대표사진? "));
    cafe.setInfo(Prompt.inputString("소개글? "));
    cafe.setLocation(Prompt.inputString("주소? "));
    cafe.setPhone(Prompt.inputString("전화번호? "));
    cafe.setHrs(Prompt.inputString("운영시간? "));
    cafe.setPersonalSeat(Prompt.inputString("개인좌석유무? "));

  }

}
