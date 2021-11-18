package com.ogong.pms.web.ceoCafe;

import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.CafeReservationDao;
import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CeoMember;
import com.ogong.pms.web.cafe.CafeHandlerHelper;

@Controller
public class CeoCafeReservationController {

  @Autowired CeoMemberDao ceoMemberDao;
  @Autowired CafeReservationDao cafeReservationDao;
  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired ServletContext sc;

  // 예약 목록
  @GetMapping("/ceomember/cafe/reser/list")
  public ModelAndView reservationList(HttpSession session) throws Exception {

    CeoMember loginCeo = (CeoMember) session.getAttribute("loginCeoUser");
    List<CafeReservation> reserList = cafeReservationDao.findReservationListByCeoMember(loginCeo.getCeoNo());

    ModelAndView mv = new ModelAndView();

    if (reserList.isEmpty()) {
      mv.addObject("pageTitle", "📝 예약 내역 목록");
      mv.addObject("contentUrl", "ceoCafe/CeoCafeReservationList.jsp");
      mv.setViewName("template1");
      return mv;

    } else {
      // request.setAttribute("ceoMember", ceoMember);
      mv.addObject("reserList", reserList);

      mv.addObject("pageTitle", "📝 예약 내역 목록");
      mv.addObject("contentUrl", "ceoCafe/CeoCafeReservationList.jsp");
      mv.setViewName("template1");
      return mv;
    }
  }

  // 예약 상세
  @GetMapping("/ceomember/cafe/reser/detail")
  public ModelAndView reservationDetail(int resno, HttpSession session) throws Exception {

    CeoMember loginCeo = (CeoMember) session.getAttribute("loginCeoUser");
    CeoMember ceoMember = ceoMemberDao.findByNo(loginCeo.getCeoNo());

    CafeReservation cafeReservation = cafeReservationDao.findReservationByCeoMember(ceoMember.getCeoNo(), resno);

    String reviewStatusLable = CafeHandlerHelper.getReviewStatusLabel(
        String.valueOf(cafeReservation.getWirteReview()));

    String memberNick = cafeReservation.getMember().getPerNickname();

    System.out.println(cafeReservation);

    ModelAndView mv = new ModelAndView();

    mv.addObject("memberNick", memberNick);
    mv.addObject("cafeReser", cafeReservation);
    mv.addObject("reviewStatusLable", reviewStatusLable);
    mv.addObject("cafeReserEndTime", 
        cafeReservation.getStartTime().plusHours(cafeReservation.getUseTime()));

    mv.addObject("pageTitle", "📝 예약 내역 상세");
    mv.addObject("contentUrl", "ceoCafe/CeoCafeReservationDetail.jsp");
    mv.setViewName("template1");
    return mv;
  }

  // 예약 거절 폼
  @GetMapping("/ceomember/cafe/reser/rejectform")
  public ModelAndView reservationRejectForm(int resno) throws Exception {

    ModelAndView mv = new ModelAndView();

    mv.addObject("reserNo", resno);
    mv.addObject("pageTitle", "👩‍🏫 스터디룸 예약 거절");
    mv.addObject("contentUrl", "ceoCafe/CeoCafeReservationRejectForm.jsp");
    mv.setViewName("template1");
    return mv;
  }


  @GetMapping("/ceomember/cafe/reser/reject")
  public ModelAndView reservationReject(int resno) throws Exception {


    cafeReservationDao.deleteReservation(resno, 5);
    sqlSessionFactory.openSession().commit();

    //    } else if (reserDate.toLocalDate().compareTo(today.toLocalDate()) == 0) {
    //      System.out.println(" >> 당일 예약은 거절 불가능합니다.");
    //      return;
    //    } else {
    //      System.out.println(" >> 지난 예약은 선택할 수 없습니다.");
    //      return;
    //    }

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:detail?resno=" + resno);
    return mv;
  }
}
