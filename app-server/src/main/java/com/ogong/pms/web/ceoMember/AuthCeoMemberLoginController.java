package com.ogong.pms.web.ceoMember;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.domain.CeoMember;

@WebServlet("/ceomember/login")
public class AuthCeoMemberLoginController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  CeoMemberDao ceoMemberDao;


  //  public static CeoMember loginCeoMember;
  //  public static CeoMember getLoginCeoMember() {
  //    return loginCeoMember;
  //  }

  @Override
  public void init() throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = getServletContext();
    ceoMemberDao = (CeoMemberDao) 웹애플리케이션공용저장소.getAttribute("ceoMemberDao");

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String email = request.getParameter("email");
    String password = request.getParameter("password");

    try {
      Cookie cookie = null;
      if (request.getParameter("saveEmail") != null) {
        // 클라이언트에게 맡길 쿠키 준비
        cookie = new Cookie("email", email);

        // 유효기간 설정
        cookie.setMaxAge(60 * 60 * 24 * 7);

        // 사용범위 설정
        cookie.setPath(getServletContext().getContextPath() + "/ceomember");

      } else {
        cookie = new Cookie("email", "");
        cookie.setMaxAge(0);        // 유효기간을 0으로 설정, 웹브라우저가 받는 즉시 무효한 쿠키가 된다
      }

      CeoMember ceoMember = ceoMemberDao.findByEmailAndPassword(email, password);

      if (ceoMember != null && ceoMember.getCeoStatus() == CeoMember.CEO) {
        if (ceoMember.getActive() == CeoMember.INUSER) {
          request.getSession().setAttribute("loginCeoUser", ceoMember);
          response.sendRedirect("../index.jsp");
        }

      } else {
        response.setHeader("Refresh", "2;url=form");

        request.setAttribute("pageTitle", "로그인오류!");
        request.setAttribute("contentUrl", "/auth/LoginFail.jsp");
        request.getRequestDispatcher("/template1.jsp").forward(request, response);
      }

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}