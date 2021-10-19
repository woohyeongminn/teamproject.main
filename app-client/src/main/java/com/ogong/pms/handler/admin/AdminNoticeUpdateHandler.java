package com.ogong.pms.handler.admin;

import com.ogong.pms.dao.AdminDao;
import com.ogong.pms.domain.AdminNotice;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class AdminNoticeUpdateHandler implements Command {

  AdminDao adminDao;

  public AdminNoticeUpdateHandler(AdminDao adminDao) {
    this.adminDao = adminDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 공지 변경");
    System.out.println();
    int noticeNo = (int) request.getAttribute("noticeNo");

    AdminNotice notice = adminDao.findByNoticeNo(noticeNo);

    System.out.println("1. 제목");
    System.out.println("2. 내용");
    System.out.println("3. 파일추가");
    System.out.println("4. 파일변경");
    System.out.println();
    int selectNo = Prompt.inputInt(" 수정하고 싶은 정보를 선택해 주세요. > ");
    System.out.println();

    String adminNoticeTitle = notice.getAdminNotiTitle();
    String adminNoticeContent = notice.getAdminNotiContent();
    String adminNoticeFile = notice.getAdminNotiFile();

    switch (selectNo) {
      case 1: adminNoticeTitle = Prompt.inputString(" 제목(" + notice.getAdminNotiTitle()  + ") : ");
      break;
      case 2: adminNoticeContent = Prompt.inputString(" 내용(" + notice.getAdminNotiTitle()  + ") : ");
      break;
      case 3:
        String inputfile = Prompt.inputString(" 첨부파일을 등록하시겠습니까? (네 / 아니오) ");
        if (inputfile.equals("네")) {
          notice.setAdminNotiFile(Prompt.inputString(" 첨부파일 : "));
        } 
        //        else {
        //          System.out.println(" >> 파일 등록이 취소되었습니다.");
        //          request.getRequestDispatcher("/adminNotice/list").forward(request);
        //          return;
        //        }
        break;
      case 4:
        if (notice.getAdminNotiFile() == null) {
          System.out.println(" >> 등록된 첨부파일이 없습니다.");
          return;
        }
        adminNoticeFile = Prompt.inputString(" 파일(" + notice.getAdminNotiFile()  + ") : ");
        break;
      default : 
        System.out.println(" >> 올바른 번호를 입력해 주세요.");
        return;
    }

    System.out.println();
    String inputnotice = Prompt.inputString(" 정말 변경하시겠습니까? (네 / 아니오) ");
    if (!inputnotice.equalsIgnoreCase("네")) {
      System.out.println(" >> 변경이 취소되었습니다.");
      request.getRequestDispatcher("/adminNotice/list").forward(request);
      return;
    }

    if (selectNo == 1) {
      notice.setAdminNotiTitle(adminNoticeTitle);
      adminDao.updateTitle(notice);
    } else if (selectNo == 2) {
      notice.setAdminNotiContent(adminNoticeContent);
      adminDao.updateContent(notice);
    } else if (selectNo == 3) {
      adminDao.insertFilepath(notice);
    } else if (selectNo == 4) {
      notice.setAdminNotiFile(adminNoticeFile);
      adminDao.updateFilepath(notice);
    }

    System.out.println(" >> 공지가 변경되었습니다.");
    request.getRequestDispatcher("/adminNotice/list").forward(request);
    return;
  }

}
