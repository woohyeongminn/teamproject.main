package com.ogong.pms.servlet.admin;

import java.io.IOException;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import com.ogong.pms.dao.AskBoardDao;
import com.ogong.pms.domain.AskBoard;

@WebServlet("/admin/askboarddetail")
public class AdminAskBoardDetailHandler extends GenericServlet {
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
      int askBoardNo = Integer.parseInt(request.getParameter("no"));
      AskBoard askBoard = askBoardDao.findByNo(askBoardNo);

      if (askBoard == null) {
        throw new Exception("해당 번호의 게시글이 없습니다.");
      }
      request.setAttribute("askBoard", askBoard);
      request.getRequestDispatcher("/admin/AskBoardDetail.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
