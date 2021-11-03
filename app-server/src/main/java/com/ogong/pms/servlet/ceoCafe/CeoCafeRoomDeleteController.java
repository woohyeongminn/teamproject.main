package com.ogong.pms.servlet.ceoCafe;

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
import com.ogong.pms.dao.CafeRoomDao;
import com.ogong.pms.domain.CafeRoom;

@WebServlet("/ceomember/cafe/room/delete")
public class CeoCafeRoomDeleteController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CafeRoomDao cafeRoomDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cafeRoomDao = (CafeRoomDao) 웹애플리케이션공용저장소.getAttribute("cafeRoomDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int cafeno = Integer.parseInt(request.getParameter("cafeno"));

      int roomNo = Integer.parseInt(request.getParameter("no"));
      CafeRoom cafeRoom = cafeRoomDao.findByRoomNo(roomNo);

      if (!cafeRoom.getRoomImgs().isEmpty()) {
        HashMap<String,Object> deleteParams = new HashMap<>();
        deleteParams.put("fileNames", cafeRoom.getRoomImgs());
        cafeRoomDao.deleteCafeRoomImage(deleteParams);
      }

      cafeRoomDao.deleteCafeRoom(cafeRoom.getRoomNo());
      sqlSession.commit();

      response.sendRedirect("list?no="+ cafeno);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
