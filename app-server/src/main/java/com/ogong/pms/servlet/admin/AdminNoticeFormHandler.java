package com.ogong.pms.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/adminNotice/form")
public class AdminNoticeFormHandler extends GenericServlet {
  private static final long serialVersionUID = 1L;

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
    out.println("<h1> ▶ 새 공지 </h1>");
    out.println("<hr>");

    out.println("<form action='add'>");
    out.println("<fieldset>");
    out.println("<legend><b> 🔔 공지게시글 등록 </b></legend>");
    out.println("<table>");
    out.println("<tr>");
    out.println("<td><label for='title'>제목</label></td>");
    out.println("<td><input id='f-title' type='text' name='title'></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td><label for='content'>내용</label></td>");
    out.println("<td><input id='f-content' type='text' name='content'></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td><label for='filepath'>파일</label></td>");
    out.println("<td><input id='f-filepath' type='file' name='filepath'></td>");
    out.println("</tr>");
    out.println("</table>");
    out.println("<input type='submit'/>");
    out.println("</fieldset>");

    //    out.println("<label for='f-title'>제목</label> <input id='f-title' type='text' name='title'><br>");
    //    out.println("<label for='f-content'>내용</label> <input id='f-content' type='text' name='content'><br>");
    //    out.println("<label for='f-filepath'>파일</label> <input id='f-filepath' type='text' name='filepath'><br>");
    //    out.println("<button>등록</button><br>");
    out.println("</form>");


    out.println("</body>");
    out.println("</html>");
  }
}

