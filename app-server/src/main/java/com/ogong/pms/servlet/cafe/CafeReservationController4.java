package com.ogong.pms.servlet.cafe;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CafeReservationDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.Member;

@WebServlet("/cafe/reservationEnd")
public class CafeReservationController4 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CafeReservationDao cafeReservationDao;
  StudyDao studyDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cafeReservationDao = (CafeReservationDao) 웹애플리케이션공용저장소.getAttribute("cafeReservationDao");
    studyDao = (StudyDao) 웹애플리케이션공용저장소.getAttribute("studyDao");
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

      List<Map<String,String>> myStudy = studyDao.findAllByMyNo(memberNo);

      request.setAttribute("perNo", memberNo);
      request.setAttribute("myStudy", myStudy);
      request.getRequestDispatcher("/cafe/CafeReservationEnd.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      //      request.setAttribute("error", e);
      //      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
