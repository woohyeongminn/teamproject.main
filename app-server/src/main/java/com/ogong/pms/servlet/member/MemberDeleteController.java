package com.ogong.pms.servlet.member;

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

@WebServlet("/member/delete")
public class MemberDeleteController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  MemberDao memberDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }
  // 개인
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    //    String email = request.getParameter("email");
    //    String password = request.getParameter("password");

    try {
      //Member perMember = memberDao.findByEmailAndPassword(email, password);
      int no = Integer.parseInt(request.getParameter("no"));
      Member perMember = memberDao.findByNo(no);

      if (perMember == null) {
        throw new Exception("정확히 입력해주세요.");
      }

      perMember.setPerName("Deleted Name");
      perMember.setPerNickname("Deleted Member("+ perMember.getPerNickname() +")");
      perMember.setPerEmail("Deleted Email");
      perMember.setPerPassword("Deleted Password");
      perMember.setPerPhoto("Deleted Photo");
      perMember.setPerStatus(Member.PER);
      perMember.setActive(Member.OUTUSER);
      out.println("test");
      memberDao.updateActive(perMember);
      out.println("test");
      sqlSession.commit();

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
