package com.ogong.pms.servlet.myStudy.comment;

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

@WebServlet("/comment/delete")
public class CommentDeleteController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  StudyDao studyDao;
  FreeBoardDao freeBoardDao;
  CommentDao commentDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    studyDao = (StudyDao) 웹애플리케이션공용저장소.getAttribute("studyDao");
    freeBoardDao = (FreeBoardDao) 웹애플리케이션공용저장소.getAttribute("freeBoardDao");
    commentDao = (CommentDao) 웹애플리케이션공용저장소.getAttribute("commentDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      System.out.println("▶ 댓글 삭제");

      /*
       * if (AuthPerMemberLoginHandler.getLoginUser() == null) {
       * System.out.println(" >> 삭제 권한이 없습니다."); return; }
       */

      int studyNo = Integer.parseInt(request.getParameter("studyNo"));
      Study study = studyDao.findByNo(studyNo);

      // FreeBoard freeBoard = (FreeBoard) request.getAttribute("freeBoard");
      int freeBoardNo = Integer.parseInt(request.getParameter("freeBoardNo"));
      FreeBoard freeBoard = freeBoardDao.findByNo(freeBoardNo, studyNo);

      if (freeBoard == null) {
        System.out.println(" >> 해당 번호의 게시글이 없습니다.\n");
        return;
      }

      List<Comment> commentList = commentDao.findAll(freeBoard.getFreeBoardNo());

      /*
       * int commentNo = 0;
       * 
       * while (true) { try { commentNo = Prompt.inputInt(" 번호 : ");
       * 
       * } catch (NumberFormatException e){ System.out.println(" >> 숫자를 입력해 주세요."); continue; }
       * break; }
       * 
       * int index = -1;
       * 
       * Member member = AuthPerMemberLoginHandler.getLoginUser();
       * 
       * for (int i = 0; i < commentList.size(); i++) { if ((commentList.get(i).getCommentNo() ==
       * commentNo) && (commentList.get(i).getCommentWiter().getPerNo() == member.getPerNo())) {
       * index = i; } }
       * 
       * if (index == -1) { System.out.println(" >> 알맞는 번호를 입력해 주세요.");
       * request.getRequestDispatcher("/myStudy/freeBoardDetail").forward(request); }
       * 
       * String inputno = Prompt.inputString(" 정말 삭제하시겠습니까? (네 / 아니오) "); if
       * (!inputno.equalsIgnoreCase("네")) { System.out.println(" >> 댓글 삭제를 취소하였습니다.");
       * request.getRequestDispatcher("/myStudy/freeBoardDetail").forward(request); }
       * 
       * commentList.remove(commentList.get(index));
       */

      commentDao.delete(Integer.parseInt(request.getParameter("commentNo")));

      // System.out.println(" >> 댓글이 삭제되었습니다.");
      request.setAttribute("perNo", Integer.parseInt(request.getParameter("perNo")));
      request.setAttribute("study", study);
      request.setAttribute("freeBoard", freeBoard);
      request.setAttribute("commentList", commentList);
      request.getRequestDispatcher("/myStudy/freeBoardDetail").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
