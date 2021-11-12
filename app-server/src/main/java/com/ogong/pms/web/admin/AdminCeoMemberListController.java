package com.ogong.pms.web.admin;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.domain.CeoMember;

@WebServlet("/admin/ceomember/list")
public class AdminCeoMemberListController extends GenericServlet  {
  private static final long serialVersionUID = 1L;

  CeoMemberDao ceoMemberDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    ceoMemberDao = (CeoMemberDao) 웹애플리케이션공용저장소.getAttribute("ceoMemberDao");
  }

  // 관리자가 사용
  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      Collection<CeoMember> ceoMemberList  = ceoMemberDao.findAll();

      request.setAttribute("ceoMemberList", ceoMemberList);

      request.setAttribute("pageTitle", " 🏢 기업회원 목록");
      request.setAttribute("contentUrl", "/admin/AdminCeoMemberList.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
