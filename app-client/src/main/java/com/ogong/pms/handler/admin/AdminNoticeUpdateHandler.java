package com.ogong.pms.handler.admin;

import java.util.HashMap;
import com.ogong.pms.domain.AdminNotice;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class AdminNoticeUpdateHandler implements Command {

  RequestAgent requestAgent;

  public AdminNoticeUpdateHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 공지 변경");
    int noticeNo = (int) request.getAttribute("noticeNo");

    HashMap<String,String> params = new HashMap<>();
    params.put("noticeNo", String.valueOf(noticeNo));

    requestAgent.request("notice.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 공지를 다시 선택하세요.");
      return;
    }

    AdminNotice notice = requestAgent.getObject(AdminNotice.class);

    String adminNoticeTitle = Prompt.inputString(
        String.format(" 제목(%s) : ", notice.getAdminNotiTitle()));
    String adminNoticeContent = Prompt.inputString(
        String.format(" 내용(%s) : ", notice.getAdminNotiContent()));

    System.out.println();
    String inputnotice = Prompt.inputString(" 정말 변경하시겠습니까? (네 / 아니오) ");
    if (!inputnotice.equalsIgnoreCase("네")) {
      System.out.println(" >> 변경이 취소되었습니다.");
      return;
    }

    notice.setAdminNotiTitle(adminNoticeTitle);
    notice.setAdminNotiContent(adminNoticeContent);

    requestAgent.request("notice.update", notice);

    System.out.println(" >> 공지가 변경되었습니다.");
  }

}
