package com.ogong.pms.servlet.ceoCafe;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.dao.CafeRoomDao;

@WebServlet("/ceomember/cafe/room/addform")
public class CeoCafeRoomAddFormContoller extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CafeDao cafeDao;
  CafeRoomDao cafeRoomDao;
  SqlSession sqlSession;

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
      int cafeNo = Integer.parseInt(request.getParameter("cafeno"));    //카페번호

      //      List<CafeRoom> cafeRoomList = cafeRoomDao.findCafeRoomListByCafe(cafeNo);

      request.setAttribute("cafeNo", cafeNo);
      //      request.setAttribute("cafeRoomList", cafeRoomList);
      request.getRequestDispatcher("/ceoCafe/CeoCafeRoomAddForm.jsp").forward(request, response);


    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
