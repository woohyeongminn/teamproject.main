package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class CafeHandler {

  List<Cafe> cafeList;
  List<Member> memberHandler;
  int findSize;

  public CafeHandler (List<Cafe> cafeList, List<Member> memberHandler) {
    this.cafeList = cafeList;
    this.memberHandler = memberHandler;
  }

  public void add () {
    System.out.println("[장소 등록]");

    Cafe cafe = new Cafe();

    cafe.setName(Prompt.inputString("장소명? "));
    //cafe.setMainImg(Prompt.inputString("대표사진? "));
    //cafe.setInfo(Prompt.inputString("소개글? "));
    cafe.setLocation(Prompt.inputString("주소? "));
    //cafe.setPhone(Prompt.inputString("전화번호? "));
    //cafe.setHrs(Prompt.inputString("운영시간? "));
    //    cafe.setPersonalSeat(Prompt.inputString("개인좌석유무?(y/n) "));
    //    cafe.setRoom(Prompt.inputString("미팅룸유무?(y/n) "));
    cafe.setBookable(Prompt.inputInt("예약가능인원? "));
    //    cafe.setUsingNotebooks(Prompt.inputString("노트북대여?(y/n) "));
    //    cafe.setUsingCopyMachine(Prompt.inputString("복사기사용?(y/n) "));
    //    cafe.setUsingWifi(Prompt.inputString("와이파이유무?(y/n) "));
    //    cafe.setDrinksProvided(Prompt.inputString("음료제공유무?(y/n) "));

    cafeList.add(cafe);
  }

  public void list() {
    System.out.println("[장소 목록]");

    Cafe[] list = cafeList.toArray(new Cafe[cafeList.size()]);
    for(Cafe cafe : list) {
      System.out.printf("이름: %s, 주소: %s, 예약가능인원: %d\n"
          , cafe.getName(), cafe.getLocation(), cafe.getBookable());
    }
  }

  public void find() {
    System.out.println("[장소 검색하기]");

    Cafe[] list = findByLocation(Prompt.inputString("지역? "));

    if (findSize != 0) {
      for (int i = 0; i < findSize; i++) {
        System.out.println(list[i].getName() + ", " + list[i].getLocation() + ", " + list[i].getBookable());
      }
    } else {
      System.out.println("검색 결과가 존재하지 않습니다.");
      return;
    }
  }

  private Cafe[] findByLocation(String location) {
    Cafe[] arr = cafeList.toArray(new Cafe[0]);
    Cafe[] findArr = new Cafe[20];
    int size = 0;

    for (int i = 0; i < cafeList.size(); i++) {
      if(arr[i].getLocation().contains(location)) {
        findArr[size++] = arr[i];
      }
    }
    this.findSize = size;
    return findArr;
  }

  public void detail() {
    System.out.println("[장소 상세보기]");

    Cafe cafe = findByName(Prompt.inputString("장소명? "));

    if (cafe == null) {
      System.out.println("해당 이름의 장소가 존재하지 않습니다.");
      return;
    }

    System.out.printf("장소명: %s\n", cafe.getName());
    System.out.printf("대표이미지: %s\n", cafe.getMainImg());
    System.out.printf("소개글: %s\n", cafe.getInfo());
    System.out.printf("주소: %s\n", cafe.getLocation());
    System.out.printf("전화번호: %s\n", cafe.getPhone());
    System.out.printf("운영시간: %s\n", cafe.getHrs());
    System.out.printf("개인좌석여부: %s\n", cafe.getPersonalSeat());
    System.out.printf("미팅룸여부: %s\n", cafe.getRoom());
    System.out.printf("예약가능인원: %d\n", cafe.getBookable());
    System.out.printf("노트북대여여부: %s\n", cafe.getBookable());
    System.out.printf("복사기사용여부: %s\n", cafe.getUsingCopyMachine());
    System.out.printf("와이파이유무: %s\n", cafe.getUsingWifi());
    System.out.printf("음료제공여부: %s\n", cafe.getDrinksProvided());

  }

  private Cafe findByName(String name) {
    Cafe[] arr = cafeList.toArray(new Cafe[0]);

    for (Cafe cafe : arr) {
      if (cafe.getName().equals(name)) {
        return cafe;
      }
    }
    return null;
  }

  public void update() {
    System.out.println("[장소 변경]");

  }

}
