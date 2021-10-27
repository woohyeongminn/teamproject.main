package com.ogong.pms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.domain.Member;

@WebServlet("/member/login")
public class AuthPerMemberLoginHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;

  MemberDao memberDao;

  public static Member loginUser;
  public static Member getLoginUser() {
    return loginUser;
  }

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head>");
    out.println("   <title>로그인</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>[로그인 결과]</h1>");

    String email = request.getParameter("email");
    String password = request.getParameter("password");

    try {
      Member member = memberDao.findByEmailAndPassword(email, password);

      if (member != null && member.getPerStatus() == Member.PER) {

        if (member.getActive() == Member.OUTUSER) {
          out.println("<p> >> 회원가입을 진행해 주세요.</p><br>");
          return;
        }

        out.println();
        out.printf("<p>'%s'님 환영합니다! 🖐</p><br>", member.getPerNickname());

      } else {
        out.println("<p> >> 이메일과 암호가 일치하는 회원을 찾을 수 없습니다.</p><br>");
      }
    } catch (Exception e) {
      throw new ServletException(e);
    }
    out.println("</body>");
    out.println("</html>");
  } 
}
