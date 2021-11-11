package com.ogong.pms.servlet.admin;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import com.ogong.pms.dao.AdminDao;
import com.ogong.pms.dao.NoticeDao;
import com.ogong.pms.domain.AdminNotice;

@WebServlet("/adminNotice/list")
public class AdminNoticeListController extends GenericServlet {
  private static final long serialVersionUID = 1L;

  NoticeDao noticeDao;
  AdminDao adminDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    noticeDao = (NoticeDao) 웹애플리케이션공용저장소.getAttribute("noticeDao");
    adminDao = (AdminDao) 웹애플리케이션공용저장소.getAttribute("adminDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      //      Admin loginAdmin = (Admin) request.getAttribute("loginAdmin");
      //      Admin admin = adminDao.findByAdminNo(loginAdmin.getMasterNo());

      Collection<AdminNotice> adminNoticeList = noticeDao.findAll();

      request.setAttribute("adminNoticeList", adminNoticeList);

      //request.getRequestDispatcher("/admin/NoticeList.jsp").forward(request, response);

      request.setAttribute("pageTitle", "🔔 공지게시글 목록");
      request.setAttribute("contentUrl", "/admin/NoticeList.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);

    } catch (Exception e) {
      // 오류를 출력할 때 사용할 수 있도록 예외 객체를 저장소에 보관한다.
      request.setAttribute("error", e);

      // 오류가 발생하면 오류 내용을 출력할 뷰를 호출한다.
      RequestDispatcher 요청배달자 = request.getRequestDispatcher("/Error.jsp");
      요청배달자.forward(request, response);
    }
  }
}