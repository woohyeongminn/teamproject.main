package com.ogong.pms.web.myStudy.freeBoard;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogong.pms.dao.CommentDao;
import com.ogong.pms.dao.FreeBoardDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Comment;
import com.ogong.pms.domain.FreeBoard;

@WebServlet("/freeboard/detail")
public class FreeBoardDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  // MemberDao memberDao;
  StudyDao studyDao;
  FreeBoardDao freeBoardDao;
  CommentDao commentDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    // memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
    studyDao = (StudyDao) 웹애플리케이션공용저장소.getAttribute("studyDao");
    freeBoardDao = (FreeBoardDao) 웹애플리케이션공용저장소.getAttribute("freeBoardDao");
    commentDao = (CommentDao) 웹애플리케이션공용저장소.getAttribute("commentDao");

  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      // int perNo = Integer.parseInt(request.getParameter("perno"));
      // Member member = memberDao.findByNo(perNo);

      int studyNo = Integer.parseInt(request.getParameter("studyno"));
      int freeBoardNo = Integer.parseInt(request.getParameter("freeboardno"));

      FreeBoard freeBoard = freeBoardDao.findByNo(freeBoardNo, studyNo);
      // System.out.println(freeBoard.getFreeBoardNo());

      // if (freeBoard == null) {
      // System.out.println(" >> 해당 번호의 게시글이 없습니다.\n");
      // request.getRequestDispatcher("/myStudy/freeBoardList").forward(request);
      // return;
      // }

      List<Comment> commentList = commentDao.findAll(freeBoard.getFreeBoardNo());

      /* [Test] */
      // if (commentList.isEmpty()) {
      // System.out.println("댓글 목록이 비어 있습니다.");
      // }

      // request.setAttribute("member", member);
      request.setAttribute("freeBoard", freeBoard);
      request.setAttribute("commentList", commentList);
      request.setAttribute("pageTitle", "자유 게시판 상세");
      request.setAttribute("contentUrl", "/myStudy/freeBoard/FreeBoardDetail.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
