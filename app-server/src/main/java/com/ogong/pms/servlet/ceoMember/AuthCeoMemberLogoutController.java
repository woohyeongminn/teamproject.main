package com.ogong.pms.servlet.ceoMember;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogong.pms.domain.CeoMember;

@WebServlet("/ceomember/logout")
public class AuthCeoMemberLogoutController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public static CeoMember loginCeoMember;
  public static CeoMember getLoginCeoMember() {
    return loginCeoMember;
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {


    AuthCeoMemberLogoutController.loginCeoMember = null;
    request.getSession().invalidate();

    response.sendRedirect("../index.jsp");

  }


}
