package com.ogong.pms.web.ceoMember;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.domain.CeoMember;

@WebServlet("/ceomember/updateform")
public class CeoUpdateFormController extends HttpServlet  {
  private static final long serialVersionUID = 1L;

  CeoMemberDao ceoMemberDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    ceoMemberDao = (CeoMemberDao) 웹애플리케이션공용저장소.getAttribute("ceoMemberDao");
  }

  // 기업회원 개인정보 수정은 이름,이메일,비밀번호만 가능
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      CeoMember loginCeo = (CeoMember) request.getSession().getAttribute("loginCeoUser");
      CeoMember ceoMember = ceoMemberDao.findByNo(loginCeo.getCeoNo());

      if (ceoMember == null) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      }

      request.setAttribute("ceoMember", ceoMember);

      request.setAttribute("pageTitle", "🙂 마이페이지 - 프로필 수정");
      request.setAttribute("contentUrl", "/ceoMember/CeoMemberUpdateForm.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}







