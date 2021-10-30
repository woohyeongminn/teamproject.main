package com.ogong.pms.servlet.ceoMember;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ceomember/addform")
public class CeoAddFormController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  // 기업 개인
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.getRequestDispatcher("/ceoMember/CeoMemberAddForm.jsp").forward(request, response);

  }
}