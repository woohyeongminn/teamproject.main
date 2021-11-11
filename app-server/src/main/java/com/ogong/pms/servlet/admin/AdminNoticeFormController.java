package com.ogong.pms.servlet.admin;

import java.io.IOException;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/adminNotice/form")
public class AdminNoticeFormController extends GenericServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    //request.getRequestDispatcher("/admin/NoticeAddForm.jsp").forward(request, response);
    try {

      request.setAttribute("pageTitle", "🔔 공지게시글 등록");
      request.setAttribute("contentUrl", "/admin/NoticeAddForm.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}

