package com.ogong.pms.handler.cafe;

import java.util.Collection;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.Member;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class CafeMyReviewListHandler implements Command {

  PromptCafe promptcafe;

  public CafeMyReviewListHandler (PromptCafe promptcafe) {
    this.promptcafe = promptcafe;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 내가 쓴 후기 보기");
    System.out.println();

    Member member = AuthPerMemberLoginHandler.getLoginUser();

    if (member == null) {
      System.out.println(" >> 로그인 한 회원만 볼 수 있습니다.");
      return;
    }

    Collection<CafeReview> reviewList = promptcafe.findReviewListByMember(member.getPerNo());

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

      System.out.printf(" (%d)\n [%s]\n 별점 : %s (%d)\n 내용 : %s\n 등록일 : %s\n",
          cafeReview.getReviewNo(), cafe.getName()
          , CafeHandlerHelper.getReviewGradeStatusLabel(cafeReview.getGrade()), cafeReview.getGrade()
          , cafeReview.getContent(), cafeReview.getRegisteredDate());
      System.out.println();
    }

    System.out.println("----------------------");
    System.out.println("1. 삭제");
    System.out.println("0. 이전");

    int selectNo = Prompt.inputInt("선택> ");
    switch (selectNo) {
      case 1: deleteMyReview(); break;
      default : return;
    }
  }

  public void deleteMyReview() throws Exception {
    System.out.println();
    int inputNo = Prompt.inputInt(" 삭제할 리뷰 번호 : ");

    Member member = AuthPerMemberLoginHandler.getLoginUser();

    if (member == null) {
      System.out.println(" >> 로그인 한 회원만 삭제할 수 있습니다.");
      return;
    }

    CafeReview myReviewByNo = getMyReviewByNo(member, inputNo);

    if (myReviewByNo == null) {
      System.out.println(" >> 리뷰 번호를 잘못 선택하셨습니다.");
      return;
    }

    String input = Prompt.inputString(" 정말 삭제하시겠습니까? (네 /아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 삭제를 취소합니다.");
      return;
    }

    promptcafe.deleteCafeReview(myReviewByNo.getReviewNo());
  }

  private CafeReview getMyReviewByNo(Member member, int reviewNo) throws Exception {
    Collection<CafeReview> reviewList = promptcafe.getCafeReviewList();
    for (CafeReview cafeReview : reviewList) {
      if (cafeReview.getMember().getPerNo() == member.getPerNo() &&
          cafeReview.getReviewNo() == reviewNo) {
        return cafeReview;
      }
    }
    return null;
  }
}
