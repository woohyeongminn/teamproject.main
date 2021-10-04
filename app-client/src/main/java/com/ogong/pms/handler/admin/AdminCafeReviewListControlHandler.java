package com.ogong.pms.handler.admin;

import java.util.Collection;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.pms.handler.cafe.PromptCafe;
import com.ogong.util.Prompt;

public class AdminCafeReviewListControlHandler implements Command{

  PromptCafe promptcafe;

  public AdminCafeReviewListControlHandler(PromptCafe promptcafe) {
    this.promptcafe = promptcafe;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 장소 후기 목록");
    System.out.println();

    Collection<CafeReview> reviewList = promptcafe.getCafeReviewList();

    if (reviewList.isEmpty()) {
      System.out.println(" >> 리뷰 내역이 존재하지 않습니다.");
      return;
    }

    for (CafeReview cafeReview : reviewList) {
      if (cafeReview.getReviewStatus() == 1) {
        System.out.printf(" \n (%s)", cafeReview.getReviewNo());
        System.out.println(" >> 삭제한 리뷰입니다.\n");
        continue;
      }

      Cafe cafe = promptcafe.findByCafeNo(cafeReview.getCafe().getNo());
      System.out.printf(" (%d)\n [%s]\n 별점 : %d\n 내용 : %s\n 등록일 : %s\n",
          cafeReview.getReviewNo(), cafe.getName(), cafeReview.getGrade(),
          cafeReview.getContent(), cafeReview.getRegisteredDate());
      System.out.println();
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


  protected void deleteReview() throws Exception {
    System.out.println();
    System.out.println("▶ 리뷰 삭제하기");
    System.out.println();

    int userReviewNo = Prompt.inputInt(" 번호 : ");

    CafeReview cafeReview = promptcafe.findByReviewNo(userReviewNo);

    if (cafeReview == null) {
      return;
    }

    if (cafeReview.getReviewStatus() == 1) {
      System.out.println(" >> 이미 삭제된 리뷰입니다.");
      return;
    }

    String input = Prompt.inputString(" 정말 삭제하시겠습니까? (네 /아니오) ");
    System.out.println();
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 삭제를 취소합니다.");
      return;
    }

    promptcafe.deleteCafeReview(userReviewNo);
  }
}
