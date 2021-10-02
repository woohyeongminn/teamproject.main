package com.ogong.pms.table;

import com.ogong.pms.domain.CafeReservation;
import com.ogong.server.DataProcessor;
import com.ogong.server.Request;
import com.ogong.server.Response;

public class CafeReservationTable extends JsonDataTable<CafeReservation> implements DataProcessor {

  public CafeReservationTable() {
    super("cafeReservation.json", CafeReservation.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    switch(request.getCommand()) {
      case "cafeReservation.selectList" : selectList(request, response); break;
      case "cafeReservation.insert" : insert(request, response); break;
      default:
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void selectList(Request request, Response response) throws Exception {
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  private void insert(Request request, Response response) throws Exception {
    CafeReservation cafeReservation = request.getObject(CafeReservation.class);
    list.add(cafeReservation);
    response.setStatus(Response.SUCCESS);
  }

}
