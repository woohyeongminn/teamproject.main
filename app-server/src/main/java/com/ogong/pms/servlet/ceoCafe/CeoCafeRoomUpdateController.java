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
import com.ogong.pms.dao.CafeRoomDao;
import com.ogong.pms.domain.CafeRoom;

@WebServlet("/ceomember/cafe/room/update")
public class CeoCafeRoomUpdateController extends HttpServlet {
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
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int no = Integer.parseInt(request.getParameter("roomno")); // 룸 번호

      CafeRoom cafeRoom = cafeRoomDao.findByRoomNo(no);

      if (cafeRoom == null) {
        throw new Exception("등록된 스터디룸이 없습니다.");
      }

      cafeRoom.setRoomName(request.getParameter("name"));
      cafeRoom.setRoomInfo(request.getParameter("info"));
      cafeRoom.setPeople(Integer.parseInt(request.getParameter("people")));
      cafeRoom.setRoomPrice(Integer.parseInt(request.getParameter("roomPrice")));

      //    사진 어떻게 할까 고민....
      //    String mainImg = Prompt.inputString(String.format(" 스터디룸 사진(%s) : ", cafeRoom.getRoomImg()));
      //    cafeRoom.setRoomImg(mainImg);

      cafeRoomDao.updateCafeRoom(cafeRoom);
      sqlSession.commit();

      response.sendRedirect("detail?roomno="+ cafeRoom.getRoomNo());

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
