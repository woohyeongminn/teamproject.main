package com.ogong.pms.web.cafe;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.dao.CafeReservationDao;
import com.ogong.pms.dao.CafeReviewDao;
import com.ogong.pms.vo.CafeReview;
import com.ogong.pms.vo.Member;

@Controller
public class CafeReviewController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired CafeDao cafeDao;
  @Autowired CafeReviewDao cafeReviewDao;
  @Autowired CafeReservationDao cafeReservationDao;

  @PostMapping("/cafe/reviewAdd")
  public ModelAndView add(CafeReview cafeReview) throws Exception {

    try {
      cafeReviewDao.insertCafeReview(cafeReview);
      cafeReservationDao.updateCafeReservationReviewStatus(cafeReview.getReservationNo());
      sqlSessionFactory.openSession().commit();
    } catch (Exception e) {
      sqlSessionFactory.openSession().rollback();
    }

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:reviewList");

    return mv;
  }

  @GetMapping("/cafe/reviewList")
  public ModelAndView list(HttpSession session) throws Exception {

    Member member = (Member) session.getAttribute("loginUser");

    List<CafeReview> reviewList = cafeReviewDao.findReviewListByMember(member.getPerNo());

    ModelAndView mv = new ModelAndView();

    mv.addObject("reviewList", reviewList);
    mv.addObject("count", reviewList.size());
    mv.addObject("pageTitle", "✒ 리뷰 내역");
    mv.addObject("contentUrl", "cafe/CafeReviewList.jsp");
    mv.setViewName("template1");

    return mv;
  }

  @GetMapping("/cafe/reviewUpdateForm")
  public ModelAndView updateForm(int reviewNo) throws Exception {

    CafeReview cafeReview = cafeReviewDao.findByReviewNo(reviewNo);

    ModelAndView mv = new ModelAndView();

    mv.addObject("cafeReview", cafeReview);
    mv.addObject("pageTitle", "📝 리뷰 수정");
    mv.addObject("contentUrl", "cafe/CafeReviewUpdateForm.jsp");
    mv.setViewName("/template1");

    return mv;
  }

  @PostMapping("/cafe/reviewUpdate")
  public ModelAndView update(CafeReview cafeReview) throws Exception {

    cafeReviewDao.updateCafeReview(cafeReview);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:reviewList");

    return mv;
  }

  @PostMapping("/cafe/reviewDelete")
  public ModelAndView delete(int reviewNo) throws Exception {

    cafeReviewDao.deleteCafeReview(reviewNo);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:reviewList");

    return mv;
  }
}
