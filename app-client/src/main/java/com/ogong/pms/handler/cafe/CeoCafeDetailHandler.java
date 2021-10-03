package com.ogong.pms.handler.cafe;

import java.util.Collection;
import java.util.HashMap;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.CafeRoom;
import com.ogong.pms.domain.CeoMember;
import com.ogong.pms.handler.AuthCeoMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class CeoCafeDetailHandler implements Command {

  PromptCafe promptcafe;
  int roomNo = 5;

  public CeoCafeDetailHandler (PromptCafe promptcafe) {
    this.promptcafe = promptcafe;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 장소 상세보기");
    System.out.println();

    CeoMember ceoMember = AuthCeoMemberLoginHandler.getLoginCeoMember();
    Cafe cafe = promptcafe.findByCafeNo(Prompt.inputInt(" 번호 : "));
    System.out.println();
    if (cafe == null || ceoMember.getCeoNo() != cafe.getCeoMember().getCeoNo() ||
        cafe.getCafeStatus() == Cafe.DELETE) {
      System.out.println(" >> 번호를 다시 선택하세요.");
      return;
    }

    System.out.printf(" (%s)\n", cafe.getNo());
    System.out.printf(" [%s]\n", cafe.getName());
    System.out.printf(" >> 사업자 등록번호 : %s\n", cafe.getCafeLicenseNo());
    System.out.printf(" >> 대표이미지 : %s\n", cafe.getMainImg());
    System.out.printf(" >> 소개글 : %s\n", cafe.getInfo());
    System.out.printf(" >> 주소 : %s\n", cafe.getLocation());
    System.out.printf(" >> 번호 : %s\n", cafe.getPhone());
    System.out.printf(" >> 오픈시간 : %s\n", cafe.getOpenTime());
    System.out.printf(" >> 마감시간 : %s\n", cafe.getCloseTime());
    System.out.printf(" >> 휴무일 : %s\n", cafe.getHoliday());
    System.out.printf(" >> 예약가능 인원 : %d\n", cafe.getBookable());
    System.out.printf(" >> 시간당 금액 : %d원\n", cafe.getTimePrice());
    System.out.printf(" >> 상태 : %s\n", CafeHandlerHelper.getCafeStatusLabel(cafe.getCafeStatus()));
    getStarRatingAverage(cafe); // 리뷰 평점계산
    listReview(cafe); // 리뷰 목록
    System.out.println();

    request.setAttribute("cafe", cafe);

    System.out.println("----------------------");
    System.out.println("1. 수정");
    System.out.println("2. 삭제");
    System.out.println("3. 스터디룸 관리");
    System.out.println("0. 이전");
    int selectNo = Prompt.inputInt(" 선택> ");
    switch (selectNo) {
      case 1: request.getRequestDispatcher("/ceoMember/cafeUpdate").forward(request); return;
      case 2: request.getRequestDispatcher("/ceoMember/cafeDelete").forward(request); return;
      case 3: controlStudyRoom(cafe);
      case 0: return;
      default : 
        System.out.println(" >> 번호를 다시 선택해 주세요.");
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

  private void controlStudyRoom(Cafe cafe) throws Exception {
    System.out.println();
    System.out.println("▶ 스터디룸 관리");
    System.out.println();

    Collection<CafeRoom> cafeRoom = promptcafe.findCafeRoomListByCafe(cafe.getNo());

    if (cafeRoom.isEmpty()) {
      System.out.println(" >> 등록된 스터디룸이 없습니다.");
      System.out.println("\n----------------------");
      System.out.println("1. 등록");
      System.out.println("0. 이전");
      int selectNo = Prompt.inputInt(" 선택> ");
      switch (selectNo) {
        case 1: StudyRoomAdd(cafe); return;
        case 0: return;
        default : 
          System.out.println(" >> 번호를 다시 선택해 주세요."); return;
      }
    }

    int i = 1;
    HashMap<Integer, Integer> selectRoomNo = new HashMap<>();
    for (CafeRoom myCafeRoom : cafeRoom) {
      System.out.println(" " + i + ". " + myCafeRoom.getRoomName());
      selectRoomNo.put(i, myCafeRoom.getRoomNo());
      i++;
    }

    System.out.println("\n----------------------");
    System.out.println("1. 등록");
    System.out.println("2. 상세");
    System.out.println("0. 이전");
    int selectNo = Prompt.inputInt(" 선택> ");
    switch (selectNo) {
      case 1: StudyRoomAdd(cafe); return;
      case 2: myCafeStudyRoomDetail(selectRoomNo, cafe); return;
      case 0: return;
      default : 
        System.out.println(" >> 번호를 다시 선택해 주세요.");
    }
  }

  private void StudyRoomAdd(Cafe cafe) throws Exception {
    System.out.println();
    System.out.println("▶ 스터디룸 등록");
    System.out.println();

    CafeRoom cafeRoom = new CafeRoom();
    cafeRoom.setCafe(cafe);
    cafeRoom.setRoomName(Prompt.inputString(" 스터디룸 이름 : "));
    cafeRoom.setRoomImg(Prompt.inputString(" 스터디룸 사진 : "));
    cafeRoom.setRoomInfo(Prompt.inputString(" 스터디룸 설명 : "));
    cafeRoom.setStartTime(cafe.getOpenTime());
    cafeRoom.setEndTime(cafe.getCloseTime());
    cafeRoom.setRoomPrice(Prompt.inputInt(" 스터디룸 시간당금액 : "));

    System.out.println();
    String input = Prompt.inputString(" 등록하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 등록이 취소되었습니다.");
      return;
    }
    cafeRoom.setRoomNo(roomNo++);

    promptcafe.insertCafeRoom(cafeRoom);
  }

  private void myCafeStudyRoomDetail(HashMap<Integer, Integer> selectRoomNo, Cafe cafe) throws Exception {
    System.out.println();
    System.out.println("▶ 스터디룸 상세");

    CafeRoom cafeRoom = null;
    int input;

    while (true) {
      System.out.println();
      input = Prompt.inputInt(" 번호 : ");

      if (input > selectRoomNo.size() || input <= 0) {
        System.out.println(" >> 번호를 다시 선택해 주세요.");
        continue;
      }

      cafeRoom = promptcafe.findByRoomNo(selectRoomNo.get(input));

      if (cafeRoom != null && cafeRoom.getCafe().getNo() == cafe.getNo()) {
        break;
      } 
    }

    System.out.println();
    System.out.printf(" (%s)\n", cafeRoom.getRoomNo());
    System.out.printf(" [%s - %s]\n", cafeRoom.getCafe().getName(), cafeRoom.getRoomName());
    System.out.printf(" >> 대표이미지 : %s\n", cafeRoom.getRoomImg());
    System.out.printf(" >> 소개글 : %s\n", cafeRoom.getRoomInfo());
    System.out.printf(" >> 시작시간 : %s\n", cafe.getOpenTime());
    System.out.printf(" >> 마감시간 : %s\n", cafe.getCloseTime());
    System.out.printf(" >> 시간당 금액 : %d원\n", cafeRoom.getRoomPrice());

    System.out.println("\n----------------------");
    System.out.println("1. 수정");
    System.out.println("2. 삭제");
    System.out.println("0. 이전");
    int selectNo = Prompt.inputInt(" 선택> ");
    switch (selectNo) {
      case 1: studyRoomUpdate(cafeRoom); return;
      case 2: studyRoomDelete(cafeRoom); return;
      case 0: return;
      default : 
        System.out.println(" >> 번호를 다시 선택해 주세요.");
    }
  }

  private void studyRoomUpdate(CafeRoom cafeRoom) throws Exception {
    System.out.println();
    System.out.println("▶ 스터디룸 수정");
    System.out.println();

    String name = Prompt.inputString(String.format(" 스터디룸 이름(%s) : ", cafeRoom.getRoomName()));
    String mainImg = Prompt.inputString(String.format(" 스터디룸 사진(%s) : ", cafeRoom.getRoomImg()));
    String Info = Prompt.inputString(String.format(" 스터디룸 설명(%s) : ", cafeRoom.getRoomInfo()));
    int timePrice = Prompt.inputInt(String.format(" 스터디룸 시간당금액(%d) : ", cafeRoom.getRoomPrice()));

    System.out.println();
    String input = Prompt.inputString(" 정말 수정하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 수정을 취소하였습니다.");
      return;
    }

    cafeRoom.setRoomName(name);
    cafeRoom.setRoomImg(mainImg);
    cafeRoom.setRoomInfo(Info);
    cafeRoom.setRoomPrice(timePrice);

    promptcafe.updateCafeRoom(cafeRoom);
  }

  public void studyRoomDelete(CafeRoom cafeRoom) throws Exception {
    System.out.println();
    System.out.println("▶ 스터디룸 삭제");
    System.out.println();

    String input = Prompt.inputString(" 정말 삭제하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 스터디룸 삭제를 취소하였습니다.");
      return;
    }

    promptcafe.deleteCafeRoom(cafeRoom.getRoomNo());
  }
}
