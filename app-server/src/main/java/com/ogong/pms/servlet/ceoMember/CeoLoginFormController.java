package com.ogong.pms.servlet.ceoMember;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ceomember/form")
public class CeoLoginFormController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    
    request.setAttribute("pageTitle", "🖐 오늘의 공부 로그인");
    request.setAttribute("contentUrl", "/ceoMember/CeoMemberLoginForm.jsp");
    request.getRequestDispatcher("/template1.jsp").forward(request, response);

  }
}