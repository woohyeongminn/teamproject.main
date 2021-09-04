package com.ogong.pms.handler2;

import java.util.List;
import com.ogong.pms.domain.Admin;
import com.ogong.pms.domain.AdminNotice;

public class AdminNoticeListHandler extends AbstractAdminNoticeHandler {

  public AdminNoticeListHandler(List<AdminNotice> adminNoticeList, List<Admin> adminList) {
    super(adminNoticeList, adminList);
  }


  public void execute() {
    System.out.println();
    System.out.println("▶ 공지 목록");
    System.out.println();

    for (AdminNotice adminWriteList : adminNoticeList) {
      System.out.printf("(%d)\n 제목 : %s\n 작성자 : %s\n 등록일 : %s\n", 
          adminWriteList.getAdminNotiNo(), 
          adminWriteList.getAdminNotiTitle(),
          adminWriteList.getAdminNotiWriter().getMasterNickname(),
          adminWriteList.getAdminNotiRegisteredDate());
      System.out.println();
    }      
  }

}
