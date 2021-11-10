package com.ogong.pms.servlet.myStudy.freeBoard;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogong.pms.dao.FreeBoardDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.FreeBoard;

@WebServlet("/freeboard/list")
public class FreeBoardListController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  // MemberDao memberDao;
  StudyDao studyDao;
  FreeBoardDao freeBoardDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    // memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
    studyDao = (StudyDao) 웹애플리케이션공용저장소.getAttribute("studyDao");
    freeBoardDao = (FreeBoardDao) 웹애플리케이션공용저장소.getAttribute("freeBoardDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      // int perNo = Integer.parseInt(request.getParameter("perNo"));
      // Member member = memberDao.findByNo(perNo);

      int studyNo = Integer.parseInt(request.getParameter("studyno"));
      List<FreeBoard> freeBoardList = freeBoardDao.findAll(studyNo);

      // request.setAttribute("member", member);
      request.setAttribute("studyno", studyNo);
      request.setAttribute("freeBoardList", freeBoardList);
      request.setAttribute("pageTitle", "자유 게시판 목록");
      request.setAttribute("contentUrl", "/myStudy/freeBoard/FreeBoardList.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
