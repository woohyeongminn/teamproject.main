package com.ogong.pms.handler.admin;

import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.NoticeDao;
import com.ogong.pms.domain.AdminNotice;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class AdminNoticeDeleteHandler implements Command {

  NoticeDao noticeDao;
  SqlSession sqlSession;

  public AdminNoticeDeleteHandler(NoticeDao noticeDao, SqlSession sqlSession) {
    this.noticeDao = noticeDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 공지 삭제");
    int noticeNo = (int) request.getAttribute("noticeNo");

    AdminNotice notice = noticeDao.findByNoticeNo(noticeNo);

    String inputnotice = Prompt.inputString("\n 정말 삭제하시겠습니까? (네 / 아니오) ");
    if (!inputnotice.equalsIgnoreCase("네")) {
      System.out.println(" >> 삭제가 취소되었습니다.");
      request.getRequestDispatcher("/adminNotice/list").forward(request);
      return;
    }

    try {
      noticeDao.deletenoticefile(noticeNo);
      noticeDao.delete(noticeNo);
      sqlSession.commit();
    } catch (Exception e) {
      System.out.println("공지 삭제 실패!");
      sqlSession.rollback();
    }

    System.out.println(" >> 공지가 삭제되었습니다.");
    request.getRequestDispatcher("/adminNotice/list").forward(request);
  }

}
