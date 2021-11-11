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
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.domain.Reply;

@WebServlet("/askboard/replyadd")
public class ReplyAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  AskBoardDao askBoardDao;
  SqlSession sqlSession;

  @Override
  public void init() {
    ServletContext 웹애플리케이션공용저장소 = getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    askBoardDao = (AskBoardDao) 웹애플리케이션공용저장소.getAttribute("askBoardDao");
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {

      int askNo = Integer.parseInt(request.getParameter("askNo"));
      AskBoard askBoard = askBoardDao.findByNo(askNo);

      if (askBoard.getReply() != null) {
        throw new Exception(" >> 이미 등록된 답변이 있습니다.");
      }

      Reply reply = new Reply();
      reply.setReplyTitle(request.getParameter("title"));
      reply.setReplyContent(request.getParameter("content"));

      askBoard.setReply(reply);

      askBoardDao.insertreply(askBoard);
      sqlSession.commit();

      request.setAttribute("pageTitle", "📖답변 등록");
      request.setAttribute("contentUrl", "/askBoard/ReplyAdd.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      sqlSession.rollback();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
