package com.ogong.pms.servlet.cafe;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogong.pms.dao.CafeReservationDao;

@WebServlet("/cafe/myReservationList")
public class CafeMyReviewAddFormController extends HttpServlet {
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
      int memberNo = Integer.parseInt(request.getParameter("perNo"));
      int reservationNo = Integer.parseInt(request.getParameter("reservationNo"));
      System.out.println(memberNo + ", " + reservationNo);

      request.setAttribute("perNo", memberNo);
      request.setAttribute("reservationNo", reservationNo);
      request.getRequestDispatcher("/cafe/CafeReviewForm.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}