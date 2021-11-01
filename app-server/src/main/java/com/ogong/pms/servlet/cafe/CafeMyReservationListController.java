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
import com.ogong.pms.dao.CafeReservationDao;
import com.ogong.pms.domain.CafeReservation;

@WebServlet("/cafe/reservationList")
public class CafeMyReservationListController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CafeReservationDao cafeReservationDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cafeReservationDao = (CafeReservationDao) 웹애플리케이션공용저장소.getAttribute("cafeReservationDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int memberNo = Integer.parseInt(request.getParameter("perNo"));
      //      System.out.println(memberNo);

      List<CafeReservation> reserList = 
          cafeReservationDao.findReservationListByMember(memberNo);

      if (reserList.isEmpty()) {
        throw new Exception("예약 내역이 없습니다.");
      }

      request.setAttribute("reserList", reserList);
      request.setAttribute("perNo", memberNo);
      request.getRequestDispatcher("/cafe/CafeMyReservationList.jsp").forward(request, response);

      //      Member member = AuthPerMemberLoginHandler.getLoginUser(); 

      //      if (member == null) {
      //        System.out.println(" >> 로그인 한 회원만 볼 수 있습니다.");
      //        return;
      //      }
      //
      //      List<CafeReservation> reserList = 
      //          cafeReservationDao.findReservationListByMember(member.getPerNo());
      //
      //      if (reserList.isEmpty()) {
      //        System.out.println(" >> 예약 내역이 존재하지 않습니다.");
      //        return;
      //      } else {
      //        for (CafeReservation myReservationList : reserList) {
      //          System.out.printf(" (%d) | 예약날짜 : %s | 이용날짜 : %s | 예약장소 : %s | 결제금액 : %d원 | 상태 : %s\n", 
      //              myReservationList.getReservationNo(), 
      //              myReservationList.getReservationDate(),
      //              myReservationList.getUseDate(),
      //              myReservationList.getCafe().getName(), 
      //              myReservationList.getTotalPrice(),
      //              myReservationList.getReservationStatusName());
      //          System.out.println();
      //        }
      //      }
      //    }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}