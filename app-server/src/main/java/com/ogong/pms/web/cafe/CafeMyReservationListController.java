package com.ogong.pms.web.cafe;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CafeReservationDao;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.Member;

@WebServlet("/cafe/reservationList")
public class CafeMyReservationListController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CafeReservationDao cafeReservationDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cafeReservationDao = (CafeReservationDao) 웹애플리케이션공용저장소.getAttribute("cafeReservationDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      Member member = (Member)request.getSession().getAttribute("loginUser");

      List<CafeReservation> reserList = 
          cafeReservationDao.findReservationListByMember(member.getPerNo());

      cafeReservationDao.updateReservationStatusComplete();
      sqlSession.commit();

      reserList = cafeReservationDao.findReservationListByMember(member.getPerNo());

      for (CafeReservation cafeReser : reserList) {

        if (cafeReser.getReservationStatus() == 7 && cafeReser.getWirteReview()) {
          cafeReser.setWirteReviewLable("작성완료");
        } else if(cafeReser.getReservationStatus() == 7 && !cafeReser.getWirteReview()) {
          cafeReser.setWirteReviewLable("작성가능");
        } else {
          cafeReser.setWirteReviewLable("작성불가");
        }
      }
      request.setAttribute("reserList", reserList);
      request.setAttribute("pageTitle", "내 예약 내역");
      request.setAttribute("contentUrl", "/cafe/CafeMyReservationList.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}