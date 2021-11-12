package com.ogong.pms.web.ceoMember;
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
//import com.ogong.pms.dao.CeoMemberDao;
//import com.ogong.pms.domain.CeoMember;
//
//@WebServlet("/ceomember/logincheck")
//public class CeoLoginCheck extends HttpServlet {
//
//  private static final long serialVersionUID = 1L;
//
//  CeoMemberDao ceoMemberDao;
//
//  public static CeoMember loginCeoMember;
//  public static CeoMember getLoginCeoMember() {
//    return loginCeoMember;
//  }
//
//  @Override
//  public void init(ServletConfig config) throws ServletException {
//    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
//    ceoMemberDao = (CeoMemberDao) 웹애플리케이션공용저장소.getAttribute("ceoMemberDao");
//  }
//
//  @Override
//  protected void service(HttpServletRequest request, HttpServletResponse response)
//      throws ServletException, IOException {
//
//    try {
//      String email = request.getParameter("email");
//
//      CeoMember ceoMember = ceoMemberDao.findByEmail(email);
//
//      if (email.equals(ceoMember.getCeoEmail())) {
//        
//      }
//    } 
//  } catch (Exception e) {
//    e.printStackTrace();
//    request.setAttribute("error", e);
//    request.getRequestDispatcher("/Error.jsp").forward(request, response);
//  }
//}