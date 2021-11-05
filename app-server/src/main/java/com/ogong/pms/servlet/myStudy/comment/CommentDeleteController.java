package com.ogong.pms.servlet.myStudy.comment;

import java.util.List;
import com.ogong.pms.dao.CommentDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Comment;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Member;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class CommentDeleteController implements Command {

  StudyDao studyDao;
  CommentDao commentDao;

  public CommentDeleteController(StudyDao studyDao, CommentDao commentDao) {
    this.studyDao = studyDao;
    this.commentDao = commentDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 댓글 삭제");
    System.out.println();

    if (AuthPerMemberLoginHandler.getLoginUser() == null) {
      System.out.println(" >> 삭제 권한이 없습니다.");
      return;
    }

    FreeBoard freeBoard = (FreeBoard) request.getAttribute("freeBoard");

    if (freeBoard == null) {
      System.out.println(" >> 해당 번호의 게시글이 없습니다.\n");
      return;
    }

    List<Comment> commentList = commentDao.findAll(freeBoard.getFreeBoardNo());

    int commentNo = 0;
    while (true) {
      try {
        commentNo = Prompt.inputInt(" 번호 : ");
      } catch (NumberFormatException e){
        System.out.println(" >> 숫자를 입력해 주세요.");
        continue;
      }
      break;
    }
    int index = -1;

    Member member = AuthPerMemberLoginHandler.getLoginUser();

    for (int i = 0; i < commentList.size(); i++) {
      if ((commentList.get(i).getCommentNo() == commentNo) &&
          (commentList.get(i).getCommentWiter().getPerNo() == member.getPerNo())){
        index = i;
      }
    }

    if (index == -1) {
      System.out.println(" >> 알맞는 번호를 입력해 주세요.");
      request.getRequestDispatcher("/myStudy/freeBoardDetail").forward(request);
    }

    System.out.println();
    String inputno = Prompt.inputString(" 정말 삭제하시겠습니까? (네 / 아니오) ");
    if (!inputno.equalsIgnoreCase("네")) {
      System.out.println(" >> 댓글 삭제를 취소하였습니다.");
      request.getRequestDispatcher("/myStudy/freeBoardDetail").forward(request);
    }

    commentList.remove(commentList.get(index));

    commentDao.delete(commentNo);

    System.out.println(" >> 댓글이 삭제되었습니다.");
    request.getRequestDispatcher("/myStudy/freeBoardDetail").forward(request);
    return;
  }
}
