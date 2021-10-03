package com.ogong.pms.table;

import java.util.ArrayList;
import com.ogong.pms.domain.Study;
import com.ogong.server.DataProcessor;
import com.ogong.server.Request;
import com.ogong.server.Response;

// 역할 
// - 회원 데이터를 저장하고 조회하는 일을 한다.
public class StudyTable extends JsonDataTable<Study> implements DataProcessor {

  public StudyTable() {
    super("study.json", Study.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    switch (request.getCommand()) {
      case "study.selectList" : selectList(request, response); break;
      case "study.selectListByKeyword" : selectListByKeyword(request, response); break;

      default : 
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void selectList(Request request, Response response) throws Exception {
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  private void selectListByKeyword(Request request, Response response) throws Exception {
    String keyword = request.getParameter("keyword");

    ArrayList<Study> searchResult = new ArrayList<>();

    for (Study study : list) {
      if (!study.getStudyTitle().contains(keyword) &&
          !study.getSubject().contains(keyword) &&
          !study.getArea().contains(keyword)) {
        continue;
      }
      searchResult.add(study);
    }
    response.setStatus(Response.SUCCESS);
    response.setValue(searchResult);
  }
}
