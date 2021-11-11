package com.ogong.pms.servlet.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/form")
public class PerLoginFormController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setAttribute("pageTitle", " 🖐 개인 회원 로그인 ");
    request.setAttribute("contentUrl", "/member/PerMemberLoginForm.jsp");
    request.getRequestDispatcher("/template1.jsp").forward(request, response);
  } 
}
