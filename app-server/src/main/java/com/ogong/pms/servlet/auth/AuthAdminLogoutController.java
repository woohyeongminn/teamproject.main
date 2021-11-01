package com.ogong.pms.servlet.auth;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogong.pms.domain.Admin;

@WebServlet("/admin/logout")
public class AuthAdminLogoutController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public static Admin loginAdmin;
  public static Admin getLoginAdmin() {
    return loginAdmin;
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    AuthAdminLogoutController.loginAdmin = null;

    request.getRequestDispatcher("/admin/AdminLoginForm.jsp").forward(request, response);
  }
}
