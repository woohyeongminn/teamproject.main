package com.ogong.pms.servlet.auth;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogong.pms.dao.AdminDao;
import com.ogong.pms.domain.Admin;

@WebServlet("/admin/login")
public class AuthAdminLoginController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  AdminDao adminDao;

  //  public static Admin loginAdmin;
  //  public static Admin getLoginAdmin() {
  //    return loginAdmin;
  //  }

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    adminDao = (AdminDao) 웹애플리케이션공용저장소.getAttribute("adminDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      String email = request.getParameter("email");
      String password = request.getParameter("password");

      Admin admin = adminDao.findByEmailAndPassword(email, password);

      if (admin != null) {

        //        request.setAttribute("admin", admin);
        request.getSession().setAttribute("loginAdmin", admin);
        request.getRequestDispatcher("/admin/AdminLogin.jsp").forward(request, response);
      }

      else {
        throw new Exception(" >> 이메일과 암호가 일치하는 관리자를 찾을 수 없습니다.");
      }

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
