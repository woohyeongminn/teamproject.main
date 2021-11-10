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

  MemberDao memberDao;
  SqlSession sqlSession;

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
      Member perMember = memberDao.findByNo(no);

      if (perMember == null) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      }

      perMember.setPerNickname(request.getParameter("nickname"));
      perMember.setPerName(request.getParameter("name"));
      perMember.setPerEmail(request.getParameter("email"));
      perMember.setPerPassword(request.getParameter("confirmPassword"));
      perMember.setPerPhoto(request.getParameter("photo"));
      perMember.setPerTel(request.getParameter("tel"));

      memberDao.updateName(perMember);
      memberDao.updateNickname(perMember);
      memberDao.updateEmail(perMember);
      memberDao.updatePassword(perMember);
      memberDao.updatePhoto(perMember);
      memberDao.updateTel(perMember);
      sqlSession.commit();

      response.sendRedirect("detail?no="+ perMember.getPerNo());

    }catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}


