package com.ogong.pms.servlet.ceoCafe;

import java.io.IOException;
import java.util.ArrayList;
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
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CafeRoom;
import com.ogong.pms.domain.CeoMember;
import com.ogong.pms.servlet.ceoMember.AuthCeoMemberLoginController;
import com.ogong.util.Prompt;

@WebServlet("/ceomember/cafe/reser/detail")
public class CeoReservationDetailHandler extends HttpServlet {

  CafeDao cafeDao;
  CafeReservationDao cafeReservationDao;
  CafeRoomDao cafeRoomDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cafeDao = (CafeDao) 웹애플리케이션공용저장소.getAttribute("cafeDao");
    cafeReservationDao = (CafeReservationDao) 웹애플리케이션공용저장소.getAttribute("cafeReservationDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {


    int no = Integer.parseInt(request.getParameter("no"));  // 카페번호
    // 회원번호
    CeoMember ceoMember = ceoMemberDao.findByNo(no);

    CeoMember ceoMember = AuthCeoMemberLoginController.getLoginCeoMember();

    request.setAttribute("cafeReservationList", cafeReservationList);


    CeoCafeReservationDetail.jsp

    //    List<CafeReservation> myCafeReserList = new ArrayList<>();
    //    List<CafeReservation> reserList = 
    //        cafeReservationDao.findReservationListByCeoMember(ceoMember.getCeoNo());
    //
    //    for (CafeReservation cafeReser : reserList) {
    //      myCafeReserList.add(cafeReser);
    //      Cafe cafeReserCafe = cafeDao.findByCafeNo(cafeReser.getCafe().getNo());
    //      CafeRoom cafeRoom = cafeRoomDao.findByRoomNo(cafeReser.getRoomNo());
    //      String reserStatusLable = cafeReser.getReservationStatusName();


  }
