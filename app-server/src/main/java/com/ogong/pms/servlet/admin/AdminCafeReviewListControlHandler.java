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
import com.ogong.pms.dao.CafeReviewDao;
import com.ogong.pms.domain.CafeReview;

@WebServlet("/admin/reviewList")
public class AdminCafeReviewListControlHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CafeDao cafeDao;
  CafeReviewDao cafeReviewDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cafeDao = (CafeDao) 웹애플리케이션공용저장소.getAttribute("cafeDao");
    cafeReviewDao = (CafeReviewDao) 웹애플리케이션공용저장소.getAttribute("cafeReviewDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {

      List<CafeReview> reviewList = cafeReviewDao.getCafeReviewList();

      request.setAttribute("reviewList", reviewList);
      request.getRequestDispatcher("/admin/AdminCafeReview.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
