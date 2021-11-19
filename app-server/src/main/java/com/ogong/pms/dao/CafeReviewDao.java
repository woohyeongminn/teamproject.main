package com.ogong.pms.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ogong.pms.vo.CafeReview;

public interface CafeReviewDao {

  //-----------------------CafeReview--------------------------------------

  List<CafeReview> getCafeReviewList() throws Exception;
  List<CafeReview> findReviewListByCafeNo(int cafeNo) throws Exception;
  List<CafeReview> findReviewListByCafeNo_(@Param("cafeNo")int cafeNo, @Param("offset")int offset, @Param("length")int length) throws Exception;
  List<CafeReview> findReviewListByMember(int memberNo) throws Exception;
  int countReviewList(int cafeNo) throws Exception;
  CafeReview findByReviewNo(int reviewNo) throws Exception;
  void insertCafeReview(CafeReview cafeReview) throws Exception;
  void updateCafeReview(CafeReview cafeReview) throws Exception;
  void deleteCafeReview(int reviewNo) throws Exception;

}
