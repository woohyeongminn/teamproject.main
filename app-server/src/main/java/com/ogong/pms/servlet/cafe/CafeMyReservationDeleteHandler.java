package com.ogong.pms.servlet.cafe;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CafeReservationDao;

@WebServlet("/cafe/reservationDelete")
public class CafeMyReservationDeleteHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CafeReservationDao cafeReservationDao;
  SqlSession sqlSession;

  @Override
  public void init() throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = getServletContext();
    cafeReservationDao = (CafeReservationDao) 웹애플리케이션공용저장소.getAttribute("cafeReservationDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    try {
      String[] reservationNo = request.getParameterValues("reservationNo");

      for(int i = 0; i < reservationNo.length; i++) {
        cafeReservationDao.deleteReservation(Integer.parseInt(reservationNo[i]), 3);
        sqlSession.commit();
      }

      response.sendRedirect("reservationList");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
