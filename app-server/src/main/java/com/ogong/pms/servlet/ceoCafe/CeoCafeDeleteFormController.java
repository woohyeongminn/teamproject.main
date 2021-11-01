package com.ogong.pms.servlet.ceoCafe;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ceomember/cafe/deleteform")
public class CeoCafeDeleteFormController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    int no = Integer.parseInt(request.getParameter("no"));

    request.setAttribute("cafe", no);
    request.getRequestDispatcher("/ceoCafe/CeoCafeDeleteForm.jsp").forward(request, response);

  }
}