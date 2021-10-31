package com.ogong.pms.servlet.cafe;

import java.io.IOException;
import java.util.HashMap;
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
import com.ogong.pms.domain.CafeRoom;

@WebServlet("/cafe/reservationPay")
public class CafeReservationController3 extends HttpServlet {
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
      HashMap<Integer,String> reservationInfo = new HashMap<>();
      String[] selectedTime = request.getParameterValues("selectedTime");
      for(int i = 0; i < selectedTime.length; i++) {
        System.out.println(selectedTime[i]);

        //        if (Integer.parseInt(selectedTime[i].split(",")[0]) != 
        //            Integer.parseInt(selectedTime[i+1].split(",")[0]) -1) {
        //          response.setHeader("Refresh", "1;url=list");
        //          throw new Exception("연속된 시간만 선택 가능합니다. 다시 선택해 주세요.");
        //        }

        int index = Integer.parseInt(selectedTime[i].split(",")[0]);
        String startTime = selectedTime[i].split(",")[1];
        String endTime = selectedTime[i].split(",")[2];
        reservationInfo.put(index, startTime + "," + endTime);
      }

      int roomNo = Integer.parseInt(request.getParameter("roomNo"));
      CafeRoom cafeRoom = cafeRoomDao.findByRoomNo(roomNo);
      int totalPrice = cafeRoom.getRoomPrice() * reservationInfo.size();

      String selectedDate = request.getParameter("selectedDate");
      int people = Integer.parseInt(request.getParameter("people"));
      System.out.println("people : " + people);

      request.setAttribute("roomNo", roomNo);
      request.setAttribute("reservationInfo", reservationInfo);
      request.setAttribute("startTime", selectedTime[0].split(",")[1]);
      request.setAttribute("usingTime", reservationInfo.size());
      request.setAttribute("totalPrice", totalPrice);
      request.setAttribute("selectedDate", selectedDate);
      request.setAttribute("people", people);
      request.setAttribute("perNo", request.getParameter("perNo"));
      request.getRequestDispatcher("/cafe/CafeReservation3.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      //      request.setAttribute("error", e);
      //      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
