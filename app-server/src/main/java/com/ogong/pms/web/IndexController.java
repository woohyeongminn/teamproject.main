package com.ogong.pms.web;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.vo.Study;

@Controller
public class IndexController {

  @Autowired StudyDao studyDao;

  @GetMapping("/index")
  public ModelAndView index2() throws Exception {

    List<Study> findstudyList = studyDao.findAll();

    if (findstudyList.isEmpty()) {
      throw new Exception("스터디 정보가 없습니다.");
    }

    Study study = new Study();
    List<Study> studyList = new ArrayList<>();

    for (int i = 0; i < findstudyList.size(); i++) {
      if (findstudyList.get(i).getReport() != 2 && studyList.size() < 6) {
        study = findstudyList.get(i);
        studyList.add(study);
      }
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("studyList", studyList);
    mv.addObject("pageTitle", "오늘의 공부");
    mv.setViewName("index");
    return mv;
  }
}
