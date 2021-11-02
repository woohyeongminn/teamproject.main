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
import com.ogong.pms.dao.CafeReviewDao;
import com.ogong.pms.domain.CafeReview;

@WebServlet("/cafe/reviewList")
public class CafeMyReviewListController extends HttpServlet {
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

      //      Member member = AuthPerMemberLoginHandler.getLoginUser();
      //
      //      if (member == null) {
      //        System.out.println(" >> 로그인 한 회원만 볼 수 있습니다.");
      //        return;
      //      }
      int memberNo = Integer.parseInt(request.getParameter("perNo"));

      List<CafeReview> reviewList = cafeReviewDao.findReviewListByMember(memberNo);

      //      if (reviewList.isEmpty()) {
      //        throw new Exception("리뷰 내역이 없습니다.");
      //      } 

      request.setAttribute("perNo", memberNo);
      request.setAttribute("reviewList", reviewList);
      request.getRequestDispatcher("/cafe/CafeReviewList.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}