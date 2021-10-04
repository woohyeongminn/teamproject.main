package com.ogong.pms.handler.admin;

import java.util.Collection;
import com.ogong.pms.domain.AdminNotice;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;

public class AdminNoticeListHandler implements Command {

  RequestAgent requestAgent;

  public AdminNoticeListHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 공지 목록");
    System.out.println();

    requestAgent.request("notice.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 공지글이 없습니다.");
      return;
    }

    Collection<AdminNotice> adminNoticeList = requestAgent.getObjects(AdminNotice.class);

    for (AdminNotice noticeList : adminNoticeList) {
      System.out.printf(" (%d)\n 제목 : %s\n 작성자 : %s\n 등록일 : %s\n", 
          noticeList.getAdminNotiNo(), 
          noticeList.getAdminNotiTitle(),
          noticeList.getAdminNotiWriter(),
          noticeList.getAdminNotiRegisteredDate());
      System.out.println();
    }      
  }

}
