package com.ogong.pms.servlet.cafe;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.domain.Cafe;

@WebServlet("/cafe/list")
public class CafeListController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CafeDao cafeDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cafeDao = (CafeDao) 웹애플리케이션공용저장소.getAttribute("cafeDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      List<Cafe> cafeList = cafeDao.getCafeListByMember();

      if (cafeList == null) {
        throw new Exception("등록된 스터디카페가 없습니다.");
      }

      request.setAttribute("cafeList", cafeList);
      request.getRequestDispatcher("/cafe/CafeList.jsp").forward(request, response);

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
