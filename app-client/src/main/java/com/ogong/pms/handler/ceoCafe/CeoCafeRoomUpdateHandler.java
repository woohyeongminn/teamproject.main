package com.ogong.pms.handler.ceoCafe;

import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.domain.CafeRoom;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class CeoCafeRoomUpdateHandler implements Command {

  CafeDao cafeDao;
  SqlSession sqlSession;

  public CeoCafeRoomUpdateHandler (CafeDao cafeDao, SqlSession sqlSession) {
    this.cafeDao = cafeDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 스터디룸 수정");
    System.out.println();

    CafeRoom cafeRoom = (CafeRoom) request.getAttribute("cafeRoom");

    String name = Prompt.inputString(String.format(" 스터디룸 이름[%s](건너 뛰기: 빈 문자열) : ", cafeRoom.getRoomName()));
    if (name.length() > 0) {
      cafeRoom.setRoomName(name);
    }
    String Info = Prompt.inputString(String.format(" 스터디룸 설명[%s](건너 뛰기: 빈 문자열) : ", cafeRoom.getRoomInfo()));
    if (Info.length() > 0) {
      cafeRoom.setRoomInfo(Info);
    }

    int people = -1;
    try {
      while (true) {
        people = Prompt.inputInt(String.format(" 스터디룸 인원[%d](건너 뛰기: 빈 문자열) : ", cafeRoom.getPeople()));

        if (people <= 0) {
          System.out.println(" >> 인원을 0보다 작게 설정할 수 없습니다. 다시 입력해주세요.");
        } else if (people > 50) {
          System.out.println(" >> 최대 50명까지 입력할 수 있습니다. 다시 입력해주세요.");
        } else {
          cafeRoom.setPeople(people);
          break;
        }
      }
    } catch (NumberFormatException e) {}

    int timePrice = -1;
    try {
      while (true) {
        timePrice = Prompt.inputInt(String.format(" 스터디룸 시간당금액[%d](건너 뛰기: 빈 문자열) : ", cafeRoom.getRoomPrice()));

        if (timePrice <= 0) {
          System.out.println(" >> 금액을 0보다 작게 설정할 수 없습니다. 다시 입력해주세요.");
        } else if (timePrice > 500000) {
          System.out.println(" >> 50만원 이상 입력할 수 없습니다. 다시 입력해주세요.");
        } else {
          cafeRoom.setRoomPrice(timePrice);
          break;
        }
      }
    } catch (NumberFormatException e) {}



    System.out.println();
    String input = Prompt.inputString(" 정말 수정하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 수정을 취소하였습니다.");
      return;
    }

    //    사진 어떻게 할까 고민....
    //    String mainImg = Prompt.inputString(String.format(" 스터디룸 사진(%s) : ", cafeRoom.getRoomImg()));
    //    cafeRoom.setRoomImg(mainImg);

    cafeDao.updateCafeRoom(cafeRoom);
    sqlSession.commit();

    System.out.println(" >> 수정완료. ");
  }
}
