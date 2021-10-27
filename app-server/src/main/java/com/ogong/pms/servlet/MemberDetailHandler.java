package com.ogong.pms.servlet;

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
import com.ogong.util.Prompt;

@WebServlet("/member/datail")
public class MemberDetailHandler extends GenericServlet {
  private static final long seriaVersionUID = 1L;

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
    out.println("   <title>회원상세</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>[회원 상세보기]</h1>");
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

    int no = Integer.parseInt(request.getParameter("no"));

    try {
      Member member = memberDao.findByNo(no);

      System.out.println();
      System.out.printf(" [%s]\n", member.getPerNickname());
      System.out.printf(" >> 이  름 : %s\n", member.getPerName());
      System.out.printf(" >> 사  진 : %s\n", member.getPerPhoto());
      System.out.printf(" >> 전화번호 : %s\n", member.getPerTel());
      System.out.printf(" >> 이메일 : %s\n", member.getPerEmail());
      System.out.printf(" >> 가입일 : %s\n", member.getPerRegisteredDate());

    } catch (NullPointerException e) {
      System.out.println();
      System.out.println(" >> 프로필 실행 오류");
    }

    if (member == null) {
      return;
    }

    request.setAttribute("memberNo", member.getPerNo());

    System.out.println();
    System.out.println("1. 수정");
    System.out.println("2. 탈퇴");
    System.out.println("0. 이전");
    while (true) {
      int selectNo = Prompt.inputInt("선택> ");
      switch (selectNo) {
        case 1:
          request.getRequestDispatcher("/member/update").forward(request);
          return;
        case 2:
          request.getRequestDispatcher("/member/delete").forward(request);
          return;
        case 0:
          return;
        default:
          System.out.println(" >> 번호를 다시 선택해 주세요.");
      }
    }

  }
}
