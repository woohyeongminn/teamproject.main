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
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.dao.CafeReservationDao;
import com.ogong.pms.dao.CafeRoomDao;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeRoom;

@WebServlet("/cafe/reservation")
public class CafeReservationController extends HttpServlet {
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
      int cafeNo = Integer.parseInt(request.getParameter("no"));

      Cafe cafe = cafeDao.findByCafeNoMember(cafeNo);

      if (cafe == null) {
        throw new Exception(" >> 해당 번호의 장소가 존재하지 않습니다.");
      }

      List<CafeRoom> roomList = cafeRoomDao.findCafeRoomListByCafe(cafe.getNo());
      request.setAttribute("roomList", roomList);
      request.getRequestDispatcher("/cafe/CafeReservation.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      //      request.setAttribute("error", e);
      //      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
