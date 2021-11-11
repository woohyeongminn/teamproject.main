package com.ogong.pms.servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminNotice/form")
public class AdminNoticeFormController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    //request.getRequestDispatcher("/admin/NoticeAddForm.jsp").forward(request, response);

    request.setAttribute("pageTitle", "🔔 공지게시글 등록");
    request.setAttribute("contentUrl", "/admin/NoticeAddForm.jsp");
    request.getRequestDispatcher("/template1.jsp").forward(request, response);

  } 
}

