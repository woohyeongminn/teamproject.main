package com.ogong.pms.web.study;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;

@Controller
public class StudyAddController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired StudyDao studyDao;
  @Autowired ServletContext sc;

  @PostMapping("/study/add")
  public ModelAndView add(Study study, HttpSession session) throws Exception {
    // Study study = new Study();

    // study.setStudyTitle(request.getParameter("studytitle"));
    study.setOwner((Member) session.getAttribute("loginUser"));
    // study.setSubjectNo(Integer.parseInt(request.getParameter("subjectno")));
    // study.setArea(request.getParameter("area"));
    // study.setNumberOfPeple(Integer.parseInt(request.getParameter("numberofpeple")));
    // study.setFaceNo(Integer.parseInt(request.getParameter("faceno")));
    // study.setIntroduction(request.getParameter("introduction"));
    System.out.println(study);

    studyDao.insert(study);
    studyDao.insertGuilder(study.getStudyNo(), ((Member) session.getAttribute("loginUser")).getPerNo());
    studyDao.updateGuilder(study.getStudyNo(), ((Member) session.getAttribute("loginUser")).getPerNo());
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }
}
