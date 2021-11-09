package com.ogong.pms.servlet.myStudy.freeBoard.comment;

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
import com.ogong.pms.domain.Study;

@WebServlet("/freeboard/comment/update")
public class CommentUpdateController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  StudyDao studyDao;
  FreeBoardDao freeBoardDao;
  CommentDao commentDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    studyDao = (StudyDao) 웹애플리케이션공용저장소.getAttribute("studyDao");
    freeBoardDao = (FreeBoardDao) 웹애플리케이션공용저장소.getAttribute("freeBoardDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      // if (AuthPerMemberLoginHandler.getLoginUser() == null) {
      // System.out.println(" >> 변경 권한이 없습니다.");
      // return;
      // }

      int studyNo = Integer.parseInt(request.getParameter("studyno"));
      Study study = studyDao.findByNo(studyNo);

      int freeBoardNo = Integer.parseInt(request.getParameter("freeboardno"));
      FreeBoard freeBoard = freeBoardDao.findByNo(freeBoardNo, studyNo);

      if (freeBoard == null) {
        throw new Exception(" >> 해당 번호의 게시글이 없습니다.\n");
      }

      List<Comment> commentList = commentDao.findAll(freeBoard.getFreeBoardNo());

      /*
       * int commentNo = 0; while (true) { try { commentNo = Prompt.inputInt(" 번호 : "); } catch
       * (NumberFormatException e){ System.out.println(" >> 숫자를 입력해 주세요."); continue; } break; }
       * 
       * String commentTitle = null; String perNickname =
       * AuthPerMemberLoginHandler.getLoginUser().getPerNickname();
       * 
       * int index = -1; for (int i = 0; i < commentList.size(); i++) { if
       * ((commentList.get(i).getCommentNo() == commentNo) &&
       * (commentList.get(i).getCommentWiter().getPerNickname().equals(perNickname))){
       * 
       * commentTitle = Prompt.inputString( "댓글 내용(" + commentList.get(i).getCommentText() +
       * ") : "); System.out.println(); index = i; } }
       * 
       * if (index < 0) { System.out.println(" >> 알맞는 번호를 입력해 주세요.");
       * request.getRequestDispatcher("/myStudy/freeBoardDetail").forward(request); return; }
       * 
       * String input = Prompt.inputString(" 정말 변경하시겠습니까? (네 / 아니오) "); if
       * (!input.equalsIgnoreCase("네")) { System.out.println(" >> 댓글 변경이 취소되었습니다.");
       * request.getRequestDispatcher("/myStudy/freeBoardList").forward(request); return; }
       */

      freeBoard.setComment(commentList);

      commentDao.update(Integer.parseInt(request.getParameter("commentno")),
          request.getParameter("commenttext"));

      // System.out.println(" >> 댓글을 변경하였습니다.");
      request.setAttribute("study", study);
      request.setAttribute("freeBoard", freeBoard);
      request.setAttribute("commentList", commentList);
      request.getRequestDispatcher("/myStudy/freeBoardDetail.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
