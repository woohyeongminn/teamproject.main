package com.ogong.pms.servlet.ceoCafe;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CeoMember;
import com.ogong.pms.servlet.cafe.CafeHandlerHelper;

@WebServlet("/ceomember/cafe/updateform")
public class CeoCafeUpdateFormController extends HttpServlet  {
  private static final long serialVersionUID = 1L;

  CeoMemberDao ceoMemberDao;
  CafeDao cafeDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    ceoMemberDao = (CeoMemberDao) 웹애플리케이션공용저장소.getAttribute("ceoMemberDao");
    cafeDao = (CafeDao) 웹애플리케이션공용저장소.getAttribute("cafeDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      CeoMember loginCeo = (CeoMember) request.getSession().getAttribute("loginCeoUser");
      CeoMember ceoMember = ceoMemberDao.findByNo(loginCeo.getCeoNo());

      Cafe cafe = cafeDao.findByCafeNo(Integer.parseInt(request.getParameter("cafeno")));

      if (cafe == null) {
        throw new Exception("등록된 카페가 없습니다.");
      }

      String status = CafeHandlerHelper.getCafeStatusLabel(cafe.getCafeStatus());

      request.setAttribute("cafe", cafe);
      request.setAttribute("cafeStatus", status);
      request.setAttribute("ceoMember", ceoMember);
      request.getRequestDispatcher("/ceoCafe/CeoCafeUpdateForm.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}




