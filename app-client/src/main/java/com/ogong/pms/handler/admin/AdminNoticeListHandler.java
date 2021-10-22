package com.ogong.pms.handler.admin;

import java.util.Collection;
import com.ogong.pms.dao.NoticeDao;
import com.ogong.pms.domain.AdminNotice;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;

public class AdminNoticeListHandler implements Command {

  NoticeDao noticeDao;

  public AdminNoticeListHandler(NoticeDao noticeDao) {
    this.noticeDao = noticeDao;
  }

  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 공지 목록");
    System.out.println();

    Collection<AdminNotice> adminNoticeList = noticeDao.findAll();

    for (AdminNotice noticeList : adminNoticeList) {
      if (noticeList.getAdminNotiFile() != null) {
        System.out.printf(" (%d)\n 제목 : %s\n 첨부파일명 : %s\n 등록일 : %s\n", 
            noticeList.getAdminNotiNo(), 
            noticeList.getAdminNotiTitle(),
            noticeList.getAdminNotiFile(),
            noticeList.getAdminNotiRegisteredDate());
        System.out.println();
      }
      else if (noticeList.getAdminNotiFile() == null) {
        System.out.printf(" (%d)\n 제목 : %s\n 등록일 : %s\n", 
            noticeList.getAdminNotiNo(), 
            noticeList.getAdminNotiTitle(),
            noticeList.getAdminNotiRegisteredDate());
        System.out.println();
      }
    }      
  }

}
