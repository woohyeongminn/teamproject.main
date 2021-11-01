package com.ogong.pms.servlet.ceoCafe;

import java.io.IOException;
import java.util.List;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.dao.CafeReviewDao;
import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.CeoMember;
import com.ogong.pms.servlet.cafe.CafeHandlerHelper;

@WebServlet("/ceomember/cafe/detail")
public class CeoCafeDetailController extends GenericServlet {
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

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      CeoMember ceoMember = ceoMemberDao.findByNo(no);

      Cafe cafe = cafeDao.findByCeoMember(ceoMember.getCeoNo());

      if (cafe == null) {
        throw new Exception("등록된 카페가 없습니다.");
      }

      String status = CafeHandlerHelper.getCafeStatusLabel(cafe.getCafeStatus());
      List<CafeReview> reviewList = cafeReviewDao.findReviewListByCafeNo(cafe.getNo());

      request.setAttribute("ceoMember", ceoMember);
      request.setAttribute("cafe", cafe);
      request.setAttribute("cafeStatus", status);
      request.setAttribute("reviewList", reviewList);

      request.getRequestDispatcher("/ceoCafe/CeoCafeDetail.jsp").forward(request, response);

      //      request.getRequestDispatcher("/ceoMember/cafeAdd").forward(request); return;
      //      request.getRequestDispatcher("/ceoMember/cafeUpdate").forward(request); return;
      //      request.getRequestDispatcher("/ceoMember/cafeDelete").forward(request); return;
      //      request.getRequestDispatcher("/ceoMember/cafeRoomList").forward(request); return;
      //      request.getRequestDispatcher("/ceoMember/ReservationDetail").forward(request); return;

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
