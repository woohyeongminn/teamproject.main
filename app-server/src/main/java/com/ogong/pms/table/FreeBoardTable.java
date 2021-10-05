package com.ogong.pms.table;

import com.ogong.pms.domain.FreeBoard;
import com.ogong.server.DataProcessor;
import com.ogong.server.Request;
import com.ogong.server.Response;

// 역할 
// - 회원 데이터를 저장하고 조회하는 일을 한다.
public class FreeBoardTable extends JsonDataTable<FreeBoard> implements DataProcessor {

  public FreeBoardTable() {
    super("freeBoard.json", FreeBoard.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    switch (request.getCommand()) {
      case "freeBoard.insert" : insert(request, response); break;
      case "freeBoard.selectOne" : selectOne(request, response); break;
      case "freeBoard.selectList" : selectList(request, response); break;
      case "freeBoard.update" : update(request, response); break;
      case "freeBoard.delete" : delete(request, response); break;
      default : 
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insert(Request request, Response response) throws Exception {
    FreeBoard freeBoard = request.getObject(FreeBoard.class);
    list.add(freeBoard);
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  private void selectList(Request request, Response response) throws Exception {
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  private void selectOne(Request request, Response response) throws Exception {
    int no = Integer.parseInt(request.getParameter("FreeBoardNo"));
    FreeBoard freeBoard = findByFreeBoardNo(no);

    if (freeBoard != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(freeBoard);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 스터디를 찾을 수 없습니다.");
    }
  }

  private void update(Request request, Response response) throws Exception {
    FreeBoard freeBoard = request.getObject(FreeBoard.class);

    int index = indexOf(freeBoard.getFreeBoardNo());
    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 공지글을 찾을 수 없습니다.");
      return;
    }

    list.set(index, freeBoard);
    response.setStatus(Response.SUCCESS);
  }

  public FreeBoard findByFreeBoardNo(int inputNo) {
    for (FreeBoard freeBoard : list) {
      if (freeBoard.getFreeBoardNo() == inputNo) {
        return freeBoard;
      }
    }
    return null;
  }

  private void delete(Request request, Response response) throws Exception {
    int no = Integer.parseInt(request.getParameter("freeBoardNo"));
    int index = indexOf(no);

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 공지글을 찾을 수 없습니다.");
      return;
    }

    list.remove(index);
    response.setStatus(Response.SUCCESS);
  }

  private int indexOf(int freeBoardNo) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getFreeBoardNo() == freeBoardNo) {
        return i;
      }
    }
    return -1;
  }

}
