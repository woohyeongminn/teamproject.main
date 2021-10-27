package com.ogong.pms.servlet.ceoCafe;

import java.util.HashMap;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.dao.CafeRoomDao;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeRoom;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class CeoCafeRoomDetailHandler implements Command {

  CafeDao cafeDao;
  CafeRoomDao cafeRoomDao;

  public CeoCafeRoomDetailHandler (CafeDao cafeDao, CafeRoomDao cafeRoomDao) {
    this.cafeDao = cafeDao;
    this.cafeRoomDao = cafeRoomDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 스터디룸 상세");

    CafeRoom cafeRoom = null;
    int input;
    HashMap<Integer,Integer> selectRoomNo = (HashMap) request.getAttribute("selectRoomNo");
    Cafe cafe = cafeDao.findByCafeNo((int) request.getAttribute("cafeNo"));

    while (true) {
      System.out.println();
      input = Prompt.inputInt(" 번호 : ");

      if (input > selectRoomNo.size() || input <= 0) {
        System.out.println(" >> 번호를 다시 선택해 주세요.");
        continue;
      }

      cafeRoom = cafeRoomDao.findByRoomNo(selectRoomNo.get(input));

      if (cafeRoom != null && cafeRoom.getCafe().getNo() == cafe.getNo()) {
        break;
      } 
    }

    request.setAttribute("cafeRoom", cafeRoom);

    System.out.println();
    System.out.printf(" (%s)\n", cafeRoom.getRoomNo());
    System.out.printf(" [%s]\n", cafeRoom.getRoomName());
    System.out.printf(" >> 대표이미지 : %s\n", cafeRoom.getCafeRoomImageNames());
    System.out.printf(" >> 소개글 : %s\n", cafeRoom.getRoomInfo());
    System.out.printf(" >> 인원 : %s\n", cafeRoom.getPeople());
    System.out.printf(" >> 시간당 금액 : %d원\n", cafeRoom.getRoomPrice());

    System.out.println("\n----------------------");
    System.out.println("1. 수정");
    System.out.println("2. 삭제");
    System.out.println("0. 이전");
    int selectNo = Prompt.inputInt(" 선택> ");
    switch (selectNo) {
      case 1: request.getRequestDispatcher("/ceoMember/cafeRoomUpdate").forward(request); return;
      case 2: request.getRequestDispatcher("/ceoMember/cafeRoomDelete").forward(request); return;
      case 0: return;
      default : 
        System.out.println(" >> 번호를 다시 선택해 주세요.");
    }
  }
}
