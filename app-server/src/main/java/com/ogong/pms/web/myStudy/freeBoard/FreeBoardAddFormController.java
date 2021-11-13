package com.ogong.pms.web.myStudy.freeBoard;

import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.FreeBoardDao;
import com.ogong.pms.domain.FreeBoard;

@Controller
public class FreeBoardAddFormController {

  @Autowired
  SqlSessionFactory sqlSessionFactory;
  @Autowired
  FreeBoardDao freeBoardDao;

  @GetMapping("/freeboard/form")
  public ModelAndView addform(HttpSession session) {
    // int studyNo = Integer.parseInt(request.getParameter("studyno"));

    ModelAndView mv = new ModelAndView();

    mv.addObject("studyno", ((FreeBoard) session.getAttribute("studyno")).getStudyNo());
    mv.addObject("pageTitle", "자유 게시판 등록");
    mv.addObject("contentUrl", "myStudy/freeBoard/FreeBoardAddForm.jsp");
    mv.setViewName("template1");

    return mv;
  }
}
