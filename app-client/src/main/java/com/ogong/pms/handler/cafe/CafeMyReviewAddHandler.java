package com.ogong.pms.handler.cafe;

import java.sql.Date;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.Member;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class CafeMyReviewAddHandler implements Command {

  PromptCafe promptcafe;
  int reviewNo = 100; // 리뷰번호

  public CafeMyReviewAddHandler(PromptCafe promptcafe) {
    this.promptcafe = promptcafe;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 리뷰 등록하기");

    if (AuthPerMemberLoginHandler.getLoginUser() == null) {
      System.out.println(" >> 로그인 한 회원만 등록 가능합니다.");
      return;
    }

    Cafe cafe = promptcafe.findByCafeNoMember((int) request.getAttribute("cafeNo"));

    CafeReview cafeReview = new CafeReview();

    String content = Prompt.inputString(" 리뷰 내용 : ");
    int grade = Prompt.inputInt(" 별점(0~5점) : ");
    while (grade < 0 || grade > 5) {
      System.out.println(" 별점을 다시 입력해 주세요.");
      grade = Prompt.inputInt(" 별점(0~5점) : ");
    }
    Member member = AuthPerMemberLoginHandler.getLoginUser();
    Date registeredDate = new Date(System.currentTimeMillis());

    String input = Prompt.inputString(" 정말 등록하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 리뷰 등록을 취소하였습니다.");
      return;
    }

    cafeReview.setReviewNo(reviewNo++);
    cafeReview.setContent(content);
    cafeReview.setGrade(grade);
    cafeReview.setCafe(cafe);
    cafeReview.setMember(member);
    cafeReview.setRegisteredDate(registeredDate);
    cafeReview.setReviewStatus(0);

    promptcafe.insertCafeReview(cafeReview);
    promptcafe.updateWirteReview((int) request.getAttribute("reservationNo"));

  }
}
