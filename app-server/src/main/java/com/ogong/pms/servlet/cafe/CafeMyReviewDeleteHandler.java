package com.ogong.pms.servlet.cafe;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CafeReviewDao;
import com.ogong.pms.domain.Member;

@WebServlet("/cafe/reviewDelete")
public class CafeMyReviewDeleteHandler extends HttpServlet {
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

      Member member = (Member)request.getSession().getAttribute("loginUser");
      int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));

      cafeReviewDao.deleteCafeReview(reviewNo);
      sqlSession.commit();

      response.sendRedirect("reviewList");

    } catch (Exception e) {
      e.printStackTrace();
    }
  } 
}

/*

CafeReview myReviewByNo = getMyReviewByNo(member, inputNo);

      if (myReviewByNo == null) {
        System.out.println(" >> 리뷰 번호를 잘못 선택하셨습니다.");
        return;
      }

      if (myReviewByNo.getReviewStatus() == 2) {
        System.out.println(" >> 이미 삭제한 리뷰입니다.");
        return;
      }

      String input = Prompt.inputString(" 정말 삭제하시겠습니까? (네 /아니오) ");
      if (!input.equalsIgnoreCase("네")) {
        System.out.println(" >> 삭제를 취소합니다.");
        return;
      }

 */