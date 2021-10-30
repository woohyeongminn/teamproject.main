package com.ogong.pms.servlet.member;

import java.io.IOException;
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

@WebServlet("/member/update")
public class MemberUpdateController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  MemberDao memberDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      Member member = memberDao.findByNo(no);

      member.setPerNickname("nickname");
      member.setPerName(request.getParameter("name"));
      member.setPerEmail(request.getParameter("email"));
      member.setPerPassword(request.getParameter("password"));
      member.setPerPhoto(request.getParameter("photo"));
      member.setPerTel(request.getParameter("tel"));

      memberDao.updateName(member);
      memberDao.updateNickname(member);
      memberDao.updateEmail(member);
      memberDao.updatePassword(member);
      memberDao.updatePhoto(member);
      memberDao.updateTel(member);
      sqlSession.commit();

      response.sendRedirect("list");

    }catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }

  @Override
  public String getServletInfo() {
    return null;
  }

  @Override
  public ServletConfig getServletConfig() {
    return null;
  }

}


