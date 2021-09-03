package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Comment;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.util.Prompt;

public class CommentHandler {

  List<FreeBoard> freeBoardList;
  List<Comment> commentList;

  public CommentHandler(List<FreeBoard> freeBoardList, List<Comment> commentList) {
    this.freeBoardList = freeBoardList;
    this.commentList = commentList;
  }

  //로그인 했는지 ( > 안했으면 로그인하세요)
  // 가입한 스터디가 하나라도 있는지 (> 안했으면 가입한 스터디가 없습니다)
  // 가입한 스터디 중에 내가 속한 스터디가 있는지 (> 없으면 해당 스터디의 구성원이 아닙니다)
  // 가입한 스터디의 자유게시판에 글이 하나라도 있는지 (> 안했으면 자유게시판에 글이 없습니다)
  // 댓글 등록 (> 등록하시겠습니까???)



  public void add () {
    System.out.println("[댓글 등록]");

    Comment comment = new Comment();

    comment.setCommentText(Prompt.inputString("내용: "));

  }

}

