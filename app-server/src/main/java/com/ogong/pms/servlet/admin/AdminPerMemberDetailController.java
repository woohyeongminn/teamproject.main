package com.ogong.pms.servlet.admin;

import java.io.IOException;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.domain.Member;

@WebServlet("/admin/permemberdetail")
public class AdminPerMemberDetailController extends GenericServlet {
  private static final long serialVersionUID = 1L;

  MemberDao memberDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      int inputPerNo = Integer.parseInt(request.getParameter("no"));
      Member perMember = memberDao.findByNo(inputPerNo);

      if (perMember == null) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      }

      request.setAttribute("pageTitle", "📖 개인 회원 상세");
      request.setAttribute("perMember", perMember);
      request.setAttribute("contentUrl", "/admin/AdminPerMemberDetail.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
