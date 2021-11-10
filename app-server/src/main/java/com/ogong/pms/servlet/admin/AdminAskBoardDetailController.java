package com.ogong.pms.servlet.admin;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.ogong.pms.dao.AskBoardDao;
import com.ogong.pms.domain.AskBoard;

@WebServlet("/admin/askboarddetail")
public class AdminAskBoardDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  AskBoardDao askBoardDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    askBoardDao = (AskBoardDao) 웹애플리케이션공용저장소.getAttribute("askBoardDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      int askBoardNo = Integer.parseInt(request.getParameter("askNo"));
      AskBoard adminAskBoard = askBoardDao.findByNo(askBoardNo);

      if (adminAskBoard == null) {
        throw new Exception("문의게시글 상세 오류!");
      }

      request.setAttribute("pageTitle", "💬문의글 상세");
      request.setAttribute("adminAskBoard", adminAskBoard);
      request.setAttribute("contentUrl", "/admin/AskBoardDetail.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
