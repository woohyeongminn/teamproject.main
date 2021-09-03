package com.ogong.pms.handler2;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.Admin;
import com.ogong.pms.domain.AdminNotice;
import com.ogong.util.Prompt;

public class AdminNoticeAddHandler {

  List<AdminNotice> adminNoticeList;
  List<Admin> adminList;

  public AdminNoticeAddHandler(List<AdminNotice> adminNoticeList, List<Admin> adminList) {
    this.adminNoticeList = adminNoticeList;
    this.adminList = adminList;

    AdminNotice adminWriteList = new AdminNotice();
    adminWriteList.setAdminNotiNo(1);
    adminWriteList.setAdminNotiTitle("오늘의 공부 회원 가입을 환영합니다!");
    adminWriteList.setAdminNotiContent("오늘의 공부와 함께 목표를 향해 으쌰으쌰!");
    adminWriteList.setAdminNotiWriter(adminList.get(0));
    adminWriteList.setAdminNotiRegisteredDate(new Date(System.currentTimeMillis()));

    adminNoticeList.add(adminWriteList);

    adminWriteList = new AdminNotice();
    adminWriteList.setAdminNotiNo(2);
    adminWriteList.setAdminNotiTitle("오늘의 공부 홈페이지 이용 방법");
    adminWriteList.setAdminNotiContent("내용 업데이트 예정입니다.");
    adminWriteList.setAdminNotiWriter(adminList.get(0));
    adminWriteList.setAdminNotiRegisteredDate(new Date(System.currentTimeMillis()));

    adminNoticeList.add(adminWriteList);
  }

  public void add() {
    System.out.println();
    System.out.println("▶ 공지 등록");
    System.out.println();

    AdminNotice adminWriteList = new AdminNotice();

    adminWriteList.setAdminNotiNo(Prompt.inputInt("번호 : "));
    adminWriteList.setAdminNotiTitle(Prompt.inputString("제목 : "));
    adminWriteList.setAdminNotiContent(Prompt.inputString("내용 : "));
    adminWriteList.setAdminNotiWriter(AdminHandler.getLoginAdmin());
    adminWriteList.setAdminNotiRegisteredDate(new Date(System.currentTimeMillis()));

    adminNoticeList.add(adminWriteList);
  }

}
