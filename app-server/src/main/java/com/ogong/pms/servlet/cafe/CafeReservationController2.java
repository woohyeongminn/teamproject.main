package com.ogong.pms.servlet.cafe;

import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.dao.CafeReservationDao;
import com.ogong.pms.dao.CafeRoomDao;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CafeRoom;

@WebServlet("/cafe/reservationSelectTime")
public class CafeReservationController2 extends HttpServlet {
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
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      int roomNo = Integer.parseInt(request.getParameter("roomName"));
      String selectedDate = request.getParameter("date");
      int cafeNo = Integer.parseInt(request.getParameter("cafeNo"));

      //      System.out.println(roomNo + ", " + selectedDate + ", " + cafeNo);

      Cafe cafe = cafeDao.findByCafeNo(cafeNo);
      CafeRoom cafeRoom = cafeRoomDao.findByRoomNo(roomNo);
      List<CafeReservation> todayReserList = 
          cafeReservationDao.findReservationListByDate(selectedDate, roomNo);
      ArrayList<LocalTime> useTimeList = new ArrayList<>();

      try {
        if (!todayReserList.isEmpty()) {
          for (CafeReservation cafeReser : todayReserList) {
            for (int i = 0; i < cafeReser.getUseTime(); i++) {
              if (i == 0) {
                useTimeList.add(cafeReser.getStartTime().plusHours(1));
                continue;
              } else if (i > 0) {
                LocalTime tempTime = useTimeList.get(useTimeList.size()-1).plusHours(1);
                useTimeList.add(tempTime);
              }
            }
          }
        }
      } catch (NullPointerException e) {e.printStackTrace();}

      LocalTime startTime = cafe.getOpenTime();
      LocalTime endTime = cafe.getCloseTime();
      LocalTime tempEndTime = LocalTime.parse("00:00");
      String status = "";

      int availableTime = (int) ChronoUnit.HOURS.between(startTime, endTime);
      HashMap<Integer, String> statusOfNumber = new HashMap<>();

      //      System.out.println("\n [ 예약 현황 ]");
      for (int i = 0; i < availableTime; i++) {
        if (i == 0) {
          tempEndTime = startTime.plusHours(1);
        } else {
          startTime = tempEndTime;
          tempEndTime = startTime.plusHours(1);
        }

        status = "예약 가능";

        if (useTimeList.size() != 0) {
          for (int j = 0; j < useTimeList.size(); j++) {
            if (useTimeList.get(j).compareTo(tempEndTime) == 0) {
              status = "예약 불가";
            }
          }
        }
        //        System.out.printf(" %d. %s ~ %s : %s\n", i+1, startTime, tempEndTime, status);
        statusOfNumber.put(i+1, startTime + "," + tempEndTime + "," + status);
      }

      request.setAttribute("statusOfNumber", statusOfNumber);
      request.setAttribute("roomNo", roomNo);
      request.setAttribute("selectedDate", selectedDate);
      request.setAttribute("people", cafeRoom.getPeople());
      request.setAttribute("perNo", request.getParameter("perNo"));
      request.getRequestDispatcher("/cafe/CafeReservation2.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      //      request.setAttribute("error", e);
      //      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
