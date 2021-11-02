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
import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.domain.CeoMember;

@WebServlet("/askboard/ceoadd")
public class AskBoardCeoAddHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  AskBoardDao askBoardDao;
  CeoMemberDao ceoMemberDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    askBoardDao = (AskBoardDao) 웹애플리케이션공용저장소.getAttribute("askBoardDao");
    ceoMemberDao = (CeoMemberDao) 웹애플리케이션공용저장소.getAttribute("ceoMemberDao");
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {

      int no = Integer.parseInt(request.getParameter("writer"));
      CeoMember ceoMember = ceoMemberDao.findByNo(no);

      AskBoard askBoard = new AskBoard();

      askBoard.setAskTitle(request.getParameter("title"));
      askBoard.setAskContent(request.getParameter("content"));
      askBoard.setAskCeoWriter(ceoMember);
      askBoard.setAskStatus(Integer.parseInt(request.getParameter("status")));
      askBoard.setAskTempPW(Integer.parseInt(request.getParameter("tempPW")));

      askBoardDao.insertCeo(askBoard);
      sqlSession.commit();

      request.setAttribute("ceoNo", askBoard.getAskCeoWriter().getCeoNo());
      request.getRequestDispatcher("/askBoard/AskBoardCeoAdd.jsp").forward(request, response);


    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
