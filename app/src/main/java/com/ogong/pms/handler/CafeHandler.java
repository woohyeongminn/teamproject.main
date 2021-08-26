package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class CafeHandler {

  List<Cafe> cafeList;
  List<Member> memberHandler;
  int no;

  public CafeHandler (List<Cafe> cafeList, List<Member> memberHandler) {
    this.cafeList = cafeList;
    this.memberHandler = memberHandler;
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
    cafe.setPersonalSeat(Prompt.inputString("개인좌석유무?(y/n) "));
    cafe.setRoom(Prompt.inputString("미팅룸유무?(y/n) "));
    cafe.setBookable(Prompt.inputInt("예약가능인원? "));
    cafe.setUsingNotebooks(Prompt.inputString("노트북대여?(y/n) "));
    cafe.setUsingCopyMachine(Prompt.inputString("복사기사용?(y/n) "));
    cafe.setUsingWifi(Prompt.inputString("와이파이유무?(y/n) "));
    cafe.setDrinksProvided(Prompt.inputString("음료제공유무?(y/n) "));

    cafeList.add(cafe);
  }

}
