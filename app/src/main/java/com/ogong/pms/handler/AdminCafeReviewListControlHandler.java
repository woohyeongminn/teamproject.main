package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CafeReview;
import com.ogong.util.Prompt;

public class AdminCafeReviewListControlHandler extends AbstractCafeHandler {

  public AdminCafeReviewListControlHandler (List<Cafe> cafeList, List<CafeReview> reviewList, List<CafeReservation> reserList) {
    super (cafeList, reviewList, reserList);
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 장소 후기 목록");
    System.out.println();

    int count = 0;
    for (CafeReview cafeReview : reviewList) {
      if (cafeReview.getMember().getPerNickname() 
          != AuthAdminLoginHandler.getLoginAdmin().getMasterNickname()) {
        System.out.printf(" (%d)\n [%s]\n 별점 : %d\n 내용 : %s\n 등록일 : %s\n",
            cafeReview.getReviewNo(), cafeReview.getCafe().getName(), cafeReview.getGrade(),
            cafeReview.getContent(), cafeReview.getRegisteredDate());
        System.out.println();
        count++;
      } 
    }

    if (count == 0) {
      System.out.println(" >> 리뷰 내역이 존재하지 않습니다.");
      return;
    }

    System.out.println("----------------------");
    System.out.println("1. 리뷰 삭제");
    System.out.println("0. 뒤로 가기");

    int selectNo = Prompt.inputInt("선택> ");
    switch (selectNo) {
      case 1: deleteReview(); break;
      default : return;
    }
  }


  protected void deleteReview() {
    System.out.println();
    System.out.println("▶ 리뷰 삭제하기");
    System.out.println();
    int count = 0;

    int userReviewNo = Prompt.inputInt(" 번호 : ");

    CafeReview cafeReview = findByReview(userReviewNo);

    if (cafeReview.getMember().getPerNickname() 
        != AuthAdminLoginHandler.getLoginAdmin().getMasterNickname()) {
      count++;

      String input = Prompt.inputString(" 정말 삭제하시겠습니까? (네 /아니오) ");
      System.out.println();
      if (!input.equalsIgnoreCase("네")) {
        System.out.println(" >> 삭제를 취소합니다.");
        return;
      }

      reviewList.remove(cafeReview);
      System.out.println(" >> 삭제를 완료하였습니다.");
    } 

    if (count == 0) {
      System.out.println(" >> 리뷰 번호를 잘못 선택하셨습니다.");
    }
  }
}
