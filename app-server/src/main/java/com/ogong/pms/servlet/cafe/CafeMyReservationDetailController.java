package com.ogong.pms.servlet.cafe;

import java.io.IOException;
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

@WebServlet("/cafe/myReservationDetail")
public class CafeMyReservationDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CafeDao cafeDao;
  CafeReservationDao cafeReservationDao;
  CafeRoomDao cafeRoomDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cafeDao = (CafeDao) 웹애플리케이션공용저장소.getAttribute("cafeDao");
    cafeReservationDao = (CafeReservationDao) 웹애플리케이션공용저장소.getAttribute("cafeReservationDao");
    cafeRoomDao = (CafeRoomDao) 웹애플리케이션공용저장소.getAttribute("cafeRoomDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {

      //      Member member = AuthPerMemberLoginHandler.getLoginUser(); 
      //      if (member == null) {
      //        System.out.println(" >> 로그인 한 회원만 볼 수 있습니다.");
      //        return;
      //      }

      int memberNo = Integer.parseInt(request.getParameter("perNo"));
      int reservationNo = Integer.parseInt(request.getParameter("reservationNo"));

      CafeReservation cafeReser = 
          cafeReservationDao.findReservationByMember(memberNo, reservationNo);
      if (cafeReser == null) {
        throw new Exception("예약 내역이 없습니다.");
      }

      Cafe cafeReserCafe = cafeDao.findByCafeNo(cafeReser.getCafe().getNo());
      CafeRoom cafeRoom = cafeRoomDao.findByRoomNo(cafeReser.getRoomNo());

      String reviewStatusLable = CafeHandlerHelper.getReviewStatusLabel(
          String.valueOf(cafeReser.getWirteReview()));
      String reserStatusLable = cafeReser.getReservationStatusName();

      if (cafeReser.getReservationStatus() != 1 && cafeReser.getReservationStatus() != 2) {
        reviewStatusLable = "작성불가";
      }

      request.setAttribute("cafeReser", cafeReser);
      request.setAttribute("cafeReserEndTime", 
          cafeReser.getStartTime().plusHours(cafeReser.getUseTime()));
      request.setAttribute("cafeName", cafeReserCafe.getName());
      request.setAttribute("cafeRoomName", cafeRoom.getRoomName());
      request.setAttribute("reviewStatusLable", reviewStatusLable);
      request.setAttribute("reserStatusLable", reserStatusLable);
      request.setAttribute("memberNo", memberNo);
      request.getRequestDispatcher("/cafe/CafeMyReservationDetail.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  //
  //  private void goToAddReview(CommandRequest request,CafeReservation myReservation) throws Exception {
  //    System.out.println();
  //
  //    if (myReservation == null) {
  //      System.out.println(" >> 예약번호를 잘못 선택하셨습니다.");
  //      return;
  //    }
  //
  //    Date today = new Date(System.currentTimeMillis());
  //    Date reserDate = myReservation.getUseDate();
  //
  //    if (reserDate.toLocalDate().compareTo(today.toLocalDate()) < 0) {
  //      if (!myReservation.getWirteReview()) {
  //        System.out.println(" >> 리뷰 작성 화면으로 이동합니다.");
  //        request.setAttribute("reservationNo", myReservation.getReservationNo());
  //        request.setAttribute("cafeNo", myReservation.getCafe().getNo());
  //        request.getRequestDispatcher("/cafe/myReviewAdd").forward(request);
  //        return;
  //
  //      } else {
  //        System.out.println(" >> 이미 리뷰를 작성한 예약입니다.");
  //        return;
  //      }
  //    } else {
  //      System.out.println(" >> 이용 후 다음 날부터 작성 가능합니다.");
  //      return;
  //    }
  //  }
  //
  //  private CafeReservation printMyReserDetail(Member member, int input) throws Exception {
  //    CafeReservation cafeReser = 
  //        cafeReservationDao.findReservationByMember(member.getPerNo(), input);
  //
  //    if (cafeReser != null) {
  //      Cafe cafeReserCafe = cafeDao.findByCafeNo(cafeReser.getCafe().getNo());
  //      CafeRoom cafeRoom = cafeRoomDao.findByRoomNo(cafeReser.getRoomNo());
  //
  //      String reviewStatusLable = CafeHandlerHelper.getReviewStatusLabel(
  //          String.valueOf(cafeReser.getWirteReview()));
  //      String reserStatusLable = cafeReser.getReservationStatusName();
  //
  //      if (cafeReser.getReservationStatus() != 1 && cafeReser.getReservationStatus() != 2) {
  //        reviewStatusLable = "작성불가";
  //      }
  //
  //      System.out.printf(" (%d)\n 예약날짜 : %s\n 이용날짜 : %s\n 예약장소 : %s\n"
  //          + " 이용시간 : %s ~ %s (%s시간)\n 스터디룸 : %s\n"
  //          + " 결제금액 : %d원\n 리뷰작성여부 : %s\n 상태 : %s\n"
  //          , cafeReser.getReservationNo(), cafeReser.getReservationDate(), cafeReser.getUseDate()
  //          , cafeReserCafe.getName(), cafeReser.getStartTime()
  //          , cafeReser.getStartTime().plusHours(cafeReser.getUseTime())
  //          , cafeReser.getUseTime(), cafeRoom.getRoomName(), cafeReser.getTotalPrice() 
  //          , reviewStatusLable , reserStatusLable);
  //      System.out.println();  
  //      return cafeReser;
  //
  //    } else {
  //      return null;
  //    }
  //  }
}
