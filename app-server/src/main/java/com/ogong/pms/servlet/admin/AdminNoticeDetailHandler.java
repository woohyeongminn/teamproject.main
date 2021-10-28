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

@WebServlet("/adminNotice/detail")
public class AdminNoticeDetailHandler extends GenericServlet {
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
    out.println("<h1> ▶ 공지 상세 </h1>");
    out.println("<hr>");
    //    out.println("<table border='2'>");

    out.println("<fieldset>");
    out.println("<legend><b> 🔔 공지게시글 상세 </b></legend>");
    out.println("<table>");

    int adminnotiNo = Integer.parseInt(request.getParameter("no"));

    try {
      AdminNotice adminNotice = noticeDao.findByNoticeNo(adminnotiNo);
      out.println("<form action='update'>");
      if (adminNotice.getAdminNotiFile() != null) { 
        out.println("<tr>");
        out.printf("<td><a href='detail?no=%1$d'>( %d )</a></td>", adminNotice.getAdminNotiNo());
        out.println("<tr>");
        out.printf("<td><label for='title'>제목</label> ㅣ  %s</td>", adminNotice.getAdminNotiTitle());
        out.println("</tr>");
        out.println("<tr>");
        out.printf("<td><label for='filepath'>파일</label> ㅣ  %s</td>", adminNotice.getAdminNotiFile());
        out.println("</tr>");
        out.println("<tr>");
        out.printf("<td><label for='registeredDate'>등록일</label>ㅣ  %s</td>", adminNotice.getAdminNotiRegisteredDate());
        out.println("</tr>");
        //        out.printf("<tr><td><label for='f-title'>제목</label></td> <td> [ %s ] </td></tr>", adminNotice.getAdminNotiTitle());
        //        out.printf("<tr><td><label for='f-content'>내용</label></td> <td>%s</td></tr>", adminNotice.getAdminNotiContent());
        //        out.printf("<tr><td><label for='f-filepath'>파일</label></td> <td>%s</td></tr>", adminNotice.getAdminNotiFile());
        //        out.printf("<tr><td><label for='f-registeredDate'>등록일</label></td> <td><span id='f-registeredDate'>%s</span><td></tr>", adminNotice.getAdminNotiRegisteredDate());
      }
      else if (adminNotice.getAdminNotiFile() == null) {
        //        out.println("<form action='update'>");
        out.println("<tr>");
        out.printf("<td><a href='detail?no=%1$d'>( %d )</a></td>", adminNotice.getAdminNotiNo());
        out.println("<tr>");
        out.printf("<td><label for='title'>제목</label>  ㅣ  %s</td>", adminNotice.getAdminNotiTitle());
        out.println("</tr>");
        out.println("<tr>");
        out.printf("<td><label for='registeredDate'>등록일</label>ㅣ  %s</td>", adminNotice.getAdminNotiRegisteredDate());
        out.println("</tr>");
        //        out.printf("<tr><td><label for='f-title'>제목</label></td> <td> [ %s ] </td></tr>", adminNotice.getAdminNotiTitle());
        //        out.printf("<tr><td><label for='f-content'>내용</label></td> <td>%s</td></tr>", adminNotice.getAdminNotiContent());
        //        out.printf("<tr><td><label for='f-registeredDate'>등록일</label></td> <td><span id='f-registeredDate'>%s</span><td></tr>", adminNotice.getAdminNotiRegisteredDate());
      }
      out.println("</table>");
      out.println("</fieldset>");
      out.println("<button><a href='list'>목록</a></button>");
      out.printf("<button><a href='update?no=%d'>변경</a></button>", adminNotice.getAdminNotiNo());
      out.printf("<button><a href='delete?no=%d'>삭제</a></button>", adminNotice.getAdminNotiNo());
      out.println("</form>");

    } catch (Exception e) {
      throw new ServletException(e);
    }


    out.println("</body>");
    out.println("</html>");
  }
}

