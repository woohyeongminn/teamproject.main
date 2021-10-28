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
import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.domain.CeoMember;

@WebServlet("/ceomember/login")
public class AuthCeoMemberLoginHandler extends HttpServlet {

  private static final long serialVersionUID = 1L;

  CeoMemberDao ceoMemberDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    ceoMemberDao = (CeoMemberDao) 웹애플리케이션공용저장소.getAttribute("ceoMemberDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("  <title>로그인 성공</title>");
    out.println("</head>");
    out.println("<body>");

    String email = request.getParameter("email");
    String password = request.getParameter("password");

    try {
      CeoMember ceoMember = ceoMemberDao.findByEmailAndPassword(email, password);

      if (ceoMember != null && ceoMember.getCeoStatus() == CeoMember.CEO) {

        if (ceoMember.getActive() == CeoMember.OUTUSER) {
          out.println();
          out.println("<p>회원가입을 진행해 주세요.</p>");
          return;
        }

        out.printf("<p>'%s'님 환영합니다! 🖐</p>", ceoMember.getCeoNickname());

      } else {
        out.println("<p>이메일과 암호가 일치하는 회원을 찾을 수 없습니다.</p>");
        return;
      }
    } catch (Exception e) {
      throw new ServletException(e);
    }
    out.println("</body>");
    out.println("</html>");
  }
}