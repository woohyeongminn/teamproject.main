package com.ogong.pms.web.myStudy.freeBoard.comment;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.CommentDao;
import com.ogong.pms.domain.Comment;

@Controller
public class CommentUpdateController {

  @Autowired
  SqlSessionFactory sqlSessionFactory;
  @Autowired
  CommentDao commentDao;

  @PostMapping("/mystudy/freeboard/comment/update")
  public ModelAndView update(Comment comment) throws Exception {
    // if (AuthPerMemberLoginHandler.getLoginUser() == null) {
    // System.out.println(" >> 변경 권한이 없습니다.");
    // return;
    // }

    Comment oldComment = commentDao.findByNo(comment.getCommentNo());

    if (oldComment == null) {
      throw new Exception("해당 번호의 댓글이 없습니다.");
    }

    // comment.setCommentText(request.getParameter("commenttext"));

    commentDao.updateContent(comment);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.addObject("comment", comment);
    mv.setViewName("redirect:../detail?studyno=" + comment.getStudyNo() + "&freeboardno="
        + comment.getBoardNo());

    return mv;
  }
}
