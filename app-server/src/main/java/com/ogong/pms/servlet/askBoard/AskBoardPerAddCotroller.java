package com.ogong.pms.servlet.askBoard;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.AskBoardDao;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.domain.Member;

@WebServlet("/askboard/peradd")
public class AskBoardPerAddCotroller extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  AskBoardDao askBoardDao;
  MemberDao memberDao;

  @Override
  public void init() {
    ServletContext 웹애플리케이션공용저장소 = getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    askBoardDao = (AskBoardDao) 웹애플리케이션공용저장소.getAttribute("askBoardDao");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {

      int no = Integer.parseInt(request.getParameter("writer"));
      Member member = memberDao.findByNo(no);

      AskBoard askBoard = new AskBoard();


      askBoard.setAskTitle(request.getParameter("title"));
      askBoard.setAskContent(request.getParameter("content"));
      askBoard.setAskMemberWriter(member);
      askBoard.setAskStatus(Integer.parseInt(request.getParameter("status")));

      if (askBoard.getAskStatus() == 2) {
        askBoard.setAskTempPW(Integer.parseInt(request.getParameter("tempPW")));
      }

      System.out.println(askBoard);
      askBoardDao.insertPer(askBoard);
      sqlSession.commit();

      request.setAttribute("pageTitle", "💬문의글 등록");
      request.setAttribute("contentUrl", "/askBoard/AskBoardPerAdd.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      sqlSession.rollback();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
