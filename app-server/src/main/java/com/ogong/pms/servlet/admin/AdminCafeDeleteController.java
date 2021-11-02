package com.ogong.pms.servlet.admin;

import static com.ogong.pms.domain.Cafe.DELETE;
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
        System.out.println(" >> 해당 번호의 장소가 존재하지 않습니다.");
        return;
      } 

      else if (cafe.getCafeStatus() == DELETE) {
        System.out.println(" >> 이미 삭제된 장소입니다.");
        return;
      }

      cafeDao.deleteCafe(cafe.getNo());
      sqlSession.commit();

      response.sendRedirect("cafeList");

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}