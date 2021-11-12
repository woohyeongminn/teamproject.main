package com.ogong.pms.web.myStudy.freeBoard;

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
import com.ogong.pms.domain.FreeBoard;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@WebServlet("/freeboard/update")
public class FreeBoardUpdateController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  // MemberDao memberDao;
  FreeBoardDao freeBoardDao;
  // PromptFreeBoard promptFreeBoard;
  SqlSession sqlSession;

  @Override
  public void init() {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    // memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
    freeBoardDao = (FreeBoardDao) 웹애플리케이션공용저장소.getAttribute("freeBoardDao");
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      // int perNo = Integer.parseInt(request.getParameter("perNo"));
      int studyNo = Integer.parseInt(request.getParameter("studyno"));
      int freeBoardNo = Integer.parseInt(request.getParameter("freeboardno"));

      FreeBoard freeBoard = freeBoardDao.findByNo(freeBoardNo, studyNo);

      freeBoard.setFreeBoardTitle(request.getParameter("title"));
      freeBoard.setFreeBoardContent(request.getParameter("content"));

      freeBoardDao.update(freeBoard, studyNo);
      sqlSession.commit();

      response.sendRedirect("detail?studyno=" + studyNo + "&freeboardno=" + freeBoardNo);

    } catch (Exception e) {
      e.printStackTrace();
      sqlSession.rollback();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
