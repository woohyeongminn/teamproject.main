package com.ogong.pms.web.myStudy;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;

@Controller
public class MyStudyDetailController {

  @Autowired StudyDao studyDao;

  @GetMapping("/mystudy/detail")
  public ModelAndView mystudyDetail(HttpSession session, int studyNo) throws Exception {

    Member loginUser = (Member) session.getAttribute("loginUser");

    Study myStudy = studyDao.findByMyNo(studyNo, loginUser.getPerNo());

    ModelAndView mv = new ModelAndView();
    mv.addObject("study", myStudy);
    mv.addObject("pageTitle", "🗃 내 스터디 상세");
    mv.addObject("contentUrl", "myStudy/MyStudyDetail.jsp");
    mv.setViewName("template1");
    return mv;
  }
}
