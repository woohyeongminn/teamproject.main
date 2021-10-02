package com.ogong.pms.handler.cafe;

import java.util.Collection;
import java.util.HashMap;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.CafeRoom;
import com.ogong.request.RequestAgent;

public class PromptCafe {

  RequestAgent requestAgent;

  public PromptCafe (RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  //  public Cafe findByCafeNo(int no) throws Exception {
  //    Collection<Cafe> cafeList = getCafeList();
  //    for (Cafe cafe : cafeList) {
  //      if (cafe.getNo() == no && cafe.getCafeStatus() != 0 && cafe.getCafeStatus() != 3) {
  //        return cafe;
  //      }
  //    }
  //    return null;
  //  }

  public Cafe findByCafeName(String name) throws Exception {
    Collection<Cafe> cafeList = getCafeList();
    for (Cafe cafe : cafeList) {
      if (cafe.getName().equals(name) && cafe.getCafeStatus() != 0 && cafe.getCafeStatus() != 3) {
        return cafe;
      }
    }
    return null;
  }

  public CafeReview findByCafeReview (int reviewNo) throws Exception {
    Collection<CafeReview> cafeReviewList = getCafeReviewList();
    for (CafeReview cafeReview : cafeReviewList) {
      if (cafeReview.getReviewNo() == reviewNo) {
        return cafeReview;
      }
    }
    return null;
  }

  public Collection<Cafe> getCafeList() throws Exception {
    requestAgent.request("cafe.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 장소 목록 조회를 실패하였습니다.");
      return null;
    }
    return requestAgent.getObjects(Cafe.class);
  }

  public Collection<Cafe> getCafeListByLocation(String input) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("inputLocation", input);

    requestAgent.request("cafe.selectListByLocation", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 장소 검색 조회를 실패하였습니다.");
      return null;
    }
    return requestAgent.getObjects(Cafe.class);
  }

  public Collection<CafeReview> getCafeReviewList() throws Exception {
    requestAgent.request("cafeReview.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 리뷰 목록 조회를 실패하였습니다.");
      return null;
    }
    return requestAgent.getObjects(CafeReview.class);
  }

  public Collection<CafeRoom> getCafeRoomList() throws Exception {
    requestAgent.request("cafeRoom.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 스터디룸 목록 조회를 실패하였습니다.");
      return null;
    }
    return requestAgent.getObjects(CafeRoom.class);
  }

  public CafeRoom getCafeRoomByNo(int roomNo, Cafe cafe) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("roomNo", String.valueOf(roomNo));
    params.put("cafeNo", String.valueOf(cafe.getNo()));

    requestAgent.request("cafeRoom.selectOneByRoomNoCafeNo", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 스터디룸 조회를 실패해였습니다.");
    } else {
      return requestAgent.getObject(CafeRoom.class);
    }
    return null;
  }

  public Collection<CafeReservation> getCafeReservationList() throws Exception {
    requestAgent.request("cafeReservation.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 예약 목록 조회를 실패하였습니다.");
      return null;
    }
    return requestAgent.getObjects(CafeReservation.class);
  }

  public void insertReservation(CafeReservation cafeReservation) throws Exception {
    requestAgent.request("cafeReservation.insert", cafeReservation);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 장소 예약을 실패하였습니다.");
    } else {
      System.out.println();
      System.out.println(" *** 예약 되었습니다 ***");
    }
  }

}
