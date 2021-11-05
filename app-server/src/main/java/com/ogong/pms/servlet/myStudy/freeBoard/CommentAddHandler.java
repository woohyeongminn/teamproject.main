package com.ogong.pms.handler.myStudy.freeBoard;

import java.sql.Date;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CommentDao;
import com.ogong.pms.domain.Comment;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class CommentAddHandler implements Command {

  CommentDao commentDao;
  SqlSession sqlSession;

  public CommentAddHandler(CommentDao commentDao, SqlSession sqlSession) {
    this.commentDao = commentDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 댓글 작성");

    if (AuthPerMemberLoginHandler.getLoginUser() == null) {
      System.out.println(" >> 로그인 한 회원만 등록 가능합니다.");
    } else {

      FreeBoard freeBoard = (FreeBoard) request.getAttribute("freeBoard");

      if (freeBoard == null) {
        System.out.println(" >> 해당 번호의 게시글이 없습니다.\n");
        return;
      }

      Comment comment = new Comment();

      comment.setStudyNo(freeBoard.getStudyNo());
      comment.setBoardNo(freeBoard.getFreeBoardNo());
      comment.setCommentText(Prompt.inputString(" 댓글 내용 : "));
      comment.setCommentWiter(AuthPerMemberLoginHandler.getLoginUser());
      comment.setCommentRegisteredDate(new Date(System.currentTimeMillis()));

      String input = Prompt.inputString(" 정말 등록하시겠습니까? (네 / 아니오) ");

      if (!input.equalsIgnoreCase("네")) {
        System.out.println(" >> 댓글 등록을 취소하였습니다.");
        request.getRequestDispatcher("/myStudy/freeBoardList").forward(request);
        return;
      }

      freeBoard.getComment().add(comment);

      commentDao.insert(freeBoard.getStudyNo(), freeBoard.getFreeBoardNo(), comment);
      sqlSession.commit();

      System.out.println(" >> 댓글이 등록되었습니다.");
      request.getRequestDispatcher("/myStudy/freeBoardDetail").forward(request);
      return;
    }
  }
}

