package com.ogong.pms.web.admin;

import static com.ogong.pms.domain.Cafe.DELETE;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.dao.CafeReviewDao;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReview;

@Controller
public class AdminCafeController {

  @Autowired CafeDao cafeDao;
  @Autowired CafeReviewDao cafeReviewDao;
  @Autowired SqlSessionFactory sqlSessionFactory;

  @GetMapping("/admin/cafeList")
  public ModelAndView cafeList() throws Exception {

    List<Cafe> cafeList = cafeDao.getCafeList();

    if (cafeList == null) {
      throw new Exception("등록된 스터디카페가 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("cafeList", cafeList);
    mv.addObject("pageTitle", "🏘 스터디 카페 목록");
    mv.addObject("contentUrl", "admin/AdminCafeList.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @GetMapping("/admin/cafeDetail")
  public ModelAndView cafeDetail(Cafe cafe) throws Exception {

    Cafe cafeList = cafeDao.findByCafeNo(cafe.getNo());

    if (cafeList == null) {
      throw new Exception(" >> 해당 번호의 장소가 존재하지 않습니다.");
    }

    List<CafeReview> reviewList = cafeReviewDao.findReviewListByCafeNo(cafeList.getNo());

    HashMap<String,Object> params = new HashMap<>();
    params.put("cafeNo", cafeList.getNo());
    cafe.setHoliday(cafeDao.getCafeHoliday(params));

    cafeDao.updateViewCount(cafeList.getNo());
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.addObject("cafe", cafeList);
    mv.addObject("reviewList", reviewList);
    mv.addObject("pageTitle", "🏘 스터디 카페 상세");
    mv.addObject("contentUrl", "admin/AdminCafeDetail.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @GetMapping("/admin/cafeDelete")
  public ModelAndView cafeDelete(int cafeNo) throws Exception {

    Cafe oldCafe = cafeDao.findByCafeNo(cafeNo);

    if (oldCafe == null) {
      throw new Exception(" >> 해당 번호의 장소가 존재하지 않습니다.");
    } 

    else if (oldCafe.getCafeStatus() == DELETE) {
      throw new Exception(" >> 이미 삭제된 장소입니다.");
    }

    oldCafe.setName("");
    oldCafe.setMainImg("");
    oldCafe.setInfo("");
    oldCafe.setLocation("");
    oldCafe.setPhone("");
    oldCafe.setOpenTime(LocalTime.of(00, 00));
    oldCafe.setCloseTime(LocalTime.of(00, 00));
    oldCafe.setHoliday("");
    oldCafe.setBookable(0);
    oldCafe.setTimePrice(0);
    oldCafe.setCafeStatus(DELETE);

    cafeDao.updateCafe(oldCafe);
    cafeDao.deleteCafe(oldCafe.getNo());
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:cafeList");
    return mv;
  }

  @GetMapping("/admin/cafeControl") //updateForm 형식
  public ModelAndView cafeControl(int no) throws Exception {

    Cafe cafe = cafeDao.findByCafeNo(no);

    if (cafe == null) {
      throw new Exception(" >> 해당 번호의 장소가 존재하지 않습니다.");
    }

    List<CafeReview> reviewList = cafeReviewDao.findReviewListByCafeNo(cafe.getNo());

    HashMap<String,Object> params = new HashMap<>();
    params.put("cafeNo", cafe.getNo());
    cafe.setHoliday(cafeDao.getCafeHoliday(params));

    cafeDao.updateViewCount(cafe.getNo());
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.addObject("cafe", cafe);
    mv.addObject("reviewList", reviewList);
    mv.addObject("pageTitle", "🏘 스터디 카페 승인");
    mv.addObject("contentUrl", "admin/AdminCafeApprovalForm.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @PostMapping("/admin/cafeApproval") // update 형식
  public ModelAndView cafeApproval(int no, int cafeStatus) throws Exception {

    Cafe cafe = cafeDao.findByCafeNo(no);

    cafe.setCafeStatus(cafeStatus);

    cafeDao.updateCafeStatusToGENERAL(cafe.getNo());
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:cafeList");
    return mv;
  }
}
