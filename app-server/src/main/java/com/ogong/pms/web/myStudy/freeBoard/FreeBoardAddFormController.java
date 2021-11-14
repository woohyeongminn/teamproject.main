package com.ogong.pms.web.myStudy.freeBoard;

import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.FreeBoardDao;

@Controller
public class FreeBoardAddFormController {

  @Autowired
  SqlSessionFactory sqlSessionFactory;
  @Autowired
  FreeBoardDao freeBoardDao;

  @GetMapping("/mystudy/freeboard/form")
  public ModelAndView addform(int studyno, HttpSession session) {
    // int studyNo = Integer.parseInt(request.getParameter("studyno"));

    ModelAndView mv = new ModelAndView();

    mv.addObject("studyno", studyno);
    mv.addObject("pageTitle", "자유 게시판 등록");
    mv.addObject("contentUrl", "myStudy/freeBoard/FreeBoardAddForm.jsp");
    mv.setViewName("template1");

    return mv;
  }
}
