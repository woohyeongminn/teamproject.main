package com.ogong.pms.web.myStudy;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.dao.ToDoDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.domain.ToDo;

@Controller
public class MyStudyController {

  @Autowired
  SqlSessionFactory sqlSessionFactory;
  @Autowired
  StudyDao studyDao;
  @Autowired
  ToDoDao toDoDao;

  @GetMapping("/mystudy/list")
  public ModelAndView mystudyList(HttpSession session) throws Exception {

    Member loginUser = (Member) session.getAttribute("loginUser");
    List<Study> studyList = studyDao.findAll();

    // 조장
    List<Study> ownerStudyList = new ArrayList<>();

    for (Study study : studyList) {
      if (study.getOwner().getPerNo() == loginUser.getPerNo()) {
        ownerStudyList.add(study);
      }
    }

    // 구성원
    List<Study> guilderMembers = new ArrayList<>();

    for (int i = 0; i < studyList.size(); i++) {
      List<Member> guilders = studyDao.findByGuildersAll(studyList.get(i).getStudyNo());
      studyList.get(i).setMembers(guilders);

      for (Member mem : studyList.get(i).getMembers()) {
        if (mem.getPerNo() == loginUser.getPerNo()) {
          if (studyList.get(i).getOwner().getPerNo() != loginUser.getPerNo()) {
            guilderMembers.add(studyList.get(i));
          }
        }
      }
    }

    // 승인 대기
    List<Study> waitingStudyList = new ArrayList<>();

    for (int i = 0; i < studyList.size(); i++) {
      List<Member> waiting = studyDao.findByWaitingGuilderAll(studyList.get(i).getStudyNo());
      studyList.get(i).setWaitingMember(waiting);

      for (Member mem : studyList.get(i).getWaitingMember()) {
        if (loginUser.getPerNo() == mem.getPerNo()) {
          waitingStudyList.add(studyList.get(i));
        }
      }
    }

    ModelAndView mv = new ModelAndView();

    mv.addObject("ownerStudyList", ownerStudyList);
    mv.addObject("guilderMembers", guilderMembers);
    mv.addObject("waitingStudyList", waitingStudyList);
    mv.addObject("pageTitle", "🗃 내 스터디 목록");
    mv.addObject("contentUrl", "myStudy/MyStudyList.jsp");
    mv.setViewName("template1");

    return mv;
  }

  @GetMapping("/mystudy/detail")
  public ModelAndView mystudyDetail(HttpSession session, int studyNo) throws Exception {

    Member loginUser = (Member) session.getAttribute("loginUser");

    Study myStudy = studyDao.findByMyNo(studyNo, loginUser.getPerNo());
    List<ToDo> todoList = toDoDao.findAll(myStudy.getStudyNo());

    ModelAndView mv = new ModelAndView();
    mv.addObject("member", loginUser);
    mv.addObject("study", myStudy);
    mv.addObject("todoList", todoList);
    mv.addObject("pageTitle", "🗃 내 스터디 상세");
    mv.addObject("contentUrl", "myStudy/MyStudyDetail.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @GetMapping("/mystudy/updateform")
  public ModelAndView updateform(int studyno) throws Exception {
    Study study = studyDao.findByNo(studyno);

    ModelAndView mv = new ModelAndView();

    mv.addObject("study", study);
    mv.addObject("pageTitle", "내 스터디 수정");
    mv.addObject("contentUrl", "myStudy/MyStudyUpdateForm.jsp");
    mv.setViewName("template1");

    return mv;
  }

  @PostMapping("/mystudy/update")
  public ModelAndView update(Study study) throws Exception {
    Study oldStudy = studyDao.findByNo(study.getStudyNo());

    if (oldStudy == null) {
      throw new Exception("해당 번호의 스터디가 없습니다.");
    }

    studyDao.updateStudyTitle(study);
    studyDao.updateNumberOfPeple(study);
    studyDao.updateFaceNo(study);
    studyDao.updateIntroduction(study);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.addObject("study", study);
    mv.setViewName("redirect:detail?studyNo=" + study.getStudyNo());

    return mv;
  }

  @GetMapping("/mystudy/delete")
  public ModelAndView delete(int studyno) throws Exception {
    Study study = studyDao.findByNo(studyno);

    studyDao.updateStatusDelete(study);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.addObject("study", study);
    mv.setViewName("redirect:list");

    return mv;
  }

  @GetMapping("/mystudy/exit")
  public ModelAndView exit(int studyno, int guilderno, HttpSession session) throws Exception {
    Study study = studyDao.findByNo(studyno);

    if (study.getOwner().getPerNo() == ((Member) session.getAttribute("loginUser")).getPerNo()) {
      // 참여 중인 구성원 O
      if (study.getCountMember() > 1) {
        studyDao.updateOwner(study.getStudyNo(), guilderno);
        sqlSessionFactory.openSession().commit();

        studyDao.deleteGuilder(study.getStudyNo(),
            ((Member) session.getAttribute("loginUser")).getPerNo());
        sqlSessionFactory.openSession().commit();

        // 승인 대기 중인 구성원 O
      } else if (study.getWaitingCountMember() > 0) {
        studyDao.deleteAllWaitingGuilder(study.getStudyNo());
        sqlSessionFactory.openSession().commit();

        // 참여 중인 구성원 X
      } else if (study.getCountMember() > 1) {
        studyDao.updateStatusDelete(study);
        sqlSessionFactory.openSession().commit();
      }
    }

    studyDao.deleteGuilder(study.getStudyNo(),
        ((Member) session.getAttribute("loginUser")).getPerNo());

    ModelAndView mv = new ModelAndView();

    mv.addObject("study", study);
    mv.addObject("pageTitle", "내 스터디 탈퇴");
    mv.setViewName("redirect:list");
    mv.setViewName("template1");

    return mv;
  }
}
