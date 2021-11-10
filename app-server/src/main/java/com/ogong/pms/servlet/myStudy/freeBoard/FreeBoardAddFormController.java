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

@WebServlet("/freeboard/addform")
public class FreeBoardAddFormController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  MemberDao memberDao;
  FreeBoardDao freeBoardDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    freeBoardDao = (FreeBoardDao) 웹애플리케이션공용저장소.getAttribute("freeBoardDao");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      // int perNo = Integer.parseInt(request.getParameter("perNo"));
      // Member member = memberDao.findByNo(perNo);

      int studyNo = Integer.parseInt(request.getParameter("studyno"));

      // request.setAttribute("member", member);
      request.setAttribute("studyno", studyNo);
      request.setAttribute("pageTitle", "자유 게시판 등록");
      request.setAttribute("contentUrl", "/myStudy/freeBoard/FreeBoardAddForm.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
