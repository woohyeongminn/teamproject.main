package com.ogong.pms.handler;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.Admin;
import com.ogong.pms.domain.AdminNotice;
import com.ogong.util.Prompt;

public class AdminNoticeAddHandler extends AbstractAdminNoticeHandler {

  static Admin loginAdmin;
  public static Admin getLoginAdmin() {
    return loginAdmin;
  }

  public AdminNoticeAddHandler(List<AdminNotice> adminNoticeList) {
    super(adminNoticeList);
  }

  public void execute() {
    System.out.println();
    System.out.println("▶ 공지 등록");
    System.out.println();

    AdminNotice adminWriteList = new AdminNotice();

    adminWriteList.setAdminNotiNo(Prompt.inputInt("번호 : "));
    adminWriteList.setAdminNotiTitle(Prompt.inputString("제목 : "));
    adminWriteList.setAdminNotiContent(Prompt.inputString("내용 : "));
    adminWriteList.setAdminNotiWriter(getLoginAdmin());
    adminWriteList.setAdminNotiRegisteredDate(new Date(System.currentTimeMillis()));

    adminNoticeList.add(adminWriteList);
  }

}
