package com.ogong.pms.servlet.myStudy.freeBoard.comment;

import java.io.IOException;
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
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Study;

@WebServlet("/freeboard/comment/form")
public class CommentAddFormController extends HttpServlet {
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
      // int no = Integer.parseInt(request.getParameter("perNo"));
      // Member member = memberDao.findByNo(no);

      int studyNo = Integer.parseInt(request.getParameter("studyno"));
      Study study = studyDao.findByNo(studyNo);

      int freeBoardNo = Integer.parseInt(request.getParameter("freeboardno"));
      FreeBoard freeBoard = freeBoardDao.findByNo(freeBoardNo, studyNo);

      // request.setAttribute("member", member);
      request.setAttribute("study", study);
      request.setAttribute("freeBoard", freeBoard);
      response.sendRedirect("../detail?studyno=" + freeBoard.getStudyNo() + "&freeboardno=" + freeBoard.getFreeBoardNo());

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
