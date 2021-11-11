package com.ogong.pms.servlet.admin;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogong.pms.dao.NoticeDao;
import com.ogong.pms.domain.AdminNotice;

@WebServlet("/adminNotice/Updateform")
public class AdminNoticeUpdateFormController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  NoticeDao noticeDao;

  @Override
  public void init() throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = getServletContext();
    noticeDao = (NoticeDao) 웹애플리케이션공용저장소.getAttribute("noticeDao");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int noticeNo = Integer.parseInt(request.getParameter("no"));
      AdminNotice notice = noticeDao.findByNoticeNo(noticeNo);

      request.setAttribute("notice", notice);

      request.setAttribute("pageTitle", "🔔 공지게시글 변경");
      request.setAttribute("contentUrl", "/admin/NoticeUpdateform.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);

      //request.getRequestDispatcher("/admin/NoticeUpdateform.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}

