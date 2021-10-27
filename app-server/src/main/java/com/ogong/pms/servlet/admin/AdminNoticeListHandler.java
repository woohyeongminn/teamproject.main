package com.ogong.pms.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import com.ogong.pms.dao.NoticeDao;
import com.ogong.pms.domain.AdminNotice;

@WebServlet("/adminNotice/list")
public class AdminNoticeListHandler extends GenericServlet {
  private static final long serialVersionUID = 1L;

  NoticeDao noticeDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    noticeDao = (NoticeDao) 웹애플리케이션공용저장소.getAttribute("noticeDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("   <title>공지게시판</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1> ▶ 공지 목록 </h1>");
    //    out.println("<a href='form'>새회원</a><br>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr>");
    out.println("    <th>번호</th>");
    out.println("    <th>내용</th>");
    out.println("    <th>파일</th>");
    out.println("    <th>등록일</th>");
    out.println("  <tr>");
    out.println("</thread>");
    out.println("<tbody>");

    try {
      Collection<AdminNotice> adminNoticeList = noticeDao.findAll();

      for (AdminNotice noticeList : adminNoticeList) {
        if (noticeList.getAdminNotiFile() != null) {
          out.printf("<tr>"
              + " <td>(%d)</td>"
              + " <td><a href='detail?no=%1$d'>%s</a></td>"
              + " <td>%s</td>"
              + " <td>%s</td>"
              + "</tr>", 
              noticeList.getAdminNotiNo(), 
              noticeList.getAdminNotiTitle(),
              noticeList.getAdminNotiFile(),
              noticeList.getAdminNotiRegisteredDate());
        }
        else if (noticeList.getAdminNotiFile() == null) {
          out.printf("<tr>"
              + " <td>(%d)</td>"
              + " <td><a href='detail?no=%1$d'>%s</a></td>"
              + " <td>%s</td>"
              + "</tr>", 
              noticeList.getAdminNotiNo(), 
              noticeList.getAdminNotiTitle(),
              noticeList.getAdminNotiRegisteredDate());
        }
      } 
    } catch (Exception e) {
      throw new ServletException(e); 
    }
    out.println("</tbody>");
    out.println("</table>");
    out.println("</body>");
    out.println("</html>");
  }

}