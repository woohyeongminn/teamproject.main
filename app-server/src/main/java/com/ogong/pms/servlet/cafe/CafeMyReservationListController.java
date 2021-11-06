package com.ogong.pms.servlet.cafe;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogong.pms.dao.CafeReservationDao;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.Member;

@WebServlet("/cafe/reservationList")
public class CafeMyReservationListController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CafeReservationDao cafeReservationDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cafeReservationDao = (CafeReservationDao) 웹애플리케이션공용저장소.getAttribute("cafeReservationDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      Member member = (Member)request.getSession().getAttribute("loginUser");

      List<CafeReservation> reserList = 
          cafeReservationDao.findReservationListByMember(member.getPerNo());

      request.setAttribute("reserList", reserList);
      request.getRequestDispatcher("/cafe/CafeMyReservationList.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}