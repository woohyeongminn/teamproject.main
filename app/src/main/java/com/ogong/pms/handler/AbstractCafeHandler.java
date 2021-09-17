package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CafeReview;

public abstract class AbstractCafeHandler implements Command {

  List<Cafe> cafeList;
  List<CafeReview> reviewList;
  List<CafeReservation> reserList;
  int reviewNo = 1; // 리뷰번호
  int reservationNo = 4; // 예약번호

  public AbstractCafeHandler (List<Cafe> cafeList, List<CafeReview> reviewList,
      List<CafeReservation> reserList) {
    this.cafeList = cafeList;
    this.reviewList = reviewList;
    this.reserList = reserList;
  }

  protected String getReviewStatusLabel(String status) {
    switch (status) {
      case "true": return "작성완료";
      case "false": return "작성대기";
      default: return "오류";
    }
  }

  protected String getReviewGradeStatusLabel(int status) {
    switch (status) {
      case 0: return "☆☆☆☆☆";
      case 1: return "★☆☆☆☆";
      case 2: return "★★☆☆☆";
      case 3: return "★★★☆☆";
      case 4: return "★★★★☆";
      case 5: return "★★★★★";
      default: return "오류";
    }
  }

  protected Cafe findByNo(int no) {
    for (Cafe cafe : cafeList) {
      if (cafe.getNo() == no && cafe.getCafeStatus() != 0 && cafe.getCafeStatus() != 3) {
        return cafe;
      }
    }
    return null;
  }

  protected Cafe findByName(String name) {
    for (Cafe cafe : cafeList) {
      if (cafe.getName().equals(name) && cafe.getCafeStatus() != 0 && cafe.getCafeStatus() != 3) {
        return cafe;
      }
    }
    return null;
  }

  protected CafeReview findByReview (int reviewNo) {
    for (CafeReview cafeReview : reviewList) {
      if (cafeReview.getReviewNo() == reviewNo) {
        return cafeReview;
      }
    }
    return null;
  }
}
