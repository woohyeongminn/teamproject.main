package com.ogong.pms.servlet.myStudy.freeBoard;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.FreeBoardDao;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Member;

@WebServlet("/freeboard/add")
public class FreeBoardAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  FreeBoardDao freeBoardDao;
  MemberDao memberDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    freeBoardDao = (FreeBoardDao) 웹애플리케이션공용저장소.getAttribute("freeBoardDao");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      Member loginUser = (Member) request.getSession().getAttribute("loginUser");
      Member member = memberDao.findByNo(loginUser.getPerNo());

      int studyNo = Integer.parseInt(request.getParameter("studyno"));

      FreeBoard freeBoard = new FreeBoard();

      freeBoard.setStudyNo(studyNo);
      freeBoard.setFreeBoardWriter(member);
      freeBoard.setFreeBoardTitle(request.getParameter("title"));
      freeBoard.setFreeBoardContent(request.getParameter("content"));
      System.out.println(freeBoard);

      freeBoardDao.insert(freeBoard);
      sqlSession.commit();

      response.sendRedirect("list?studyno=" + studyNo);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
