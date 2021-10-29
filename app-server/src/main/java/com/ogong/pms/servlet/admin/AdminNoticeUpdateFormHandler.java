package com.ogong.pms.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import com.ogong.pms.dao.NoticeDao;
import com.ogong.pms.domain.AdminNotice;

@WebServlet("/adminNotice/Updateform")
public class AdminNoticeUpdateFormHandler extends GenericServlet {
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
    out.println("<title>공지게시판</title>");
    out.println("<style>");
    out.println("label {");
    out.println("margin-right: 5px;");
    out.println("text-align: center;");
    out.println("display: inline;");
    out.println("width: 60px;");
    out.println("}");
    out.println("</style>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1> ▶ 공지 변경 </h1>");
    out.println("<hr>");
    out.println("<form action='update'>");
    out.println("<fieldset>");
    out.println("<legend><b> 🔔 공지게시글 수정 </b></legend>");
    out.println("<table>");

    int noticeNo = Integer.parseInt(request.getParameter("no"));
    try {

      AdminNotice notice = noticeDao.findByNoticeNo(noticeNo);

      out.println("<tr>");
      out.printf("<td><label for='f-no'>( %d )</label><input id='f-no' type='hidden' name='no' value='%1$d'></td>", notice.getAdminNotiNo());
      out.println("</tr>");
      out.println("<tr>");
      out.printf("<td><label for='f-title'>제목</label> ㅣ <input id='f-title' type='text' name='title' value='%s'></td>", notice.getAdminNotiTitle());
      out.println("</tr>");
      out.println("<tr>");
      out.printf("<td><label for='f-content'>내용</label> ㅣ  <input id='f-content' type='text' name='content' value='%s'></td>", notice.getAdminNotiContent());
      out.println("</tr>");
      out.println("<tr>");
      if (!notice.getAdminNotiFile().equals("")) {
        out.printf("<td><label for='filepath'>파일</label> ㅣ  <input id='f-filepath' type='image' name='filepath' value='%s'></td>", notice.getAdminNotiFile());
        out.printf("<td><input id='f-filepath' type='file' name='filepath' value='%s'></td>", notice.getAdminNotiFile());
      }
      else {
        out.printf("<td><label for='filepath'>파일</label> ㅣ  <input id='f-filepath' type='file' name='filepath' value='%s'></td>", notice.getAdminNotiFile());
      }
      out.println("</tr>");
      out.println("</table>");
      out.println("<input type='submit'/>");
      out.println("</fieldset>");
      out.println("</form>");

    } catch (Exception e) {
      throw new ServletException(e);
    }

    out.println("</body>");
    out.println("</html>");
  }
}

