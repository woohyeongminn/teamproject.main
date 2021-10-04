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
  // -----------------------Cafe--------------------------------------
  public Collection<Cafe> getCafeList() throws Exception {
    requestAgent.request("cafe.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 장소 목록 조회를 실패하였습니다.");
      return null;
    }
    return requestAgent.getObjects(Cafe.class);
  }

  public Collection<Cafe> findCafeListByLocation(String input) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("inputLocation", input);
    requestAgent.request("cafe.selectListByLocation", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 장소 검색 조회를 실패하였습니다.");
      return null;
    }
    return requestAgent.getObjects(Cafe.class);
  }

  public Collection<Cafe> findCafeListByCeoMember(int ceoNo) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("ceoNo", String.valueOf(ceoNo));
    requestAgent.request("cafe.selectListByCeoMember", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 장소 목록 조회를 실패하였습니다.");
      return null;
    }
    return requestAgent.getObjects(Cafe.class);
  }

  public Cafe findByCafeNo(int cafeNo) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("cafeNo", String.valueOf(cafeNo));
    requestAgent.request("cafe.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      //      System.out.println(" >> 해당 번호의 카페가 존재하지 않습니다.");
      return null;
    }
    return requestAgent.getObject(Cafe.class);
  }

  public void insertCafe(Cafe cafe) throws Exception {
    requestAgent.request("cafe.insert", cafe);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 장소 등록 실패하였습니다.");
    } else {
      System.out.println(" >> 등록되었습니다.");
    }
  }

  public void updateCafe(Cafe cafe) throws Exception {
    requestAgent.request("cafe.update", cafe);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 카페 수정을 실패하였습니다.");
    } else {
      //      System.out.println(" >> 수정이 완료 되었습니다.");
    }
  }

  public void deleteCafe(Cafe cafe) throws Exception {
    requestAgent.request("cafe.update", cafe);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 카페 삭제를 실패하였습니다.");
    } else {
      System.out.println(" >> 카페를 삭제하였습니다.");
    }
  }

  //-----------------------CafeReview--------------------------------------
  public Collection<CafeReview> getCafeReviewList() throws Exception {
    requestAgent.request("cafeReview.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 리뷰 목록 조회를 실패하였습니다.");
      return null;
    }
    return requestAgent.getObjects(CafeReview.class);
  }

  public Collection<CafeReview> findReviewListByCafeNo(int cafeNo) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("cafeNo", String.valueOf(cafeNo));
    requestAgent.request("cafeReview.selectListByCafeNo", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 리뷰 목록 조회를 실패하였습니다.");
      return null;
    }
    return requestAgent.getObjects(CafeReview.class);
  }

  public Collection<CafeReview> findReviewListByMember(int memberNo) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("memberNo", String.valueOf(memberNo));
    requestAgent.request("cafeReview.selectListByMember", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 리뷰 목록 조회를 실패하였습니다.");
      return null;
    }
    return requestAgent.getObjects(CafeReview.class);
  }

  public CafeReview findByReviewNo(int reviewNo) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("reviewNo", String.valueOf(reviewNo));
    requestAgent.request("cafeReview.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 해당 번호의 리뷰가 존재하지 않습니다.");
    } else {
    }
    return requestAgent.getObject(CafeReview.class);
  }

  public void deleteCafeReview(int reviewNo) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("reviewNo", String.valueOf(reviewNo));
    requestAgent.request("cafeReview.updateReviewStatus", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 리뷰 삭제 실패!");
    } else {
      System.out.println(" >> 삭제를 완료하였습니다.");
    }
  }

  //-----------------------CafeRoom--------------------------------------
  public Collection<CafeRoom> getCafeRoomList() throws Exception {
    requestAgent.request("cafeRoom.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 스터디룸 목록 조회를 실패하였습니다.");
      return null;
    }
    return requestAgent.getObjects(CafeRoom.class);
  }

  public Collection<CafeRoom> findCafeRoomListByCafe(int cafeNo) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("cafeNo", String.valueOf(cafeNo));
    requestAgent.request("cafeRoom.selectListByCafe", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 스터디룸 목록 조회를 실패하였습니다.");
      return null;
    }
    return requestAgent.getObjects(CafeRoom.class);
  }

  public CafeRoom findByRoomNo(int roomNo) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("roomNo", String.valueOf(roomNo));
    requestAgent.request("cafeRoom.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 스터디룸 조회를 실패해였습니다.");
    } else {
      return requestAgent.getObject(CafeRoom.class);
    }
    return null;
  }

  public void insertCafeRoom(CafeRoom cafeRoom) throws Exception {
    requestAgent.request("cafeRoom.insert", cafeRoom);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 스터디룸 등록을 실패하였습니다.");
    } else {
      System.out.println(" >> 등록되었습니다.");
    }
  }

  public void updateCafeRoom(CafeRoom cafeRoom) throws Exception {
    requestAgent.request("cafeRoom.update", cafeRoom);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 스터디룸 수정을 실패하였습니다.");
    } else {
      System.out.println(" >> 수정이 완료 되었습니다.");
    }
  }

  public void deleteCafeRoom(int roomNo) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("roomNo", String.valueOf(roomNo));
    requestAgent.request("cafeRoom.delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 스터디룸 삭제 실패하였습니다.");
    } else {
      System.out.println(" >> 스터디룸을 삭제하였습니다.");
    }
  }

  //-----------------------CafeReservation--------------------------------------
  public Collection<CafeReservation> getCafeReservationList() throws Exception {
    requestAgent.request("cafeReservation.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 예약 목록 조회를 실패하였습니다.");
      return null;
    }
    return requestAgent.getObjects(CafeReservation.class);
  }

  public Collection<CafeReservation> findReservationListByMember(int memberNo) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("memberNo", String.valueOf(memberNo));
    requestAgent.request("cafeReservation.selectListByMember", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 예약 목록 조회를 실패하였습니다.");
      return null;
    }
    return requestAgent.getObjects(CafeReservation.class);
  }

  public Collection<CafeReservation> findReservationListByCeoMember(int ceoNo, int cafeNo) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("ceoNo", String.valueOf(ceoNo));
    params.put("cafeNo", String.valueOf(cafeNo));
    requestAgent.request("cafeReservation.selectListByCeoMember", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 예약 목록 조회를 실패하였습니다.");
      return null;
    }
    return requestAgent.getObjects(CafeReservation.class);
  }

  public CafeReservation findReservationByMember(int memberNo, int reserNo) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("memberNo", String.valueOf(memberNo));
    params.put("reservationNo", String.valueOf(reserNo));
    requestAgent.request("cafeReservation.selectOneByMember", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 예약 목록 조회를 실패하였습니다.");
      return null;
    }
    return requestAgent.getObject(CafeReservation.class);
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

  public void deleteReservation(CafeReservation cafeReservation, int status) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("reservationNo", String.valueOf(cafeReservation.getReservationNo()));
    params.put("status", String.valueOf(status));

    requestAgent.request("cafeReservation.updateReservationStatus", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 예약 취소를 실패하였습니다.");
    } else {
      if (status == 2) {
        System.out.println(" >> 예약이 취소되었습니다.");
      } else if (status == 4) {
        System.out.println(" >> 예약을 거절하였습니다.");
      }
    }
  }
}
