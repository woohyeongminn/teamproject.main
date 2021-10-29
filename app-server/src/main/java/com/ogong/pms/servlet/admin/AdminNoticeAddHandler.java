package com.ogong.pms.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
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
public class AdminNoticeAddHandler extends HttpServlet {
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

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head>");
    out.println("  <title>공지게시판</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>▶ 공지 등록 </h1>");
    out.println("<hr>");

    AdminNotice adminNotice = new AdminNotice();

    adminNotice.setAdminNotiTitle(request.getParameter("title"));
    adminNotice.setAdminNotiContent(request.getParameter("content"));
    adminNotice.setAdminNotiFile(request.getParameter("filepath"));
    adminNotice.setAdminNotiRegisteredDate(new Date(System.currentTimeMillis()));

    try {
      noticeDao.insert(adminNotice);
      noticeDao.insertFilepath(adminNotice);
      sqlSession.commit();

      out.println(" >> 공지글 등록이 완료되었습니다.<br>");
      out.println("<button><a href='list'>목록</a></button>");

    } catch (Exception e) {
      throw new ServletException(e);
    }

    out.println("</body>");
    out.println("</html>");
  }
}
