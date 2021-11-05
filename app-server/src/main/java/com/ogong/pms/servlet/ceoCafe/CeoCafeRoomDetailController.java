package com.ogong.pms.servlet.ceoCafe;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.dao.CafeRoomDao;
import com.ogong.pms.domain.CafeRoom;

@WebServlet("/ceomember/cafe/room/detail")
public class CeoCafeRoomDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CafeDao cafeDao;
  CafeRoomDao cafeRoomDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cafeDao = (CafeDao) 웹애플리케이션공용저장소.getAttribute("cafeDao");
    cafeRoomDao = (CafeRoomDao) 웹애플리케이션공용저장소.getAttribute("cafeRoomDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      //Cafe cafe = cafeDao.findByCafeNo((int) request.getAttribute("cafeNo"));     // 카페번호

      int roomNo = Integer.parseInt(request.getParameter("roomno"));

      //CafeRoom cafeRoom = cafeRoomDao.findByRoomNo(roomNo);     // 스터디룸 번호

      CafeRoom cafeRoom = cafeRoomDao.findByRoomNo(roomNo);
      cafeRoom.setRoomInfo(cafeRoom.getRoomInfo().replace("\n", "<br><br>"));

      request.setAttribute("cafeRoom", cafeRoom);
      request.getRequestDispatcher("/ceoCafe/CeoCafeRoomDetail.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}