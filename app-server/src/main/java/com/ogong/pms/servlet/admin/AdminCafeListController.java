package com.ogong.pms.servlet.admin;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletConfig;
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
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cafeDao = (CafeDao) 웹애플리케이션공용저장소.getAttribute("cafeDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      List<Cafe> cafeList = cafeDao.getCafeList();

      if (cafeList == null) {
        throw new Exception("등록된 스터디카페가 없습니다.");
      }

      request.setAttribute("cafeList", cafeList);
      request.getRequestDispatcher("/admin/AdminCafeList.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      throw new ServletException(e);
    }
  }
}
