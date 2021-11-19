package com.ogong.pms.web.cafe;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.dao.CafeReviewDao;
import com.ogong.pms.dao.CafeRoomDao;
import com.ogong.pms.vo.Cafe;
import com.ogong.pms.vo.CafeReview;
import com.ogong.pms.vo.CafeRoom;

@Controller
public class CafeController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired CafeDao cafeDao;
  @Autowired CafeReviewDao cafeReviewDao;
  @Autowired CafeRoomDao cafeRoomDao;

  @GetMapping("/cafe/list")
  public ModelAndView list() throws Exception {

    List<Cafe> cafeList = cafeDao.getCafeListByMember();
    List<CafeRoom> cafeRoomList = cafeRoomDao.findAllCafeRoomListByMember();

    ModelAndView mv = new ModelAndView();
    mv.addObject("cafeList", cafeList);
    mv.addObject("cafeRoomList", cafeRoomList);
    mv.addObject("pageTitle", "스터디 장소 검색");
    mv.addObject("contentUrl", "cafe/CafeList.jsp");
    mv.setViewName("template3");

    return mv;
  }

  @GetMapping("/cafe/search")
  public ModelAndView search(String where, 
      String keyword) throws Exception {

    if (where.equals("1")) {
      where = "location";
    } else if (where.equals("2")) {
      where = "name";
    }

    List<Cafe> cafeList = cafeDao.findCafeListByLocation(where, keyword);
    List<CafeRoom> cafeRoomList = cafeRoomDao.findAllCafeRoomListByMember();

    ModelAndView mv = new ModelAndView();
    mv.addObject("cafeList", cafeList);
    mv.addObject("cafeRoomList", cafeRoomList);
    mv.addObject("pageTitle", "스터디 장소 검색");
    mv.addObject("contentUrl", "cafe/CafeList.jsp");
    mv.setViewName("template3");

    return mv;
  }

  @GetMapping("/cafe/detail")
  public ModelAndView detail(int no) throws Exception {

    Cafe cafe = cafeDao.findByCafeNoMember(no);
    cafe.setInfo(cafe.getInfo().replace("\n", "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"));

    HashMap<String,Object> params = new HashMap<>();
    params.put("cafeNo", cafe.getNo());
    cafe.setHoliday(cafeDao.getCafeHoliday(params));

    List<CafeReview> reviewList = cafeReviewDao.findReviewListByCafeNo(cafe.getNo());
    List<CafeRoom> roomList = cafeRoomDao.findCafeRoomListByMember(cafe.getNo());

    cafeDao.updateViewCount(cafe.getNo());
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.addObject("cafe", cafe);
    mv.addObject("reviewList", reviewList);
    mv.addObject("roomList", roomList);
    mv.addObject("roomListSize", roomList.size());
    mv.addObject("pageTitle", cafe.getName());
    mv.addObject("contentUrl", "cafe/CafeDetail.jsp");
    mv.setViewName("template1");

    return mv;
  }
}
