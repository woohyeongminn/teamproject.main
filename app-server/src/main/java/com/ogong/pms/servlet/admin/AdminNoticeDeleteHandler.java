package com.ogong.pms.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/adminNotice/delete")
public class AdminNoticeDeleteHandler extends HttpServlet {
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
    out.println("<h1> ▶ 공지 삭제 </h1>");
    out.println("<hr>");

    try {
      int noticeNo = Integer.parseInt(request.getParameter("no"));

      AdminNotice notice = noticeDao.findByNoticeNo(noticeNo);

      if (notice == null) {
        out.println(" >> 해당 번호의 공지글이 없습니다.<br>");

      } else {

        noticeDao.deletenoticefile(noticeNo);
        noticeDao.delete(noticeNo);
        sqlSession.commit();

        out.println(" >> 공지가 삭제되었습니다.<br>");

      }
      out.println("<a href='list'>[목록]<a><br>");

    } catch (Exception e) {
      throw new ServletException(e);
    }

    out.println("</body>");
    out.println("</html>");
  }
}
