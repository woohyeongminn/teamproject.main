package com.ogong.pms.web.admin;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.domain.Cafe;

@WebServlet("/admin/cafeList")
public class AdminCafeListController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CafeDao cafeDao;

  @Override
  public void init() throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = getServletContext();
    cafeDao = (CafeDao) 웹애플리케이션공용저장소.getAttribute("cafeDao");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      List<Cafe> cafeList = cafeDao.getCafeList();

      if (cafeList == null) {
        throw new Exception("등록된 스터디카페가 없습니다.");
      }

      request.setAttribute("cafeList", cafeList);
      request.setAttribute("pageTitle", "🏘 스터디 카페 목록");
      request.setAttribute("contentUrl", "/admin/AdminCafeList.jsp");

      request.getRequestDispatcher("/template1.jsp").forward(request, response);

      // request.getRequestDispatcher("/admin/AdminCafeList.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
