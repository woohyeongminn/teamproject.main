package com.ogong.pms.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.domain.CeoMember;

@WebServlet("/adminCeoMember/detail")
public class AdminCeoMemberDetailHandler extends GenericServlet {
  private static final long serialVersionUID = 1L;

  CeoMemberDao ceoMemberDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    ceoMemberDao = (CeoMemberDao) 웹애플리케이션공용저장소.getAttribute("ceoMemberDao");
  }

  //관리자가 사용
  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("  <title>기업회원 상세</title>");
    out.println("  <style>");
    out.println("  label {");
    out.println("    margin-right: 5px;");
    out.println("    text-align: left;");
    out.println("    display: inline-block;");
    //    out.println("    width: 150px;");
    out.println("  }");
    out.println("  </style>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>▶ 기업회원 상세</h1>");

    int inputCeoNo = Integer.parseInt(request.getParameter("no"));

    try {

      CeoMember ceoMember = ceoMemberDao.findByNo(inputCeoNo);

      if (ceoMember == null) {
        out.println("해당 번호의 회원이 없습니다.");

      } else {

        out.println("<form action='delete'>");
        out.printf("<label for='f-no'>번호</label> <span id='f-no'>%s</span><br>\n", ceoMember.getCeoNo());
        out.printf("<label for='f-name'>이름</label> <span id='f-name'>%s</span><br>\n", ceoMember.getCeoNickname());
        out.printf("<label for='f-email'>이메일</label> <span id='f-email'>%s</span><br>\n", ceoMember.getCeoEmail());
        out.printf("<label for='f-licenseno'>사업자등록번호</label> <span id='f-licenseno'>%s</span><br>\n", ceoMember.getCeoLicenseNo());
        out.printf("<label for='f-photo'>사진</label> <span id='f-photo'>%s</span><br>\n", ceoMember.getCeoPhoto());
        out.printf("<label for='f-registeredDate'>등록일</label> <span id='f-registeredDate'>%s</span><br>", ceoMember.getCeoRegisteredDate());

        // request.setAttribute("inputCeoNo", inputCeoNo);

        out.printf(" <a href='delete?no=%d'>[탈퇴]</a>", ceoMember.getCeoNo());
        out.println(" <a href='list'>[목록]</a><br>");
        out.println("</form>");
      }
    } catch (Exception e) {
      throw new ServletException(e);
    }

    out.println("</body>");
    out.println("</html>");
  }
}
