package com.ogong.pms.servlet.admin;

import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CafeReviewDao;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class AdminCafeReviewListDeleteHandler implements Command{

  CafeReviewDao cafeReviewDao;
  SqlSession sqlSession;

  public AdminCafeReviewListDeleteHandler (CafeReviewDao cafeReviewDao, SqlSession sqlSession) {
    this.cafeReviewDao = cafeReviewDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 리뷰 삭제하기");
    System.out.println();

    int userReviewNo = Prompt.inputInt(" 번호 : ");

    CafeReview cafeReview = cafeReviewDao.findByReviewNo(userReviewNo);

    if (cafeReview == null) {
      System.out.println(" >> 리뷰가 존재하지 않습니다.");
      return;
    }

    if (cafeReview.getReviewStatus() == 2) {
      System.out.println(" >> 이미 삭제된 리뷰입니다.");
      return;
    }

    String input = Prompt.inputString(" 정말 삭제하시겠습니까? (네 /아니오) ");
    System.out.println();
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 삭제를 취소합니다.");
      return;
    }

    cafeReviewDao.deleteCafeReview(userReviewNo);
    sqlSession.commit();

    System.out.println(" >> 리뷰 삭제를 완료하였습니다.");
  }
}
