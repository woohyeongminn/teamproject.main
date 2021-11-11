package com.ogong.pms.servlet.admin;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogong.pms.dao.AdminDao;

@WebServlet("/admin/detail")
public class AdminDetailController extends HttpServlet {
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
      //      int no = Integer.parseInt(request.getParameter("no"));
      //      Admin adminpro = adminDao.findByAdminNo(no);
      //
      //      if (adminpro == null) {
      //        throw new Exception(" >> 다시 선택해 주세요.");
      //      }
      //
      //      request.setAttribute("adminpro", adminpro);

      //request.getRequestDispatcher("/admin/AdminDetail.jsp").forward(request, response);

      request.setAttribute("pageTitle", "🙂 마이페이지");
      request.setAttribute("contentUrl", "/admin/AdminDetail.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
