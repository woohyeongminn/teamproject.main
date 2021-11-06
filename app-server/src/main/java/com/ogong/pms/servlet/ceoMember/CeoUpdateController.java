package com.ogong.pms.servlet.ceoMember;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.domain.CeoMember;

@WebServlet("/ceomember/update")
public class CeoUpdateController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CeoMemberDao ceoMemberDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    ceoMemberDao = (CeoMemberDao) 웹애플리케이션공용저장소.getAttribute("ceoMemberDao");
  }

  // 기업회원 개인정보 수정은 이름,이메일,비밀번호만 가능
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      CeoMember loginCeo = (CeoMember) request.getSession().getAttribute("loginCeoUser");
      CeoMember ceoMember = ceoMemberDao.findByNo(loginCeo.getCeoNo());

      if (ceoMember == null) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      }

      ceoMember.setCeoName(request.getParameter("name"));
      ceoMember.setCeoNickname(request.getParameter("nickname"));
      ceoMember.setCeoPhoto(request.getParameter("photo"));
      ceoMember.setCeoTel(request.getParameter("tel"));
      ceoMember.setCeoEmail(request.getParameter("email"));
      ceoMember.setCeoPassword(request.getParameter("password"));

      ceoMemberDao.updateName(ceoMember);
      ceoMemberDao.updateNickName(ceoMember);
      ceoMemberDao.updatePhoto(ceoMember);
      ceoMemberDao.updateTel(ceoMember);
      ceoMemberDao.updateEmail(ceoMember);
      ceoMemberDao.updatePassword(ceoMember);

      sqlSession.commit();

      response.sendRedirect("detail");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}