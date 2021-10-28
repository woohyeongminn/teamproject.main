package com.ogong.pms.servlet.member;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.domain.Member;

@WebServlet("/member/detail")
public class MemberDetailHandler extends GenericServlet {
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

    // 클라이언트로 출력할 때 utf-8로 인코딩 해서 한다는 의미
    response.setContentType("text/html;charset=UTF-8");
    // 출력 스트림을 달라는 의미
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("   <title>마이 페이지</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1> ▶내 프로필</h1>");
    out.println("<style>");
    out.println(" label {");
    out.println("    margin-right: 5px;");
    out.println("    text-align: right;");
    out.println("    display: inline-block;");
    out.println("    width: 60px;");
    out.println("   }");
    out.println("</style>");
    out.println("</head>");
    out.println("<body>");


    try {
      int no = Integer.parseInt(request.getParameter("no")); 
      Member member = memberDao.findByNo(no);
      if (member == null) {
        out.println("해당 번호의 회원이 없습니다.");

      } else {
        out.println();
        out.printf("<td>닉네임:%s</td><br>", member.getPerNickname());
        out.printf("<td>이름:%s</td><br>", member.getPerName());
        out.printf("<td>사진%s</td><br>", member.getPerPhoto());
        out.printf("<td>번호:%s</td><br>", member.getPerTel());
        out.printf("<td>이메일:%s</td><br>", member.getPerEmail());
        out.printf("<td>가입일:%s</td><br>", member.getPerRegisteredDate());

        out.printf("<button>");
        out.printf("<a href='detail?no=%1$d'>", member.getPerNo());
        out.printf("<p>개인정보수정</p>");
        out.printf("</a>");
        out.printf("</button>");
        out.printf("<button>");
        out.printf("<a href='detail?no=%1$d'>", member.getPerNo());
        out.printf("<p>회원탈퇴</p>");
        out.printf("</a>");
        out.printf("</button>");

      }
    } catch (Exception e) {
      // 에러를 통제하는 출력을 직접 관리할 수 있다.
      throw new ServletException(e);
    }

    out.println("</tbody>");
    out.println("</table>");
    out.println("</body>");
    out.println("</html>");
  }

  @Override
  public String getServletInfo() {
    return null;
  }

  @Override
  public ServletConfig getServletConfig() {
    return null;
  }

}
