package com.ogong.pms.web.myStudy.freeBoard;

import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.FreeBoardDao;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;

@Controller
public class FreeBoardAddController {

  @Autowired
  SqlSessionFactory sqlSessionFactory;
  @Autowired
  FreeBoardDao freeBoardDao;

  @PostMapping("/freeboard/add")
  public ModelAndView add(Study study, Part photoFile, HttpSession session) throws Exception {
    FreeBoard freeBoard = new FreeBoard();

    freeBoard.setStudyNo(((Study) session.getAttribute("studyno")).getStudyNo());
    freeBoard.setFreeBoardWriter((Member) session.getAttribute("loginUser"));
    // freeBoard.setFreeBoardTitle(request.getParameter("title"));
    // freeBoard.setFreeBoardContent(request.getParameter("content"));
    System.out.println(freeBoard);

    freeBoardDao.insert(freeBoard);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.setViewName("redirect:list?studyno=" + study.getStudyNo());

    return mv;
  }
}
