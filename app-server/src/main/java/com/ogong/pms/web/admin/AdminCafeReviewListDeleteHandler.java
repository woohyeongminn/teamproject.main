package com.ogong.pms.web.admin;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CafeReviewDao;
import com.ogong.pms.domain.CafeReview;

@WebServlet("/admin/reviewDelete")
public class AdminCafeReviewListDeleteHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CafeReviewDao cafeReviewDao;
  SqlSession sqlSession;

  @Override
  public void init() throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = getServletContext();
    cafeReviewDao = (CafeReviewDao) 웹애플리케이션공용저장소.getAttribute("cafeReviewDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {

      int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));

      CafeReview cafeReview = cafeReviewDao.findByReviewNo(reviewNo);

      cafeReviewDao.deleteCafeReview(reviewNo);
      sqlSession.commit();

      response.sendRedirect("cafeList");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  } 
}
