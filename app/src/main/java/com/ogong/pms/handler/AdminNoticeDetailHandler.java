package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Admin;
import com.ogong.pms.domain.AdminNotice;
import com.ogong.util.Prompt;

public class AdminNoticeDetailHandler extends AbstractAdminNoticeHandler {

  public AdminNoticeDetailHandler(List<AdminNotice> adminNoticeList, List<Admin> adminList) {
    super(adminNoticeList, adminList);
  }


  public void execute() {
    System.out.println();
    System.out.println("▶ 공지 상세");
    int adminnotiNo = Prompt.inputInt("번호 : ");
    System.out.println();

    AdminNotice adminWriteList = findByNotiNo(adminnotiNo);

    if (adminWriteList == null) {
      System.out.println("공지를 다시 선택하세요.");
      return;
    }

    System.out.printf(">> %s\n", adminWriteList.getAdminNotiTitle());
    System.out.printf(">> %s\n", adminWriteList.getAdminNotiContent());
    System.out.printf(">> %s\n", adminWriteList.getAdminNotiWriter().getMasterNickname());
    System.out.printf(">> %s\n", adminWriteList.getAdminNotiRegisteredDate());
  }

}
