package com.ogong.pms.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.dao.CafeReviewDao;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.servlet.cafe.CafeHandlerHelper;

@WebServlet("/admin/cafeDetail")
public class AdminCafeDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CafeDao cafeDao;
  CafeReviewDao cafeReviewDao;
  SqlSession sqlSession;

  @Override
  public void init() throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = getServletContext();
    cafeDao = (CafeDao) 웹애플리케이션공용저장소.getAttribute("cafeDao");
    cafeReviewDao = (CafeReviewDao) 웹애플리케이션공용저장소.getAttribute("cafeReviewDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      Cafe cafe = cafeDao.findByCafeNo(Integer.parseInt(request.getParameter("no")));

      if (cafe == null) {
        System.out.println(" >> 해당 번호의 장소가 존재하지 않습니다.");
        return;
      }

      List<CafeReview> reviewList = cafeReviewDao.findReviewListByCafeNo(cafe.getNo());

      HashMap<String,Object> params = new HashMap<>();
      params.put("cafeNo", cafe.getNo());
      cafe.setHoliday(cafeDao.getCafeHoliday(params));

      cafeDao.updateViewCount(cafe.getNo());
      sqlSession.commit();

      request.setAttribute("cafe", cafe);
      request.setAttribute("reviewList", reviewList);
      //      request.setAttribute("perNo", request.getParameter("perNo"));

      request.setAttribute("pageTitle", "🏘 스터디 카페 상세");
      request.setAttribute("contentUrl", "/admin/AdminCafeDetail.jsp");

      request.getRequestDispatcher("/template1.jsp").forward(request, response);
      //request.getRequestDispatcher("/admin/AdminCafeDetail.jsp").forward(request, response);
    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }

  private void listReview(Cafe cafe, PrintWriter out) throws Exception {
    out.println();
    out.println("================= 리뷰 =================<br>");

    List<CafeReview> reviewList = cafeReviewDao.findReviewListByCafeNo(cafe.getNo());

    if (reviewList.isEmpty()) {
      System.out.println(" >> 등록된 리뷰가 없습니다.");
    } else {
      for (CafeReview review : reviewList) {
        if (review.getReviewStatus() == 2) {
          out.printf("<div id='c-review'>삭제 된 리뷰입니다.</div><br>");
          continue;
        }
        String nickname = review.getMember().getPerNickname();
        out.printf("<div id='c-review'><span>%s</span> <span id='c-grade'>%s(%d/5)</span> <span id='c-grade'>%s</span> <br><p id='c-review-content'>%s</p><br></div>",
            nickname
            , CafeHandlerHelper.getReviewGradeStatusLabel(review.getGrade())
            , review.getGrade()
            , review.getRegisteredDate()
            , review.getContent());
      }
    }
  }
}