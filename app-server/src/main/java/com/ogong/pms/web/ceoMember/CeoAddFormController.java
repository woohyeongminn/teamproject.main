package com.ogong.pms.web.ceoMember;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.domain.CeoMember;

@WebServlet("/ceomember/addform")
public class CeoAddFormController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CeoMemberDao ceoMemberDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    ceoMemberDao = (CeoMemberDao) 웹애플리케이션공용저장소.getAttribute("ceoMemberDao");
  }

  // 기업 회원가입
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      Collection<CeoMember> ceoMemberList  = ceoMemberDao.findAll();

      // 중복체크 하려고 보냈음
      request.setAttribute("ceoMemberList", ceoMemberList);

      request.setAttribute("pageTitle", "기업 회원가입");
      request.setAttribute("contentUrl", "/ceoMember/CeoMemberAddForm.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);


    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }


  }
}