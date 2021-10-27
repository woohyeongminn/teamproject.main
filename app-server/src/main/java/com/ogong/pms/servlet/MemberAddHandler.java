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
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.domain.Member;

@WebServlet("/member/add")
public class MemberAddHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;

  MemberDao memberDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
  }

  // 개인
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head>");
    out.println("   <title>▶회원가입</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>[회원 가입 결과]</h1>");

    Member member = new Member();

    member.setPerName(request.getParameter("name"));
    member.setPerEmail(request.getParameter("nickname"));
    member.setPerEmail(request.getParameter("email"));
    member.setPerPassword(request.getParameter("password"));
    member.setPerPhoto(request.getParameter("photo"));
    member.setPerTel(request.getParameter("tel"));
    member.setPerStatus(Member.PER);
    member.setActive(Member.INUSER);

    try {
      memberDao.insert(member);
      sqlSession.commit();

      out.println(" >> 회원가입이 완료되었습니다.<br>");
      out.println("<a href='detail'>[마이페이지]</a><br>");

    } catch (Exception e) {
      throw new ServletException(e);
    }
    out.println("</body>");
    out.println("</html>");

  }
}


