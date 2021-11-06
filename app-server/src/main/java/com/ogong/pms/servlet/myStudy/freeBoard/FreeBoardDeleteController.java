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

@WebServlet("/mystudy/freeboarddelete")
public class FreeBoardDeleteController extends HttpServlet {
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

      int studyNo = Integer.parseInt(request.getParameter("studyNo"));
      int freeNo = Integer.parseInt(request.getParameter("freeNo"));
      int perNo = Integer.parseInt(request.getParameter("perNo"));
      FreeBoard freeBoard = freeBoardDao.findByNo(freeNo, studyNo);

      if (freeBoard == null) {
        System.out.println(" >> 해당 번호의 게시글이 없습니다.\n");
        return;
      }

      if (!freeBoard.getFreeBoardFile().isEmpty()) {
        System.out.println(" >> 게시글의 첨부파일이 모두 삭제됩니다.");
      }

      freeBoardDao.deleteComment(freeNo);
      freeBoardDao.deleteFile(freeNo);
      freeBoardDao.delete(freeNo, studyNo);
      sqlSession.commit();

      response.sendRedirect(
          "freeboardlist?studyNo="+studyNo+"&perNo="+ perNo);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }

  }
}


