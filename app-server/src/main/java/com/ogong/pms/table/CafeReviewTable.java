package com.ogong.pms.table;

import com.ogong.pms.domain.CafeReview;
import com.ogong.server.DataProcessor;
import com.ogong.server.Request;
import com.ogong.server.Response;

public class CafeReviewTable extends JsonDataTable<CafeReview> implements DataProcessor {

  public CafeReviewTable() {
    super("cafeReview.json", CafeReview.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    switch(request.getCommand()) {
      case "cafeReview.selectList" : selectList(request, response); break;
      default:
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void selectList(Request request, Response response) throws Exception {
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

}
