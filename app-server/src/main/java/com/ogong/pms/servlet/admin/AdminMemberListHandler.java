package com.ogong.pms.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.domain.Member;

@WebServlet("/admin/permemberlist")
public class AdminMemberListHandler extends GenericServlet {
  private static final long serialVersionUID = 1L;

  MemberDao memberDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
  }

  // 관리자
  @Override
  public void service(ServletRequest requset, ServletResponse response)
      throws ServletException, IOException {

    // 클라이언트로 출력할 때 utf-8로 인코딩 해서 한다는 의미
    response.setContentType("text/html;charset=UTF-8");
    // 출력 스트림을 달라는 의미
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("   <title>개인회원목록</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1> ▶회원 목록</h1>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr>");
    out.println("    <th>번호</th>");
    out.println("    <th>이름</th>");
    out.println("    <th>이메일</th>");
    out.println("    <th>전화</th>");
    out.println("    <th>등록일</th>");
    out.println("  <tr>");
    out.println("</thead>");
    out.println("<tbody>");

    try {
      Collection<Member> memberList = memberDao.findAll();

      for (Member member : memberList) {
        out.printf("<tr>"
            + "<td>(%d)</td>"
            + "<td>%s</td>"
            + "<td>%s</td>"
            + "<td>%s</td>"
            + "<td>%s</td>"
            + "</tr>",
            member.getPerNo(),
            member.getPerName(),
            member.getPerNickname(), 
            member.getPerEmail(),
            member.getPerRegisteredDate());
        System.out.println();
      }
    } catch (Exception e) {
      throw new ServletException(e);
    }
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






