package com.ogong.pms.servlet.myStudy.comment;

import java.util.List;
import com.ogong.pms.dao.CommentDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Comment;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class CommentUpdateController implements Command {

  StudyDao studyDao;
  CommentDao commentDao;

  public CommentUpdateController(StudyDao studyDao, CommentDao commentDao) {
    this.studyDao = studyDao;
    this.commentDao = commentDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 댓글 수정");
    System.out.println();

    if (AuthPerMemberLoginHandler.getLoginUser() == null) {
      System.out.println(" >> 변경 권한이 없습니다.");
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

    String commentTitle = null;
    String perNickname = AuthPerMemberLoginHandler.getLoginUser().getPerNickname();

    int index = -1;
    for (int i = 0; i < commentList.size(); i++) {
      if ((commentList.get(i).getCommentNo() == commentNo) &&
          (commentList.get(i).getCommentWiter().getPerNickname().equals(perNickname))){

        commentTitle = 
            Prompt.inputString( "댓글 내용(" +  commentList.get(i).getCommentText() + ") : ");
        System.out.println();
        index = i;
      }
    }

    if (index < 0) {
      System.out.println(" >> 알맞는 번호를 입력해 주세요.");
      request.getRequestDispatcher("/myStudy/freeBoardDetail").forward(request);
      return;
    }

    String input = Prompt.inputString(" 정말 변경하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 댓글 변경이 취소되었습니다.");
      request.getRequestDispatcher("/myStudy/freeBoardList").forward(request);
      return;
    }

    freeBoard.setComment(commentList);

    commentDao.update(commentNo, commentTitle);

    System.out.println(" >> 댓글을 변경하였습니다.");
    request.getRequestDispatcher("/myStudy/freeBoardDetail").forward(request);
    return;
  }
}

