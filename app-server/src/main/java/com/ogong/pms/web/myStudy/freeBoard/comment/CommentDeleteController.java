package com.ogong.pms.web.myStudy.freeBoard.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.CommentDao;
import com.ogong.pms.domain.Comment;

@Controller
public class CommentDeleteController {

  @Autowired
  CommentDao commentDao;

  @GetMapping("/freeboard/comment/delete")
  public ModelAndView delete(int commentno) throws Exception {
    // if (AuthPerMemberLoginHandler.getLoginUser() == null) {
    // System.out.println(" >> 삭제 권한이 없습니다.");
    // return;
    // }

    Comment comment = commentDao.findByNo(commentno);

    if (comment == null) {
      throw new Exception("해당 번호의 댓글이 없습니다.");
    }

    commentDao.delete(comment.getCommentNo());

    ModelAndView mv = new ModelAndView();

    mv.addObject("comment", comment);
    mv.setViewName("redirect:../detail?studyno=" + comment.getStudyNo() + "&freeboardno="
        + comment.getBoardNo());

    return mv;
  }
}
