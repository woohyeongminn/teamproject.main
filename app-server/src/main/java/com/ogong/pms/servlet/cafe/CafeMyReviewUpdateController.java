package com.ogong.pms.servlet.cafe;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CafeReviewDao;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.Member;

@WebServlet("/cafe/reviewUpdate")
public class CafeMyReviewUpdateController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CafeReviewDao cafeReviewDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cafeReviewDao = (CafeReviewDao) 웹애플리케이션공용저장소.getAttribute("cafeReviewDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      Member member = (Member)request.getSession().getAttribute("loginUser");

      CafeReview cafeReview = new CafeReview();
      cafeReview.setContent(request.getParameter("content"));
      cafeReview.setReviewNo(Integer.parseInt(request.getParameter("reviewNo")));
      cafeReview.setGrade(Integer.parseInt(request.getParameter("grade")));

      cafeReviewDao.updateCafeReview(cafeReview);
      sqlSession.commit();

      response.sendRedirect("reviewList?perNo=" + member.getPerNo());

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}