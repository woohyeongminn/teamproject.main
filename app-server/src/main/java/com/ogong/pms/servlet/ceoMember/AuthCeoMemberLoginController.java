package com.ogong.pms.servlet.ceoMember;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.domain.CeoMember;

@WebServlet("/ceomember/login")
public class AuthCeoMemberLoginController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  CeoMemberDao ceoMemberDao;

  //  public static CeoMember loginCeoMember;
  //  public static CeoMember getLoginCeoMember() {
  //    return loginCeoMember;
  //  }

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    ceoMemberDao = (CeoMemberDao) 웹애플리케이션공용저장소.getAttribute("ceoMemberDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      String email = request.getParameter("email");
      String password = request.getParameter("password");

      CeoMember ceoMember = ceoMemberDao.findByEmailAndPassword(email, password);

      if (ceoMember == null) {
        throw new Exception("이메일과 암호가 일치하는 회원을 찾을 수 없습니다.");
      } 

      if (ceoMember != null && ceoMember.getCeoStatus() == CeoMember.CEO) {

        if (ceoMember.getActive() == CeoMember.OUTUSER) {
          throw new Exception ("회원가입을 진행해 주세요.");
        }

        //request.setAttribute("ceoMember", ceoMember);
        request.getSession().setAttribute("loginCeoUser", ceoMember);

        request.getRequestDispatcher("/ceoMember/CeoMemberLogin.jsp").forward(request, response);

      } 
    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}