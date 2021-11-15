package com.ogong.pms.web.myStudy.freeBoard.comment;

import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.CommentDao;
import com.ogong.pms.dao.FreeBoardDao;
import com.ogong.pms.domain.Comment;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Member;

@Controller
public class CommentAddController {

  @Autowired
  SqlSessionFactory sqlSessionFactory;
  @Autowired
  FreeBoardDao freeBoardDao;
  @Autowired
  CommentDao commentDao;

  @PostMapping("/mystudy/freeboard/comment/add")
  public ModelAndView add(Comment comment, HttpSession session) throws Exception {
    // if (AuthPerMemberLoginHandler.getLoginUser() == null) {
    // System.out.println(" >> 로그인 한 회원만 등록 가능합니다.");
    // }

    // int studyNo = Integer.parseInt(request.getParameter("studyno"));
    // int freeBoardNo = Integer.parseInt(request.getParameter("freeboardno"));
    FreeBoard freeBoard = freeBoardDao.findByNo(comment.getBoardNo(), comment.getStudyNo());
    // String commentText = request.getParameter("commenttext");

    // if (freeBoard == null) {
    // throw new Exception("해당 번호의 게시글이 없습니다.");
    // }

    // Comment comment = new Comment();

    comment.setCommentWriter((Member) session.getAttribute("loginUser"));
    // comment.setStudyNo(comment.getStudyNo());
    // comment.setBoardNo(comment.getBoardNo());
    // comment.setCommentText(commentText);
    // comment.setCommentRegisteredDate(new Date(System.currentTimeMillis()));
    freeBoard.getComment().add(comment);
    System.out.println(comment);

    commentDao.insert(comment.getStudyNo(), comment.getBoardNo(), comment);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    // mv.addObject("freeBoard", freeBoard);
    // mv.addObject("myStudy/freeBoard/FreeBoardDetail.jsp").forward(request, response);
    mv.setViewName(
        "redirect:detail?studyno=" + comment.getStudyNo() + "&freeboardno=" + comment.getBoardNo());

    return mv;
  }
}
