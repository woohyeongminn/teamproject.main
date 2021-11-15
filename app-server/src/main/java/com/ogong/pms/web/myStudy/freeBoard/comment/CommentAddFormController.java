//package com.ogong.pms.web.myStudy.freeBoard.comment;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.servlet.ModelAndView;
//import com.ogong.pms.dao.CommentDao;
//import com.ogong.pms.dao.FreeBoardDao;
//
//@Controller
//public class CommentAddFormController {
//
//  FreeBoardDao freeBoardDao;
//  CommentDao commentDao;
//
//  @GetMapping("/freeboard/comment/form")
//  public ModelAndView form(int freeboardno, int studyno) throws Exception {
//    // Study study = studyDao.findByNo(studyNo);
//    // FreeBoard freeBoard = freeBoardDao.findByNo(freeboardno, studyno);
//
//    ModelAndView mv = new ModelAndView();
//
//    mv.addObject("studyno", studyno);
//    mv.addObject("freeboardno", freeboardno);
//    mv.setViewName("redirect:detail?studyno=" + studyno + "&freeboardno="
//        + freeboardno);
//
//    return mv;
//  }
//}
