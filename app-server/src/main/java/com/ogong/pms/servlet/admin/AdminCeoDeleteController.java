package com.ogong.pms.servlet.admin;

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

@WebServlet("/adminceomember/delete")
public class AdminCeoDeleteController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CeoMemberDao ceoMemberDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    ceoMemberDao = (CeoMemberDao) 웹애플리케이션공용저장소.getAttribute("ceoMemberDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      int no = Integer.parseInt(request.getParameter("no"));
      CeoMember ceoMember = ceoMemberDao.findByNo(no);

      if (ceoMember == null) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      }

      ceoMember.setCeoName("Deleted Member("+ ceoMember.getCeoName() +")");
      ceoMember.setCeoNickname("Deleted Member("+ ceoMember.getCeoNickname() +")");
      ceoMember.setCeoBossName("Deleted Member("+ ceoMember.getCeoBossName() +")");
      ceoMember.setCeoEmail("Deleted Email");
      ceoMember.setCeoPassword("Deleted Password");
      ceoMember.setCeoPhoto("Deleted Photo");
      ceoMember.setCeoLicenseNo("Deleted LicenseNo");
      ceoMember.setCeoStatus(CeoMember.CEO);
      ceoMember.setActive(CeoMember.OUTUSER);

      ceoMemberDao.updateActive(ceoMember);
      sqlSession.commit();

      response.sendRedirect("list");

      //        AuthCeoMemberLoginHandler.loginCeoMember = null;
      //        AuthCeoMemberLoginHandler.accessLevel = Menu.LOGOUT;

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
