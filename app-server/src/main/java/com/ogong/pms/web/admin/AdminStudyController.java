package com.ogong.pms.web.admin;

import java.util.Collection;
import java.util.List;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;

@Controller
public class AdminStudyController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired StudyDao studyDao;

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

  @GetMapping("/admin/study/delete")
  public ModelAndView delete(int studyno) throws Exception {
    Study study = studyDao.findByNo(studyno);

    if (study == null) {
      throw new Exception("해당 번호의 스터디가 없습니다.");
    }

    List<Member> waitingGuilder = studyDao.findByWaitingGuilderAll(study.getStudyNo());
    study.setWatingMember(waitingGuilder);

    List<Member> guilders = studyDao.findByGuildersAll(study.getStudyNo());
    study.setMembers(guilders);

    studyDao.updateStatusDelete(study);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.addObject("study", study);
    mv.setViewName("redirect:list");

    return mv;
  }
}
