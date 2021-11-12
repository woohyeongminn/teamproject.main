package com.ogong.pms.web.admin;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogong.pms.dao.NoticeDao;
import com.ogong.pms.domain.AdminNotice;

@WebServlet("/adminNotice/list")
public class AdminNoticeListController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  NoticeDao noticeDao;
  //AdminDao adminDao;

  @Override
  public void init() throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = getServletContext();
    noticeDao = (NoticeDao) 웹애플리케이션공용저장소.getAttribute("noticeDao");
    //adminDao = (AdminDao) 웹애플리케이션공용저장소.getAttribute("adminDao");
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      //      Admin loginAdmin = (Admin) request.getAttribute("loginAdmin");
      //      Admin admin = adminDao.findByAdminNo(loginAdmin.getMasterNo());

      Collection<AdminNotice> adminNoticeList = noticeDao.findAll();

      //request.getRequestDispatcher("/admin/NoticeList.jsp").forward(request, response);

      request.setAttribute("adminNoticeList", adminNoticeList);
      request.setAttribute("pageTitle", "🔔 공지게시글 목록");
      request.setAttribute("contentUrl", "/admin/NoticeList.jsp");

      request.getRequestDispatcher("/template1.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}