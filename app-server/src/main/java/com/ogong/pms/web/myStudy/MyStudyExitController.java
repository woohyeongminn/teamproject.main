package com.ogong.pms.web.myStudy;

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
public class MyStudyExitController {

  @Autowired
  SqlSessionFactory sqlSessionFactory;
  @Autowired
  StudyDao studyDao;

  @GetMapping("/mystudy/exit")
  public ModelAndView exit(int studyno, HttpSession session) throws Exception {
    Study study = studyDao.findByNo(studyno);

    // 내가 조장일 때
    if (study.getOwner().getPerNo() == ((Member) session.getAttribute("loginUser")).getPerNo()
        && study.getCountMember() == 0) {
      studyDao.updateStatusDelete(study);
      sqlSessionFactory.openSession().commit();
      // System.out.println("스터디가 삭제 되었습니다.");
    }

    studyDao.deleteGuilder(study.getStudyNo(),
        ((Member) session.getAttribute("loginUser")).getPerNo());
    // System.out.println("탈퇴 되었습니다.");

    ModelAndView mv = new ModelAndView();

    mv.addObject("study", study);
    mv.addObject("pageTitle", "내 스터디 탈퇴");
    mv.addObject("contentUrl", "myStudy/MyStudyExit.jsp");
    mv.setViewName("template1");

    return mv;
  }
}
