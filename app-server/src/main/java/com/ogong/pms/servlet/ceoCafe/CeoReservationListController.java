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

      int no = Integer.parseInt(request.getParameter("no"));  // 카페번호
      int ceoNo = Integer.parseInt(request.getParameter("ceono"));  //회원페번호

      CeoMember ceoMember = ceoMemberDao.findByNo(ceoNo);
      List<CafeReservation> reserList = cafeReservationDao.findReservationListByCeoMember(ceoMember.getCeoNo());

      //      List<CafeReservation> myCafeReserList = new ArrayList<>();
      //
      //      CafeRoom cafeRoom = null;
      //      Cafe cafe = null;
      //      for (CafeReservation cafeReser : reserList) {
      //        myCafeReserList.add(cafeReser);
      //        cafe = cafeDao.findByCafeNo(no);
      //        cafeRoom = cafeRoomDao.findByRoomNo(cafeReser.getRoomNo());
      //      }
      //
      //      if (cafeRoom == null) {
      //        throw new Exception("등록된 스터디룸이 없습니다.");
      //      }

      request.setAttribute("ceoMember", ceoMember);
      //request.setAttribute("cafe", cafe);
      request.setAttribute("cafeReservationList", reserList);
      //request.setAttribute("cafeRoom", cafeRoom);
      //request.setAttribute("cafeReser", cafeReser);

      request.getRequestDispatcher("/ceoCafe/CeoCafeReservationList.jsp").forward(request, response);
      //CeoCafeReservationDetail.jsp

      //    List<CafeReservation> myCafeReserList = new ArrayList<>();
      //    List<CafeReservation> reserList = 
      //        cafeReservationDao.findReservationListByCeoMember(ceoMember.getCeoNo());
      //
      //    for (CafeReservation cafeReser : reserList) {
      //      myCafeReserList.add(cafeReser);
      //      Cafe cafeReserCafe = cafeDao.findByCafeNo(cafeReser.getCafe().getNo());
      //      CafeRoom cafeRoom = cafeRoomDao.findByRoomNo(cafeReser.getRoomNo());
      //      String reserStatusLable = cafeReser.getReservationStatusName();


    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}