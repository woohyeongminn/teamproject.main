package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Admin;
import com.ogong.pms.domain.AdminNotice;
import com.ogong.util.Prompt;

public class AdminNoticeUpdateHandler extends AbstractAdminNoticeHandler {

  public AdminNoticeUpdateHandler(List<AdminNotice> adminNoticeList, List<Admin> adminList) {
    super(adminNoticeList, adminList);
  }

  public void execute() {
    System.out.println();
    System.out.println("▶ 공지 변경");
    int adminnotiNo = Prompt.inputInt("번호 : ");

    AdminNotice adminWriteList = findByNotiNo(adminnotiNo);

    if (adminWriteList == null) {
      System.out.println("공지를 다시 선택하세요.");
      return;
    }

    String adminNoticeTitle = Prompt.inputString(
        String.format("제목(%s) : ", adminWriteList.getAdminNotiTitle()));
    String adminNoticeContent = Prompt.inputString(
        String.format("내용(%s) : ", adminWriteList.getAdminNotiContent()));

    String inputnotice = Prompt.inputString("정말 변경하시겠습니까? (네 / 아니오) ");
    if (inputnotice.equalsIgnoreCase("아니오") || inputnotice.length() == 0) {
      System.out.println("변경이 취소되었습니다.");
      return;
    }

    adminWriteList.setAdminNotiTitle(adminNoticeTitle);
    adminWriteList.setAdminNotiContent(adminNoticeContent);
    System.out.println("공지가 변경되었습니다.");
  }

}
