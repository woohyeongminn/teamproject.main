package com.ogong.pms.handler2;

import java.util.List;
import com.ogong.pms.domain.Admin;
import com.ogong.pms.domain.AdminNotice;
import com.ogong.util.Prompt;

public class AdminNoticeDetailHandler {

  List<AdminNotice> adminNoticeList;
  List<Admin> adminList;


  public void detail() {
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


  public AdminNotice findByNotiNo(int adminnotiNo) {
    for (AdminNotice adminNotice : adminNoticeList) {
      if (adminNotice.getAdminNotiNo() == adminnotiNo) {
        return adminNotice;
      }
    }
    return null;
  }
}
