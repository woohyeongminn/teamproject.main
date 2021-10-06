package com.ogong.pms.handler.admin;

import java.util.HashMap;
import com.ogong.pms.domain.AdminNotice;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class AdminNoticeDeleteHandler implements Command {

  RequestAgent requestAgent;

  public AdminNoticeDeleteHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 공지 삭제");
    int noticeNo = (int) request.getAttribute("noticeNo");

    HashMap<String,String> params = new HashMap<>();
    params.put("noticeNo", String.valueOf(noticeNo));

    requestAgent.request("notice.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 공지를 다시 선택하세요.");
      return;
    }

    AdminNotice notice = requestAgent.getObject(AdminNotice.class);

    String inputnotice = Prompt.inputString("\n 정말 삭제하시겠습니까? (네 / 아니오) ");
    if (!inputnotice.equalsIgnoreCase("네")) {
      System.out.println(" >> 삭제가 취소되었습니다.");
      request.getRequestDispatcher("/adminNotice/list").forward(request);
      return;
    }

    requestAgent.request("notice.delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 스터디 삭제가 실패되었습니다.");
      return;
    }


    System.out.println(" >> 공지가 삭제되었습니다.");
    request.getRequestDispatcher("/adminNotice/list").forward(request);
  }

}
