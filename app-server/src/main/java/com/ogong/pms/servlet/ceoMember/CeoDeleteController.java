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

@WebServlet("/ceomember/delete")
public class CeoDeleteController extends HttpServlet {
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
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      CeoMember loginCeo = (CeoMember) request.getSession().getAttribute("loginCeoUser");
      CeoMember ceoMember = ceoMemberDao.findByNo(loginCeo.getCeoNo());

      if (ceoMember == null) {
        throw new Exception("로그인 하세요.");
      }

      String email = request.getParameter("email");
      String password = request.getParameter("password");

      if (!ceoMember.getCeoEmail().equals(email)) {
        //        System.out.println();
        //        System.out.println(" >> 이메일이 일치하지 않습니다.");
        //        return;
      } 

      ceoMember.setCeoName("Deleted Member("+ ceoMember.getCeoName() +")");
      ceoMember.setCeoNickname("Deleted Member("+ ceoMember.getCeoNickname() +")");
      ceoMember.setCeoBossName("Deleted Member("+ ceoMember.getCeoBossName() +")");
      ceoMember.setCeoEmail("Deleted Email");
      ceoMember.setCeoPassword("Deleted Password");
      ceoMember.setCeoPhoto("Deleted Photo");
      ceoMember.setCeoLicenseNo("Deleted LicenseNo");
      ceoMember.setCeoTel("Deleted Tel");
      ceoMember.setCeoStatus(CeoMember.CEO);
      ceoMember.setActive(CeoMember.OUTUSER);

      ceoMemberDao.updateActive(ceoMember);
      sqlSession.commit();

      //        AuthCeoMemberLoginHandler.loginCeoMember = null;
      //        AuthCeoMemberLoginHandler.accessLevel = Menu.LOGOUT;
      response.sendRedirect("logout");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
