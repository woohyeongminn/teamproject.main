package com.ogong.pms.web.myStudy.freeBoard.comment;

import java.sql.Date;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.CommentDao;
import com.ogong.pms.dao.FreeBoardDao;
import com.ogong.pms.domain.Comment;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Member;

@Controller
public class CommentController {

  @Autowired
  SqlSessionFactory sqlSessionFactory;
  @Autowired
  FreeBoardDao freeBoardDao;
  @Autowired
  CommentDao commentDao;

  @PostMapping("/mystudy/freeboard/comment/add")
  public ModelAndView add(Comment comment, HttpSession session) throws Exception {
    FreeBoard freeBoard = freeBoardDao.findByNo(comment.getBoardNo(), comment.getStudyNo());

    comment.setCommentWriter((Member) session.getAttribute("loginUser"));
    comment.setCommentRegisteredDate(new Date(System.currentTimeMillis()));
    freeBoard.getComment().add(comment);
    System.out.println(comment);

    commentDao.insert(comment.getStudyNo(), comment.getBoardNo(), comment);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.setViewName(
        "redirect:../detail?studyno=" + comment.getStudyNo() + "&freeboardno=" + comment.getBoardNo());

    return mv;
  }

  @GetMapping("/mystudy/freeboard/comment/updateform")
  public ModelAndView updateform(int commentno) throws Exception {
    Comment comment = commentDao.findByNo(commentno);

    ModelAndView mv = new ModelAndView();

    mv.addObject("comment", comment);
    mv.addObject("pageTitle", "댓글 수정");
    mv.addObject("contentUrl", "myStudy/freeBoard/comment/CommentUpdateForm.jsp");
    mv.setViewName("template1");

    return mv;
  }

  @PostMapping("/mystudy/freeboard/comment/update")
  public ModelAndView update(Comment comment) throws Exception {
    Comment oldComment = commentDao.findByNo(comment.getCommentNo());

    if (oldComment == null) {
      throw new Exception("해당 번호의 댓글이 없습니다.");
    }

    commentDao.updateContent(comment);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.addObject("comment", comment);
    mv.setViewName("redirect:../detail?studyno=" + comment.getStudyNo() + "&freeboardno="
        + comment.getBoardNo());

    return mv;
  }

  @GetMapping("/mystudy/freeboard/comment/delete")
  public ModelAndView delete(int commentno) throws Exception {
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
