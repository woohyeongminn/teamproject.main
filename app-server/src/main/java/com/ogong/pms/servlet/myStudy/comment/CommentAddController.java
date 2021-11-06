package com.ogong.pms.servlet.myStudy.comment;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CommentDao;
import com.ogong.pms.dao.FreeBoardDao;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Comment;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;

@WebServlet("/comment/add")
public class CommentAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  MemberDao memberDao;
  StudyDao studyDao;
  FreeBoardDao freeBoardDao;
  CommentDao commentDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
    studyDao = (StudyDao) 웹애플리케이션공용저장소.getAttribute("studyDao");
    freeBoardDao = (FreeBoardDao) 웹애플리케이션공용저장소.getAttribute("freeBoardDao");
    commentDao = (CommentDao) 웹애플리케이션공용저장소.getAttribute("commentDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      // if (AuthPerMemberLoginHandler.getLoginUser() == null) {
      // System.out.println(" >> 로그인 한 회원만 등록 가능합니다.");
      // }

      Member loginUser = (Member) request.getSession().getAttribute("loginUser");
      Member member = memberDao.findByNo(loginUser.getPerNo());

      int studyNo = Integer.parseInt(request.getParameter("studyno"));
      Study study = studyDao.findByNo(studyNo);

      int freeBoardNo = Integer.parseInt(request.getParameter("freeboardno"));
      FreeBoard freeBoard = freeBoardDao.findByNo(freeBoardNo, studyNo);

      if (freeBoard == null) {
        throw new Exception(" >> 해당 번호의 게시글이 없습니다.\n");
      }

      Comment comment = new Comment();

      comment.setStudyNo(Integer.parseInt(request.getParameter("studyno")));
      comment.setBoardNo(Integer.parseInt(request.getParameter("freeboardno")));
      comment.setCommentText(request.getParameter("commenttext"));
      comment.setCommentWiter(member);
      // comment.setCommentRegisteredDate(new Date(System.currentTimeMillis()));

      /*
       * String input = Prompt.inputString(" 정말 등록하시겠습니까? (네 / 아니오) ");
       * 
       * if (!input.equalsIgnoreCase("네")) {
       * request.getRequestDispatcher("/myStudy/freeBoardList").forward(request); throw new
       * Exception(" >> 댓글 등록을 취소하였습니다."); }
       */

      freeBoard.getComment().add(comment);

      commentDao.insert(freeBoard.getStudyNo(), freeBoard.getFreeBoardNo(), comment);
      sqlSession.commit();

      // System.out.println(" >> 댓글이 등록되었습니다.");
      // request.setAttribute("member", member);
      request.setAttribute("study", study);
      request.setAttribute("freeBoard", freeBoard);
      request.getRequestDispatcher("/myStudy/freeBoardDetail.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
