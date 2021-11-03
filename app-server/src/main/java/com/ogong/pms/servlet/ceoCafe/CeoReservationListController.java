package com.ogong.pms.servlet.ceoCafe;

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
import com.ogong.pms.dao.CafeReservationDao;
import com.ogong.pms.dao.CafeRoomDao;
import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CeoMember;

@WebServlet("/ceomember/cafe/reser/list")
public class CeoReservationListController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CafeDao cafeDao;
  CafeRoomDao cafeRoomDao;
  CafeReservationDao cafeReservationDao;
  CeoMemberDao ceoMemberDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cafeDao = (CafeDao) 웹애플리케이션공용저장소.getAttribute("cafeDao");
    cafeRoomDao = (CafeRoomDao) 웹애플리케이션공용저장소.getAttribute("cafeRoomDao");
    cafeReservationDao = (CafeReservationDao) 웹애플리케이션공용저장소.getAttribute("cafeReservationDao");
    ceoMemberDao = (CeoMemberDao) 웹애플리케이션공용저장소.getAttribute("ceoMemberDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {

      int ceoNo = Integer.parseInt(request.getParameter("ceono"));  //기업 회원 번호

      CeoMember ceoMember = ceoMemberDao.findByNo(ceoNo);

      List<CafeReservation> reserList = cafeReservationDao.findReservationListByCeoMember(ceoMember.getCeoNo());

      request.setAttribute("ceoMember", ceoMember);
      request.setAttribute("reserList", reserList);

      request.getRequestDispatcher("/ceoCafe/CeoCafeReservationList.jsp").forward(request, response);


    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}