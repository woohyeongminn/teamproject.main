package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

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
      case 1: deleteMyReview(); break;
      default : return;
    }
  }

  public void deleteMyReview() {
    System.out.println();
    int inputNo = Prompt.inputInt("삭제할 리뷰번호 : ");

    Member member = AuthPerMemberLoginHandler.getLoginUser();

    if (member == null) {
      System.out.println("로그인 한 회원만 삭제할 수 있습니다.");
      return;
    }

    CafeReview myReviewByNo = getMyReviewByNo(member, inputNo);

    if (myReviewByNo == null) {
      System.out.println("리뷰번호를 잘못 선택하셨습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까? (네 /아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println("삭제를 취소합니다.");
      return;
    }

    reviewList.remove(myReviewByNo);
    System.out.println("삭제를 완료하였습니다.");
  }

  private CafeReview getMyReviewByNo(Member member, int reviewNo) {
    for (CafeReview cafeReview : reviewList) {
      if (cafeReview.getMember().getPerNickname().equals(member.getPerNickname()) &&
          cafeReview.getReviewNo() == reviewNo) {
        return cafeReview;
      }
    }
    return null;
  }
}
