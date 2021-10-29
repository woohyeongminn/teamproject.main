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
    out.println("</head>");
    out.println("<body>");
    out.println("<h1> ▶ 공지 변경 </h1>");
    out.println("<hr>");

    out.println("<fieldset>");
    out.println("<legend><b> 🔔 공지게시글 수정 </b></legend>");
    out.println("<table>");

    int noticeNo = Integer.parseInt(request.getParameter("no"));
    try {
      out.println("test.......");

      AdminNotice notice = noticeDao.findByNoticeNo(noticeNo);

      //      out.println("<form action='update'>");

      notice.setAdminNotiTitle(request.getParameter("title"));
      //      noticeDao.updateTitle(notice);
      //      sqlSession.commit();

      notice.setAdminNotiContent(request.getParameter("content"));
      //      noticeDao.updateContent(notice);
      //      sqlSession.commit();

      if (notice.getAdminNotiFile() != null) {
        out.println(" >> 이미 등록된 첨부파일이 있습니다.<br>");
        noticeDao.updateTitle(notice);
        noticeDao.updateContent(notice);
        sqlSession.commit();

        out.println(" >> 공지가 변경되었습니다.<br>");

        return;
      }
      //      else {
      notice.setAdminNotiFile(request.getParameter("filepath"));
      //        noticeDao.insertFilepath(notice);
      //        sqlSession.commit();
      //      }

      noticeDao.updateTitle(notice);
      noticeDao.updateContent(notice);
      noticeDao.deletenoticefile(noticeNo);
      noticeDao.insertFilepath(notice);
      sqlSession.commit();

      out.println(" >> 공지가 변경되었습니다.<br>");

      out.println("</table>");
      out.println("</fieldset>");
      out.println("<button><a href='list'>목록</a></button>");
      out.println("</form>");
    } catch (Exception e) {
      throw new ServletException(e);
    }

    out.println("</body>");
    out.println("</html>");
  }

}
