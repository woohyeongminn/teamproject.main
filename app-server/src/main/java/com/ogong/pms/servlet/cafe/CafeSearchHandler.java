package com.ogong.pms.servlet.cafe;

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

@WebServlet("/cafe/search")
public class CafeSearchHandler extends HttpServlet {
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

      String input = request.getParameter("where");
      if (input.equals("1")) {
        input = "location";
      } else if (input.equals("2")) {
        input = "name";
      }

      String keyword = request.getParameter("keyword");

      List<Cafe> cafeList = cafeDao.findCafeListByLocation(input, keyword);

      request.setAttribute("cafeList", cafeList);
      request.setAttribute("pageTitle", "스터디 장소 검색");
      request.setAttribute("contentUrl", "/cafe/CafeList.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}