package com.ogong.pms.servlet.admin;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.NoticeDao;
import com.ogong.pms.domain.AdminNotice;

@WebServlet("/adminNotice/update")
public class AdminNoticeUpdateController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  NoticeDao noticeDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    noticeDao = (NoticeDao) 웹애플리케이션공용저장소.getAttribute("noticeDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int noticeNo = Integer.parseInt(request.getParameter("no"));
      AdminNotice notice = noticeDao.findByNoticeNo(noticeNo);

      if (notice == null) {
        throw new Exception(" >> 해당 번호의 공지글을 찾을 수 없습니다.");
      } 

      notice.setAdminNotiTitle(request.getParameter("title"));
      notice.setAdminNotiContent(request.getParameter("content"));
      notice.setAdminNotiFile(request.getParameter("filepath"));

      noticeDao.updateTitle(notice);
      noticeDao.updateContent(notice);
      noticeDao.deletenoticefile(noticeNo);
      noticeDao.insertFilepath(notice);
      sqlSession.commit();

      response.sendRedirect("list");

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}