package com.ogong.pms.servlet.cafe;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CafeReviewDao;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.Member;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class CafeMyReviewDeleteHandler implements Command {

  CafeReviewDao cafeReviewDao;
  SqlSession sqlSession;

  public CafeMyReviewDeleteHandler (CafeReviewDao cafeReviewDao, SqlSession sqlSession) {
    this.cafeReviewDao = cafeReviewDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    int inputNo = Prompt.inputInt(" 삭제할 리뷰 번호 : ");

    Member member = AuthPerMemberLoginController.getLoginUser();

    if (member == null) {
      System.out.println(" >> 로그인 한 회원만 삭제할 수 있습니다.");
      return;
    }

    CafeReview myReviewByNo = getMyReviewByNo(member, inputNo);

    if (myReviewByNo == null) {
      System.out.println(" >> 리뷰 번호를 잘못 선택하셨습니다.");
      return;
    }

    if (myReviewByNo.getReviewStatus() == 2) {
      System.out.println(" >> 이미 삭제한 리뷰입니다.");
      return;
    }

    String input = Prompt.inputString(" 정말 삭제하시겠습니까? (네 /아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 삭제를 취소합니다.");
      return;
    }

    cafeReviewDao.deleteCafeReview(myReviewByNo.getReviewNo());
    sqlSession.commit();

    System.out.println(" >> 삭제를 완료하였습니다.");
  }

  private CafeReview getMyReviewByNo(Member member, int reviewNo) throws Exception {
    List<CafeReview> reviewList = cafeReviewDao.getCafeReviewList();
    for (CafeReview cafeReview : reviewList) {
      if (cafeReview.getMember().getPerNo() == member.getPerNo() &&
          cafeReview.getReviewNo() == reviewNo) {
        return cafeReview;
      }
    }
    return null;
  }
}
