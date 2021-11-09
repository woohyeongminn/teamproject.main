package com.ogong.pms.servlet.cafe;

import java.io.IOException;
import java.sql.Date;
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

      for (CafeReservation cafeReser : reserList) {
        Date today = new Date(System.currentTimeMillis());

        if (today.compareTo(cafeReser.getUseDate()) > 0 && (cafeReser.getReservationStatus() == 1 || cafeReser.getReservationStatus() == 2)) {
          cafeReservationDao.updateReservationStatusComplete(cafeReser.getReservationNo());
          sqlSession.commit();
        }
      }

      reserList = cafeReservationDao.findReservationListByMember(member.getPerNo());

      for (CafeReservation cafeReser : reserList) {
        cafeReser.setWirteReviewLable(CafeHandlerHelper.getReviewStatusLabel(
            String.valueOf(cafeReser.getWirteReview())));

        if (cafeReser.getReservationStatus() != 1 && cafeReser.getReservationStatus() != 2) {
          cafeReser.setWirteReviewLable("작성불가");
        }
      }

      request.setAttribute("reserList", reserList);
      request.getRequestDispatcher("/cafe/CafeMyReservationList.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}