package com.ogong.pms.table;

import java.util.ArrayList;
import java.util.List;
import com.ogong.pms.domain.Member;
import com.ogong.pms.server.Request;
import com.ogong.pms.server.Response;

// 역할 
// - 회원 데이터를 저장하고 조회하는 일을 한다.
public class MemberTable {

  List<Member> list = new ArrayList<>();

  public void execute(Request request, Response response) throws Exception {

    switch (request.getCommand()) {
      case   "/member/insert" : insert(request, response); break;
      default : 
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insert(Request request, Response response) throws Exception {
    Member member = request.getValue(Member.class);
    list.add(member);
    response.setStatus(Response.SUCCESS);
  }

}
