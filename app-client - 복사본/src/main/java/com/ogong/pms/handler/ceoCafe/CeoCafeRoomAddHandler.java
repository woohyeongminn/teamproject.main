package com.ogong.pms.handler.ceoCafe;

import java.util.ArrayList;
import java.util.HashMap;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeImage;
import com.ogong.pms.domain.CafeRoom;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class CeoCafeRoomAddHandler implements Command {

  CafeDao cafeDao;

  public CeoCafeRoomAddHandler (CafeDao cafeDao) {
    this.cafeDao = cafeDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 스터디룸 등록");
    System.out.println();

    Cafe cafe = cafeDao.findByCafeNo((int) request.getAttribute("cafeNo"));

    CafeRoom cafeRoom = new CafeRoom();
    cafeRoom.setCafe(cafe);
    cafeRoom.setRoomName(Prompt.inputString(" 스터디룸 이름 : "));

    ArrayList<CafeImage> fileNames = new ArrayList<>();
    while(true) {
      String fileName = Prompt.inputString(" 사진 (완료:빈 문자열) : ");
      if (fileName.length() == 0) {
        break;
      }
      fileNames.add(new CafeImage(fileName));
    }

    cafeRoom.setRoomInfo(Prompt.inputString(" 스터디룸 설명 : "));
    cafeRoom.setPeople(Prompt.inputInt(" 스터디룸 최대인원 : "));
    cafeRoom.setRoomPrice(Prompt.inputInt(" 스터디룸 시간당금액 : "));

    System.out.println();
    String input = Prompt.inputString(" 등록하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 등록이 취소되었습니다.");
      return;
    }

    cafeDao.insertCafeRoom(cafeRoom);

    if (!fileNames.isEmpty()) {
      HashMap<String,Object> params = new HashMap<>();
      params.put("fileNames", fileNames);
      params.put("cafeRoomNo", cafeRoom.getRoomNo());

      cafeDao.insertCafeRoomImage(params);
    }

    System.out.println(" >> 스터디룸 등록이 완료되었습니다.");
  }
}
