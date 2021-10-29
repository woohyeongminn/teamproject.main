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
    out.println("<h1> ▶ 공지 목록 </h1>");
    out.println("<hr>");
    //    out.println("<table border='1'>");
    //    out.println("<thead>");
    //    out.println("  <tr>");
    //    out.println("    <th>번호</th>");
    //    out.println("    <th>내용</th>");
    //    out.println("    <th>파일</th>");
    //    out.println("    <th>등록일</th>");
    //    out.println("  <tr>");
    //    out.println("</thread>");
    //    out.println("<tbody>");
    out.println("<fieldset>");
    out.println("<legend><b> 🔔 공지게시글 목록 </b></legend>");
    out.println("<table>");

    try {
      Collection<AdminNotice> adminNoticeList = noticeDao.findAll();

      for (AdminNotice noticeList : adminNoticeList) {

        if (noticeList.getAdminNotiFile() != null) {
          out.println("<tr>");
          out.printf("<td><a href='detail?no=%1$d'>( %d )</a></td>", noticeList.getAdminNotiNo());
          out.println("<tr>");
          out.printf("<td><label for='title'>제목</label>  ㅣ  %s</td>", noticeList.getAdminNotiTitle());
          out.println("</tr>");
          out.println("<tr>");
          out.printf("<td><label for='content'>내용</label>  ㅣ  %s</td>", noticeList.getAdminNotiContent());
          out.println("</tr>");
          out.println("<tr>");
          out.printf("<td><label for='filepath'>파일</label>  ㅣ  %s</td>", noticeList.getAdminNotiFile());
          out.println("</tr>");
          out.println("<tr>");
          out.printf("<td><label for='registeredDate'>등록일</label>ㅣ  %s</td>", noticeList.getAdminNotiRegisteredDate());
          out.println("</tr>");
        }
        else if (noticeList.getAdminNotiFile() == null) {
          out.println("<tr>");
          out.printf("<td><a href='detail?no=%1$d'>( %d )</a></td>", noticeList.getAdminNotiNo());
          out.println("<tr>");
          out.printf("<td><label for='title'>제목</label>  ㅣ  %s</td>", noticeList.getAdminNotiTitle());
          out.println("</tr>");
          out.println("<tr>");
          out.printf("<td><label for='content'>내용</label>  ㅣ  %s</td>", noticeList.getAdminNotiContent());
          out.println("</tr>");
          out.println("<tr>");
          out.printf("<td><label for='registeredDate'>등록일</label>ㅣ  %s</td>", noticeList.getAdminNotiRegisteredDate());
          out.println("</tr>");
        }


        //          if (noticeList.getAdminNotiFile() != null) {
        //          out.printf("<tr>"
        //              + " <td>(%d)</td>"
        //              + " <td><a href='detail?no=%1$d'>%s</a></td>"
        //              + " <td>%s</td>"
        //              + " <td>%s</td>"
        //              + "</tr>", 
        //              noticeList.getAdminNotiNo(), 
        //              noticeList.getAdminNotiTitle(),
        //              noticeList.getAdminNotiFile(),
        //              noticeList.getAdminNotiRegisteredDate());
        //        }
        //        else if (noticeList.getAdminNotiFile() == null) {
        //          out.printf("<tr>"
        //              + " <td>(%d)</td>"
        //              + " <td><a href='detail?no=%1$d'>%s</a></td>"
        //              + " <td>%s</td>"
        //              + "</tr>", 
        //              noticeList.getAdminNotiNo(), 
        //              noticeList.getAdminNotiTitle(),
        //              noticeList.getAdminNotiRegisteredDate());
        //        }
      } 
      out.println("</table>");
      out.println("</fieldset>");
    } catch (Exception e) {
      throw new ServletException(e); 
    }
    out.println("<button><a href='form'> 공지 등록 </a></button>");
    //    out.println("</tbody>");
    //    out.println("</table>");
    out.println("</body>");
    out.println("</html>");
  }

}