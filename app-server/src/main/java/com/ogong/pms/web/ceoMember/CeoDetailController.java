package com.ogong.pms.web.ceoMember;

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
import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.CeoMember;
import com.ogong.pms.web.cafe.CafeHandlerHelper;

@WebServlet("/ceomember/detail")
public class CeoDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CeoMemberDao ceoMemberDao;
  CafeDao cafeDao;
  CafeReviewDao cafeReviewDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    ceoMemberDao = (CeoMemberDao) 웹애플리케이션공용저장소.getAttribute("ceoMemberDao");
    cafeDao = (CafeDao) 웹애플리케이션공용저장소.getAttribute("cafeDao");
    cafeReviewDao = (CafeReviewDao) 웹애플리케이션공용저장소.getAttribute("cafeReviewDao");
  }

  //마이페이지
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      CeoMember loginCeo = (CeoMember) request.getSession().getAttribute("loginCeoUser");
      CeoMember ceoMember = ceoMemberDao.findByNo(loginCeo.getCeoNo());

      if (ceoMember == null) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      } 

      Cafe cafe = cafeDao.findByCeoMember(loginCeo.getCeoNo());

      if (cafe != null) {

        String status = CafeHandlerHelper.getCafeStatusLabel(cafe.getCafeStatus());
        List<CafeReview> reviewList = cafeReviewDao.findReviewListByCafeNo(cafe.getNo());

        request.setAttribute("ceoMember", ceoMember);
        request.setAttribute("cafe", cafe);
        request.setAttribute("cafeStatus", status);
        request.setAttribute("reviewList", reviewList);
      }

      request.setAttribute("ceoMember", ceoMember);

      request.setAttribute("pageTitle", "🙂 마이페이지");
      request.setAttribute("contentUrl", "/ceoMember/CeoMemberDetail.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);


    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
