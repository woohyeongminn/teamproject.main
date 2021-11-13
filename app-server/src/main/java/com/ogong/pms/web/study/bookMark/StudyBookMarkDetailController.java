package com.ogong.pms.web.study.bookMark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Study;

@Controller
public class StudyBookMarkDetailController {

  @Autowired
  StudyDao studyDao;

  @GetMapping("/bookmark/detail")
  public ModelAndView detail(int studyno) throws Exception {
    Study study = studyDao.findByNo(studyno);

    if (study == null) {
      throw new Exception("해당 번호의 북마크가 없습니다.");
    }

    ModelAndView mv = new ModelAndView();

    mv.addObject("study", study);
    mv.addObject("pageTitle", "북마크 상세");
    mv.addObject("contentUrl", "study/bookMark/StudyBookMarkDetail.jsp");
    mv.setViewName("template1");

    return mv;
  }
}
