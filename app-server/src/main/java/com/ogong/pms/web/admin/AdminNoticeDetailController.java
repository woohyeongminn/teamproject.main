package com.ogong.pms.web.admin;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogong.pms.dao.NoticeDao;
import com.ogong.pms.domain.AdminNotice;

@WebServlet("/adminNotice/detail")
public class AdminNoticeDetailController extends HttpServlet {
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
      int adminnotiNo = Integer.parseInt(request.getParameter("no"));
      AdminNotice adminNotice = noticeDao.findByNoticeNo(adminnotiNo);

      if (adminNotice == null) {
        throw new Exception(" >> 해당 번호의 공지글이 없습니다.");
      }

      request.setAttribute("adminNotice", adminNotice);

      request.setAttribute("pageTitle", "🔔 공지게시글 상세");
      request.setAttribute("contentUrl", "/admin/NoticeDetail.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);

      //request.getRequestDispatcher("/admin/NoticeDetail.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}

