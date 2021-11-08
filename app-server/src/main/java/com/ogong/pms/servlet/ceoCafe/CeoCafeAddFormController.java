//package com.ogong.pms.servlet.ceoCafe;
//
//import java.io.IOException;
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import com.ogong.pms.dao.CafeDao;
//import com.ogong.pms.domain.Cafe;
//import com.ogong.pms.domain.CeoMember;
//
//@WebServlet("/ceomember/cafe/addform")
//public class CeoCafeAddFormController extends HttpServlet {
//  private static final long serialVersionUID = 1L;
//
//  CafeDao cafeDao;
//
//  @Override
//  public void init(ServletConfig config) throws ServletException {
//    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
//    cafeDao = (CafeDao) 웹애플리케이션공용저장소.getAttribute("cafeDao");
//  }
//
//  @Override
//  protected void service(HttpServletRequest request, HttpServletResponse response)
//      throws ServletException, IOException {
//
//    try {
//      CeoMember loginCeo = (CeoMember) request.getSession().getAttribute("loginCeoUser");
//      Cafe cafe = cafeDao.findByNo(loginCeo.getCeoNo());
//
//      request.setAttribute("ceoMember", ceoMember);
//      request.getRequestDispatcher("/ceoMember/CeoMemberAddForm.jsp").forward(request, response);
//
//    } catch (Exception e) {
//      request.setAttribute("error", e);
//      request.getRequestDispatcher("/Error.jsp").forward(request, response);
//    }
//
//
//  }
//}