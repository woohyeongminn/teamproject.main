package com.ogong.pms.handler;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.Login;
import com.ogong.util.Prompt;

public class CafeHandler {

  List<Cafe> cafeList;
  List<CafeReview> reviewList;
  JoinHandler joinHandler;
  Login loginStatus;

  public CafeHandler (List<Cafe> cafeList, List<CafeReview> reviewList, JoinHandler joinHandler) {
    this.cafeList = cafeList;
    this.reviewList = reviewList;
    this.joinHandler = joinHandler;
  }

  public void add () {
    System.out.println("[장소 등록]");

    Cafe cafe = new Cafe();

    cafe.setNo(Prompt.inputInt("장소번호? "));
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
      System.out.printf("번호: %s, 이름: %s, 주소: %s, 예약가능인원: %d\n"
          , cafe.getNo(), cafe.getName(), cafe.getLocation(), cafe.getBookable());
    }
  }

  public void find() {
    System.out.println("[장소 검색하기]");

    List<Cafe> list = findByLocation(Prompt.inputString("지역? "));
    if (list != null) {
      for (Cafe cafe : list) {
        System.out.println(cafe.getName() + ",  "
            + cafe.getLocation() + ", " + cafe.getBookable());
      }
    } else {
      System.out.println("검색 결과가 존재하지 않습니다.");
      return;
    }
  }

  private List<Cafe> findByLocation(String location) {
    Cafe[] arr = cafeList.toArray(new Cafe[0]);
    List<Cafe> findArr = new ArrayList<>();

    for (Cafe cafe : arr) {
      if(cafe.getLocation().contains(location)) {
        findArr.add(cafe);
      }
    }
    return findArr;
  }

  public void detail() {
    System.out.println("[장소 상세보기]");

    Cafe cafe = findByName(Prompt.inputString("장소명? "));

    if (cafe == null) {
      System.out.println("해당 이름의 장소가 존재하지 않습니다.");
      return;
    }

    System.out.printf("장소번호: %s\n", cafe.getNo());
    System.out.printf("장소명: %s\n", cafe.getName());
    System.out.printf("대표이미지: %s\n", cafe.getMainImg());
    System.out.printf("소개글: %s\n", cafe.getInfo());
    System.out.printf("주소: %s\n", cafe.getLocation());
    System.out.printf("전화번호: %s\n", cafe.getPhone());
    System.out.printf("운영시간: %s\n", cafe.getHrs());
    System.out.printf("개인좌석여부: %s\n", cafe.getPersonalSeat());
    System.out.printf("미팅룸여부: %s\n", cafe.getRoom());
    System.out.printf("예약가능인원: %d\n", cafe.getBookable());
    System.out.printf("노트북대여여부: %s\n", cafe.getUsingNotebooks());
    System.out.printf("복사기사용여부: %s\n", cafe.getUsingCopyMachine());
    System.out.printf("와이파이유무: %s\n", cafe.getUsingWifi());
    System.out.printf("음료제공여부: %s\n", cafe.getDrinksProvided());

    System.out.println("===================리뷰===================");
    int reviewSize = 0;
    for (CafeReview review : reviewList) {
      if (review.getCafeNo() == cafe.getNo()) {
        String nickname = joinHandler.findByEmail(review.getEmail()).getJoinNickname();
        System.out.printf("닉네임 : %s, 별점 : %d, 내용 : %s, 등록일 : %s\n",
            nickname, review.getGrade(), review.getContent(), review.getRegisteredDate());
        reviewSize++;
      }
    }
    if (reviewSize == 0) {
      System.out.println("등록된 리뷰가 없습니다.");
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
    System.out.println("[장소 변경]");

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
    String hrs = Prompt.inputString(String.format("운영시간(%s):  ", cafe.getHrs()));
    String personalSeat = Prompt.inputString(String.format("개인좌석유무(%s):  ", cafe.getPersonalSeat()));
    String room = Prompt.inputString(String.format("미팅룸유무(%s):  ", cafe.getRoom()));
    int bookable = Prompt.inputInt(String.format("예약가능인원(%d):  ", cafe.getBookable()));
    String usingNotebooks = Prompt.inputString(String.format("노트북대여(%s):  ", cafe.getUsingNotebooks()));
    String usingCopyMachine = Prompt.inputString(String.format("복사기사용(%s):  ", cafe.getUsingNotebooks()));
    String usingWifi = Prompt.inputString(String.format("와이파이유무(%s):  ", cafe.getUsingWifi()));
    String drinksProvided = Prompt.inputString(String.format("음료제공유무(%s):  ", cafe.getDrinksProvided()));

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
    cafe.setHrs(hrs);
    cafe.setPersonalSeat(personalSeat);
    cafe.setRoom(room);
    cafe.setBookable(bookable);
    cafe.setUsingNotebooks(usingNotebooks);
    cafe.setUsingCopyMachine(usingCopyMachine);
    cafe.setUsingWifi(usingWifi);
    cafe.setDrinksProvided(drinksProvided);

    System.out.println("작업을 변경하였습니다.");

  }

  public void delete() {
    System.out.println("[장소 삭제]");
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

  public void loginStatus(Login login) {
    loginStatus = login;
  }

  public void addReview() {
    System.out.println("[리뷰 등록하기]");

    if (loginStatus == null) {
      System.out.println("로그인 한 회원만 등록 가능합니다.");
    } else {
      //System.out.println(loginStatus.getUserEmail());

      Cafe cafe = findByNo(Prompt.inputInt("장소번호? "));

      if (cafe == null) {
        System.out.println("해당 번호의 장소가 존재하지 않습니다.");
        return;
      }

      CafeReview cafeReview = new CafeReview();

      int reviewNo = Prompt.inputInt("리뷰번호 : ");
      String content = Prompt.inputString("리뷰내용 : ");
      int grade = Prompt.inputInt("별점(0~5점) : ");
      while (grade < 0 || grade > 5) {
        System.out.println("별점을 다시 입력해주세요.");
        grade = Prompt.inputInt("별점(0~5점) : ");
      }
      int cafeNo = cafe.getNo();
      String email = loginStatus.getUserEmail();
      Date registeredDate = new Date(System.currentTimeMillis());

      String input = Prompt.inputString("정말 등록하시겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("리뷰 등록을 취소하였습니다.");
        return;
      }

      cafeReview.setReviewNo(reviewNo);
      cafeReview.setContent(content);
      cafeReview.setGrade(grade);
      cafeReview.setCafeNo(cafeNo);
      cafeReview.setEmail(email);
      cafeReview.setRegisteredDate(registeredDate);

      reviewList.add(cafeReview);

      System.out.println("리뷰가 등록되었습니다.");
    }
  }

}
