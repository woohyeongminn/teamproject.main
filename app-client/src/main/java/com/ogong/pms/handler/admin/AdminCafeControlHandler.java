package com.ogong.pms.handler.admin;

import static com.ogong.pms.domain.Cafe.DELETE;
import static com.ogong.pms.domain.Cafe.GENERAL;
import static com.ogong.pms.domain.Cafe.WAIT;
import java.util.Collection;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.pms.handler.cafe.CafeHandlerHelper;
import com.ogong.pms.handler.cafe.PromptCafe;
import com.ogong.util.Prompt;

public class AdminCafeControlHandler implements Command {

  PromptCafe promptcafe;

  public AdminCafeControlHandler (PromptCafe promptcafe) {
    this.promptcafe = promptcafe;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 장소 목록");

    Collection<Cafe> cafeList = promptcafe.getCafeList();

    if (cafeList.isEmpty()) {
      System.out.println(" >> 등록된 장소가 없습니다.");
      return;
    }

    for(Cafe cafe : cafeList) {
      if (cafe.getCafeStatus() == DELETE) {
        System.out.printf(" \n (%s)\n", cafe.getNo());
        System.out.println(" 삭제 된 장소입니다.");
        continue;
      }
      System.out.printf(" \n (%s)\n 이름 : %s\n 주소 : %s\n 예약가능인원 : %d\n"
          , cafe.getNo(), cafe.getName(), cafe.getLocation(), cafe.getBookable());
      if (cafe.getCafeStatus() == WAIT) {
        System.out.println(" * 승인 대기중인 카페입니다.");
      }
    }

    selectCafeDetailMenu();
  }

  private void selectCafeDetailMenu() throws Exception {
    System.out.println("\n----------------------");
    System.out.println("1. 상세");
    System.out.println("2. 승인");
    System.out.println("0. 이전");
    int input = Prompt.inputInt("선택> ");
    switch (input) {
      case 1: detail(); break;
      case 2: approvalCafe(); break;
      default : return;
    }
  }

  public void detail() throws Exception {
    System.out.println();
    System.out.println("▶ 장소 상세");
    System.out.println();

    Cafe cafe = promptcafe.findByCafeNo(Prompt.inputInt(" 장소 번호 : "));
    System.out.println();

    if (cafe == null || cafe.getCafeStatus() == DELETE) {
      System.out.println(" >> 해당 번호의 장소가 존재하지 않습니다.");
      return;
    }
    System.out.printf(" (%s)\n", cafe.getNo());
    System.out.printf(" [%s]\n", cafe.getName());
    System.out.printf(" >> 대표 이미지 : %s\n", cafe.getMainImg());
    System.out.printf(" >> 소개글 : %s\n", cafe.getInfo());
    System.out.printf(" >> 주소 : %s\n", cafe.getLocation());
    System.out.printf(" >> 번호 : %s\n", cafe.getPhone());
    System.out.printf(" >> 오픈 시간 : %s\n", cafe.getOpenTime());
    System.out.printf(" >> 마감 시간 : %s\n", cafe.getCloseTime());
    System.out.printf(" >> 휴무일 : %s\n", cafe.getHoliday());
    System.out.printf(" >> 예약 가능 인원 : %d\n", cafe.getBookable());
    System.out.printf(" >> 시간당 금액 : %d원\n", cafe.getTimePrice());
    System.out.printf(" >> 상태 : %s\n", CafeHandlerHelper.getCafeStatusLabel(cafe.getCafeStatus()));
    getStarRatingAverage(cafe); // 리뷰 평점계산
    listReview(cafe); // 리뷰 목록
    System.out.println();

    System.out.println("----------------------");
    System.out.println("1. 장소 삭제");
    System.out.println("0. 뒤로 가기");
    int selectNo = Prompt.inputInt("선택> ");
    switch (selectNo) {
      case 1 : delete(cafe.getNo()); break;
      default : return;
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

  public void delete(int cafeNo) throws Exception {
    System.out.println();
    System.out.println("▶ 장소 삭제");
    System.out.println();

    Cafe cafe = promptcafe.findByCafeNo(cafeNo);

    if (cafe == null) {
      System.out.println(" >> 해당 번호의 장소가 존재하지 않습니다.");
      return;
    }

    String input = Prompt.inputString(" 정말 삭제하시겠습니까? (네 / 아니오) ");
    System.out.println();
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 장소 삭제를 취소하였습니다.");
      return;
    }

    cafe.setCafeStatus(DELETE);

    promptcafe.deleteCafe(cafe);
  }

  private void approvalCafe() throws Exception {
    System.out.println();
    System.out.println("▶ 장소 승인");

    while (true) {
      System.out.println();
      int inputCafeNo = Prompt.inputInt(" 장소 번호 : ");

      Cafe cafe = promptcafe.findByCafeNo(inputCafeNo);

      if (cafe == null) {
        System.out.println(" >> 번호를 다시 선택하세요.");
        continue;
      } else if (cafe.getCafeStatus() != 0) {
        System.out.println(" >> 승인 대기 중인 카페가 아닙니다.\n    번호를 다시 선택하세요.");
        continue;
      } else if (cafe.getCafeStatus() == 0) {
        String input = Prompt.inputString(" 정말 승인하시겠습니까? (네 / 아니오) ");
        System.out.println();
        if (!input.equalsIgnoreCase("네")) {
          System.out.println(" >> 장소 승인을 취소하였습니다.");
          return;
        }
        cafe.setCafeStatus(GENERAL);
        promptcafe.updateCafe(cafe);
        System.out.printf(" >> '%s'를 승인하였습니다.\n", cafe.getName());
        return;
      }
    }
  }
}
