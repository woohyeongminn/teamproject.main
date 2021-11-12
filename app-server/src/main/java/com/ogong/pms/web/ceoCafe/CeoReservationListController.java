package com.ogong.pms.web.ceoCafe;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogong.pms.dao.CafeReservationDao;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CeoMember;

@WebServlet("/ceomember/cafe/reser/list")
public class CeoReservationListController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CafeReservationDao cafeReservationDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cafeReservationDao = (CafeReservationDao) 웹애플리케이션공용저장소.getAttribute("cafeReservationDao");
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      CeoMember loginCeo = (CeoMember) request.getSession().getAttribute("loginCeoUser");
      // CeoMember ceoMember = ceoMemberDao.findByNo(loginCeo.getCeoNo());

      List<CafeReservation> reserList = cafeReservationDao.findReservationListByCeoMember(loginCeo.getCeoNo());

      if (reserList.isEmpty()) {
        request.setAttribute("pageTitle", "📝 예약 내역 목록");
        request.setAttribute("contentUrl", "/ceoCafe/CeoCafeReservationList.jsp");
        request.getRequestDispatcher("/template1.jsp").forward(request, response);

      } else {
        // request.setAttribute("ceoMember", ceoMember);
        request.setAttribute("reserList", reserList);

        request.setAttribute("pageTitle", "📝 예약 내역 목록");
        request.setAttribute("contentUrl", "/ceoCafe/CeoCafeReservationList.jsp");
        request.getRequestDispatcher("/template1.jsp").forward(request, response);
      }

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}