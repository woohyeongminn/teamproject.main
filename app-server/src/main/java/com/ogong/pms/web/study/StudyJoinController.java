package com.ogong.pms.web.study;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;

@Controller
public class StudyJoinController {

  @Autowired
  SqlSessionFactory sqlSessionFactory;
  @Autowired
  StudyDao studyDao;

  @GetMapping("/study/join")
  public ModelAndView join(int studyno, HttpSession session) throws Exception {
    Study study = studyDao.findByNo(studyno);

    List<Member> waitingGuilder = studyDao.findByWaitingGuilderAll(study.getStudyNo());
    study.setWatingMember(waitingGuilder);

    List<Member> guilders = studyDao.findByGuildersAll(study.getStudyNo());
    study.setMembers(guilders);

    study.getWatingMember().add((Member) session.getAttribute("loginUser"));
    studyDao.insertGuilder(study.getStudyNo(),
        ((Member) session.getAttribute("loginUser")).getPerNo());
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.addObject("study", study);
    // mv.addObject("contentUrl", "study/StudyJoin.jsp");
    mv.setViewName("redirect:list");
    return mv;
  }
}
