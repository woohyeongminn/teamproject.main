package com.ogong.pms.handler.cafe;

import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class CafeMyReviewAddHandler implements Command {

  CafeDao cafeDao;

  public CafeMyReviewAddHandler(CafeDao cafeDao) {
    this.cafeDao = cafeDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 리뷰 등록하기");

    if (AuthPerMemberLoginHandler.getLoginUser() == null) {
      System.out.println(" >> 로그인 한 회원만 등록 가능합니다.");
      return;
    }

    Cafe cafe = cafeDao.findByCafeNoMember((int) request.getAttribute("cafeNo"));

    CafeReview cafeReview = new CafeReview();

    String content = Prompt.inputString(" 리뷰 내용 : ");
    int grade = Prompt.inputInt(" 별점(0~5점) : ");
    while (grade < 0 || grade > 5) {
      System.out.println(" 별점을 다시 입력해 주세요.");
      grade = Prompt.inputInt(" 별점(0~5점) : ");
    }

    String input = Prompt.inputString(" 정말 등록하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 리뷰 등록을 취소하였습니다.");
      return;
    }

    cafeReview.setReservationNo((int) request.getAttribute("reservationNo"));
    cafeReview.setContent(content);
    cafeReview.setGrade(grade);

    cafeDao.insertCafeReview(cafeReview);
    cafeDao.updateCafeReservationReviewStatus(cafeReview.getReservationNo());

    System.out.println(" >> 리뷰 등록 완료.");

  }
}
