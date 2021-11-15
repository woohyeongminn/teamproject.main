package com.ogong.pms.web.myStudy.freeBoard;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.CommentDao;
import com.ogong.pms.dao.FreeBoardDao;
import com.ogong.pms.domain.Comment;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Member;

@Controller
public class FreeBoardDetailController {

  @Autowired
  FreeBoardDao freeBoardDao;
  @Autowired
  CommentDao commentDao;

  @GetMapping("/mystudy/freeboard/detail")
  public ModelAndView detail(int freeboardno, int studyno, HttpSession session) throws Exception {
    // int studyNo = Integer.parseInt(request.getParameter("studyno"));
    // int freeBoardNo = Integer.parseInt(request.getParameter("freeboardno"));

    FreeBoard freeBoard = freeBoardDao.findByNo(freeboardno, studyno);

    if (freeBoard == null) {
      throw new Exception("해당 번호의 자유 게시판이 없습니다.");
    }

    List<Comment> commentList = commentDao.findAll(freeboardno);

    Member member = (Member) session.getAttribute("loginUser");
    ModelAndView mv = new ModelAndView();

    mv.addObject("loginUser", member);
    mv.addObject("freeBoard", freeBoard);
    mv.addObject("commentList", commentList);
    mv.addObject("pageTitle", "자유 게시판 상세");
    mv.addObject("contentUrl", "myStudy/freeBoard/FreeBoardDetail.jsp");
    mv.setViewName("template1");

    return mv;
  }
}
