package com.ogong.pms.servlet.admin;

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

@WebServlet("/admin/cafeApproval")
public class AdminCafeApprovalController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  // update 형식
  CafeDao cafeDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cafeDao = (CafeDao) 웹애플리케이션공용저장소.getAttribute("cafeDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      Cafe cafe = cafeDao.findByCafeNo(Integer.parseInt(request.getParameter("no")));

      cafe.setCafeStatus(Integer.parseInt(request.getParameter("cafeStatus")));

      cafeDao.updateCafeStatusToGENERAL(cafe.getNo());
      sqlSession.commit();

      response.sendRedirect("cafeList");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
