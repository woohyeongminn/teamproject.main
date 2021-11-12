package com.ogong.pms.web.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/member/updateform")
public class MemberUpdateFromController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {

      request.setAttribute("pageTitle", "💬프로필 수정");
      request.setAttribute("contentUrl", "/member/PerMemberUpdateForm.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}