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
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeRoom;

@WebServlet("/ceomember/cafe/room/updateform")
public class CeoCafeRoomUpdateFormController extends HttpServlet {
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
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {

      int roomNo = Integer.parseInt(request.getParameter("roomno")); // 스터디룸 번호

      CafeRoom cafeRoom = cafeRoomDao.findByRoomNo(roomNo);


      if (cafeRoom == null) {
        throw new Exception("해당 스터디룸이 없습니다.");
      }

      Cafe cafe = cafeDao.findByCafeNo(cafeRoom.getCafe().getNo());

      request.setAttribute("cafeRoom", cafeRoom);

      request.setAttribute("pageTitle", "👩‍🏫 " + cafe.getName() + " -" + " 스터디룸 수정");
      request.setAttribute("contentUrl", "/ceoCafe/CeoCafeRoomUpdateForm.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
