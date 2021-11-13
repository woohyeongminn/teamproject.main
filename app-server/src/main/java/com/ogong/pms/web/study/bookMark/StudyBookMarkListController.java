package com.ogong.pms.web.study.bookMark;

import java.util.Collection;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;

@Controller
public class StudyBookMarkListController {

  @Autowired
  MemberDao memberDao;
  @Autowired
  StudyDao studyDao;

  @GetMapping("/bookmark/list")
  public ModelAndView list(HttpSession session) throws Exception {
    Collection<Study> studyList =
        studyDao.findByMyBookmark(((Member) session.getAttribute("loginUser")).getPerNo());

    ModelAndView mv = new ModelAndView();

    mv.addObject("studyList", studyList);
    mv.addObject("pageTitle", "북마크 목록");
    mv.addObject("contentUrl", "study/bookMark/StudyBookMarkList.jsp");
    mv.setViewName("template1");

    return mv;
  }
}
