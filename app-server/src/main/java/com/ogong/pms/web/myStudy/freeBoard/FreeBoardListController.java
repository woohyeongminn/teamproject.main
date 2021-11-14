package com.ogong.pms.web.myStudy.freeBoard;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.FreeBoardDao;
import com.ogong.pms.domain.FreeBoard;

@Controller
public class FreeBoardListController {

  @Autowired
  FreeBoardDao freeBoardDao;

  @GetMapping("/mystudy/freeboard/list")
  public ModelAndView list(int studyno) throws Exception {
    // int studyNo = Integer.parseInt(request.getParameter("studyno"));

    List<FreeBoard> freeBoardList = freeBoardDao.findAll(studyno);

    ModelAndView mv = new ModelAndView();

    mv.addObject("freeBoardList", freeBoardList);
    mv.addObject("pageTitle", "자유 게시판 목록");
    mv.addObject("contentUrl", "myStudy/freeBoard/FreeBoardList.jsp");
    mv.setViewName("template1");

    return mv;
  }
}
