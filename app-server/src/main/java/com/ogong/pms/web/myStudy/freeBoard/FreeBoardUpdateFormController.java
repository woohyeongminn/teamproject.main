package com.ogong.pms.web.myStudy.freeBoard;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.FreeBoardDao;
import com.ogong.pms.domain.FreeBoard;

@Controller
public class FreeBoardUpdateFormController {

  @Autowired
  SqlSessionFactory sqlSessionFactory;
  @Autowired
  FreeBoardDao freeBoardDao;

  @GetMapping("/mystudy/freeboard/updateform")
  public ModelAndView updateform(int freeboardno, int studyno) throws Exception {
    // int studyNo = Integer.parseInt(request.getParameter("studyno"));
    // int freeBoardNo = Integer.parseInt(request.getParameter("freeboardno"));

    FreeBoard freeBoard = freeBoardDao.findByNo(freeboardno, studyno);

    ModelAndView mv = new ModelAndView();

    mv.addObject("freeBoard", freeBoard);
    mv.addObject("pageTitle", "자유 게시판 수정");
    mv.addObject("contentUrl", "myStudy/freeBoard/FreeBoardUpdateForm.jsp");
    mv.setViewName("template1");

    return mv;
  }
}
