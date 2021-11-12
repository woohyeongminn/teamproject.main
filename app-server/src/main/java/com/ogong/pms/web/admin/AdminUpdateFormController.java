package com.ogong.pms.web.admin;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogong.pms.dao.AdminDao;

@WebServlet("/admin/updateForm")
public class AdminUpdateFormController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  AdminDao adminDao;

  @Override
  public void init() throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = getServletContext();
    adminDao = (AdminDao) 웹애플리케이션공용저장소.getAttribute("adminDao");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      //      int adminNo = Integer.parseInt(request.getParameter("no"));
      //      Admin admin = adminDao.findByAdminNo(adminNo);
      //
      //      request.setAttribute("admin", admin);

      //request.getRequestDispatcher("/admin/AdminUpdateForm.jsp").forward(request, response);

      request.setAttribute("pageTitle", "🙂 마이페이지");
      request.setAttribute("contentUrl", "/admin/AdminUpdateForm.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
