package com.ogong.pms.web.study;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;

@Controller
public class StudyDetailController {

  @Autowired
  StudyDao studyDao;

  @GetMapping("/study/detail")
  public ModelAndView detail(int studyno, HttpSession session) throws Exception {
    // int studyNo = Integer.parseInt(request.getParameter("studyNo"));
    Study study = studyDao.findByNo(studyno);
    int myBookmark = studyDao.myBookmark(studyno, ((Member) session.getAttribute("loginUser")).getPerNo());

    System.out.println(myBookmark);

    if (study == null) {
      throw new Exception("해당 번호의 스터디가 없습니다.");
    }

    ModelAndView mv = new ModelAndView();

    mv.addObject("study", study);
    mv.addObject("myBookmark", myBookmark);
    mv.addObject("pageTitle", "스터디 상세");
    mv.addObject("contentUrl", "study/StudyDetail.jsp");
    mv.setViewName("template1");

    return mv;
  }
}
