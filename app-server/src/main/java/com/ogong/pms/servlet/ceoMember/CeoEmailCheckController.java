//package com.ogong.pms.servlet.ceoMember;
//
//import java.io.IOException;
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.apache.ibatis.session.SqlSession;
//import com.ogong.pms.dao.CeoMemberDao;
//
//@WebServlet("/ceomember/add/idcheck")
//public class CeoEmailCheckController extends HttpServlet {
//  private static final long serialVersionUID = 1L;
//
//  CeoMemberDao ceoMemberDao;
//  SqlSession sqlSession;
//
//  @Override
//  public void init(ServletConfig config) throws ServletException {
//    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
//    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
//    ceoMemberDao = (CeoMemberDao) 웹애플리케이션공용저장소.getAttribute("ceoMemberDao");
//  }
//
//  // 기업 개인
//  @Override
//  protected void service(HttpServletRequest request, HttpServletResponse response)
//      throws ServletException, IOException {
//
//    try {
//      String email =  request.getParameter("checkEmail");
//
//      ceoMemberDao
//
//
//      //response.setHeader("Refresh", "1;url=list");
//      request.getRequestDispatcher("/ceoMember/CeoMemberAdd.jsp").forward(request, response);
//
//    } catch (Exception e) {
//      request.setAttribute("error", e);
//      request.getRequestDispatcher("/Error.jsp").forward(request, response);
//    }
//
//  }
//}