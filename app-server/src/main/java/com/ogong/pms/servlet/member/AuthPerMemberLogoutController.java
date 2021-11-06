package com.ogong.pms.servlet.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogong.pms.domain.Member;

@WebServlet("/member/logout")
public class AuthPerMemberLogoutController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public static Member loginperMember;
  public static Member getLoginPerMember() {
    return loginperMember;
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {


    AuthPerMemberLogoutController.loginperMember = null;
    request.getSession().invalidate();

    response.sendRedirect("../index.jsp");
    //request.getRequestDispatcher("/member/PerMemberLoginForm.jsp").forward(request, response);

    //AuthCeoMemberLogoutController.accessLevel = Menu.LOGOUT;
  }


}
