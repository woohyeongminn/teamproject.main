package com.ogong.pms.web.admin;

import java.util.List;
import javax.servlet.ServletContext;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.dao.CafeReviewDao;
import com.ogong.pms.domain.CafeReview;

@Controller
public class AdminCafeReviewControlHandler {

  @Autowired CafeDao cafeDao;
  @Autowired CafeReviewDao cafeReviewDao;
  @Autowired ServletContext sc;
  @Autowired SqlSessionFactory sqlSessionFactory;

  @GetMapping("/admin/reviewList")
  public ModelAndView reviewList() throws Exception {

    List<CafeReview> reviewList = cafeReviewDao.getCafeReviewList();

    ModelAndView mv = new ModelAndView();
    mv.addObject("reviewList", reviewList);
    mv.addObject("pageTitle", "🔖 스터디 카페 리뷰 목록");
    mv.addObject("contentUrl", "admin/AdminCafeReviewList.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @GetMapping("/admin/reviewDelete")
  public ModelAndView reviewDelete(int reviewNo) throws Exception {

    CafeReview cafeReview = cafeReviewDao.findByReviewNo(reviewNo);

    if (cafeReview == null) {
      throw new Exception (" >> 해당 번호의 리뷰가 없습니다.");
    }

    cafeReviewDao.deleteCafeReview(reviewNo);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:reviewList");
    //    mv.setViewName("redirect:cafeList");
    return mv;
  } 
}
