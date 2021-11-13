package com.ogong.pms.web.myStudy;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Study;

@Controller
public class MyStudyUpdateController {

  @Autowired
  SqlSessionFactory sqlSessionFactory;
  @Autowired
  StudyDao studyDao;

  @PostMapping("/mystudy/update")
  public ModelAndView update(Study study) throws Exception {
    Study oldStudy = studyDao.findByNo(study.getStudyNo());

    if (oldStudy == null) {
      throw new Exception("해당 번호의 스터디가 없습니다.");
    }

    // study.setStudyTitle(request.getParameter("studytitle"));
    // study.setNumberOfPeple(Integer.parseInt(request.getParameter("numberofpeple")));
    // study.setFaceNo(Integer.parseInt(request.getParameter("faceno")));
    // study.setIntroduction(request.getParameter("introduction"));

    studyDao.updateStudyTitle(study);
    studyDao.updateNumberOfPeple(study);
    studyDao.updateFaceNo(study);
    studyDao.updateIntroduction(study);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.addObject("study", study);
    mv.setViewName("redirect:detail?studyno=" + study.getStudyNo());

    return mv;
  }
}
