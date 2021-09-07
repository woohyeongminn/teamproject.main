package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.Member;

public class CafeMyReviewListHandler extends AbstractCafeHandler {

  public CafeMyReviewListHandler (List<Cafe> cafeList, List<CafeReview> reviewList, List<CafeReservation> reserList) {
    super (cafeList, reviewList, reserList);
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 내가 쓴 후기 보기");
    System.out.println();

    Member member = AuthPerMemberLoginHandler.getLoginUser();

    if (member == null) {
      System.out.println("로그인 한 회원만 볼 수 있습니다.");
      return;
    }

    int count = 0;
    for (CafeReview cafeReview : reviewList) {
      if (cafeReview.getMember().getPerNickname().equals(member.getPerNickname())) {
        System.out.printf(" [%s]\n 별점 : %d\n 내용 : %s\n 등록일 : %s\n",
            cafeReview.getCafe().getName(), cafeReview.getGrade(), cafeReview.getContent(),
            cafeReview.getRegisteredDate());
        System.out.println();
        count++;
      } 
    }

    if (count == 0) {
      System.out.println("\n >> 리뷰 내역이 존재하지 않습니다.");
      return;
    }
  }
}
