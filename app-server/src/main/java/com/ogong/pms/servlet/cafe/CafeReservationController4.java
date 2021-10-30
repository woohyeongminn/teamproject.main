package com.ogong.pms.servlet.cafe;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalTime;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.dao.CafeReservationDao;
import com.ogong.pms.dao.CafeRoomDao;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.Member;

@WebServlet("/cafe/reservationEnd")
public class CafeReservationController4 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CafeDao cafeDao;
  CafeRoomDao cafeRoomDao;
  CafeReservationDao cafeReservationDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cafeDao = (CafeDao) 웹애플리케이션공용저장소.getAttribute("cafeDao");
    cafeRoomDao = (CafeRoomDao) 웹애플리케이션공용저장소.getAttribute("cafeRoomDao");
    cafeReservationDao = (CafeReservationDao) 웹애플리케이션공용저장소.getAttribute("cafeReservationDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int roomNo = Integer.parseInt(request.getParameter("roomNo"));
      String startTime = request.getParameter("startTime");
      int usingTime = Integer.parseInt(request.getParameter("usingTime"));
      int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
      Date selectedDate = Date.valueOf(request.getParameter("selectedDate"));
      int people = Integer.parseInt(request.getParameter("people"));
      int memberNo = Integer.parseInt(request.getParameter("perNo"));

      CafeReservation reservation = new CafeReservation();

      Member member = new Member();
      member.setPerNo(memberNo);

      reservation.setMember(member);
      reservation.setUseDate(selectedDate);
      reservation.setStartTime(LocalTime.parse(startTime));
      reservation.setUseTime(usingTime);
      reservation.setUseMemberNumber(people);
      reservation.setTotalPrice(totalPrice);
      reservation.setRoomNo(roomNo);
      reservation.setReservationStatus(1); // 1 : 예약완료

      cafeReservationDao.insertReservation(reservation);
      sqlSession.commit();

      System.out.println(" *** 예약 되었습니다 ***");

      request.setAttribute("perNo", memberNo);
      request.getRequestDispatcher("/cafe/CafeReservationEnd.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      //      request.setAttribute("error", e);
      //      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
