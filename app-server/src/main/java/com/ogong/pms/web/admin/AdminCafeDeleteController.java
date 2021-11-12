package com.ogong.pms.web.admin;

import static com.ogong.pms.domain.Cafe.DELETE;
import java.io.IOException;
import java.time.LocalTime;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.domain.Cafe;

@WebServlet("/admin/cafeDelete")
public class AdminCafeDeleteController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CafeDao cafeDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cafeDao = (CafeDao) 웹애플리케이션공용저장소.getAttribute("cafeDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  // 삭제할 때 파라미터로 cafeNo만 넘어왔으니까 그것만 주면 됨
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      Cafe cafe = cafeDao.findByCafeNo(Integer.parseInt(request.getParameter("cafeNo")));

      if (cafe == null) {
        throw new Exception(" >> 해당 번호의 장소가 존재하지 않습니다.");
      } 

      else if (cafe.getCafeStatus() == DELETE) {
        throw new Exception(" >> 이미 삭제된 장소입니다.");
      }

      cafe.setName("");
      cafe.setMainImg("");
      cafe.setInfo("");
      cafe.setLocation("");
      cafe.setPhone("");
      cafe.setOpenTime(LocalTime.of(00, 00));
      cafe.setCloseTime(LocalTime.of(00, 00));
      cafe.setHoliday("");
      cafe.setBookable(0);
      cafe.setTimePrice(0);
      cafe.setCafeStatus(DELETE);

      cafeDao.updateCafe(cafe);
      cafeDao.deleteCafe(cafe.getNo());
      sqlSession.commit();

      response.sendRedirect("cafeList");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}