package com.ogong.pms.handler;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class CafeHandler {

  List<Cafe> cafeList;
  List<CafeReview> reviewList;
  List<CafeReservation> reserList;
  int reviewNo = 1; // 리뷰번호
  int reservationNo = 1; // 예약번호

  public CafeHandler (List<Cafe> cafeList, List<CafeReview> reviewList, List<CafeReservation> reserList) {
    this.cafeList = cafeList;
    this.reviewList = reviewList;
    this.reserList = reserList;

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
    System.out.println("[장소 등록]");

    Cafe cafe = new Cafe();

    cafe.setNo(Prompt.inputInt("카페 번호? "));
    //cafe.setCafeCeoEmail(Prompt.inputString("사장 아이디? "));
    //cafe.setCafeceoLicenseNo(Prompt.inputString("사업자번호? "));
    //cafe.setCafeCeoBossName(Prompt.inputString("대표자명? "));
    cafe.setName(Prompt.inputString("상호명? "));
    //cafe.setMainImg(Prompt.inputString("대표사진? "));
    //cafe.setInfo(Prompt.inputString("소개글? "));
    cafe.setLocation(Prompt.inputString("주소? "));
    //cafe.setPhone(Prompt.inputString("전화번호? "));
    //cafe.setOpenTime(Prompt.inputString("오픈시간? "));
    //cafe.setCloseTime(Prompt.inputString("마감시간? "));
    //cafe.setHoliday(Prompt.inputString("휴무일? "));
    cafe.setBookable(Prompt.inputInt("예약가능인원? "));
    //cafe.setTimePrice(Prompt.inputInt("시간당금액? "));

    cafeList.add(cafe);
  }

  public void list() {
    System.out.println();
    System.out.println("▶ 장소 목록");

    for(Cafe cafe : cafeList) {
      System.out.println();
      System.out.printf(" (%s)\n 이름 : %s\n 주소 : %s\n 예약가능인원 : %d\n"
          , cafe.getNo(), cafe.getName(), cafe.getLocation(), cafe.getBookable());
    }

  }

  public void find() {
    System.out.println();
    System.out.println("▶ 장소 검색");

    int count = 0;
    String input = Prompt.inputString("지역? ");
    for (Cafe cafe : cafeList) {
      if (cafe.getLocation().contains(input)) {
        System.out.printf(" 번호: %s, 이름: %s, 주소: %s, 예약가능인원: %d\n"
            , cafe.getNo(), cafe.getName(), cafe.getLocation(), cafe.getBookable());
        count++;
      }
    }
    if (count == 0) {
      System.out.println("검색 결과가 존재하지 않습니다.");
    }
  }

  public void detail() {
    System.out.println();
    System.out.println("▶ 장소 상세보기");

    Cafe cafe = findByName(Prompt.inputString("장소명? "));

    if (cafe == null) {
      System.out.println("해당 이름의 장소가 존재하지 않습니다.");
      return;
    }

    System.out.printf("카페번호: %s\n", cafe.getNo());
    System.out.printf("상호명: %s\n", cafe.getName());
    System.out.printf("대표이미지: %s\n", cafe.getMainImg());
    System.out.printf("소개글: %s\n", cafe.getInfo());
    System.out.printf("주소: %s\n", cafe.getLocation());
    System.out.printf("전화번호: %s\n", cafe.getPhone());
    System.out.printf("오픈시간: %s\n", cafe.getOpenTime());
    System.out.printf("마감시간: %s\n", cafe.getCloseTime());
    System.out.printf("휴무일: %s\n", cafe.getHoliday());
    System.out.printf("예약가능인원: %d\n", cafe.getBookable());
    System.out.printf("시간당금액: %d원\n", cafe.getTimePrice());
    System.out.println("===================리뷰===================");
    int reviewSize = 0;
    for (CafeReview review : reviewList) {
      if (review.getCafe().getNo() == cafe.getNo()) {
        String nickname = review.getMember().getPerNickname();
        System.out.printf("닉네임 : %s, 별점 : %d, 내용 : %s, 등록일 : %s\n",
            nickname, review.getGrade(), review.getContent(), review.getRegisteredDate());
        reviewSize++;
      }
    }
    if (reviewSize == 0) {
      System.out.println("등록된 리뷰가 없습니다.");
    }

    System.out.println();

    System.out.println("1. 예약하기");
    System.out.println("2. 뒤로가기");
    int selectNo = Prompt.inputInt("선택> ");
    switch (selectNo) {
      case 1 : addReservation(cafe); break;
      default : return;
    }
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
    System.out.println();
    System.out.println("▶ 장소 정보 변경");

    Cafe cafe = findByNo(Prompt.inputInt("장소 번호? "));

    if (cafe == null) {
      System.out.println("해당 번호의 장소가 존재하지 않습니다.");
      return;
    }

    String name = Prompt.inputString(String.format("장소명(%s): ", cafe.getName()));
    String mainImg = Prompt.inputString(String.format("대표이미지(%s):  ", cafe.getMainImg()));
    String Info = Prompt.inputString(String.format("소개글(%s):  ", cafe.getInfo()));
    String location = Prompt.inputString(String.format("주소(%s):  ", cafe.getLocation()));
    String phone = Prompt.inputString(String.format("전화번호(%s):  ", cafe.getPhone()));
    String openTime = Prompt.inputString(String.format("오픈시간(%s):  ", cafe.getOpenTime()));
    String closeTime = Prompt.inputString(String.format("마감시간(%s):  ", cafe.getCloseTime()));
    String holiday = Prompt.inputString(String.format("휴무일(%s):  ", cafe.getHoliday()));
    int bookable = Prompt.inputInt(String.format("예약가능인원(%d):  ", cafe.getBookable()));
    int timePrice = Prompt.inputInt(String.format("예약가능인원(%d):  ", cafe.getTimePrice()));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
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

  public void delete() {
    System.out.println();
    System.out.println("▶ 장소 삭제");
    Cafe cafe = findByNo(Prompt.inputInt("장소 번호? "));

    if (cafe == null) {
      System.out.println("해당 번호의 장소가 존재하지 않습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("장소 삭제를 취소하였습니다.");
      return;
    }

    cafeList.remove(cafe);

    System.out.println("장소를 삭제하였습니다.");

  }

  private Cafe findByNo(int no) {
    Cafe[] arr = cafeList.toArray(new Cafe[0]);

    for (Cafe cafe : arr) {
      if (cafe.getNo() == no) {
        return cafe;
      }
    }
    return null;
  }

  public void addReview(CafeReservation cafeReser) {
    System.out.println();
    System.out.println("▶ 리뷰 등록하기");

    if (LoginHandler.getLoginUser() == null) {
      System.out.println("로그인 한 회원만 등록 가능합니다.");
    } else {

      Cafe cafe = cafeReser.getCafe();

      CafeReview cafeReview = new CafeReview();

      // int reviewNo = Prompt.inputInt("리뷰작성 번호 : ");
      String content = Prompt.inputString("리뷰 내용 : ");
      int grade = Prompt.inputInt("별점(0~5점) : ");
      while (grade < 0 || grade > 5) {
        System.out.println("별점을 다시 입력해주세요.");
        grade = Prompt.inputInt("별점(0~5점) : ");
      }
      Member member = LoginHandler.getLoginUser();
      Date registeredDate = new Date(System.currentTimeMillis());

      String input = Prompt.inputString("정말 등록하시겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("리뷰 등록을 취소하였습니다.");
        return;
      }

      cafeReview.setReviewNo(reviewNo++);
      cafeReview.setContent(content);
      cafeReview.setGrade(grade);
      cafeReview.setCafe(cafe);
      cafeReview.setMember(member);
      cafeReview.setRegisteredDate(registeredDate);

      reviewList.add(cafeReview);
      cafeReser.setWirteReview(true);

      System.out.println("리뷰가 등록되었습니다.");
    }
  }

  public void addReservation(Cafe cafe) {
    System.out.println();
    System.out.println("▶ 예약하기");

    Member member = LoginHandler.getLoginUser();
    if (member == null) {
      System.out.println("로그인 한 회원만 예약 가능합니다.");
      return;
    }

    CafeReservation reservation = new CafeReservation();

    Date reservationDate = Prompt.inputDate("예약 날짜? ");
    //Date currentDate = new Date(System.currentTimeMillis());

    int startTime = Prompt.inputInt("시작시간?(0시~24시) ");
    int useTime = Prompt.inputInt("이용할 시간? ");
    int useMemberNumber = Prompt.inputInt("사용할 인원? ");
    int totalPrice = useTime * useMemberNumber * cafe.getTimePrice();
    System.out.printf("==> 총금액: %d원\n" , totalPrice);

    String input = Prompt.inputString("정말 예약하시겠습니까?(y/n) ");

    if (input.equalsIgnoreCase("n")) {
      System.out.println("장소예약을 취소하였습니다.");
      return;
    }


    reservation.setReservationNo(reservationNo++);
    reservation.setMember(member);
    reservation.setCafe(cafe);
    reservation.setReservationDate(reservationDate);
    reservation.setStartTime(startTime);
    reservation.setUseTime(useTime);
    reservation.setUseMemberNumber(useMemberNumber);
    reservation.setTotalPrice(totalPrice);
    reservation.setWirteReview(false);

    cafe.setBookable(cafe.getBookable() - 1);

    reserList.add(reservation);

    System.out.println();
    System.out.println("*** 예약 되었습니다 ***");

  }

  public void listReservation() {
    System.out.println();
    System.out.println("▶ 내 예약 내역 보기");

    if (LoginHandler.getLoginUser() == null) {
      System.out.println("로그인 한 회원만 볼 수 있습니다.");
      return;
    }

    int count = 0;
    for (CafeReservation cafeReser : reserList) {
      if (cafeReser.getMember().getPerEmail().equalsIgnoreCase(LoginHandler.getLoginUser().getPerEmail())) {
        System.out.printf(" 예약번호: %d, 예약날짜: %s, 예약장소: %s,"
            + "\n 시작시간: %d시, 이용시간: %d시간, 사용인원: %d명, "
            + "\n 결제금액: %d원, 리뷰작성여부: %s\n"
            , cafeReser.getReservationNo(), cafeReser.getReservationDate(), cafeReser.getCafe().getName()
            , cafeReser.getStartTime(), cafeReser.getUseTime(), cafeReser.getUseTime()
            , cafeReser.getTotalPrice() ,getReviewStatusLabel(String.valueOf(cafeReser.getWirteReview())));
        count++;
      } 
    }

    if (count == 0) {
      System.out.println("예약 내역이 존재하지 않습니다.");
      return;
    }

    if (Prompt.inputString("리뷰를 작성하시겠습니까?(y/n) ").equalsIgnoreCase("n")) {
      System.out.println("리뷰 작성을 취소합니다.");
      return;
    }

    System.out.println();
    int input = Prompt.inputInt("리뷰 작성할 예약번호를 입력하세요 : ");
    for (CafeReservation cafeReser : reserList) {
      if (input == cafeReser.getReservationNo() &&
          cafeReser.getMember().getPerEmail().equalsIgnoreCase(LoginHandler.getLoginUser().getPerEmail())) {
        if (!cafeReser.getWirteReview()) {
          System.out.println("리뷰 작성 화면으로 이동합니다.");
          addReview(cafeReser);
        } else {
          System.out.println("이미 리뷰를 작성한 예약입니다.");
        }
      }
    }
  }

  private String getReviewStatusLabel(String status) {
    switch (status) {
      case "true": return "작성완료";
      case "false": return "작성가능";
      default: return "오류";
    }
  }

}
