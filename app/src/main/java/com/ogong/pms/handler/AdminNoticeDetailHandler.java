package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.AdminNotice;
import com.ogong.util.Prompt;

public class AdminNoticeDetailHandler extends AbstractAdminNoticeHandler {

  public AdminNoticeDetailHandler(List<AdminNotice> adminNoticeList) {
    super(adminNoticeList);
  }


  @Override
  public void execute(CommandRequest request) {
    System.out.println();
    System.out.println("▶ 공지 상세");
    int adminnotiNo = Prompt.inputInt(" 번호 : ");
    System.out.println();

    AdminNotice adminWriteList = findByNotiNo(adminnotiNo);

    if (adminWriteList == null) {
      System.out.println(" >> 공지를 다시 선택하세요.");
      return;
    }

    System.out.printf(" [%s]\n", adminWriteList.getAdminNotiTitle());
    System.out.printf(" >> 내용 : %s\n", adminWriteList.getAdminNotiContent());
    System.out.printf(" >> 작성자 : %s\n", adminWriteList.getAdminNotiWriter());
    System.out.printf(" >> 등록일 : %s\n", adminWriteList.getAdminNotiRegisteredDate());
  }

}
