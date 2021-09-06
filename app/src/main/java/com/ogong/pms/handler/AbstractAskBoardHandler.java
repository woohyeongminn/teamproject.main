package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.domain.Comment;

public abstract class AbstractAskBoardHandler implements Command {

  List<AskBoard> askBoardList;
  List<Comment> commentList;

  public AbstractAskBoardHandler(List<AskBoard> askBoardList, List<Comment> commentList) {
    this.askBoardList = askBoardList;
    this.commentList = commentList;
  }

  protected AskBoard findByNo(int askNo) {
    for (AskBoard askList : askBoardList) {
      if (askList.getAskNo() == askNo) {
        return askList;
      }
    }
    return null;
  }

}







