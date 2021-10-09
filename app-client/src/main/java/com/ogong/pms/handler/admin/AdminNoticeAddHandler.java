package com.ogong.pms.handler.admin;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.ogong.pms.domain.AdminNotice;
import com.ogong.pms.handler.AuthAdminLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class AdminNoticeAddHandler implements Command {

  RequestAgent requestAgent;

  public AdminNoticeAddHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 공지 등록");
    System.out.println();

    AdminNotice adminNotice = new AdminNotice();

    requestAgent.request("notice.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 공지글 목록이 없습니다.");
      return;
    }

    Collection<AdminNotice> adminNoticeList = requestAgent.getObjects(AdminNotice.class);
    List<AdminNotice> arrayAdminNotice = new ArrayList<>(adminNoticeList);

    adminNotice.setAdminNotiTitle(Prompt.inputString(" 제목 : "));
    adminNotice.setAdminNotiContent(Prompt.inputString(" 내용 : "));
    adminNotice.setAdminNotiWriter(AuthAdminLoginHandler.getLoginAdmin().getMasterNickname());
    adminNotice.setAdminNotiRegisteredDate(new Date(System.currentTimeMillis()));

    System.out.println();
    String input = Prompt.inputString(" 정말 등록하시겠습니까? (네 / 아니오) ");
    if (!input.equals("네")) {
      System.out.println(" >> 등록이 취소되었습니다.");
      request.getRequestDispatcher("/adminNotice/list").forward(request);
      return;
    }

    // 마지막 고유번호를 찾아서 신규 등록시 +1 되도록 기능 구현
    AdminNotice lastAdminNotice = null;
    if (!arrayAdminNotice.isEmpty()) {
      lastAdminNotice = arrayAdminNotice.get(arrayAdminNotice.size() - 1);
      adminNotice.setAdminNotiNo(lastAdminNotice.getAdminNotiNo() +1);
    } else {
      adminNotice.setAdminNotiNo(1);
    }

    requestAgent.request("notice.insert", adminNotice);

    System.out.println(" >> 공지글 등록이 완료되었습니다.");
    request.getRequestDispatcher("/adminNotice/list").forward(request);
  }
}
