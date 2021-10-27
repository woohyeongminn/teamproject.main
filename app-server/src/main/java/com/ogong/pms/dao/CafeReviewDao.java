package com.ogong.pms.dao;

import java.util.List;
import com.ogong.pms.domain.CafeReview;

public interface CafeReviewDao {

  //-----------------------CafeReview--------------------------------------

  List<CafeReview> getCafeReviewList() throws Exception;
  List<CafeReview> findReviewListByCafeNo(int cafeNo) throws Exception;
  List<CafeReview> findReviewListByMember(int memberNo) throws Exception;
  CafeReview findByReviewNo(int reviewNo) throws Exception;
  void insertCafeReview(CafeReview cafeReview) throws Exception;
  void deleteCafeReview(int reviewNo) throws Exception;

}
