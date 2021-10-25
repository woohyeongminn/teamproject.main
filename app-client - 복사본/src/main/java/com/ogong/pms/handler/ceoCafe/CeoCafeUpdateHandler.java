package com.ogong.pms.handler.ceoCafe;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.domain.Address;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeImage;
import com.ogong.pms.domain.CeoMember;
import com.ogong.pms.handler.AuthCeoMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.pms.handler.cafe.CafeHandlerHelper;
import com.ogong.util.AddressSearchApi;
import com.ogong.util.Prompt;

public class CeoCafeUpdateHandler implements Command {

  CafeDao cafeDao;

  public CeoCafeUpdateHandler (CafeDao cafeDao) {
    this.cafeDao = cafeDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 장소 수정");
    System.out.println();

    CeoMember ceoMember = AuthCeoMemberLoginHandler.getLoginCeoMember();
    Cafe cafe = cafeDao.findByCafeNo((int) request.getAttribute("cafeNo"));

    HashMap<String,Object> params = new HashMap<>();
    params.put("ceoNo", ceoMember.getCeoNo());
    cafe.setHoliday(cafeDao.getCafeHoliday(params));

    System.out.println(" 1. 사진 변경");
    System.out.println(" 2. 기본 정보 수정");
    System.out.println(" 0. 이전");

    int inputCase = Prompt.inputInt(" 선택> ");
    switch (inputCase) {
      default:
        System.out.println(" >> 번호를 다시 입력해주세요.");
      case 0: return;
      case 1: modifyCafeImage(cafe); return;
      case 2: 
    }

    System.out.println();
    String info = Prompt.inputString(String.format(" 소개글[%s](건너 뛰기: 빈 문자열) : ", cafe.getInfo()));
    if (info.length() > 0) {
      cafe.setInfo(info);
    }
    System.out.printf(" 기존 주소 : %s\n", cafe.getLocation());
    int input1 = Prompt.inputInt(" 1. 수정 2. 건너 뛰기 > ");

    while (true) {
      if (input1 == 1) {
        AddressSearchApi api = new AddressSearchApi();
        Address address = api.searchAddress();
        String addressString = address.getLnmAdres();
        System.out.println(" 바꿀 주소 : " + addressString);
        addressString += " " + Prompt.inputString(" 상세 주소 : ");
        cafe.setLocation(addressString);
        break;
      } else if (input1 == 2) {
        break;
      }
      System.out.println(" >> 번호를 다시 입력하세요.");
    }

    String phone = Prompt.inputString(String.format(" 전화번호[%s](건너 뛰기: 빈 문자열) : ", cafe.getPhone()));
    if (phone.length() > 0) {
      cafe.setPhone(phone);
    }
    String openTime = Prompt.inputString(String.format(" 오픈시간[%s](건너 뛰기: 빈 문자열) : ", cafe.getOpenTime()));
    if (openTime.length() > 0) {
      cafe.setOpenTime(LocalTime.parse(openTime));
    }
    String closeTime = Prompt.inputString(String.format(" 마감시간[%s](건너 뛰기: 빈 문자열) : ", cafe.getCloseTime()));
    if (closeTime.length() > 0) {
      cafe.setCloseTime(LocalTime.parse(closeTime));
    }
    //    String holiday = Prompt.inputString(String.format(" 휴무일[%s](건너 뛰기: 빈 문자열) : ", cafe.getHoliday()));
    int cafeStatus = 0;
    if (cafe.getCafeStatus() != 1) {
      cafeStatus = CafeHandlerHelper.promptCafeStatus(cafe.getCafeStatus());
      cafe.setCafeStatus(cafeStatus);
    }

    String input = Prompt.inputString(" 정말 수정하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 수정을 취소하였습니다.");
      return;
    }

    //    cafe.setName(name);
    //    cafe.setMainImg(mainImg);
    //    cafe.setHoliday(holiday);

    cafeDao.updateCafe(cafe);
    System.out.println(" >> 수정이 완료 되었습니다.");
  }

  public void modifyCafeImage(Cafe cafe) throws Exception {
    System.out.println();

    List<CafeImage> imageList = cafe.getCafeImgs(); // 원래 이미지 리스트
    HashMap<Integer,CafeImage> params = new HashMap<>(); // 리스트 1부터 출력해서 쓰는,,
    ArrayList<CafeImage> deleteImageList = new ArrayList<>(); // 삭제할 이미지 리스트
    ArrayList<CafeImage> addImageList = new ArrayList<>(); // 등록할 이미지 리스트

    if (!imageList.isEmpty()) {
      for (int i = 0; i < imageList.size(); i++) {
        CafeImage cafeImage = imageList.get(i);
        params.put(i+1, cafeImage);

        System.out.printf(" %d: %s\n",
            i+1,
            cafeImage.getName());
      }

      System.out.println();

      while (true) {
        try {
          int deleteImageNo = Prompt.inputInt(" 삭제할 이미지 번호 (완료:빈 문자열) : ");

          if (deleteImageNo < 0 && deleteImageNo > imageList.size()) {
            System.out.println(" >> 번호를 다시 입력해주세요.");
            continue;
          }

          CafeImage cafeImage = params.get(deleteImageNo);
          deleteImageList.add(cafeImage);

        } catch (NumberFormatException e) {
          break;
        }
      }
    }

    System.out.println();

    String input = Prompt.inputString(" 사진을 등록하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      if (!deleteImageList.isEmpty()) {
        HashMap<String,Object> deleteParams = new HashMap<>();
        deleteParams.put("fileNames", deleteImageList);
        cafeDao.deleteCafeImage(deleteParams);
      }
      return;
    }

    System.out.println();

    while(true) {
      String fileName = Prompt.inputString(" 등록할 사진 (완료:빈 문자열) : ");

      if (fileName.length() == 0) {
        break;
      }

      CafeImage cafeImage = new CafeImage();
      cafeImage.setName(fileName);
      cafeImage.setCafeNo(cafe.getNo());
      addImageList.add(cafeImage);
    }

    System.out.println();

    if (!deleteImageList.isEmpty()) {
      HashMap<String,Object> deleteParams = new HashMap<>();
      deleteParams.put("fileNames", deleteImageList);
      cafeDao.deleteCafeImage(deleteParams);
    }

    if (!addImageList.isEmpty()) {
      HashMap<String,Object> addParams = new HashMap<>();
      addParams.put("fileNames", addImageList);
      addParams.put("cafeNo", cafe.getNo());

      cafeDao.insertCafeImage(addParams);
    }

    System.out.println(" >> 사진 변경 완료!");
  }

}
