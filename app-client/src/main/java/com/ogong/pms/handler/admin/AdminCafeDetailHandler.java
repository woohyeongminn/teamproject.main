package com.ogong.pms.handler.admin;

import java.util.List;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.dao.CafeReviewDao;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.pms.handler.cafe.CafeHandlerHelper;
import com.ogong.util.Prompt;

public class AdminCafeDetailHandler implements Command {

  CafeDao cafeDao;
  CafeReviewDao cafeReviewDao;

  public AdminCafeDetailHandler (CafeDao cafeDao, CafeReviewDao cafeReviewDao) {
    this.cafeDao = cafeDao;
    this.cafeReviewDao = cafeReviewDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 장소 상세");
    System.out.println();

    Cafe cafe = cafeDao.findByCafeNo(Prompt.inputInt(" 장소 번호 : "));
    System.out.println();

    if (cafe == null) {
      System.out.println(" >> 해당 번호의 장소가 존재하지 않습니다.");
      return;
    }

    if (cafe.getHoliday() == null) {
      cafe.setHoliday("없음");
    }

    System.out.printf(" (%s)\n", cafe.getNo());
    System.out.printf(" [%s]\n", cafe.getName());
    System.out.printf(" >> 대표 이미지 : %s\n", cafe.getCafeImageNames());
    System.out.printf(" >> 소개글 : %s\n", cafe.getInfo());
    System.out.printf(" >> 주소 : %s\n", cafe.getLocation());
    System.out.printf(" >> 번호 : %s\n", cafe.getPhone());
    System.out.printf(" >> 오픈 시간 : %s\n", cafe.getOpenTime());
    System.out.printf(" >> 마감 시간 : %s\n", cafe.getCloseTime());
    System.out.printf(" >> 이번주 휴무일 : %s\n", cafe.getHoliday());
    System.out.printf(" >> 상태 : %s\n", CafeHandlerHelper.getCafeStatusLabel(cafe.getCafeStatus()));
    System.out.printf(" >> 리뷰평점 : ★ %.1f(%d)\n" , cafe.getAvgReview(), cafe.getCountReview());
    listReview(cafe); // 리뷰 목록
    System.out.println();

    request.setAttribute("cafeNo", cafe.getNo());

    System.out.println("----------------------");
    System.out.println("1. 장소 삭제");
    System.out.println("0. 뒤로 가기");
    int selectNo = Prompt.inputInt("선택> ");
    switch (selectNo) {
      case 1 : request.getRequestDispatcher("/cafe/controlDelete").forward(request); break;
      case 0: return;
      default :
        System.out.println(" >> 번호를 다시 선택해 주세요.");
    }
  }

  private void listReview(Cafe cafe) throws Exception {
    int i = 1;
    System.out.println();
    System.out.println("============= 리뷰 =============");

    List<CafeReview> reviewList = cafeReviewDao.findReviewListByCafeNo(cafe.getNo());

    if (reviewList.isEmpty()) {
      System.out.println(" >> 등록된 리뷰가 없습니다.");
    } else {
      for (CafeReview review : reviewList) {
        if (review.getReviewStatus() == 2) {
          //System.out.printf(" \n (%s)\n", review.getReviewNo());
          System.out.printf(" (%d) | 삭제 된 리뷰입니다. |\n", i++);
          continue;
        }
        String nickname = review.getMember().getPerNickname();
        System.out.printf(" (%d) 닉네임 : %s | 별점 : %s | 내용 : %s | 등록일 : %s\n",
            i++, nickname, CafeHandlerHelper.getReviewGradeStatusLabel(review.getGrade())
            , review.getContent(), review.getRegisteredDate());
      }
    }
  }
}
