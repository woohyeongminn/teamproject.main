package com.ogong.pms.handler.cafe;

import java.util.Collection;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.CafeRoom;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class CafeDetailHandler implements Command {

  PromptCafe promptcafe;

  public CafeDetailHandler (PromptCafe promptcafe) {
    this.promptcafe = promptcafe;

    //    CafeRoom cafeRoom = new CafeRoom();
    //    cafeRoom.setRoomNo(0);
    //    cafeRoom.setCafe(cafeList.get(0));
    //    cafeRoom.setRoomName("A타입(2~3인)");
    //    cafeRoom.setRoomImg("a_room.jpg");
    //    cafeRoom.setRoomInfo("2~3인이 이용할 수 있는 스터디룸 입니다.\n기본설비 : 화이트보드, 무선인터넷");
    //    cafeRoom.setStartTime(cafeList.get(0).getOpenTime());
    //    cafeRoom.setEndTime(cafeList.get(0).getCloseTime());
    //    cafeRoom.setRoomPrice(6000);
    //    cafeRoom.setRoomStatus(0);
    //    roomList.add(cafeRoom);
    //
    //    cafeRoom = new CafeRoom();
    //    cafeRoom.setRoomNo(1);
    //    cafeRoom.setCafe(cafeList.get(1));
    //    cafeRoom.setRoomName("A타입(2인)");
    //    cafeRoom.setRoomImg("a_room.jpg");
    //    cafeRoom.setRoomInfo("최대 2인이 이용할 수 있는 스터디룸 입니다.\n기본설비 : 화이트보드, 무선인터넷");
    //    cafeRoom.setStartTime(cafeList.get(1).getOpenTime());
    //    cafeRoom.setEndTime(cafeList.get(1).getCloseTime());
    //    cafeRoom.setRoomPrice(6000);
    //    cafeRoom.setRoomStatus(0);
    //    roomList.add(cafeRoom);
    //
    //    cafeRoom = new CafeRoom();
    //    cafeRoom.setRoomNo(2);
    //    cafeRoom.setCafe(cafeList.get(0));
    //    cafeRoom.setRoomName("B타입(3~4인)");
    //    cafeRoom.setRoomImg("b_room.jpg");
    //    cafeRoom.setRoomInfo("3~4인이 이용할 수 있는 스터디룸 입니다.\n기본설비 : 화이트보드, 무선인터넷");
    //    cafeRoom.setStartTime(cafeList.get(0).getOpenTime());
    //    cafeRoom.setEndTime(cafeList.get(0).getCloseTime());
    //    cafeRoom.setRoomPrice(9000);
    //    cafeRoom.setRoomStatus(0);
    //    roomList.add(cafeRoom);
    //
    //    cafeRoom = new CafeRoom();
    //    cafeRoom.setRoomNo(3);
    //    cafeRoom.setCafe(cafeList.get(1));
    //    cafeRoom.setRoomName("B타입(4인)");
    //    cafeRoom.setRoomImg("b_room.jpg");
    //    cafeRoom.setRoomInfo("최대 4인이 이용할 수 있는 스터디룸 입니다.\n기본설비 : 화이트보드, 무선인터넷");
    //    cafeRoom.setStartTime(cafeList.get(1).getOpenTime());
    //    cafeRoom.setEndTime(cafeList.get(1).getCloseTime());
    //    cafeRoom.setRoomPrice(9000);
    //    cafeRoom.setRoomStatus(0);
    //    roomList.add(cafeRoom);
    //
    //    cafeRoom = new CafeRoom();
    //    cafeRoom.setRoomNo(4);
    //    cafeRoom.setCafe(cafeList.get(0));
    //    cafeRoom.setRoomName("C타입(5~6인)");
    //    cafeRoom.setRoomImg("c_room.jpg");
    //    cafeRoom.setRoomInfo("5~6인이 이용할 수 있는 스터디룸 입니다.\n기본설비 : 화이트보드, 무선인터넷");
    //    cafeRoom.setStartTime(cafeList.get(0).getOpenTime());
    //    cafeRoom.setEndTime(cafeList.get(0).getCloseTime());
    //    cafeRoom.setRoomPrice(15000);
    //    cafeRoom.setRoomStatus(0);
    //    roomList.add(cafeRoom);
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 장소 상세보기");

    int inputNo = Prompt.inputInt(" 번호 : ");

    Cafe cafe = promptcafe.findByCafeNoMember(inputNo);

    if (cafe == null) {
      System.out.println(" >> 해당 번호의 장소가 존재하지 않습니다.");
      return;
    }

    System.out.println();
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
    getStarRatingAverage(cafe); // 리뷰 평점계산
    listReview(cafe); // 리뷰 목록
    System.out.println();

    if (cafe.getCafeStatus() == Cafe.STOP) {
      // 카페가 운영중단 상태일때는 예약 메뉴 출력 안하고 상세보기만
      return;
    }

    int roomCount = 0;
    Collection<CafeRoom> roomList = promptcafe.getCafeRoomList();
    for (CafeRoom cafeRoom : roomList) {
      if (cafeRoom.getCafe().getNo() == cafe.getNo()) {
        roomCount++;
      }
    }

    request.setAttribute("cafeNo", cafe.getNo());
    if (roomCount != 0) {
      System.out.println("----------------------");
      System.out.println("1. 일반 예약");
      System.out.println("2. 스터디룸 예약");
      System.out.println("0. 이전");

      int selectNo = Prompt.inputInt(" 선택> ");
      switch (selectNo) {
        case 1 : 
          request.setAttribute("selectReservation", "addReservation");
          request.getRequestDispatcher("/cafe/reservation").forward(request); 
          return;
        case 2 : 
          request.setAttribute("selectReservation", "addRoomReservation");
          request.getRequestDispatcher("/cafe/reservation").forward(request); 
          return;
        case 0 : return;
        default : 
          System.out.println(" >> 번호를 다시 선택해 주세요.");
      } 
    } else {
      System.out.println("----------------------");
      System.out.println("1. 일반 예약");
      System.out.println("0. 이전");

      int selectNo = Prompt.inputInt(" 선택> ");
      switch (selectNo) {
        case 1 : 
          request.setAttribute("selectReservation", "addReservation");
          request.getRequestDispatcher("/cafe/reservation").forward(request); 
          return;
        case 0 : return;
        default : 
          System.out.println(" >> 번호를 다시 선택해 주세요.");
      } 
    }       
  }

  private void getStarRatingAverage(Cafe cafe) throws Exception {
    int starRating = 0;
    int starRatingCount = 0;
    double starRatingAverage = 0;

    Collection<CafeReview> reviewList = promptcafe.findReviewListByCafeNo(cafe.getNo());

    if (!reviewList.isEmpty()) {
      for (CafeReview review : reviewList) {
        if (review.getReviewStatus() == 1) {
          continue;
        }
        starRating += review.getGrade();
        starRatingAverage =(double) starRating / ++starRatingCount;
      }
    } 

    System.out.printf(" >> 리뷰평점 : ★ %.1f(%d)\n" , starRatingAverage , starRatingCount);
  }

  private void listReview(Cafe cafe) throws Exception {
    int i = 1;
    System.out.println();
    System.out.println("============= 리뷰 =============");

    Collection<CafeReview> reviewList = promptcafe.findReviewListByCafeNo(cafe.getNo());

    if (reviewList.isEmpty()) {
      System.out.println(" >> 등록된 리뷰가 없습니다.");
    } else {
      for (CafeReview review : reviewList) {
        if (review.getReviewStatus() == 1) {
          //System.out.printf(" \n (%s)\n", review.getReviewNo());
          System.out.printf(" (%d) | 삭제 된 리뷰입니다. |\n", i++);
          continue;
        }
        String nickname = review.getMember().getPerNickname();
        System.out.printf(" (%d) 닉네임 : %s | 별점 : %s | 내용 : %s | 등록일 : %s\n",
            i++, nickname, CafeHandlerHelper.getReviewGradeStatusLabel(review.getGrade())
            , review.getContent(), review.getRegisteredDate());
      }
    }
  }
}