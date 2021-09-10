package com.ogong.pms.handler;

import java.time.LocalTime;
import java.util.List;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.CeoMember;
import com.ogong.util.Prompt;

public class CeoMyCafeListHandler extends AbstractCeoHandler {

  List<CeoMember> ceoMemberList;
  List<Cafe> cafeList;
  List<CafeReview> reviewList;
  int cafeNo = 15;

  public CeoMyCafeListHandler(List<CeoMember> ceoMemberList, List<Cafe> cafeList, List<CafeReview> reviewList) {
    super(ceoMemberList);
    this.cafeList = cafeList;
    this.reviewList = reviewList;
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 카페 목록");

    try {
      CeoMember ceoMember = AuthCeoMemberLoginHandler.getLoginCeoMember();

      for (Cafe cafe : cafeList) {
        if (cafe.getCeoNo() == ceoMember.getCeoNo()) {
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
    System.out.println("0. 이전");
    int selectNo = Prompt.inputInt("선택> ");
    switch (selectNo) {
      case 1 : myCafeAdd(); break;
      case 2 : myCafeDetail(); break;
      default : return;
    }
  }

  private void myCafeAdd () {
    System.out.println();
    System.out.println("▶ 장소 등록");
    System.out.println();

    CeoMember ceoMember = AuthCeoMemberLoginHandler.getLoginCeoMember();

    Cafe cafe = new Cafe();
    cafe.setNo(cafeNo++);
    cafe.setCeoNo(ceoMember.getCeoNo());
    cafe.setName(Prompt.inputString(" 상호명 : "));
    cafe.setMainImg(Prompt.inputString(" 대표사진 : "));
    cafe.setInfo(Prompt.inputString(" 소개글 : "));
    cafe.setLocation(Prompt.inputString(" 주소 : "));
    cafe.setPhone(Prompt.inputString(" 전화번호 : "));
    cafe.setOpenTime(LocalTime.parse(Prompt.inputString(" 오픈시간 (입력 예> 09:00) : ")));
    cafe.setCloseTime(LocalTime.parse(Prompt.inputString(" 마감시간 (입력 예> 21:00) : ")));
    cafe.setHoliday(Prompt.inputString(" 휴무일 : "));
    cafe.setBookable(Prompt.inputInt(" 예약가능인원 : "));
    cafe.setTimePrice(Prompt.inputInt(" 시간당금액 : "));
    cafe.setCafeStatus(1);

    cafeList.add(cafe);
  }

  private void myCafeDetail () {
    System.out.println();
    System.out.println("▶ 장소 상세보기");
    Cafe cafe = findByNo(Prompt.inputInt("번호 : "));
    System.out.println();
    if (cafe == null) {
      System.out.println(" >> 번호를 다시 선택하세요.");
      return;
    }
    System.out.printf(" (%s)\n", cafe.getNo());
    System.out.printf(" [%s]\n", cafe.getName());
    System.out.printf(" >> 대표이미지 : %s\n", cafe.getMainImg());
    System.out.printf(" >> 소개글 : %s\n", cafe.getInfo());
    System.out.printf(" >> 주소 : %s\n", cafe.getLocation());
    System.out.printf(" >> 번호 : %s\n", cafe.getPhone());
    System.out.printf(" >> 오픈시간 : %s\n", cafe.getOpenTime());
    System.out.printf(" >> 마감시간 : %s\n", cafe.getCloseTime());
    System.out.printf(" >> 휴무일 : %s\n", cafe.getHoliday());
    System.out.printf(" >> 예약가능 인원 : %d\n", cafe.getBookable());
    System.out.printf(" >> 시간당 금액 : %d원\n", cafe.getTimePrice());
    System.out.println();
    System.out.println("=============리뷰=============");
    int reviewSize = 0;
    for (CafeReview review : reviewList) {
      if (review.getCafe().getNo() == cafe.getNo()) {
        String nickname = review.getMember().getPerNickname();
        System.out.printf("닉네임 : %s | 별점 : %d | 내용 : %s | 등록일 : %s\n",
            nickname, review.getGrade(), review.getContent(), review.getRegisteredDate());
        reviewSize++;
      }
    }
    if (reviewSize == 0) {
      System.out.println("등록된 리뷰가 없습니다.");
    }

    System.out.println();

    System.out.println("1. 수정");
    System.out.println("2. 삭제");
    System.out.println("0. 이전");
    int selectNo = Prompt.inputInt(" 선택> ");
    switch (selectNo) {
      case 1 : break;
      default : return;
    }
  }

  private Cafe findByNo(int cafeNo) {
    CeoMember ceoMember = AuthCeoMemberLoginHandler.getLoginCeoMember();

    for (Cafe cafe : cafeList) {
      if (cafe.getNo() == cafeNo && cafe.getCeoNo() == ceoMember.getCeoNo()) {
        return cafe;
      }
    }
    return null;
  }
}
