package com.ogong.pms.web.admin;

import java.io.IOException;
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

@WebServlet("/admin/cafeControl")
public class AdminCafeControlController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  // updateForm형식
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

      request.setAttribute("pageTitle", "🏘 스터디 카페 승인");
      request.setAttribute("contentUrl", "/admin/AdminCafeApprovalForm.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);

      //request.getRequestDispatcher("/admin/AdminCafeApprovalForm.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
