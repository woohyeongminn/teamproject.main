package com.ogong.pms.web.admin;

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
public class AdminStudyDeleteController {

  @Autowired
  SqlSessionFactory sqlSessionFactory;
  @Autowired
  StudyDao studyDao;

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
