package com.ogong.pms.servlet.admin;

import java.io.IOException;
import java.sql.Date;
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

@WebServlet("/adminNotice/add")
public class AdminNoticeAddController extends HttpServlet {
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

    AdminNotice adminNotice = new AdminNotice();

    adminNotice.setAdminNotiTitle(request.getParameter("title"));
    adminNotice.setAdminNotiContent(request.getParameter("content"));
    adminNotice.setAdminNotiFile(request.getParameter("filepath"));
    adminNotice.setAdminNotiRegisteredDate(new Date(System.currentTimeMillis()));

    try {
      noticeDao.insert(adminNotice);
      noticeDao.insertFilepath(adminNotice);
      sqlSession.commit();
      response.setHeader("Refresh", "10;url=list"); 

      request.getRequestDispatcher("NoticeAdd.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
