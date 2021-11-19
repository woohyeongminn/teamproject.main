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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.dao.CafeReservationDao;
import com.ogong.pms.dao.CafeRoomDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.vo.Cafe;
import com.ogong.pms.vo.CafeReservation;
import com.ogong.pms.vo.CafeRoom;
import com.ogong.pms.vo.Member;

@Controller
public class CafeReservationController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired CafeDao cafeDao;
  @Autowired CafeRoomDao cafeRoomDao;
  @Autowired CafeReservationDao cafeReservationDao;
  @Autowired StudyDao studyDao;

  @GetMapping("/cafe/reservationList")
  public ModelAndView list(HttpSession session,
      String searchDate,
      @RequestParam(required=false, defaultValue="")String startDate,
      @RequestParam(required=false, defaultValue="")String endDate,
      @RequestParam(defaultValue = "1") int pageNo, 
      @RequestParam(defaultValue = "10") int pageSize) throws Exception {

    Member member = (Member) session.getAttribute("loginUser");

    int count = cafeReservationDao.countByMember(member.getPerNo(),null);

    if (pageSize < 5 || pageSize > 10) {
      pageSize = 10;
    }

    int totalPage = count / pageSize + ((count % pageSize) > 0 ? 1 : 0);

    if (pageNo < 1 || pageNo > totalPage) {
      pageNo = 1;
    }

    int offset = pageSize * (pageNo - 1);
    int length = pageSize;

    ModelAndView mv = new ModelAndView();

    List<CafeReservation> reserList = 
        cafeReservationDao.findReservationListByMember(member.getPerNo(), offset, length);

    cafeReservationDao.updateReservationStatusComplete();
    sqlSessionFactory.openSession().commit();

    if(!startDate.equals("")) {
      if (searchDate.equals("1")) {
        searchDate = "rs.rsv_dt";
      } else if (searchDate.equals("2")) {
        searchDate = "rs.using_dt";
      }

      HashMap<String,Object> params = new HashMap<>();
      params.put("memberNo", member.getPerNo());
      params.put("searchDate", searchDate);
      params.put("startDate", Date.valueOf(startDate));
      params.put("endDate", Date.valueOf(endDate));

      count = cafeReservationDao.countByMember(params);

      if (pageSize < 5 || pageSize > 10) {
        pageSize = 10;
      }

      totalPage = count / pageSize + ((count % pageSize) > 0 ? 1 : 0);

      if (pageNo < 1 || pageNo > totalPage) {
        pageNo = 1;
      }

      offset = pageSize * (pageNo - 1);
      length = pageSize;

      params.put("offset", offset);
      params.put("length", length);

      reserList = cafeReservationDao.searchReservationListByMember(params);
      mv.addObject("startDate", startDate);
      mv.addObject("endDate", endDate);
      mv.addObject("searchDate", searchDate);
    }

    for (CafeReservation cafeReser : reserList) {

      if (cafeReser.getReservationStatus() == 7 && cafeReser.getWirteReview()) {
        cafeReser.setWirteReviewLable("작성완료");
      } else if(cafeReser.getReservationStatus() == 7 && !cafeReser.getWirteReview()) {
        cafeReser.setWirteReviewLable("작성가능");
      } else {
        cafeReser.setWirteReviewLable("작성불가");
      }
    }

    mv.addObject("reserList", reserList);
    mv.addObject("pageTitle", "🧾 내 예약 내역");
    mv.addObject("totalPage", totalPage);
    mv.addObject("pageNo", pageNo);
    mv.addObject("pageSize", pageSize);
    mv.addObject("contentUrl", "cafe/CafeMyReservationList.jsp");
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

    mv.addObject("pageTitle", "스터디룸 예약");
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

    mv.addObject("pageTitle", "스터디룸 예약");
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
      HttpSession session) {

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

    try {
      cafeReservationDao.insertReservation(reservation);
      sqlSessionFactory.openSession().commit();
    } catch (Exception e) {
      sqlSessionFactory.openSession().rollback();
    }

    List<Map<String, String>> myStudy = null;

    try {
      myStudy = studyDao.findAllByMyNo(member.getPerNo());
    } catch (Exception e) {
      e.printStackTrace();
    }

    ModelAndView mv = new ModelAndView();

    mv.addObject("pageTitle", "스터디룸 예약");
    mv.addObject("perNo", member.getPerNo());
    mv.addObject("myStudy", myStudy);
    mv.addObject("contentUrl", "cafe/CafeReservationEnd.jsp");
    mv.setViewName("template1");

    return mv;
  }

  @PostMapping("/cafe/reservationDelete")
  public ModelAndView delete(int[] reservationNo, HttpSession session) throws Exception {

    for(int i = 0; i < reservationNo.length; i++) {
      cafeReservationDao.deleteReservation(reservationNo[i], 3);
      sqlSessionFactory.openSession().commit();
    }

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:reservationList");

    return mv;
  }
}
