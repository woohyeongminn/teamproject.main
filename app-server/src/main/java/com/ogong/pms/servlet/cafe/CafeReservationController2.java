package com.ogong.pms.servlet.cafe;

import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.dao.CafeReservationDao;
import com.ogong.pms.dao.CafeRoomDao;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReservation;

@WebServlet("/cafe/reservationSelectTime")
public class CafeReservationController2 extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CafeDao cafeDao;
  CafeRoomDao cafeRoomDao;
  CafeReservationDao cafeReservationDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cafeDao = (CafeDao) 웹애플리케이션공용저장소.getAttribute("cafeDao");
    cafeRoomDao = (CafeRoomDao) 웹애플리케이션공용저장소.getAttribute("cafeRoomDao");
    cafeReservationDao = (CafeReservationDao) 웹애플리케이션공용저장소.getAttribute("cafeReservationDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      int roomNo = Integer.parseInt(request.getParameter("roomName"));
      String selectedDate = request.getParameter("date");
      int cafeNo = Integer.parseInt(request.getParameter("cafeNo"));

      System.out.println(roomNo + ", " + selectedDate + ", " + cafeNo);

      Cafe cafe = cafeDao.findByCafeNo(cafeNo);
      List<CafeReservation> todayReserList = 
          cafeReservationDao.findReservationListByDate(selectedDate, roomNo);
      ArrayList<LocalTime> useTimeList = new ArrayList<>();

      try {
        if (!todayReserList.isEmpty()) {
          for (CafeReservation cafeReser : todayReserList) {
            for (int i = 0; i < cafeReser.getUseTime(); i++) {
              if (i == 0) {
                useTimeList.add(cafeReser.getStartTime().plusHours(1));
                continue;
              } else if (i > 0) {
                LocalTime tempTime = useTimeList.get(useTimeList.size()-1).plusHours(1);
                useTimeList.add(tempTime);
              }
            }
          }
        }
      } catch (NullPointerException e) {e.printStackTrace();}

      LocalTime startTime = cafe.getOpenTime();
      LocalTime endTime = cafe.getCloseTime();
      LocalTime tempEndTime = LocalTime.parse("00:00");
      String status = "";

      int availableTime = (int) ChronoUnit.HOURS.between(startTime, endTime);
      HashMap<Integer, String> statusOfNumber = new HashMap<>();

      System.out.println("\n [ 예약 현황 ]");
      for (int i = 0; i < availableTime; i++) {
        if (i == 0) {
          tempEndTime = startTime.plusHours(1);
        } else {
          startTime = tempEndTime;
          tempEndTime = startTime.plusHours(1);
        }

        status = "예약 가능";

        if (useTimeList.size() != 0) {
          for (int j = 0; j < useTimeList.size(); j++) {
            if (useTimeList.get(j).compareTo(tempEndTime) == 0) {
              status = "예약 불가";
            }
          }
        }
        System.out.printf(" %d. %s ~ %s : %s\n", i+1, startTime, tempEndTime, status);
        statusOfNumber.put(i+1, startTime + " ~ " + tempEndTime + " : " +status);
      }

      request.setAttribute("statusOfNumber", statusOfNumber);
      request.getRequestDispatcher("/cafe/CafeReservation2.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      //      request.setAttribute("error", e);
      //      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }

  }
  /*
  private void addRoomReservation(Cafe cafe) throws Exception {
    System.out.println();
    System.out.println("▶ 스터디룸 예약하기");

    Member member = AuthPerMemberLoginHandler.getLoginUser();
    if (member == null) {
      System.out.println(" >> 로그인 한 회원만 예약 가능합니다.");
      return;
    }

    int i = 1;
    HashMap<Integer, Integer> selectRoomNo = new HashMap<>();

    List<CafeRoom> roomList = cafeRoomDao.findCafeRoomListByCafe(cafe.getNo());
    for (CafeRoom cafeRoom : roomList) {
      System.out.println(" " + i + ". " + cafeRoom.getRoomName());
      selectRoomNo.put(i, cafeRoom.getRoomNo());
      i++;
    }

    if (roomList.isEmpty()) {
      System.out.println(" >> 등록된 스터디룸이 없습니다.");
      return;
    }

    int selectNo = Prompt.inputInt(" 선택> ");
    CafeRoom cafeRoom = null;
    try {
      cafeRoom = cafeRoomDao.findByRoomNo(selectRoomNo.get(selectNo));
    } catch (NullPointerException e) {
      System.out.println(" >> 스터디룸 번호를 잘못 선택하셨습니다.");
      return;
    }

    detailRoomReservation(cafe.getNo(), selectRoomNo.get(selectNo));
  }

  private void detailRoomReservation(int cafeNo, Integer selectNo) throws Exception {
    //    int roomNo = selectNo; // room 고유번호
    Cafe cafe = cafeDao.findByCafeNoMember(cafeNo);
    CafeRoom cafeRoom = cafeRoomDao.findByRoomNo(selectNo);

    System.out.printf("\n [%s]\n", cafeRoom.getRoomName());
    System.out.printf(" >> 스터디룸 이미지 : %s\n", cafeRoom.getCafeRoomImageNames());
    System.out.printf(" >> 소개글 : %s\n", cafeRoom.getRoomInfo());

    Member member = AuthPerMemberLoginHandler.getLoginUser();

    Date today = new Date(System.currentTimeMillis());
    Date reservationDate = Prompt.inputDate("\n 예약 날짜(당일 예약 불가) : ");
    while (today.toLocalDate().compareTo(reservationDate.toLocalDate()) > 0) {
      System.out.println(" >> 이전 날짜는 예약 불가능 합니다.");
      System.out.println("    날짜를 다시 입력해 주세요.\n");
      reservationDate = Prompt.inputDate(" 예약 날짜 : ");
    }

    List<CafeReservation> todayReserList = getCafeReserList(cafeNo, selectNo, reservationDate);
    ArrayList<LocalTime> useTimeList = new ArrayList<>();

    try {
      if (!todayReserList.isEmpty()) {
        for (CafeReservation cafeReser : todayReserList) {
          for (int i = 0; i < cafeReser.getUseTime(); i++) {
            if (i == 0) {
              useTimeList.add(cafeReser.getStartTime().plusHours(1));
              continue;
            } else if (i > 0) {
              LocalTime tempTime = useTimeList.get(useTimeList.size()-1).plusHours(1);
              useTimeList.add(tempTime);
            }
          }
        }
      }
    } catch (NullPointerException e) {

    }

    int useMember;
    while(true) {
      useMember = Prompt.inputInt("\n 이용할 인원수 : ");
      if (useMember <= 0 || useMember > cafeRoom.getPeople()) {
        System.out.println(" >> 인원수가 너무 적거나 많습니다.");
        System.out.println("    다시 입력해주세요.");
        continue;
      }
      break;
    }

    LocalTime startTime = cafe.getOpenTime();
    LocalTime endTime = cafe.getCloseTime();
    LocalTime tempEndTime = LocalTime.parse("00:00");

    int availableTime = (int) ChronoUnit.HOURS.between(startTime, endTime);
    HashMap<Integer, String> statusOfNumber = new HashMap<>();

    //System.out.println("스터디룸 시작 시간 : " + startTime + ", 끝 시간 : " + endTime);

    System.out.println("\n [ 예약 현황 ]");
    for (int i = 0; i < availableTime; i++) {
      if (i == 0) {
        tempEndTime = startTime.plusHours(1);
      } else {
        startTime = tempEndTime;
        tempEndTime = startTime.plusHours(1);
      }

      String status = "예약 가능";

      if (useTimeList.size() != 0) {
        for (int j = 0; j < useTimeList.size(); j++) {
          if (useTimeList.get(j).compareTo(tempEndTime) == 0) {
            status = "예약 불가";
          }
        }
      }
      System.out.printf(" %d. %s ~ %s : %s\n", i+1, startTime, tempEndTime, status);
      statusOfNumber.put(i+1, startTime + "," + tempEndTime + "," +status);
    }

    ArrayList<Integer> selectStatusOfNumber = new ArrayList<>();
    while (true) {
      int number = Prompt.inputInt("\n 예악하고 싶은 시간의 번호를 입력하세요 (종료:0) : ");
      if (number == 0) {
        break;
      }
      if (!ReservationStatus(statusOfNumber, number)) {
        System.out.println(" >> 예약 가능한 시간이 아닙니다. 다시 선택해 주세요.");
        continue;
      } else if (ReservationStatus(statusOfNumber, number)) {
        if (selectStatusOfNumber.size() == 0) {
          selectStatusOfNumber.add(number);
          continue;
        } else {
          if (number == selectStatusOfNumber.get(selectStatusOfNumber.size()-1)+1) {
            selectStatusOfNumber.add(number);
            continue;
          } else {
            System.out.println(" >> 연속된 시간만 선택 가능합니다. 다시 선택해 주세요.");
            continue;
          }
        }
      }
    }

    System.out.println("\n [ 예약 시간 ]");
    for (int i = 0; i < selectStatusOfNumber.size(); i++) {
      String[] reserTimeInfo = statusOfNumber.get(selectStatusOfNumber.get(i)).split(","); 
      System.out.printf(" %d. %s ~ %s\n", i+1, reserTimeInfo[0], reserTimeInfo[1]);
    }
    System.out.println(" 총 이용 시간 : " + selectStatusOfNumber.size() +"시간");

    if (selectStatusOfNumber.size() == 0) {
      System.out.println(" >> 종료");
      return;
    }
    int totalPrice = cafeRoom.getRoomPrice() * selectStatusOfNumber.size();
    System.out.println(" 총 금액 : " + totalPrice);

    String input = Prompt.inputString("\n 정말 예약하시겠습니까? (네 / 아니오) ");

    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 스터디룸 예약을 취소하였습니다.");
      return;
    }

    String[] realReserTime = statusOfNumber.get(selectStatusOfNumber.get(0)).split(",");
    //String[] stringStartTime = realReserTime[0].split(":");
    LocalTime realStartTime = LocalTime.parse(realReserTime[0]);

    CafeReservation reservation = new CafeReservation();

    reservation.setMember(member);
    reservation.setUseDate(reservationDate);
    reservation.setStartTime(realStartTime);
    reservation.setUseTime(selectStatusOfNumber.size());
    reservation.setUseMemberNumber(useMember);
    reservation.setTotalPrice(totalPrice);
    reservation.setRoomNo(selectNo);
    reservation.setReservationStatus(1); // 1 : 예약완료

    cafeReservationDao.insertReservation(reservation);
    sqlSession.commit();

    System.out.println(" *** 예약 되었습니다 ***");
  }

  private List<CafeReservation> getCafeReserList(
      int cafeNo, int roomNo, Date reservationDate) throws Exception {

    List<CafeReservation> reserList = cafeReservationDao.getCafeReservationList();

    List<CafeReservation> todayReserList = new ArrayList<>();
    for (CafeReservation cafeReser : reserList) {

      if (reservationDate.toLocalDate().compareTo(cafeReser.getUseDate().toLocalDate()) == 0 &&
          cafeReser.getCafe().getNo() == cafeNo && cafeReser.getRoomNo() == roomNo &&
          cafeReser.getReservationStatus() == 1 || cafeReser.getReservationStatus() == 2) {
        todayReserList.add(cafeReser);
      }
    }
    return todayReserList;
  }

  private boolean ReservationStatus(HashMap<Integer, String> statusOfNumber, int number) {

    if (number > statusOfNumber.size()) {
      return false;
    }

    for (int i = 0; i < statusOfNumber.size(); i++) {
      if (i == number) {
        String[] reserInfo = statusOfNumber.get(i).split(",");
        if (reserInfo[2].equals("예약 불가")) {
          return false;
        }
      }
    }
    return true;
  }
   */
}
