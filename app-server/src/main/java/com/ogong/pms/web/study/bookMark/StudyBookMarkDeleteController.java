package com.ogong.pms.web.study.bookMark;

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
public class StudyBookMarkDeleteController {

  @Autowired
  SqlSessionFactory sqlSessionFactory;
  @Autowired
  StudyDao studyDao;

  @GetMapping("/bookmark/delete")
  public ModelAndView delete(int studyno, HttpSession session) throws Exception {
    Study study =
        studyDao.findByBookmark(studyno, ((Member) session.getAttribute("loginUser")).getPerNo());

    if (study == null) {
      throw new Exception("해당 번호의 북마크가 없습니다.");
    }

    // for (int i = 0; i < myStudy.getBookMarkMember().size(); i++) {
    // if (member.getPerNo() == myStudy.getBookMarkMember().get(i).getPerNo()) {
    // myStudy.getBookMarkMember().remove(i);
    // break;
    // }
    // }

    studyDao.deleteBookmark(study.getStudyNo(),
        ((Member) session.getAttribute("loginUser")).getPerNo());
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.addObject("study", study);
    mv.setViewName("redirect:list");

    return mv;
  }
}
