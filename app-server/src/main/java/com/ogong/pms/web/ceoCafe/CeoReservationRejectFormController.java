package com.ogong.pms.web.ceoCafe;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ceomember/cafe/reser/rejectform")
public class CeoReservationRejectFormController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int resNo = Integer.parseInt(request.getParameter("resno"));  // 예약번호

      request.setAttribute("reserNo", resNo);

      request.setAttribute("pageTitle", "👩‍🏫 스터디룸 예약 거절");
      request.setAttribute("contentUrl", "/ceoCafe/CeoCafeReservationRejectForm.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
