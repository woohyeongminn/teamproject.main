package com.ogong.pms.servlet.cafe;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.dao.CafeReviewDao;
import com.ogong.pms.dao.CafeRoomDao;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReview;

@WebServlet("/cafe/detail")
public class CafeDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CafeDao cafeDao;
  CafeReviewDao cafeReviewDao;
  CafeRoomDao cafeRoomDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cafeDao = (CafeDao) 웹애플리케이션공용저장소.getAttribute("cafeDao");
    cafeReviewDao = (CafeReviewDao) 웹애플리케이션공용저장소.getAttribute("cafeReviewDao");
    cafeRoomDao = (CafeRoomDao) 웹애플리케이션공용저장소.getAttribute("cafeRoomDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {

      Cafe cafe = cafeDao.findByCafeNoMember(Integer.parseInt(request.getParameter("no")));
      if (cafe == null) {
        throw new Exception(" >> 해당 번호의 장소가 존재하지 않습니다.");
      }

      List<CafeReview> reviewList = cafeReviewDao.findReviewListByCafeNo(cafe.getNo());

      HashMap<String,Object> params = new HashMap<>();
      params.put("cafeNo", cafe.getNo());
      cafe.setHoliday(cafeDao.getCafeHoliday(params));

      cafeDao.updateViewCount(cafe.getNo());
      sqlSession.commit();

      request.setAttribute("cafe", cafe);
      request.setAttribute("reviewList", reviewList);
      request.getRequestDispatcher("/cafe/CafeDetail.jsp").forward(request, response);
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }

  private void listReview(Cafe cafe, PrintWriter out) throws Exception {
    int i = 1;
    out.println();
    out.println("================= 리뷰 =================<br>");

    List<CafeReview> reviewList = cafeReviewDao.findReviewListByCafeNo(cafe.getNo());

    if (reviewList.isEmpty()) {
      out.println("등록된 리뷰가 없습니다.<br><br>");
    } else {
      for (CafeReview review : reviewList) {
        if (review.getReviewStatus() == 2) {
          //System.out.printf(" \n (%s)\n", review.getReviewNo());
          out.printf("<div id='c-review'>삭제 된 리뷰입니다.</div><br>"/* , i++ */);
          continue;
        }
        String nickname = review.getMember().getPerNickname();
        out.printf("<div id='c-review'><span>%s</span> <span id='c-grade'>%s(%d/5)</span> <span id='c-grade'>%s</span> <br><p id='c-review-content'>%s</p><br></div>",
            /* i++, */ nickname
            , CafeHandlerHelper.getReviewGradeStatusLabel(review.getGrade())
            , review.getGrade()
            , review.getRegisteredDate()
            , review.getContent());
      }
    }
  }
}