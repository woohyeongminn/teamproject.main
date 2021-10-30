package com.ogong.pms.servlet.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.domain.Member;

@WebServlet("/member/update/form")
public class MemberUpdateFromHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;

  MemberDao memberDao;
  SqlSession sqlSession;

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
      request.getRequestDispatcher("/auth/PerLoginForm.jsp").forward(request, response);
    }
  }
