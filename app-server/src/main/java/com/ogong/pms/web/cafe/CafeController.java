package com.ogong.pms.web.cafe;

import java.sql.Date;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.dao.CafeReservationDao;
import com.ogong.pms.dao.CafeReviewDao;
import com.ogong.pms.dao.CafeRoomDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.CafeRoom;
import com.ogong.pms.domain.Member;

@Controller
public class CafeController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired CafeDao cafeDao;
  @Autowired CafeReviewDao cafeReviewDao;
  @Autowired CafeRoomDao cafeRoomDao;
  @Autowired CafeReservationDao cafeReservationDao;
  @Autowired StudyDao studyDao;

  @GetMapping("/cafe/list")
  public ModelAndView list() throws Exception {

    List<Cafe> cafeList = cafeDao.getCafeListByMember();

    ModelAndView mv = new ModelAndView();
    mv.addObject("cafeList", cafeList);
    mv.addObject("pageTitle", "스터디 장소 검색");
    mv.addObject("contentUrl", "cafe/CafeList.jsp");
    mv.setViewName("template1");

    return mv;
  }

  @GetMapping("/cafe/search")
  public ModelAndView search(String where, 
      String keyword) throws Exception {

    if (where.equals("1")) {
      where = "location";
    } else if (where.equals("2")) {
      where = "name";
    }

    List<Cafe> cafeList = cafeDao.findCafeListByLocation(where, keyword);

    ModelAndView mv = new ModelAndView();
    mv.addObject("cafeList", cafeList);
    mv.addObject("pageTitle", "스터디 장소 검색");
    mv.addObject("contentUrl", "cafe/CafeList.jsp");
    mv.setViewName("template1");

    return mv;
  }

  @GetMapping("/cafe/detail")
  public ModelAndView detail(int no) throws Exception {

    Cafe cafe = cafeDao.findByCafeNoMember(no);
    cafe.setInfo(cafe.getInfo().replace("\n", "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"));

    HashMap<String,Object> params = new HashMap<>();
    params.put("cafeNo", cafe.getNo());
    cafe.setHoliday(cafeDao.getCafeHoliday(params));

    List<CafeReview> reviewList = cafeReviewDao.findReviewListByCafeNo(cafe.getNo());
    List<CafeRoom> roomList = cafeRoomDao.findCafeRoomListByMember(cafe.getNo());

    cafeDao.updateViewCount(cafe.getNo());
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.addObject("cafe", cafe);
    mv.addObject("reviewList", reviewList);
    mv.addObject("roomList", roomList);
    mv.addObject("roomListSize", roomList.size());
    mv.addObject("pageTitle", cafe.getName());
    mv.addObject("contentUrl", "cafe/CafeDetail3.jsp");
    mv.setViewName("template1");

    return mv;
  }

  @GetMapping("/cafe/reservation")
  public ModelAndView reservation1(int no) throws Exception {

    Cafe cafe = cafeDao.findByCafeNoMember(no);

    if (cafe == null) {
      throw new Exception(" >> 해당 번호의 장소가 존재하지 않습니다.");
    }

    List<CafeRoom> roomList = cafeRoomDao.findCafeRoomListByCafe(cafe.getNo());

    ModelAndView mv = new ModelAndView();

    mv.addObject("roomList", roomList);
    mv.addObject("pageTitle", "스터디룸 예약");
    mv.addObject("contentUrl", "cafe/CafeReservation.jsp");
    mv.setViewName("template1");

    return mv;
  }

  @GetMapping("/cafe/reservationSelectTime")
  public ModelAndView reservation2(int roomNo, 
      String date, 
      int cafeNo, 
      HttpSession session) throws Exception {

    Cafe cafe = cafeDao.findByCafeNo(cafeNo);
    CafeRoom cafeRoom = cafeRoomDao.findByRoomNo(roomNo);
    List<CafeReservation> todayReserList = 
        cafeReservationDao.findReservationListByDate(date, roomNo);
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
      statusOfNumber.put(i+1, startTime + "," + tempEndTime + "," + status);
    }

    ModelAndView mv = new ModelAndView();

    mv.addObject("statusOfNumber", statusOfNumber);
    mv.addObject("roomNo", roomNo);
    mv.addObject("selectedDate", date);
    mv.addObject("people", cafeRoom.getPeople());
    mv.addObject("perNo", ((Member) session.getAttribute("loginUser")).getPerNo());
    mv.addObject("contentUrl", "cafe/CafeReservation2.jsp");
    mv.setViewName("template1");

    return mv;
  }

  @GetMapping("/cafe/reservationPay")
  public ModelAndView reservation3(String[] selectedTime, 
      int roomNo, 
      String selectedDate, 
      int people,
      HttpSession session) throws Exception {

    HashMap<Integer,String> reservationInfo = new HashMap<>();
    for(int i = 0; i < selectedTime.length; i++) {
      int index = Integer.parseInt(selectedTime[i].split(",")[0]);
      String startTime = selectedTime[i].split(",")[1];
      String endTime = selectedTime[i].split(",")[2];
      reservationInfo.put(index, startTime + "," + endTime);
    }

    CafeRoom cafeRoom = cafeRoomDao.findByRoomNo(roomNo);
    int totalPrice = cafeRoom.getRoomPrice() * reservationInfo.size();

    ModelAndView mv = new ModelAndView();

    mv.addObject("roomNo", roomNo);
    mv.addObject("reservationInfo", reservationInfo);
    mv.addObject("startTime", selectedTime[0].split(",")[1]);
    mv.addObject("usingTime", reservationInfo.size());
    mv.addObject("totalPrice", totalPrice);
    mv.addObject("selectedDate", selectedDate);
    mv.addObject("people", people);
    mv.addObject("perNo", ((Member) session.getAttribute("loginUser")).getPerNo());
    mv.addObject("contentUrl", "cafe/CafeReservation3.jsp");
    mv.setViewName("template1");

    return mv;
  }

  @GetMapping("/cafe/reservationEnd")
  public ModelAndView reservation3(int roomNo, 
      String startTime, 
      int usingTime, 
      int totalPrice,
      String selectedDate,
      int people,
      HttpSession session) throws Exception {

    Member member = (Member) session.getAttribute("loginUser");

    CafeReservation reservation = new CafeReservation();

    reservation.setMember(member);
    reservation.setUseDate(Date.valueOf(selectedDate));
    reservation.setStartTime(LocalTime.parse(startTime));
    reservation.setUseTime(usingTime);
    reservation.setUseMemberNumber(people);
    reservation.setTotalPrice(totalPrice);
    reservation.setRoomNo(roomNo);
    reservation.setReservationStatus(1); // 1 : 예약완료

    cafeReservationDao.insertReservation(reservation);
    sqlSessionFactory.openSession().commit();

    List<Map<String,String>> myStudy = studyDao.findAllByMyNo(member.getPerNo());

    ModelAndView mv = new ModelAndView();

    mv.addObject("perNo", member.getPerNo());
    mv.addObject("myStudy", myStudy);
    mv.addObject("contentUrl", "cafe/CafeReservationEnd.jsp");
    mv.setViewName("template1");

    return mv;
  }

}
