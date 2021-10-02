package com.ogong.pms.table;

import com.ogong.pms.domain.AskBoard;
import com.ogong.server.DataProcessor;
import com.ogong.server.Request;
import com.ogong.server.Response;

// 역할
// - 문의사항 데이터를 저장하고 조회하는 일을 한다.
public class AskBoardTable extends JsonDataTable<AskBoard> implements DataProcessor {

  public AskBoardTable() {
    super("askBoard.json", AskBoard.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    switch (request.getCommand()) {
      case "askBoard.insert" : insert(request, response); break;
      case "askBoard.selectList" : selectList(request, response); break;
      default : 
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insert(Request request, Response response) throws Exception {
    AskBoard askBoard = request.getObject(AskBoard.class);
    list.add(askBoard);
    response.setStatus(Response.SUCCESS);
  }

  private void selectList(Request request, Response response) throws Exception {
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

}
