package com.ogong.pms.servlet.admin;

import java.io.IOException;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import com.ogong.pms.dao.AdminDao;

@WebServlet("/admin/updateForm")
public class AdminUpdateFormController extends GenericServlet {
  private static final long serialVersionUID = 1L;

  AdminDao adminDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    adminDao = (AdminDao) 웹애플리케이션공용저장소.getAttribute("adminDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      //      int adminNo = Integer.parseInt(request.getParameter("no"));
      //      Admin admin = adminDao.findByAdminNo(adminNo);
      //
      //      request.setAttribute("admin", admin);

      request.getRequestDispatcher("/admin/AdminUpdateForm.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
