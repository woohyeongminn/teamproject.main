package com.ogong.pms.web.ceoMember;

import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.dao.CafeReviewDao;
import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.CeoMember;
import com.ogong.pms.web.cafe.CafeHandlerHelper;

@Controller
public class CeoDetailController {

  @Autowired CeoMemberDao ceoMemberDao;
  @Autowired CafeDao cafeDao;
  @Autowired CafeReviewDao cafeReviewDao;
  @Autowired ServletContext sc;

  //마이페이지
  @GetMapping("/ceomember/detail")
  public ModelAndView ceoDetail(HttpSession session) throws Exception {

    CeoMember loginCeo = (CeoMember) session.getAttribute("loginCeoUser");
    //      CeoMember ceoMember = ceoMemberDao.findByNo(loginCeo.getCeoNo());
    //
    //      if (ceoMember == null) {
    //        throw new Exception("해당 번호의 회원이 없습니다.");
    //      } 
    //      Cafe cafe = cafeDao.findByCeoMember(loginCeo.getCeoNo());

    CeoMember ceoMember = ceoMemberDao.findByNo(loginCeo.getCeoNo());

    if (ceoMember == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    } 

    Cafe cafe = cafeDao.findByCeoMember(ceoMember.getCeoNo());

    ModelAndView mv = new ModelAndView();

    if (cafe != null) {
      String status = CafeHandlerHelper.getCafeStatusLabel(cafe.getCafeStatus());
      List<CafeReview> reviewList = cafeReviewDao.findReviewListByCafeNo(cafe.getNo());

      mv.addObject("ceoMember", ceoMember);
      mv.addObject("cafe", cafe);
      mv.addObject("cafeStatus", status);
      mv.addObject("reviewList", reviewList);
    }

    mv.addObject("ceoMember", ceoMember);

    mv.addObject("pageTitle", "🙂 마이페이지");
    mv.addObject("contentUrl", "ceoMember/CeoMemberDetail.jsp");
    mv.setViewName("template1");
    return mv;
  }
}
