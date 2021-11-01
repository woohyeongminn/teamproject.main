package com.ogong.pms.servlet.cafe;

import java.io.IOException;
import javax.servlet.ServletConfig;
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
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cafeReservationDao = (CafeReservationDao) 웹애플리케이션공용저장소.getAttribute("cafeReservationDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {

      int memberNo = Integer.parseInt(request.getParameter("perNo"));
      int reservationNo = Integer.parseInt(request.getParameter("reservationNo"));

      cafeReservationDao.deleteReservation(reservationNo, 3);
      sqlSession.commit();

      response.sendRedirect("reservationDetail?perNo=" + memberNo + "&reservationNo=" + reservationNo);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

/*

Date today = new Date(System.currentTimeMillis());
      Date reserDate = myReservation.getUseDate();

      if (reserDate.toLocalDate().compareTo(today.toLocalDate()) > 0) {

        String input = Prompt.inputString(" 정말 예약 취소 하시겠습니까? (네 / 아니오) ");

        if (!input.equalsIgnoreCase("네")) {
          System.out.println(" >> 예약 취소를 취소합니다.");
          return;
        }

        cafeReservationDao.deleteReservation(myReservation.getReservationNo(), 3);
        sqlSession.commit();

        System.out.println(" >> 예약이 취소되었습니다.");

      } else if (reserDate.toLocalDate().compareTo(today.toLocalDate()) == 0) {
        System.out.println(" >> 당일 예약은 취소 불가능합니다.");
        return;
      } else {
        System.out.println(" >> 지난 예약은 선택할 수 없습니다.");
        return;
      }

 */
