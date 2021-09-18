package com.ogong.pms.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.CafeRoom;
import com.ogong.pms.domain.CeoMember;
import com.ogong.util.Prompt;

public class CeoCafeDetailHandler extends AbstractCeoMemberHandler {

  List<CeoMember> ceoMemberList;
  List<Cafe> cafeList;
  List<CafeReview> reviewList;
  List<CafeRoom> roomList;

  public CeoCafeDetailHandler (List<CeoMember> ceoMemberList, List<Cafe> cafeList,
      List<CafeReview> reviewList, List<CafeRoom> roomList) {
    super (ceoMemberList);
    this.cafeList = cafeList;
    this.reviewList = reviewList;
    this.roomList = roomList;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 장소 상세보기");
    System.out.println();

    Cafe cafe = findByNo(Prompt.inputInt(" 번호 : "));
    System.out.println();
    if (cafe == null) {
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
    System.out.printf(" >> 상태 : %s\n", getCafeStatusLabel(cafe.getCafeStatus()));
    System.out.println();
    System.out.println("============= 리뷰 =============");
    int reviewSize = 0;
    for (CafeReview review : reviewList) {
      if (review.getCafe().getNo() == cafe.getNo()) {
        String nickname = review.getMember().getPerNickname();
        System.out.printf(" 닉네임 : %s | 별점 : %d | 내용 : %s | 등록일 : %s\n",
            nickname, review.getGrade(), review.getContent(), review.getRegisteredDate());
        reviewSize++;
      }
    }
    if (reviewSize == 0) {
      System.out.println(" >> 등록된 리뷰가 없습니다.");
    }

    request.setAttribute("cafe", cafe);

    System.out.println();
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

  private void controlStudyRoom(Cafe cafe) {
    System.out.println();
    System.out.println("▶ 스터디룸 관리");
    System.out.println();
    // 구현중

    List<CafeRoom> cafeRoom = myCafeStudyRoomList(cafe);

    if (cafeRoom == null) {
      System.out.println(" >> 등록된 스터디룸이 없습니다.");
      System.out.println("\n----------------------");
      System.out.println("1. 등록");
      System.out.println("0. 이전");
      int selectNo = Prompt.inputInt(" 선택> ");
      switch (selectNo) {
        case 1: return;
        case 0: return;
        default : 
          System.out.println(" >> 번호를 다시 선택해 주세요."); return;
      }
    }

    int i = 1;
    HashMap<Integer, Integer> selectRoomNo = new HashMap<>();
    for (CafeRoom myCafeRoom : cafeRoom) {
      if (cafe.getNo() == myCafeRoom.getCafe().getNo()) {
        System.out.println(" " + i + ". " + myCafeRoom.getRoomName());
        selectRoomNo.put(i, myCafeRoom.getRoomNo());
        i++;
      }
    }

    System.out.println("\n----------------------");
    System.out.println("1. 등록");
    System.out.println("2. 상세");
    System.out.println("0. 이전");
    int selectNo = Prompt.inputInt(" 선택> ");
    switch (selectNo) {
      case 1: return;
      case 2: myCafeStudyRoomDetail(selectRoomNo, cafe); return;
      case 0: return;
      default : 
        System.out.println(" >> 번호를 다시 선택해 주세요.");
    }
  }

  private List<CafeRoom> myCafeStudyRoomList(Cafe cafe) {
    List<CafeRoom> myCafeStudyRoomList = new ArrayList<>();
    for (CafeRoom cafeRoom : roomList) {
      if (cafeRoom.getCafe().getNo() == cafe.getNo()) {
        myCafeStudyRoomList.add(cafeRoom);
      }
    }
    return myCafeStudyRoomList;
  } 

  private void myCafeStudyRoomDetail(HashMap<Integer, Integer> selectRoomNo, Cafe cafe) {
    System.out.println();
    System.out.println("▶ 스터디룸 상세");

    int input;
    CafeRoom cafeRoom = new CafeRoom();
    // 오류,, 고쳐야함
    while (true) {
      System.out.println();
      input = Prompt.inputInt(" 번호 : ");
      cafeRoom = getCafeRoomByNo(selectRoomNo.get(input), cafe);

      if (cafeRoom == null) {
        System.out.println(" >> 번호를 다시 선택해 주세요.");
        continue;
      } else {
        break;
      }
    }

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
      case 1: return;
      case 2: return;
      case 0: return;
      default : 
        System.out.println(" >> 번호를 다시 선택해 주세요.");
    }

  }

  private CafeRoom getCafeRoomByNo(int roomNo, Cafe cafe) {
    CafeRoom myCafeRoom = new CafeRoom();
    for (CafeRoom cafeRoom : roomList) {
      if (cafeRoom.getRoomNo() == roomNo && cafeRoom.getCafe().getNo() == cafe.getNo()) {
        return cafeRoom;
      }
    }
    return myCafeRoom;
  }

  private Cafe findByNo(int cafeNo) {
    CeoMember ceoMember = AuthCeoMemberLoginHandler.getLoginCeoMember();

    for (Cafe cafe : cafeList) {
      if (cafe.getNo() == cafeNo && cafe.getCeoMember().getCeoNo() == ceoMember.getCeoNo()) {
        return cafe;
      }
    }
    return null;
  }
}
