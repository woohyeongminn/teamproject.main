package com.ogong.pms.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Study;

@Controller
public class AdminStudyDetailController {

  @Autowired
  StudyDao studyDao;

  @GetMapping("/admin/study/detail")
  public ModelAndView detail(int studyno) throws Exception {
    Study study = studyDao.findByNo(studyno);

    if (study == null) {
      throw new Exception("해당 번호의 스터디가 없습니다.");
    }

    ModelAndView mv = new ModelAndView();

    mv.addObject("study", study);
    mv.addObject("pageTitle", "스터디 상세");
    mv.addObject("contentUrl", "admin/AdminStudyDetail.jsp");
    mv.setViewName("template1");

    return mv;
  }
}
