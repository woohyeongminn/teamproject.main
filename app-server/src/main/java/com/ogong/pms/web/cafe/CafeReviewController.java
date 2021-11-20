package com.ogong.pms.web.cafe;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.dao.CafeReservationDao;
import com.ogong.pms.dao.CafeReviewDao;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.Member;

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
  public ModelAndView list(HttpSession session,
      @RequestParam(defaultValue = "1") int pageNo, 
      @RequestParam(defaultValue = "5") int pageSize) throws Exception {

    Member member = (Member) session.getAttribute("loginUser");

    int count = cafeReviewDao.countReviewList("rs.member_no", member.getPerNo());

    if (pageSize < 4 || pageSize > 10) {
      pageSize = 5;
    }

    int totalPage = count / pageSize + ((count % pageSize) > 0 ? 1 : 0);

    if (pageNo < 1 || pageNo > totalPage) {
      pageNo = 1;
    }

    int offset = pageSize * (pageNo - 1);
    int length = pageSize;

    List<CafeReview> reviewList = cafeReviewDao.findReviewListByMember(member.getPerNo(),offset,length);

    ModelAndView mv = new ModelAndView();

    mv.addObject("reviewList", reviewList);
    mv.addObject("count", count);
    mv.addObject("totalPage", totalPage);
    mv.addObject("pageNo", pageNo);
    mv.addObject("pageSize", pageSize);
    mv.addObject("pageTitle", "✒ 리뷰 내역");
    mv.addObject("contentUrl", "cafe/CafeReviewList.jsp");
    mv.setViewName("template1");

    return mv;
  }

  @PostMapping(value="/cafe/cafeReviewList")
  @ResponseBody
  public ResponseEntity<HashMap<String, Object>> cafeReviewList(int cafeNo,
      @RequestParam(defaultValue = "1") int pageNo, 
      @RequestParam(defaultValue = "4") int pageSize) throws Exception {

    HashMap<String,Object> result = new HashMap<>();

    int count = cafeReviewDao.countReviewList("sr.cafe_no", cafeNo);

    if (pageSize < 4 || pageSize > 10) {
      pageSize = 4;
    }

    int totalPage = count / pageSize + ((count % pageSize) > 0 ? 1 : 0);

    if (pageNo < 1 || pageNo > totalPage) {
      pageNo = 1;
    }

    int offset = pageSize * (pageNo - 1);
    int length = pageSize;

    List<CafeReview> reviewList = cafeReviewDao.findReviewListByCafeNo_(cafeNo, offset, length);

    for(CafeReview cafeReview : reviewList) {
      cafeReview.setStar(CafeHandlerHelper.getReviewGradeStatusLabel(cafeReview.getGrade()));
      cafeReview.setRegisteredDateStr(cafeReview.getRegisteredDate().toString());
    }

    Cafe cafe = cafeDao.findByCafeNoMember(cafeNo);

    result.put("reviewList", reviewList);
    result.put("totalPage", totalPage);
    result.put("pageNo", pageNo);
    result.put("pageSize", pageSize);
    result.put("count", count);
    result.put("grade", cafe.getAvgReview());

    return ResponseEntity.ok(result);
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
