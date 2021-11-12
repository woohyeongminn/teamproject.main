package com.ogong.pms.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class SignUp extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setAttribute("pageTitle", "회원가입");
    request.setAttribute("contentUrl", "signUp.jsp");
    request.getRequestDispatcher("/template1.jsp").forward(request, response);
  } 
}
