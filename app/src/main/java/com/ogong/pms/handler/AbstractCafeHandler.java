package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CafeReview;

public abstract class AbstractCafeHandler implements Command {

  List<Cafe> cafeList;
  List<CafeReview> reviewList;
  List<CafeReservation> reserList;

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


}
