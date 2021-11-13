package com.ogong.pms.web.study;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Study;

@Controller
public class StudyUpdateFormController {

  @Autowired
  SqlSessionFactory sqlSessionFactory;
  @Autowired
  StudyDao studyDao;

  @GetMapping("/study/updateform")
  public ModelAndView updateform(int studyno) throws Exception {
    Study study = studyDao.findByNo(studyno);

    ModelAndView mv = new ModelAndView();

    mv.addObject("study", study);
    mv.addObject("pageTitle", "내 스터디 수정");
    mv.addObject("contentUrl", "myStudy/MyStudyUpdateForm.jsp");
    mv.setViewName("template1");

    return mv;
  }
}
