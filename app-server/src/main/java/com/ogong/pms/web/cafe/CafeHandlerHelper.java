package com.ogong.pms.web.cafe;

import static com.ogong.pms.vo.Cafe.DELETE;
import static com.ogong.pms.vo.Cafe.GENERAL;
import static com.ogong.pms.vo.Cafe.STOP;
import static com.ogong.pms.vo.Cafe.WAIT;

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
      case 1: return "⭐☆☆☆☆";
      case 2: return "⭐⭐☆☆☆";
      case 3: return "⭐⭐⭐☆☆";
      case 4: return "⭐⭐⭐⭐☆";
      case 5: return "⭐⭐⭐⭐⭐";
      default: return "오류";
    }
  }

  //  public static String getReservationStatus(int status) {
  //    switch (status) {
  //      case 1: return "예약완료(현장결제)";
  //      case 2: return "결제완료";
  //      case 3: return "예약취소";
  //      case 4: return "결제취소";
  //      case 5: return "예약거절";
  //      case 6: return "결제거절";
  //      default: return "오류";
  //    }
  //  }

  public static String getCafeStatusLabel(int status) {
    switch (status) {
      case WAIT: return "승인대기";         // 1
      case GENERAL: return "운영중";        // 2
      case STOP: return "운영중단";         // 3
      case DELETE: return "삭제";           // 4
      default: return "오류";
    }
  }

  //  public static int promptCafeStatus(int status) {
  //    System.out.printf(" 상태(%s) : \n", getCafeStatusLabel(status));
  //    int input = 0;
  //    do {
  //      System.out.println(" 2 : 운영중");
  //      System.out.println(" 3 : 운영중단");
  //      input = Prompt.inputInt(" > ");
  //
  //      if (input != GENERAL && input != STOP) {
  //        System.out.println(" >> 번호를 잘못 선택하셨습니다.\n    다시 입력해 주세요.\n");
  //      }
  //    } while (input != GENERAL && input != STOP);
  //
  //    return input;
  //  }

  //  public static List<CafeImage> promptCafeImage(Cafe cafe) {
  //
  //    List<CafeImage> imageList = cafe.getCafeImgs(); // 원래 이미지 리스트
  //    HashMap<Integer,CafeImage> params = new HashMap<>(); // 리스트 1부터 출력해서 쓰는,,
  //    List<CafeImage> updateImageList = new ArrayList<>(); // 삭제할 이미지 리스트
  //
  //    if (!imageList.isEmpty()) {
  //      for (int i = 0; i < imageList.size(); i++) {
  //        CafeImage cafeImage = imageList.get(i);
  //        params.put(i+1, cafeImage);
  //
  //        System.out.printf(" %d: %s\n",
  //            i+1,
  //            cafeImage.getName());
  //      }
  //
  //      System.out.println();
  //
  //      while (true) {
  //        try {
  //          int deleteImageNo = Prompt.inputInt(" 삭제할 이미지 번호 (완료:빈 문자열) : ");
  //
  //          if (deleteImageNo < 0 && deleteImageNo > imageList.size()) {
  //            System.out.println(" >> 번호를 다시 입력해주세요.");
  //            continue;
  //          }
  //
  //          CafeImage cafeImage = params.get(deleteImageNo);
  //          cafeImage.setStatus(CafeImage.DELETE);
  //          updateImageList.add(cafeImage);
  //
  //        } catch (NumberFormatException e) {
  //          break;
  //        }
  //      }
  //    }
  //
  //    System.out.println();
  //
  //    String input = Prompt.inputString(" 사진을 등록하시겠습니까? (네 / 아니오) ");
  //    if (!input.equalsIgnoreCase("네")) {
  //      return null;
  //    }
  //
  //    while(true) {
  //      String fileName = Prompt.inputString(" 등록할 사진 (완료:빈 문자열) : ");
  //
  //      if (fileName.length() == 0) {
  //        break;
  //      }
  //
  //      CafeImage cafeImage = new CafeImage();
  //      cafeImage.setName(fileName);
  //      cafeImage.setCafeNo(cafe.getNo());
  //      cafeImage.setStatus(CafeImage.ADD);
  //      updateImageList.add(cafeImage);
  //    }
  //
  //    for (CafeImage cafeImage : updateImageList) {
  //      System.out.println(cafeImage);
  //    }
  //
  //    return updateImageList;
  //  }
}

