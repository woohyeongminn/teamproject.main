package com.ogong.pms.handler.cafe;

public abstract class CafeHandlerHelper {

  public static String getReviewStatusLabel(String status) {
    switch (status) {
      case "true": return "작성완료";
      case "false": return "작성대기";
      default: return "오류";
    }
  }

  public static String getReviewGradeStatusLabel(int status) {
    switch (status) {
      case 0: return "☆☆☆☆☆";
      case 1: return "★☆☆☆☆";
      case 2: return "★★☆☆☆";
      case 3: return "★★★☆☆";
      case 4: return "★★★★☆";
      case 5: return "★★★★★";
      default: return "오류";
    }
  }

  public static String getReservationStatus(int status) {
    switch (status) {
      case 0: return "예약완료(현장결제)";
      case 1: return "결제완료";
      case 2: return "예약취소";
      case 3: return "결제취소";
      case 4: return "예약거절";
      case 5: return "결제거절";
      default: return "오류";
    }
  }

}
