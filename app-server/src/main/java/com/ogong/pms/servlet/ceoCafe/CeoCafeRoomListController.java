package com.ogong.pms.servlet.ceoCafe;

import java.io.IOException;
import java.util.List;
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

@WebServlet("/ceomember/cafe/room/list")
public class CeoCafeRoomListController extends HttpServlet {
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
      int cafeNo = Integer.parseInt(request.getParameter("cafeno"));    //카페번호
      Cafe cafe = cafeDao.findByCafeNo(cafeNo);

      List<CafeRoom> cafeRoomList = cafeRoomDao.findCafeRoomListByCafe(cafeNo);

      //      if (cafeRoomList.isEmpty()) {
      //        request.setAttribute("cafeNo", cafeNo);
      //        request.setAttribute("cafeRoomList", cafeRoomList);
      //        request.getRequestDispatcher("/ceoCafe/CeoCafeRoomAddForm.jsp").forward(request, response);
      //        return;
      //      }

      request.setAttribute("cafeNo", cafeNo);
      request.setAttribute("cafeRoomList", cafeRoomList);

      request.setAttribute("pageTitle", "👩‍🏫 " + cafe.getName() + " -" + " 스터디룸 목록");
      request.setAttribute("contentUrl", "/ceoCafe/CeoCafeRoomList.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}

