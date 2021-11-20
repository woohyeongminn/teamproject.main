package com.ogong.pms.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ogong.pms.domain.CafeReview;

public interface CafeReviewDao {

  //-----------------------CafeReview--------------------------------------

  List<CafeReview> getCafeReviewList() throws Exception;
  List<CafeReview> findReviewListByCafeNo(int cafeNo) throws Exception;
  List<CafeReview> findReviewListByCafeNo_(
      @Param("cafeNo")int cafeNo, @Param("offset")int offset, @Param("length")int length) throws Exception;
  List<CafeReview> findReviewListByMember(
      @Param("memberNo")int memberNo, @Param("offset")int offset, @Param("length")int length) throws Exception;
  int countReviewList(@Param("column")String column, @Param("no")int no) throws Exception;
  CafeReview findByReviewNo(int reviewNo) throws Exception;
  void insertCafeReview(CafeReview cafeReview) throws Exception;
  void updateCafeReview(CafeReview cafeReview) throws Exception;
  void deleteCafeReview(int reviewNo) throws Exception;

}
