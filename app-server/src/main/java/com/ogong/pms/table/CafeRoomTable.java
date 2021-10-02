package com.ogong.pms.table;

import com.ogong.pms.domain.CafeRoom;
import com.ogong.server.DataProcessor;
import com.ogong.server.Request;
import com.ogong.server.Response;

public class CafeRoomTable extends JsonDataTable<CafeRoom> implements DataProcessor {

  public CafeRoomTable() {
    super("cafeRoom.json", CafeRoom.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    switch(request.getCommand()) {
      case "cafeRoom.selectList" : selectList(request, response); break;
      case "cafeRoom.selectOne" : selectOne(request, response); break;
      case "cafeRoom.selectOneByRoomNoCafeNo" : selectOneByRoomNoCafeNo(request, response); break;
      default:
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void selectList(Request request, Response response) throws Exception {
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  private void selectOne(Request request, Response response) throws Exception {
    int roomNo = Integer.parseInt(request.getParameter("roomNo"));
    CafeRoom cafeRoom = findByRoomNo(roomNo);

    if (cafeRoom != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(cafeRoom);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 스터디룸을 찾을 수 없습니다.");
    }
  }

  private void selectOneByRoomNoCafeNo(Request request, Response response) throws Exception {
    int roomNo = Integer.parseInt(request.getParameter("roomNo"));
    int cafeNo = Integer.parseInt(request.getParameter("cafeNo"));
    CafeRoom cafeRoom = findByRoomNoCafeNo(roomNo, cafeNo);

    if (cafeRoom != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(cafeRoom);
    } else {
      response.setStatus(Response.FAIL);
    }
  }

  private CafeRoom findByRoomNo(int roomNo) {
    for (CafeRoom cafeRoom : list) {
      if (cafeRoom.getRoomNo() == roomNo) {
        return cafeRoom;
      }
    }
    return null;
  }

  private CafeRoom findByRoomNoCafeNo(int roomNo, int cafeNo) {
    for (CafeRoom cafeRoom : list) {
      if (cafeRoom.getRoomNo() == roomNo && cafeRoom.getCafe().getNo() == cafeNo) {
        return cafeRoom;
      }
    }
    return null;
  }
}
