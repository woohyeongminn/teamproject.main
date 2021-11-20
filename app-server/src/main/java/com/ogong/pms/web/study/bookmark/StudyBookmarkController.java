package com.ogong.pms.web.study.bookmark;

import java.util.Collection;
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
public class StudyBookmarkController {

  @Autowired
  SqlSessionFactory sqlSessionFactory;
  @Autowired
  StudyDao studyDao;

  @GetMapping("/study/bookmark/add")
  public ModelAndView add(int studyno, HttpSession session) throws Exception {
    Study study = studyDao.findByNo(studyno);

    List<Member> waitingGuilder = studyDao.findByWaitingGuilderAll(study.getStudyNo());
    study.setWaitingMember(waitingGuilder);

    List<Member> guilders = studyDao.findByGuildersAll(study.getStudyNo());
    study.setMembers(guilders);

    List<Member> bookmark = studyDao.findByBookmarkAll(study.getStudyNo());
    study.setBookMarkMember(bookmark);

    studyDao.insertBookmark(study.getStudyNo(),
        ((Member) session.getAttribute("loginUser")).getPerNo());
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.addObject("study", study);
    mv.setViewName("redirect:../detail?studyno=" + study.getStudyNo());

    return mv;
  }

  @GetMapping("/study/bookmark/list")
  public ModelAndView list(HttpSession session) throws Exception {
    Collection<Study> studyList =
        studyDao.findByMyBookmark(((Member) session.getAttribute("loginUser")).getPerNo());

    ModelAndView mv = new ModelAndView();

    mv.addObject("studyList", studyList);
    mv.addObject("pageTitle", "북마크 목록");
    mv.addObject("contentUrl", "study/bookmark/StudyBookmarkList.jsp");
    mv.setViewName("template1");

    return mv;
  }

  @GetMapping("/study/bookmark/detail")
  public ModelAndView detail(int studyno) throws Exception {
    Study study = studyDao.findByNo(studyno);

    if (study == null) {
      throw new Exception("해당 번호의 북마크가 없습니다.");
    }

    ModelAndView mv = new ModelAndView();

    mv.addObject("study", study);
    mv.addObject("pageTitle", "북마크 상세");
    mv.addObject("contentUrl", "study/bookmark/StudyBookmarkDetail.jsp");
    mv.setViewName("template1");

    return mv;
  }

  @GetMapping("/study/bookmark/delete")
  public ModelAndView delete(int studyno, HttpSession session) throws Exception {
    Study study = studyDao.findByNo(studyno);

    if (study == null) {
      throw new Exception("해당 번호의 북마크가 없습니다.");
    }

    studyDao.deleteBookmark(study.getStudyNo(), ((Member) session.getAttribute("loginUser")).getPerNo());
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.addObject("study", study);
    mv.setViewName("redirect:../detail?studyno=" + study.getStudyNo());

    return mv;
  }
}
