package com.ogong.pms.servlet.auth;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogong.pms.dao.AdminDao;
import com.ogong.pms.domain.Admin;

@WebServlet("/admin/login")
public class AuthAdminLoginHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;

  AdminDao adminDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    adminDao = (AdminDao) 웹애플리케이션공용저장소.getAttribute("adminDao");
  }

  // ----------------------------------------------------------------------

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("  <title>관리자 로그인</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1> 🖐 로그인 </h1>");

    String email = request.getParameter("email");
    String password = request.getParameter("password");

    try {

      Admin admin = adminDao.findByEmailAndPassword(email, password);
      if (admin != null) {
        out.printf("<p>'%s'님 환영합니다! 🖐</p><br>", admin.getMasterNickname());
        //        out.printf("<button>");
        //        out.printf("<a href='detail?no=%1$d'>", admin.getMasterNo());
        //        out.printf("<p> 마이페이지 </p>");
        //        out.printf("</a>");
        //        out.printf("</button>");
      } 
      else {
        out.println("<p>이메일과 암호가 일치하는 회원을 찾을 수 없습니다.</p>");
      }
      //      } else {
      //        out.println("<p> >> 이메일과 암호가 일치하는 관리자를 찾을 수 없습니다.</p><br>");
      //        out.printf("<button>");
      //        out.printf("<a href='form'>");
      //        out.printf("<p> 👈 이전 메뉴 </p>");
      //        out.printf("</a>");
      //        out.printf("</button>");
      //      }
    } catch (Exception e) {
      throw new ServletException(e);
    }
    out.println("</body>");
    out.println("</html>");
  }
}
