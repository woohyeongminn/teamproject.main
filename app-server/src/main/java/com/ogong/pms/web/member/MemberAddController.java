package com.ogong.pms.web.member;

import java.io.IOException;
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
public class MemberAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  MemberDao memberDao;
  SqlSession sqlSession;

  @Override
  public void init() throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
  }

  // 개인
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      Member member = new Member();

      member.setPerName(request.getParameter("name"));
      member.setPerNickname(request.getParameter("nickname"));
      member.setPerEmail(request.getParameter("email"));
      member.setPerPassword(request.getParameter("password"));
      member.setPerPhoto(request.getParameter("photo"));
      member.setPerTel(request.getParameter("tel"));
      member.setPerStatus(Member.PER);

      memberDao.insert(member);
      sqlSession.commit();

      response.setHeader("Refresh", "2;url=form");
      request.setAttribute("pageTitle", " 👋환영 합니다!");
      request.setAttribute("contentUrl", "/member/PerMemberAdd.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      sqlSession.rollback();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}


