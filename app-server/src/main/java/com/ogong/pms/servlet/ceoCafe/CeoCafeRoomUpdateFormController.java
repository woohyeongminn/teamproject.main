package com.ogong.pms.servlet.ceoCafe;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CafeRoomDao;
import com.ogong.pms.domain.CafeRoom;

@WebServlet("/ceomember/cafe/room/updateform")
public class CeoCafeRoomUpdateFormController extends HttpServlet {
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
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {

      int no = Integer.parseInt(request.getParameter("no")); // 스터디룸 번호

      CafeRoom cafeRoom = cafeRoomDao.findByRoomNo(no);

      if (cafeRoom == null) {
        throw new Exception("해당 번호의 스터디룸이 없습니다.");
      }

      request.setAttribute("cafeRoom", cafeRoom);
      request.getRequestDispatcher("/ceoMember/CeoCafeRoomUpdateForm.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
