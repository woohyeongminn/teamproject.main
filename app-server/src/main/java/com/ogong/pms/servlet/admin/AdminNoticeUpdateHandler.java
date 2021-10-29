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

@WebServlet("/adminNotice/update")
public class AdminNoticeUpdateHandler extends HttpServlet {
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

    int noticeNo = Integer.parseInt(request.getParameter("no"));

    try {

      AdminNotice notice = noticeDao.findByNoticeNo(noticeNo);

      if (notice == null) {
        throw new Exception(" >> 해당 번호의 공지글을 찾을 수 없습니다.");
      } else {

        notice.setAdminNotiTitle(request.getParameter("title"));

        notice.setAdminNotiContent(request.getParameter("content"));

        notice.setAdminNotiFile(request.getParameter("filepath"));

        noticeDao.updateTitle(notice);
        noticeDao.updateContent(notice);
        noticeDao.deletenoticefile(noticeNo);
        noticeDao.insertFilepath(notice);
        sqlSession.commit();
      }
      response.sendRedirect("list");

      //      out.println(" >> 공지가 변경되었습니다.<br>");
      //      out.println("<button><a href='list'>목록</a></button>");

    } catch (Exception e) {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("   <title>공지게시판</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("  <style>");
      out.println("  label {");
      out.println("    margin-right: 5px;");
      out.println("    text-align: center;");
      out.println("    display: inline;");
      out.println("    width: 60px;");
      out.println("  }");
      out.println("  </style>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1> ▶ 공지 변경 오류 </h1>");
      out.println("<hr>");
      out.println("<fieldset>");
      out.println("<legend><b> 🔔 공지게시글 수정 오류 </b></legend>");
      out.println("<table>");
      out.println("</table>");
      out.println("</fieldset>");
      out.println("</form>");
      out.println("</body>");
      out.println("</html>");
      e.printStackTrace();
    }
  }
}