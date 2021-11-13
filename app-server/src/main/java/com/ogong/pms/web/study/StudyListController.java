package com.ogong.pms.web.study;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Study;

@Controller
public class StudyListController {

  @Autowired
  StudyDao studyDao;

  @GetMapping("/study/list")
  public ModelAndView list() throws Exception {
    // 전체 스터디 목록
    Collection<Study> studyList = studyDao.findAll();

    // 진행 스터디 목록
    Collection<Study> studyIngList = studyDao.findAllIng();

    // 종료 스터디 목록
    Collection<Study> studyEndList = studyDao.findAllEnd();

    ModelAndView mv = new ModelAndView();

    mv.addObject("studyList", studyList);
    mv.addObject("studyIngList", studyIngList);
    mv.addObject("studyEndList", studyEndList);
    mv.addObject("pageTitle", "스터디 목록");
    mv.addObject("contentUrl", "study/StudyList.jsp");
    mv.setViewName("template1");

    return mv;
  }
}
