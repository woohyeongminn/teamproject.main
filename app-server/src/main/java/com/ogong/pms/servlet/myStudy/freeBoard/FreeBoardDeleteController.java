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
import com.ogong.pms.domain.FreeBoard;

@WebServlet("/freeboard/delete")
public class FreeBoardDeleteController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  // MemberDao memberDao;
  FreeBoardDao freeBoardDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    // memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
    freeBoardDao = (FreeBoardDao) 웹애플리케이션공용저장소.getAttribute("freeBoardDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      // int perNo = Integer.parseInt(request.getParameter("perNo"));
      int studyNo = Integer.parseInt(request.getParameter("studyno"));
      int freeBoardNo = Integer.parseInt(request.getParameter("freeboardno"));

      FreeBoard freeBoard = freeBoardDao.findByNo(freeBoardNo, studyNo);

      if (freeBoard == null) {
        throw new Exception("해당 번호의 게시글이 없습니다.");
      }

      if (!freeBoard.getFreeBoardFile().isEmpty()) {
        throw new Exception("게시글의 첨부파일이 모두 삭제됩니다.");
      }

      freeBoardDao.deleteComment(freeBoardNo);
      freeBoardDao.deleteFile(freeBoardNo);
      freeBoardDao.delete(freeBoardNo, studyNo);
      sqlSession.commit();

      response.sendRedirect("list?studyno=" + studyNo);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
