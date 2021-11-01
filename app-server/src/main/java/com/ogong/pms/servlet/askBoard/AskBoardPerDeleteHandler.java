package com.ogong.pms.servlet.askBoard;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.AskBoardDao;

@WebServlet("/askboard/perdelete")
public class AskBoardPerDeleteHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;

  AskBoardDao askBoardDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    askBoardDao = (AskBoardDao) 웹애플리케이션공용저장소.getAttribute("askBoardDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int askNo = Integer.parseInt(request.getParameter("askNo")); 
      int perNo = Integer.parseInt(request.getParameter("perNo")); 
      askBoardDao.deletereply(askNo);
      askBoardDao.delete(askNo);
      sqlSession.commit();
      response.sendRedirect("permylist?perNo=" + perNo);

    } catch (Exception e) {
      e.getStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}







