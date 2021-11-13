package com.ogong.pms.web.myStudy.freeBoard.comment;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogong.pms.dao.CommentDao;
import com.ogong.pms.dao.FreeBoardDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Comment;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Study;

public class CommentUpdateFormController extends HttpServlet {

  StudyDao studyDao;
  FreeBoardDao freeBoardDao;
  CommentDao commentDao;

  @WebServlet("/freeboard/comment/updateform")
  protected void service(HttpServletRequest request, HttpServletResponse response) {
    int studyNo = Integer.parseInt(request.getParameter("studyno"));
    Study study = studyDao.findByNo(studyNo);

    int freeBoardNo = Integer.parseInt(request.getParameter("freeboardno"));
    FreeBoard freeBoard = freeBoardDao.findByNo(freeBoardNo, studyNo);

    int commentNo = Integer.parseInt(request.getParameter("commentno"));
    Comment comment = commentDao.findByNo(commentNo);

    request.setAttribute("study", study);
    request.setAttribute("freeBoard", freeBoard);
    request.setAttribute("comment", comment);
    request.setAttribute("pageTitle", "댓글 수정");
    request.setAttribute("contentUrl", "/myStudy/freeBoard/comment/CommentUpdateForm.jsp");
    request.getRequestDispatcher("/template1.jsp").forward(request, response);
  }
}
