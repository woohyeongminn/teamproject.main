package com.ogong.pms.web.myStudy.freeBoard.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.CommentDao;
import com.ogong.pms.domain.Comment;

@Controller
public class CommentUpdateFormController {

  @Autowired
  CommentDao commentDao;

  @GetMapping("/freeboard/comment/updateform")
  public ModelAndView updateform(int commentno) throws Exception {
    Comment comment = commentDao.findByNo(commentno);

    ModelAndView mv = new ModelAndView();

    mv.addObject("comment", comment);
    mv.addObject("pageTitle", "댓글 수정");
    mv.addObject("contentUrl", "myStudy/freeBoard/comment/CommentUpdateForm.jsp");
    mv.setViewName("template1");

    return mv;
  }
}
