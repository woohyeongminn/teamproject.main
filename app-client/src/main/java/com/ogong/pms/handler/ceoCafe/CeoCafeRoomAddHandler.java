package com.ogong.pms.handler.ceoCafe;

import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeRoom;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.pms.handler.cafe.PromptCafe;
import com.ogong.util.Prompt;

public class CeoCafeRoomAddHandler implements Command {

  PromptCafe promptcafe;
  int roomNo = 5;

  public CeoCafeRoomAddHandler (PromptCafe promptcafe) {
    this.promptcafe = promptcafe;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 스터디룸 등록");
    System.out.println();

    Cafe cafe = promptcafe.findByCafeNo((int) request.getAttribute("cafeNo"));

    CafeRoom cafeRoom = new CafeRoom();
    cafeRoom.setCafe(cafe);
    cafeRoom.setRoomName(Prompt.inputString(" 스터디룸 이름 : "));
    cafeRoom.setRoomImg(Prompt.inputString(" 스터디룸 사진 : "));
    cafeRoom.setRoomInfo(Prompt.inputString(" 스터디룸 설명 : "));
    cafeRoom.setStartTime(cafe.getOpenTime());
    cafeRoom.setEndTime(cafe.getCloseTime());
    cafeRoom.setRoomPrice(Prompt.inputInt(" 스터디룸 시간당금액 : "));

    System.out.println();
    String input = Prompt.inputString(" 등록하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 등록이 취소되었습니다.");
      return;
    }
    cafeRoom.setRoomNo(roomNo++);

    promptcafe.insertCafeRoom(cafeRoom);
  }
}
