package com.ogong.pms.servlet.ceoCafe;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.dao.CafeReservationDao;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.util.Prompt;

@WebServlet("/ceomember/cafe/reser/detail")
public class CeoReservationRejectHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CafeDao cafeDao;
  CafeReservationDao cafeReservationDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cafeDao = (CafeDao) 웹애플리케이션공용저장소.getAttribute("cafeDao");
    cafeReservationDao = (CafeReservationDao) 웹애플리케이션공용저장소.getAttribute("cafeReservationDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }


  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    List<CafeReservation> cafeReservationList = cafeReservationDao.getCafeReservationList(); 

    int reservationNo = Prompt.inputInt(" 번호 : ");
    for (CafeReservation cafeReservation : cafeReservationList) {
      if (cafeReservation.getReservationNo() == reservationNo) {
        Date today = new Date(System.currentTimeMillis());
        Date reserDate = cafeReservation.getUseDate();

        if (cafeReservation.getReservationStatus() == 3) {
          System.out.println(" >> 이미 취소 된 예약입니다.");
          return;
        } else if (cafeReservation.getReservationStatus() == 5) {
          System.out.println(" >> 이미 거절 한 예약입니다.");
          return;
        }

        if (reserDate.toLocalDate().compareTo(today.toLocalDate()) > 0) {

          String input = Prompt.inputString(" 정말 예약 거절 하시겠습니까? (네 / 아니오) ");

          if (!input.equalsIgnoreCase("네")) {
            System.out.println(" >> 예약 거절을 취소합니다.");
            return;
          }

          cafeReservationDao.deleteReservation(cafeReservation.getReservationNo(), 5);
          sqlSession.commit();

          System.out.println(" >> 예약을 거절하였습니다.");
          return;

        } else if (reserDate.toLocalDate().compareTo(today.toLocalDate()) == 0) {
          System.out.println(" >> 당일 예약은 거절 불가능합니다.");
          return;
        } else {
          System.out.println(" >> 지난 예약은 선택할 수 없습니다.");
          return;
        }
      }
    }
    System.out.println(" >> 예약 번호를 잘못 선택하셨습니다.");
    return;
  }
}
