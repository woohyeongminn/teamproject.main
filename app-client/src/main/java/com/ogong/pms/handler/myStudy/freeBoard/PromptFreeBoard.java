package com.ogong.pms.handler.myStudy.freeBoard;

import java.util.List;
import com.ogong.pms.dao.CommentDao;
import com.ogong.pms.domain.Comment;
import com.ogong.pms.domain.FreeBoard;

public class PromptFreeBoard {

  CommentDao commentDao;

  public PromptFreeBoard(CommentDao commentDao) {
    this.commentDao = commentDao;
  }

  public void printComments(FreeBoard freeboard) throws Exception {
    System.out.println();
    System.out.println("============= 댓글 =============");


    List<Comment> commentList = commentDao.findAll(freeboard.getFreeBoardNo());

    for (Comment comment : commentList) {
      System.out.printf(" (%d) | 내용 : %s | 작성자 : %s | 등록일 : %s\n",
          comment.getCommentNo(),
          comment.getCommentText(),
          comment.getCommentWiter().getPerNickname(),
          comment.getCommentRegisteredDate());
    }

    if (commentList.isEmpty()) {
      System.out.println(" >> 등록된 댓글이 없습니다.");
    }
  }

}