package com.ogong.pms.web.admin;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Study;

@Controller
public class AdminStudyListController {

  @Autowired
  StudyDao studyDao;

  @GetMapping("/admin/study/list")
  public ModelAndView list() throws Exception {
    Collection<Study> studyList = studyDao.findAll();

    ModelAndView mv = new ModelAndView();

    mv.addObject("studyList", studyList);
    mv.addObject("pageTitle", "스터디 관리");
    mv.addObject("contentUrl", "admin/AdminStudyList.jsp");
    mv.setViewName("template1");

    return mv;
  }
}
