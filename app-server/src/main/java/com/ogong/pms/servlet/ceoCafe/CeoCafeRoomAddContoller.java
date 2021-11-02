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
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeRoom;

@WebServlet("/ceomember/cafe/room/add")
public class CeoCafeRoomAddContoller extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CafeDao cafeDao;
  CafeRoomDao cafeRoomDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cafeDao = (CafeDao) 웹애플리케이션공용저장소.getAttribute("cafeDao");
    cafeRoomDao = (CafeRoomDao) 웹애플리케이션공용저장소.getAttribute("cafeRoomDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int cafeNo = Integer.parseInt(request.getParameter("no"));    //카페번호

      Cafe cafe = cafeDao.findByCafeNo(cafeNo);

      CafeRoom cafeRoom = new CafeRoom();

      cafeRoom.setCafe(cafe);
      cafeRoom.setRoomName(request.getParameter("name"));
      cafeRoom.setRoomInfo(request.getParameter("info"));
      cafeRoom.setPeople(Integer.parseInt(request.getParameter("people")));
      cafeRoom.setRoomPrice(Integer.parseInt(request.getParameter("roomPrice")));

      //fileNames.add(new CafeImage(fileName));

      //      if (!fileNames.isEmpty()) {
      //        HashMap<String,Object> params = new HashMap<>();
      //        params.put("fileNames", fileNames);
      //        params.put("cafeRoomNo", cafeRoom.getRoomNo());
      //
      //        cafeRoomDao.insertCafeRoomImage(params);
      //      }

      cafeRoomDao.insertCafeRoom(cafeRoom);
      sqlSession.commit();

      response.sendRedirect("list?no="+ cafe.getNo());


    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
