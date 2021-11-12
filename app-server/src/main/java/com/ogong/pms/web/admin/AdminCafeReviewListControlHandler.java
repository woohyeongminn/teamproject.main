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
import com.ogong.pms.dao.CafeReviewDao;
import com.ogong.pms.domain.CafeReview;

@WebServlet("/admin/reviewList")
public class AdminCafeReviewListControlHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CafeDao cafeDao;
  CafeReviewDao cafeReviewDao;

  @Override
  public void init() throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = getServletContext();
    cafeDao = (CafeDao) 웹애플리케이션공용저장소.getAttribute("cafeDao");
    cafeReviewDao = (CafeReviewDao) 웹애플리케이션공용저장소.getAttribute("cafeReviewDao");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {

      List<CafeReview> reviewList = cafeReviewDao.getCafeReviewList();

      request.setAttribute("reviewList", reviewList);
      request.setAttribute("pageTitle", "🔖 스터디 카페 리뷰 목록");
      request.setAttribute("contentUrl", "/admin/AdminCafeReviewList.jsp");

      request.getRequestDispatcher("/template1.jsp").forward(request, response);

      //request.getRequestDispatcher("/admin/AdminCafeReviewList.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
