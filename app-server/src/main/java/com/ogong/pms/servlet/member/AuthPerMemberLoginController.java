package com.ogong.pms.servlet.member;

import java.io.IOException;
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
public class AuthPerMemberLoginController extends HttpServlet {

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

    try {
      String email = request.getParameter("email");
      String password = request.getParameter("password");

      Member member = memberDao.findByEmailAndPassword(email, password);

      if (member == null) {
        throw new Exception("이메일과 암호가 일치하는 회원을 찾을 수 없습니다.");
      }

      if (member != null && member.getPerStatus() == Member.PER) {
        if (member.getActive() == Member.OUTUSER) {
          throw new Exception ("<p>회원가입을 진행해 주세요.</p>");
        }
      }

      //      request.setAttribute("perMember", member);
      // 로그인 성공한다면, 로그인 사용자 정보를 세션 객체에 보관한다.
      request.getSession().setAttribute("loginUser", member);
      //      request.getRequestDispatcher("memberSession.jsp").forward(request, response);
      request.getRequestDispatcher("/member/PerMemberLogin.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);

    }

  } 
}
