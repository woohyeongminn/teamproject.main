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
import com.ogong.pms.dao.CafeRoomDao;
import com.ogong.pms.domain.CafeRoom;

@WebServlet("/ceomember/cafe/roomlist")
public class CeoCafeRoomListController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CafeRoomDao cafeRoomDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cafeRoomDao = (CafeRoomDao) 웹애플리케이션공용저장소.getAttribute("cafeRoomDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int cafeNo = Integer.parseInt(request.getParameter("no"));    //카페번호

      List<CafeRoom> cafeRoomList = cafeRoomDao.findCafeRoomListByCafe(cafeNo);

      if (cafeRoomList.isEmpty()) {
        request.setAttribute("cafeNo", cafeNo);
        request.setAttribute("cafeRoomList", cafeRoomList);
        request.getRequestDispatcher("/ceoCafe/CeoCafeRoomAdd.jsp").forward(request, response);
        return;
      }

      request.setAttribute("cafeNo", cafeNo);
      request.setAttribute("cafeRoomList", cafeRoomList);
      request.getRequestDispatcher("/ceoCafe/CeoCafeRoomList.jsp").forward(request, response);

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}

